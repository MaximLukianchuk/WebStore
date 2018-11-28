<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
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

    <div class="">
        <div class="row">
            <#list stores as store>
                <div class="col-md-4 mt-4">
                    <div class="card chooseCard text-center img">
                        <#if store.filename??>
                            <div class="wrapper">
                                <img class="card-img-top" src="/img/${store.filename}" alt="Card image cap">
                            </div>
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${store.name}</h5>
                            <p>${store.address}</p>
                            <hr class="hr">
                                <button type="submit" class="btn btn-primary see-prod"><a href="/stores/${store.id}" class="see-prod" style="text-decoration: none; display: block">
                                    See products
                                </a></button>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>
</@C.page>