<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Store list:</h2>
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