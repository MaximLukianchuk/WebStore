<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Store list</h2>
    <#list stores as store>
        <div class="row">
            <div class="col-sm-12">
                <div class="card-wrap horizontal codepen">
                    <div class="outer">
                        <a href="#">
                            <figure>
                            <#if store.filename??>
                                <div class="img-height-list img-wrap-list">
                                    <img class="img-responsive" src="/img/${store.filename}" alt="" >
                                </div>
                            </#if>
                            </figure>
                        </a>
                    </div>

                    <div class="card-info">
                        <div class="card-address">
                            <a href="#">
                                <p class="card-title">${store.name}</p>
                                <p>${store.description}<br>
                                </p>
                            </a>
                        </div>
                        <div class="card-phone">
                            <#if store.published>
                            <a href="#" class="green"><span>Published</span></a>
                            <#else>
                            <a href="#" class="orange"><span>Pending</span></a>
                            </#if>
                            <a href="/stores/${store.id}/edit" class="blue"><span>Edit</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
</@C.page>