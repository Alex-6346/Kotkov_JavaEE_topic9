$(function(){
    $('#registerForm').submit(function(e){
        e.preventDefault();

        if ($('#password').val() !== $('#rPassword').val()) {
            alert('Passwords are not matching!');
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/register',
            dataType: 'json',
            data: JSON.stringify({
                login: $(this).find('[name=login]').val(),
                password: $(this).find('[name=password]').val()
            }),
            beforeSend: function(xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json')
            },
            success: function (response) {
                alert("User registered!");
                console.log(response);
            },
            error:function (){
                alert("Error in registering user!");
            }
        })

    })
});