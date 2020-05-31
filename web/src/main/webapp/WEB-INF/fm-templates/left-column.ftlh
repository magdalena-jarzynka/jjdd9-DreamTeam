<nav class="templatemo-left-nav">
    <ul>
        <li><a href="/" class="${_active!}">${main_page}</a></li>
        <li><a href="browse" class="${browse_active!}">${browse}</a></li>
        <li><a href="search" class="${search_active!}">${search}</a></li>
        <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
            <li><a href="favourites" class="${favourites_active!}">${favourites}</a></li>
            <li><a href="reservations" class="${reservations_active!}">${reservations}</a></li>
        </#if>
        <#if role = "ADMIN" || role = "SUPER_ADMIN">
            <li><a href="stats" class="${stats_active!}">${stats}</a></li>
            <li><a href="manage" class="${manage_active!}">${manage}</a></li>
        </#if>
    </ul>
</nav>