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
                    "Краткое описание продукта"
                </div>
            </h2>
        </div>
        <div class="col-sm">
            <div class="card" style="width: 20rem;">
                <div class="card-body">
                    <h5 class="card-title">${product.price} ₽</h5>
                    <hr>
                    <h6 class="card-subtitle mb-2 text-muted">В наличии</h6>
                    <h6 class="card-subtitle mb-2 text-muted">Курьер доставит завтра, бесплатно</h6>
                    <div class="container-fluid-wide">
                        <button class="btn btn-primary" type="submit" style="display: block; width: 320px;
padding: 15px 0; margin: 0 -21px -21px;border-radius: 0 0 4px 4px;">Добавить в корзину</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@C.page>