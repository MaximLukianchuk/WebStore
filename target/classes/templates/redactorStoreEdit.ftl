<#import "parts/common.ftl" as c>
<@c.page>
<div class="container mt-5">
    <h5 class="mt-4">${store.name}</h5>
    <hr>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-8 mt-2">
                <label>Store name:</label>
                <input type="text" name="name" class="form-control" value="<#if store??>${store.name!''}</#if>"
                       required/>
            </div>
            <br>
            <div class="col-md-8 mt-2">
                <label>Store address:</label>
                <input type="text" class="form-control" name="address" placeholder="${store.address}"
                       value="${store.address}" required/>
            </div>
            <div class="col-md-10 mt-2">
                <img style="max-width: 176px" src="/img/${store.filename}">
            </div>
            <div class="col-md-6 mt-2">
                <div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm" style="width: 120px">Save</button>
                </div>
                <div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm mt-2" style="width: 120px">
                        <a href="/stores/${store.id}/delete"
                           style="color: white; text-decoration: none; display: block">Delete</a>
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
</@c.page>