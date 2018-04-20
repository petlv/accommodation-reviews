$(document).ready(function () {

    $('#button-load-comments').on('click', function (event) {
        event.preventDefault();

        fire_ajax_load();

    });

});

function fire_ajax_load() {

    $('#button-load-comments').prop('disabled', true);

    $.ajax({
        type: 'GET',
        url: '/ajax/comments',
        dataType: 'text',
        cache: false,
        timeout: 600000,
        success: function (data) {
            
            $('#feedback-comments').html(data);

            console.log('SUCCESS : ', data);
            $("#button-load-comments").prop('disabled', false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback-comments').html(json);

            console.log('ERROR : ', e);
            $('#button-load-comments').prop('disabled', false);

        }
    });

}