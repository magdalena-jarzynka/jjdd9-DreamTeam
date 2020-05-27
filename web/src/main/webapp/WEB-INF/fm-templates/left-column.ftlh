<nav class="templatemo-left-nav">
    <ul>
        <li><a href="/" class="active">${main_page}</a></li>
        <li><a href="browse">${browse}</a></li>
        <li><a href="search">${search}</a></li>
        <li><a href="genres">${genres}</a></li>
        <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
            <li><a href="favourites">${favourites}</a></li>
            <li><a href="reservations">${reservations}</a></li>
        </#if>
        <#if role = "ADMIN" || role = "SUPER_ADMIN">
            <li><a href="stats">${stats}</a></li>
            <li><a href="manage">${manage}</a></li>
        </#if>
    </ul>
</nav>
