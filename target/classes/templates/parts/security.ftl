<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.getAuthorities()?seq_contains('ADMIN')
    isRedactor = user.getAuthorities()?seq_contains('REDACTOR')
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    isRedactor = false
    >
</#if>