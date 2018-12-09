<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Product list</h2>
    <#list stores as store>
        <div class="row">
            <div class="col-sm-12">
                <div class="card-wrap horizontal codepen">
                    <div class="outer">
                        <a href="#">
                            <figure>
                            <#if store.filename??>
                                <img class="card-img-top img-responsive" src="/img/${store.filename}" alt="" >
                            </#if>
                            </figure>
                        </a>
                    </div>

                    <div class="card-info">
                        <div class="card-address">
                            <a href="#">
                                <p class="card-title">${store.name}</p>
                                <p>${store.address}<br>
                                </p>
                            </a>
                        </div>
                        <div class="card-phone">
                            <a href="#" class="green"><span>Published</span></a>
                            <a href="#" class="blue"><span>Edit</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
</@C.page>