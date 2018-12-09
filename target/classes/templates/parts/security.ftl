<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    isRedactor = user.isRedactor()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    isRedactor = false
    >
</#if>