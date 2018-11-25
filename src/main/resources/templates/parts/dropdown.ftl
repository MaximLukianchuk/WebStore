<#include "security.ftl">
<#if isAdmin || isRedactor>
 <li class="nav-item dropdown">
     <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         Admin Panel
     </a>
     <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         <a class="dropdown-item" href="#">Update products</a>
         <a class="dropdown-item" href="#">Update stores</a>
         <#if isAdmin>
         <div class="dropdown-divider"></div>
         <a class="dropdown-item" href="/user">User list</a>
         </#if>
     </div>
 </li>
</#if>