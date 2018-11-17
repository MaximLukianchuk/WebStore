<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <input type="text" class="form-control" name="name" placeholder="наименование товара"/>
                <#--<input type="file" name="file">-->
            </div>
            <div class="col-md-4 mb-3">
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-light">Добавить</button>
            </div>
        </div>
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