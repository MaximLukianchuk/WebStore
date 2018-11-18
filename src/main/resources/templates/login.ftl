<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>

<@C.page>
<#if message??>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Success!</strong> ${message?if_exists}
    </div>
</#if>
<@L.login "/login" false/>
</@C.page>