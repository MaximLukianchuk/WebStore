<#import "parts/common.ftl" as C>
<@C.page>
<div class="container">
    <div class="row">
        <div class="col-sm">
            <img style="max-width: 400px" src="/img/${product.filename}">
        </div>
        <div class="col-sm">
            <h2 class="mb-3">${product.name}
                <div class="small" style="margin-top: 10px">
                    <#if product.description??>
                        ${product.description}
                    <#else>
                        No description.
                    </#if>
                </div>
            </h2>
        </div>
        <div class="col-sm">
            <div class="card" style="width: 20rem;">
                <div class="card-body">
                    <h5 class="card-title"><#if product.discount??>
                        <del style="color:red;text-decoration:line-through">${product.price} $</del>
                        <br> <h5>${product.price * (100 - product.discount) / 100} $</h5>
                    <#else>
                            <p>${product.price} $</p>
                    </#if></h5>
                    <hr>
                    <i class="fa fa-check" style="font-size:24px"></i>
                    Available
                    <br>
                    <i class="fa fa-car" style="font-size:24px"></i>
                    Will be delivered tomorrow
                    <div class="container-fluid-wide">
                        <button class="btn btn-primary" type="submit" style="display: block; width: 322px;
padding: 15px 0; margin: 0 -21px -21px;border-radius: 0 0 4px 4px;">Add to cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@C.page>