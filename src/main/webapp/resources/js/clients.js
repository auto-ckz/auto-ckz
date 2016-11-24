function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function callAjax(url, type, data, success){
    $.ajax({
        url: url,
        type : type,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType : 'json',
        data : JSON.stringify(data),
        success : success,
        error: function(xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    });
};
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
        return;
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
        return;
    }
    callAjax('/rest/clients/' + clientId , 'DELETE', clientId, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });
    return false;
};
