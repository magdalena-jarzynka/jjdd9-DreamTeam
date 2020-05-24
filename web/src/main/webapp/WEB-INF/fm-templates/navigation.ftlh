<nav class="templatemo-top-nav col-lg-12 col-md-12">
    <ul class="text-uppercase">
        <#if user.isLoggedIn()>
            <li><a href="http://localhost:8080/logout" class="active">${"logOut"}</a></li>
        <#else>
            <li><a href="http://localhost:8080/login" class="active">${"logIn"}</a></li>
        </#if>
    </ul>
</nav>