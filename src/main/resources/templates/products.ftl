<#import "parts/common.ftl" as C>
<@C.page>

<h2 class="mb-3">Product list</h2>
<div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-4 mt-2">
                <input type="text" class="form-control" name="name" placeholder="Product name" required/>
            </div>
            <div class="col-md-4 mt-2">
                <input type="text" class="form-control" name="price" placeholder="Product price" required/>
            </div>
            <div class="col-md-4 mt-2">
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="customFile"
                           onchange="$(this).next().after().text($(this).val().split('\\').slice(-1)[0])" required>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <div class="col-md-4 mt-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-light" style="width: 120px">Add</button>
            </div>
        </div>
    </form>
</div>

<div class="row">
    <#list producttypes as producttype>
        <div class="col-lg-3 col-md-6 mb-4 mt-2">
            <div class="card h-100">
            <#if producttype.filename??>
                <img src="/img/${producttype.filename}" class="card-img-top">
            </#if>
                <p>
                    <br>${producttype.price} ₽
                    <br>${producttype.name}
                </p>
            </div>
        </div>
    <#else>
        No Product Types
    </#list>
</div>

</@C.page>