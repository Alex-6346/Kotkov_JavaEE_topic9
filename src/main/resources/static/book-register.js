$(function(){
    $('#registerForm').submit(function(e){
        e.preventDefault();

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
            error:function (err){
                alert("Such user already exists!");
            }
        })

    })
});