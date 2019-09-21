<#include "security.ftl">
<#import "login.ftl" as L>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: white">
    <a class="navbar-brand" href="http://localhost:8080">
        <div id="logo"></div>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/stores">Stores</a>
            </li>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/profile?success">Profile</a>
            </li>
            </#if>
            <#include "dropdown.ftl">
        </ul>
        <div style="margin-right: 35px">
            <form action="/search" class="form-inline">
                <input class="form-control" name="name" type="text" placeholder="Search.." size="40" id="search">
            </form>
        </div>
        <#if name = "unknown">
            <@L.login2 />
        <#else>
            <div class="navbar-text mr-3">${name}</div>
            <@L.logout />
        </#if>
    </div>
</nav>