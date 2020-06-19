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
        <#include "left-column.ftlh">
    </div>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <#include "navigation.ftlh">
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-flex-row flex-content-row">
                <div class="templatemo-content-widget white-bg col-2">
                    <div class=""></div>
                    <h2 class="">ZARZĄDZANIE</h2>
                    <hr>
                    <p>
                    <div class="templatemo-flex-row flex-content-row">
                        <div class="col-1">
                            <div class="panel panel-default margin-10">
                                <div class="panel-heading"><h2 class="text-uppercase">DODAJ POZYCJĘ</h2>
                                </div>
                                <div class="panel-body">
                                <#include "manage-add-form.ftlh">
                                </div>
                            </div>
                        </div>
                        <div class="templatemo-flex-row flex-content-row">
                            <div class="col-1">
                                <div class="panel panel-default margin-10">
                                    <div class="panel-heading"><h2 class="text-uppercase">ŁADOWANIE DANYCH Z PLIKU</h2>
                                    </div>
                                    <div class="panel-body">
                                        <form enctype="multipart/form-data" method="post" action="/manage">
                                            <div class="row form-group">
                                                <div class="col-lg-12">
                                                    <label class="margin-bottom-10">Wybierz plik</label>
                                                    <input type="file" name="json">
                                                </div>
                                            </div>
                                            <button type="submit" name="save" value="save"
                                                    class="templatemo-blue-button">Zapisz
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="templatemo-flex-row flex-content-row">
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
<script src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
<script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
<script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->

</body>
</html>