function showNoty() {
    var error
    new Noty({
        text: 'ERROR: ' + error,
        layout: 'topCenter',
        type: 'error',
        theme: 'mint',
        timeout: 3000
    }).show()
}




/*<script th:inline="javascript">
/!*<![CDATA[*!/

let errors = /!*[[${#fields.errors()}]]*!/ 'default';
console.log(errors);

/!*]]>*!/
</script>*/

/*
$('#buttonRegister').click(function (ev) {

    $.ajax()
        .done(function (data) {
            new Noty({
                text: 'Registration done successfully!',
                layout: 'topCenter',
                type: 'success',
                theme: 'mint',
                timeout: 3000
            }).show()
            
        })
    
});*/
