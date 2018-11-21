<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
<#if message??>
    <#if message == "Your account was successfully activated">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script language=JavaScript>
            window.onUnload = swal("Success!", "Your account was successfully activated", "success");
        </script>
    </#if>
    <#if message == "Activation code is not found!">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script language=JavaScript>
            window.onUnload = swal("Oops!", "Activation code is not found!", "error");
        </script>
    </#if>
</#if>
<@L.login "/login" false/>
</@C.page>