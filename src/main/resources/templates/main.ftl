<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>
<@C.page>
<div>
    <@L.logout />
    <span><a href="/user">User list</a></span>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="наименование товара"/>
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список товаров</div>
<#list producttypes as producttype>
    <div>
        <b>${producttype.id}</b>
        <span>${producttype.name}</span>
        <div>
            <#if producttype.filename??>
                <img src="/img/${producttype.filename}">
            </#if>
        </div>
    </div>
<#else>
No Product Types
</#list>
</@C.page>