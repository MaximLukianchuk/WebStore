<#import "parts/common.ftl" as C>
<@C.page>

<h2 class="mb-3">Store list</h2>
<div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-4 mt-2">
                <input type="text" class="form-control" name="name" placeholder="Store name" required/>
            </div>
            <div class="col-md-4 mt-2">
                <input type="text" class="form-control" name="address" placeholder="Store address" required/>
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
<div class="row mt-2" style="background-color:#f9f9f9">
    <#list stores as store>
        <div class="col-lg-2 col-md-6 mb-4 mt-3">
            <div class="card-img-top">
            <#if store.filename??>
                <img src="/img/${store.filename}" class="card-img-top">
            </#if>
                ${store.name}
                <hr>
                ${store.address}
            </div>
        </div>
    <#else>
        No Stores
    </#list>
</div>
</@C.page>