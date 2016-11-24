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
function addOrEditClient(){
    var client = getFormData($("#addClientForm"));
    callAjax('/rest/clients/', 'POST', client, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });

    return false;
};
function editClient(){
    var client = getFormData($("#addClientForm"));
    callAjax('/rest/clients/', 'PUT', client, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });
    return false;
};

function deleteClient(){
    var clientId = $('#clientId').text();
    console.log(clientId);
    callAjax('/rest/clients/', 'Delete', clientId, function(result){
        console.log(result);
        window.location.href = '/admin/clients';
    });
    return false;
};
