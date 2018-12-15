<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Product list</h2>
    <#list producttypes as producttype>
        <div class="row">
            <div class="col-sm-12">
                <div class="card-wrap horizontal codepen">
                    <div class="">
                        <a href="#">
                            <figure>
                            <#if producttype.filename??>
                                <div class="wrapper img-wrap-list img-height-list">
                                    <img class="img-responsive" src="/img/${producttype.filename}" alt="">
                                </div>
                            </#if>
                            </figure>
                        </a>
                    </div>

                    <div class="card-info">
                        <div class="card-address">
                            <a href="#">
                                <p class="card-title">${producttype.name}</p>
                                <p>${producttype.price}$<br>
                                </p>
                            </a>
                        </div>
                        <div class="card-phone">
                            <#if producttype.published>
                            <a href="#" class="green"><span>Published</span></a>
                            <#else>
                            <a href="#" class="orange"><span>Pending</span></a>
                            </#if>
                            <a href="/products/${producttype.id}/edit" class="blue"><span>Edit</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
</@C.page>