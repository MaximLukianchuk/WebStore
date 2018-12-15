<#include "security.ftl">
<#if isAdmin || isRedactor>
 <li class="nav-item dropdown">
     <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
        aria-haspopup="true" aria-expanded="false">
         Admin Panel
     </a>
     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         <a class="dropdown-item" href="/productsList">List of products</a>
         <a class="dropdown-item" href="/storesList">List of stores</a>
         <a class="dropdown-item" href="/addProduct">Add product</a>
         <a class="dropdown-item" href="/addStore">Add store</a>
    <#if isAdmin>
         <div class="dropdown-divider"></div>
         <a class="dropdown-item" href="/user">User list</a>
    </#if>
     </div>
 </li>
</#if>