$(document).ready(function(){
    $('#status option:contains("NOT_STARTED")').text('Nie rozpoczęto');
    $('#status option:contains("IN_PROGRESS")').text('W trakcie');
    $('#status option:contains("SUSPENDED")').text('Wstrzymano');
    $('#status option:contains("DONE")').text('Zakończono');
    var id = $("#RepairOrderId").text();
    $("#editRepair").on("submit", function(){
        var repair = getFormData($("#editRepair"));
        if(!repair.id){
            console.log('Repair\'s Id not found.');
            return false;
        }
        callAjax('/rest/repairs/' + repair.id , 'PUT', repair, function(result){
            console.log(result);
            window.location.href = '/memberOfCustomerService/repair_order/' + id;
        });
        return false;
    })
});