package com.example.petadmin.controller;

import com.example.petadmin.model.OrderItem;
import com.example.petadmin.model.Orders;
import com.example.petadmin.model.Product;
import com.example.petadmin.model.User;
import com.example.petadmin.model.shoppingcart.CartInfo;
import com.example.petadmin.model.shoppingcart.CartLineInfo;
import com.example.petadmin.service.OrdersService;
import com.example.petadmin.service.ProductService;
import com.example.petadmin.service.UserService;
import com.example.petadmin.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model) {
        System.out.println("mapping /cart");
        //
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            User user = userService.findByUsername(username);
//            user.getAuthorities().forEach(grantedAuthority -> {
//                System.out.println("Authorities: " + grantedAuthority.getAuthority());
//            });
//        }
        //
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println("Logged in user: " + username);
        } else {
            String username = principal.toString();
            System.out.println("Logged in user: " + username);
        }
        //

        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        //
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        if (cartInfo.getCartLines().size() == 0) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            model.addAttribute("cartInfo", cartInfo);
        }
        return "cart";
    }

    @RequestMapping({"/buy"})
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") long id) {
        productService.findOne(id).ifPresent(product -> {
            CartInfo cartInfo = SessionUtils.getCartInSession(request);
            cartInfo.addProduct(product, 1);
        });
        return "redirect:/";
    }
    @RequestMapping({"/buyshop"})
    public String listProductHandlerShop(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") long id) {
        productService.findOne(id).ifPresent(product -> {
            CartInfo cartInfo = SessionUtils.getCartInSession(request);
            cartInfo.addProduct(product, 1);
        });
        return "redirect:/shop/productList";
    }

    @GetMapping("/listofproducts")
    public String listofproducts(HttpServletRequest request, Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        if (cartInfo.getCartLines().size() == 0) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            model.addAttribute("cartInfo", cartInfo);
        }
        return "shoppingcart/shoppingcart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpServletRequest request, Model model) {
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        if (cartInfo.getQuantityTotal() == 0) {
            return "redirect:/cart";
        } else {
            model.addAttribute("cartInfo", cartInfo);
            Orders order = new Orders();
            model.addAttribute("order", order);
            return "checkout";
        }
    }

    /* Checkout - convert shopping cart to order */
    @PostMapping("/checkout")
    public String checkoutForm(@Valid @ModelAttribute("order") Orders order, BindingResult result, HttpServletRequest request, Model model) {
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        if (result.hasErrors()) {
            model.addAttribute("cartInfo", cartInfo);
            return "checkout";
        }
        order.setTotalPrice(cartInfo.getAmountTotal());
        //get user from security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);
            order.setUser(user);
        }
        //Get current time for order time
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date d = java.sql.Timestamp.valueOf(now);
        order.setOrderDate(d);
        // create order in db
        //set product and order for orderitem
        //Convert orderLine to orderitem
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartLineInfo cartLineInfo : cartInfo.getCartLines()) {
            productService.findOne(cartLineInfo.getProduct().getId()).ifPresent(product -> {
                OrderItem orderItem = new OrderItem(order, product);
                orderItem.setPrice(cartLineInfo.getProduct().getPrice());
                orderItem.setQuantity(cartLineInfo.getQuantity());
                orderItem.setSubtotal(cartLineInfo.getAmount());
                orderItems.add(orderItem);
            });
        }
        order.setOrderItems(orderItems);
        ordersService.save(order);
        System.out.println("The order: " + order.getId() + ", time: " + order.getOrderDate());
        System.out.println("List of order items\n" + orderItems);
        SessionUtils.removeCartInSession(request);

        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess() {
        return "order-success";
    }

    /* Ajax update/remove product from cart*/
    @PostMapping("/update-cart")
    @ResponseBody
    public CartInfo updateCart(HttpServletRequest request, @RequestParam Long productId, @RequestParam Integer quantity) {
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        cartInfo.updateProduct(productId, quantity);
        return cartInfo;
    }

    @PostMapping("/remove-product")
    @ResponseBody
    public CartInfo removeProduct(HttpServletRequest request, @RequestParam Long productId) {
        CartInfo cartInfo = SessionUtils.getCartInSession(request);
        cartInfo.removeProduct(cartInfo.findProductById(productId));
        return cartInfo;
    }

    /*for testing*/
//    @GetMapping("/testajax")
//    public String testAjax(Model model, HttpServletRequest request) {
//        CartInfo cartInfo = Utils.getCartInSession(request);
//        model.addAttribute("cartInfo", cartInfo);
//        return "testajaxcart";
//    }
}
