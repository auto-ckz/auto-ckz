function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function addClient(){
    var client = getFormData($("#addClientForm"));
    $.ajax({
        url: '/rest/clients/',
        type : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType : 'json',
        data : JSON.stringify(client),
        success : function(result) {
            console.log(result);
            window.location.href = '/admin/clients/all';
        },
        error: function(xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    });

    return false;
}