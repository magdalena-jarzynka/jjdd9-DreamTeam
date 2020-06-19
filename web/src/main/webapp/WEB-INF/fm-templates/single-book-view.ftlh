<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Single Book View</title>
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
    <link href="css/rating.css" rel="stylesheet">

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

                <div class="templatemo-content-widget white-bg templatemo-overflow-hidden">
                    <div class="templatemo-flex-row flex-content-row">
                        <div class="col-sm-6">
                            <p>
                                <img src="${book.cover!}" class="img-responsive" width="360" height="500">
                            </p>
                        </div>
                        <div class="col-sm-6">
                            <#include "rating.ftlh">
                            <h2><strong>${book.title!}</strong></h2>
                            <h5 style="font-style: italic"><#list book.authorViews>
                                    <#items as authorView>
                                        ${authorView.name}<#sep>,
                                    </#items>
                                </#list></h5>
                            <hr>
                            <p>
                                <#if userRole != "GUEST" && reserved == false>
                                    <a class="btn templatemo-edit-btn add-reservation" data-id-book="${book.id?c}"
                                       data-id-user="${userId}">ZAREZERWUJ KSIĄŻKĘ</a>
                                </#if>
                            </p>
                            <#if book.translators?has_content>
                                <p style="font-style: italic">tłum. ${book.translators}</p>
                            </#if>
                            <#if book.genreViews?has_content>
                                <p>Gatunek: <#list book.genreViews>
                                        <#items as genreView>
                                            ${genreView.name}<#sep>,
                                        </#items>
                                    </#list>
                                </p>
                            </#if>
                            <#if book.kindViews?has_content>
                                <p>Gatunek literacki: <#list book.kindViews>
                                        <#items as kindView>
                                            ${kindView.name}<#sep>,
                                        </#items>
                                    </#list>
                                </p>
                            </#if>
                            <#if book.epochViews?has_content>
                                <p>Epoka: <#list book.epochViews>
                                        <#items as epochView>
                                            ${epochView.name}<#sep>,
                                        </#items>
                                    </#list>
                                </p>
                            </#if>
                            <#if book.isbn??>
                                <p>ISBN: ${book.isbn!}</p>
                            </#if>
                            <#if book.fragment??>
                                <p>Fragment książki: ${book.fragment!}</p>
                            </#if>
                            <#if book.authorViews?has_content>
                                <p>Dostępna wersja Audio:
                                <#if book.audio! == true>
                                    tak
                                <#else>
                                    nie
                                    </p></#if></#if>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
<script src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
<script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
<script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
<script type="text/javascript" src="../../js/reservation.js"></script>

</body>
</html>