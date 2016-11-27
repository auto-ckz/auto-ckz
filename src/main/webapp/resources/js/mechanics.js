function addMechanic(){
    var mechanic = getFormData($("#addMechanicForm"));
    callAjax('/rest/mechanics/', 'POST', mechanic, function(result){
        console.log(result);
        window.location.href = '/admin/mechanics';
    });

    return false;
};
function editMechanic(){
    var mechanic = getFormData($("#editMechanicForm"));
    if(!mechanic.id){
        console.log('Mechanic\'s Id not found.');
        return false;
    }
    callAjax('/rest/mechanics/' + mechanic.id , 'PUT', mechanic, function(result){
        console.log(result);
        window.location.href = '/admin/mechanics';
    });
    return false;
};

function deleteMechanic(){
    var mechanicId = $('#mechanicId').text();
    if(!mechanicId){
        console.log('Mechanic\'s Id not found.');
        return false;
    }
    callAjax('/rest/mechanics/' + mechanicId , 'DELETE', mechanicId, function(result){
        console.log(result);
        window.location.href = '/admin/mechanics';
    });
    return false;
};