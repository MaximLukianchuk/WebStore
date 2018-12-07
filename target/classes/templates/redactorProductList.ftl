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
                                <img class="card-img-top img-responsive" src="/img/${producttype.filename}" alt="" >
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