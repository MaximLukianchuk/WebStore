<#import "parts/common.ftl" as C>
<@C.page>
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
    <div class="card my-3" style="width: 18rem;">
            <#if producttype.filename??>
                <img src="/img/${producttype.filename}" class="card-img-top">
            </#if>
        <div class="card-footer text-muted">
            ${producttype.name}
        </div>

    </div>
    <#else>
No Product Types
    </#list>
</@C.page>