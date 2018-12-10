<#import "parts/common.ftl" as C>
<@C.page>
<div class="container">
    <div class="row">
        <div class="col-md-6 mt-4">
            <div class="card card-height">
                <img class="img-wrapper" src="/img/${product.filename}">
            </div>
        </div>

        <div class="col-md-6 mt-4">
            <div class="card">
                <div class="card-body">
                    <h2 class="mb-3">${product.name}</h2>
                    <div>
                        <#if product.description??>
                            <p>${product.description}</p>
                        <#else>
                            <p>No description.</p>
                        </#if>
                    </div>
                    <hr>
                    <h5 class="card-title">
                        <#if product.discount??>
                            <del class="red-font">${product.price} $</del> <span class="red-font">(-${product.discount}%)</span>
                            <br><br>
                            <p>Final price: ${product.price * (100 - product.discount) / 100} $</p>
                        <#else>
                            <p>Price: ${product.price} $</p>
                        </#if>
                    </h5>
                    <hr>
                    <i class="fa fa-check" style="font-size:24px"></i>
                        <#if store.amount != 0>
                            <#if store.amount < 10>
                                Only ${store.amount} left available!
                            <#else>
                                ${store.amount} left available
                            </#if>
                        <#else>
                            Not available
                        </#if>
                    <br>
                    <i class="fa fa-car" style="font-size:24px"></i>
                    Will be delivered tomorrow
                    <br>
                    <div class="container-fluid-wide">
                        <button class="btn btn-primary btn-atc" type="submit">Add to cart</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@C.page>