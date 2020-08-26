$(document).ready(function () {

    // GET REQUEST
    $("#searchHead").click(function (event) {
        event.preventDefault();

        ajaxGet();
    });

    // DO GET
    function ajaxGet() {
        var name = $('#nameSearch').val();
console.log(name);
        $.ajax({
            type: "GET",
            url: "/shop/search",

            data: {name: name},

            //result la json duoc tra ve
            success: function (data) {
                console.log("Success: ");

                //chay vong lap trong js voi x la index

                if (data.length === 0) {
                    console.log("oko", name);
                    // console.error("loi cmnr");
                    $('#noResult ').html("");
                    $('#productOject ').html("");
                    $('#pagination ').html("");
                    var result = "<h4 style='color:red;top: 50%;left: 50%; margin-right: -50%'> Không tìm thấy </h4>";
                    $('#noResult ').append(result);
                } else {
                    for (x in data) {
                        // var base64image =Convert.ToBase64String(data[x].image);
                        $('#noResult ').html("");
                        $('#productOject ').html("");
                        $('#pagination ').html("");

                        console.log("oko", name);
                        var img = 'data:image/png;base64,' + data[x].image;
                        var productDetail = '/shop/productdetail?id='+data[x].id+'';
                        var addToCart='/buyshop?id='+data[x].id+'';
                        $('#productOject ').append("     <div id=\"productContent\" class=\"col-lg-4 col-md-4 col-sm-6 col-6\" th:each=\"productList : ${productList}\">\n" +
                            "                                            <div class=\"single-product\">\n" +
                            "                                                <!-- Product Image Start -->\n" +
                            "                                                <div class=\"pro-img\">\n" +
                            "                                                    <a href=\""+productDetail+"\">\n" +
                            "                                                        <img class=\"primary-img\" src=\"" + img + "\" alt=\"single-product\">\n" +
                            "                                                    </a>\n" +
                            "                                                    <a href=\"#\" class=\"quick_view\" data-toggle=\"modal\" data-target=\"#myModal\" title=\"Quick View\"><i class=\"lnr lnr-magnifier\"></i></a>\n" +
                            "                                                </div>\n" +
                            "                                                <!-- Product Image End -->\n" +
                            "                                                <!-- Product Content Start -->\n" +
                            "\n" +
                            "                                                <div class=\"pro-content\"  >\n" +
                            "                                                    <form>\n" +
                            "                                                    <div class=\"pro-info\">\n" +
                            "\n" +
                            "                                                         <span hidden='hidden' id=\"idProduct\">"+data[x].id+"</span>\n" +
                            "                                                        <h4><a href=\"product.html\" id=\"nameProduct\">"+data[x].name+"</a></h4>\n" +
                            "                                                        <p><span class=\"price\"  id=\"priceProduct\">"+data[x].price+"</span></p>\n" +
                            "\n" +
                            "                                                    </div>\n" +
                            "                                                    </form>\n" +
                            "                                                    <div class=\"pro-actions\">\n" +
                            "                                                        <div class=\"actions-primary\">\n" +
                            "                                                            <a href=\"" + addToCart + "\" title=\"Add to Cart\"> + Add To Cart</a>\n" +
                            "                                                        </div>\n" +
                            "\n" +
                            "                                                    </div>\n" +
                            "                                                </div>\n" +
                            "\n" +
                            "                                                <!-- Product Content End -->\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +

                            "                                    </div>\n" +
                            "\n" +
                            "                                    <!-- Row End -->\n" +
                            "                                </div>\n" +
                            "                                <!-- #grid view End -->\n" +

                            "                                        </div>\n" +
                            "                                    </div>");

                    }
                }
            },
            error: function (e) {
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})