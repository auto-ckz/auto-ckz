function addMemberOfCustomerService(){
    var memberOfCustomerService = getFormData($("#addMemberOfCustomerServiceForm"));
    callAjax('/rest/memberOfCustomerServices/', 'POST', memberOfCustomerService, function(result){
        console.log(result);
        window.location.href = '/admin/membersOfCustomerService';
    });

    return false;
};
function editMemberOfCustomerService(){
    var memberOfCustomerService = getFormData($("#editMemberOfCustomerServiceForm"));
    if(!memberOfCustomerService.id){
        console.log('MemberOfCustomerService\'s Id not found.');
        return false;
    }
    callAjax('/rest/memberOfCustomerServices/' + memberOfCustomerService.id , 'PUT', memberOfCustomerService, function(result){
        console.log(result);
        window.location.href = '/admin/membersOfCustomerService';
    });
    return false;
};

function deleteMemberOfCustomerService(){
    var memberOfCustomerServiceId = $('#memberOfCustomerServiceId').text();
    if(!memberOfCustomerServiceId){
        console.log('MemberOfCustomerService\'s Id not found.');
        return false;
    }
    callAjax('/rest/memberOfCustomerServices/' + memberOfCustomerServiceId , 'DELETE', memberOfCustomerServiceId, function(result){
        console.log(result);
        window.location.href = '/admin/membersOfCustomerService';
    });
    return false;
};
