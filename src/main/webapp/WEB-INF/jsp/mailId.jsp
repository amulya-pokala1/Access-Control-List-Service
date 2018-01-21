<!DOCTYPE html>
<html lang="en">

<head>
    <title>
        Mail ID
    </title>

    <style>
        form { 
            margin: 0 auto;
            margin-top: 100px; 
            width:250px;
         }

    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
        crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script src="forgotpassword.js"></script>
</head>

<body >
    <div>
        <div class="container">
                <h2 style="text-align:center; margin-top: 25px;" >ACCESS CONTROL LIST SERVICE</h2>
            <form action="/access-control-list-service/mailForpass" method="POST">

                <div class="form-group">
                    <h3>Email </h3>
                    <input type="text" class="form-control" id="mail" placeholder="Enter Email" name="email">
                </div>
                

                <button type="submit" class="btn btn-primary" >Submit</button>
            </form>
        </div>
    </div>
</body>

</html>