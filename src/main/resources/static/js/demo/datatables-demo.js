// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
    responsive: true,
    "language": {
      "url": "https://cdn.datatables.net/plug-ins/1.10.19/i18n/Vietnamese.json"
    }
  });
});
