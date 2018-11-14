<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
Login page
<@L.login "/login">
</@L.login>
<a href="/registration">Add new user</a>
</@C.page>