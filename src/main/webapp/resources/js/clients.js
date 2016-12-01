function addClient(){
    var client = getFormData($("#addClientForm"));
    callAjax('/rest/clients/', 'POST', client, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });

    return false;
};
function editClient(){
    var client = getFormData($("#editClientForm"));
    if(!client.id){
        console.log('Client\'s Id not found.');
        return false;
    }
    callAjax('/rest/clients/' + client.id , 'PUT', client, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });
    return false;
};

function deleteClient(){
    var clientId = $('#clientId').text();
    if(!clientId){
        console.log('Client\'s Id not found.');
        return false;
    }
    callAjax('/rest/clients/' + clientId , 'DELETE', clientId, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });
    return false;
};
