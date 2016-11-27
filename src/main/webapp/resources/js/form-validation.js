$.validator.addMethod("lettersonly", function(value, element) {
    return this.optional(element) || /^[a-z]+$/i.test(value);
}, "Należy używać jedynie liter");

$.validator.addMethod("pesel", function(value, element) {
    var pesel = value.replace(/[\ \-]/gi, '');
    if (pesel.length != 11) { return false; } else {
        var steps = new Array(1, 3, 7, 9, 1, 3, 7, 9, 1, 3);
        var sum_nb = 0;
        for (var x = 0; x < 10; x++) { sum_nb += steps[x] * pesel[x];}
        sum_m = 10 - sum_nb % 10;
        if (sum_m == 10) { sum_c = 0; } else { sum_c = sum_m;}
        if (sum_c != pesel[10]) {	return false;}
    }
    return true;
}, "Proszę o podanie prawidłowego numeru PESEL");

$.validator.addMethod(
    "regex",
    function(value, element, regexp)
    {
        if (regexp.constructor != RegExp)
            regexp = new RegExp(regexp);
        else if (regexp.global)
            regexp.lastIndex = 0;
        return this.optional(element) || regexp.test(value);
    },
    "Upewnij się, że podane dane są prawidłowe."
);

$(function() {
    $("form[name='addPersonForm']").validate({
        rules: {
            firstName: {
                required: true,
                maxlength: 50,
                lettersonly: true
            },
            lastName: {
                required: true,
                maxlength: 50,
                lettersonly: true
            },
            phoneNumber: {
                required: true,
                maxlength: 12,
                regex: /^(?:\(?\+?48)?(?:[-\.\(\)\s]*(\d)){9}\)?/
            },
            pesel: {
                required: true,
                minlength: 11,
                maxlength: 11,
                digits: true,
                pesel: true
            }
        },
        messages: {
            firstName: {
                required: "To pole jest wymagane",
                maxlength: "Imię powinno składać się maksymalnie z 30-u znaków",
                lettersonly: "Imię powinno składać się wyłącznie z liter"
            },
            lastName: {
                required: "To pole jest wymagane",
                maxlength: "Nazwisko powinno składać się maksymalnie z 30-u znaków",
                lettersonly: "Nazwisko powinno składać się wyłącznie z liter"
            },
            phoneNumber: {
                required: "To pole jest wymagane",
                maxlength: "Telefon powinien składać się maksymalnie z 12-u cyfr",
                regex: "Upewnij się, że podany numer jest prawidłowy"
            },
            pesel: {
                required: "To pole jest wymagane",
                minlength: "Pesel powinien składać się dokładnie z 11-u cyfr",
                maxlength: "Pesel powinien składać się dokładnie z 11-u cyfr",
                digits: "Pesel powinien składać się jedynie z cyfr",
            },
        }
    });
});