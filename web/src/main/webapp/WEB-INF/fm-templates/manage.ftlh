<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Visual Admin Dashboard - Home</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <!--
    Visual Admin Template
    https://templatemo.com/tm-455-visual-admin
    -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
            <div class="square"></div>
            <h1>BIBLIOTEKA DT</h1>
        </header>
        <div class="profile-photo-container">
            <img src="images/dt-profile-photo.jpeg" alt="Profile Photo" class="img-responsive">
            <div class=""></div>
        </div>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">
            <ul>
                <li><a href="/">${main_page}</a></li>
                <li><a href="browse">${browse}</a></li>
                <li><a href="search">${search}</a></li>
                <li><a href="genres">${genres}</a></li>
                <#if user.isUserOrHigher()>
                    <li><a href="favourites">${favourites}</a></li>
                    <li><a href="reservations">${reservations}</a></li>
                </#if>
                <#if user.isAdminOrHigher()>
                    <li><a href="stats">${stats}</a></li>
                    <li><a href="manage" class="active">${manage}</a></li>
                </#if>
            </ul>
        </nav>
    </div>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <#if user.isLoggedIn()>
                            <li><a href="" class="active">WYLOGUJ</a></li>
                        <#else>
                            <li><a href="" class="active">ZALOGUJ</a></li>
                        </#if>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-flex-row flex-content-row">
                <div class="templatemo-content-widget white-bg col-2">
                    <div class=""></div>
                    <h2 class="">ZARZĄDZANIE</h2>
                    <hr>
                    <p>Przepraszamy. Strona w budowie.</p>
                </div>
            </div>
            <div class="templatemo-flex-row flex-content-row">
                <form enctype="multipart/form-data" method="post" action="/manage">
                    File: <input type="file" name="json"/><br/><br/>
                    <input type="submit" name="save" value="save"/><br/><br/>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
<script src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
<script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
<script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->

</body>
</html>