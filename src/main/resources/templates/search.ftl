<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Search results: ${searchWord}</h2>
    <div class="">
        <hr>
        <h5>Stores: ${storesAmount}</h5>
        <div class="row">
            <#list stores as store>
                <div class="col-md-4 mt-4">
                    <div class="card chooseCard text-center">
                        <#if store.filename??>
                            <div class="wrapper img-height-store img-wrap-store">
                                <img src="/img/${store.filename}" alt="Card image cap">
                            </div>
                        </#if>
                        <div class="card-body">
                            <div style="height: 75px">
                                <h5 class="card-title">${store.name}</h5>
                                <p>${store.description}</p>
                            </div>
                            <hr class="hr">
                            <button type="submit" class="btn btn-primary see-prod"><a href="/stores/${store.id}"
                                                                                      class="see-prod"
                                                                                      style="text-decoration: none; display: block">
                                See products
                            </a></button>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <hr>
        <h5>Products: ${productsAmount}</h5>
        <div class="row">
            <#list producttypes as producttype>
                <div class="col-md-3 mt-4">
                    <div class="card chooseCard text-center">
                        <#if producttype.filename??>
                            <div class="wrapper img-wrap img-height">
                                <a href="/products/${producttype.id}">
                                    <img class="card-img-top" src="/img/${producttype.filename}" alt="Card image cap">
                                </a>
                            </div>
                        </#if>
                        <div class="card-body">
                            <div style="height: 100px">
                                <h5 class="card-title">${producttype.name}</h5>
                                <#if producttype.discount??>
                                    <del style="color:red;text-decoration:line-through">${producttype.price} $</del>
                                    <br> <h5>${producttype.price * (100 - producttype.discount) / 100} $</h5>
                                <#else>
                                <p>${producttype.price} $</p>
                                </#if>
                            </div>
                            <hr class="hr">
                            <p class="see-more">
                                <a class="seemore" style="text-decoration: none; color: #167ffb" data-toggle="collapse"
                                   href="#collapse${producttype.id}" aria-expanded="false"
                                   aria-controls="collapseExample">
                                    See more
                                </a>
                            </p>
                            <div class="collapse" id="collapse${producttype.id}">
                                <div class="card-body">
                                    <#if producttype.description??>
                                        ${producttype.description}
                                    <#else>
                                        No description.
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-muted" style="background-color: white">
                            <div class="row">
                                <div class="col">
                                    <a href="#" class="seemore" style="text-decoration: none; float: right;">Add to
                                        cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <hr>
    </div>
</div>

</@C.page>