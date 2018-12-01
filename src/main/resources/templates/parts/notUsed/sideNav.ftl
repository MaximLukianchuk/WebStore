<#include "../security.ftl">
<#if isAdmin || isRedactor>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="#">UpdateProducts </a>
    <a href="#">UpdateStores </a>
    <#if isAdmin>
    <a href="/user">UserList </a>
    </#if>
    <a href="#">Help</a>
</div>
<span style="font-size:30px;cursor:pointer;" onclick="openNav()">&#9776;</span>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
</script>
</#if>