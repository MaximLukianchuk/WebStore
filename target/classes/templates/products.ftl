<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Product list</h2>
    <div class="">
        <div class="row">
            <#list producttypes as producttype>
                <div class="col-md-3 mt-4">
                    <div class="card chooseCard text-center img">
                        <#if producttype.filename??>
                            <div class="wrapper">
                                <a href="/products/${producttype.id}">
                                    <img class="card-img-top" src="/img/${producttype.filename}" alt="Card image cap">
                                </a>
                            </div>
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${producttype.name}</h5>
                            <p>${producttype.price} $</p>
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
                                    <a href="#" class="seemore" style="text-decoration: none; float: right;">Add to cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</div>
</@C.page>