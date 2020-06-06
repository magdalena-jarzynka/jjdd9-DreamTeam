<nav class="templatemo-top-nav col-lg-12 col-md-12">
    <#if role??>
    <ul class="text-uppercase">
        <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
            <li><a>${name}</a></li>
            <li><a href="/logout" class="active">${logOut}</a></li>
        <#else>
            <li><a href="/login" class="active">${logIn}</a></li>
        </#if>
        </#if>
    </ul>
</nav>