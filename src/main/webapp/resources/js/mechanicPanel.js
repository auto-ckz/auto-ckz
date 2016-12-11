$(document).ready(function(){
    $("#chooseCar").on("submit", function(){
        var id = $("#sel1 option:selected").val();
        window.location.href = '/mechanic/' + id;
        return false;
    })
    $("#chooseRepair").on("submit", function(){
        var repairId = $("#sel2 option:selected").val();
        window.location.href = window.location.href + '?repairid=' + repairId;
        return false;
    })
    $("#editRepair").on("submit", function(){
        var repair = getFormData($("#editRepair"));
        if(!repair.id){
            console.log('Repair\'s Id not found.');
            return false;
        }
        callAjax('/rest/repairs/' + repair.id , 'PUT', repair, function(result){
            console.log(result);
            if(repair.status == 'DONE'){
                $("#hiddenData").hide();
                window.location.reload();
            }
            alert("Zapisano zmiany pomyślnie");
            window.location.href;
        });
        return false;
    })
});
