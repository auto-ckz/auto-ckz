function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function callAjax(url, type, data, success){
    return $.ajax({
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