<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
<div class="container mt-5">
<h2 class="mb-3">Sign Up</h2>
<@L.login "/registration" true />
</div>
</@C.page>