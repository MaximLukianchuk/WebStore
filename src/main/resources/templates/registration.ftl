<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
Add new user
${message}
<@L.login "/registartion" />
</@C.page>