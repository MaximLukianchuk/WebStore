<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Product list</h2>
    ${addProductError?if_exists}
    <div>
        <form method="post" enctype="multipart/form-data">
            <div class="form-row">
                <div class="col-md-4 mt-2">
                    <input type="text" class="form-control" name="name" placeholder="Product name" required/>
                </div>
                <div class="col-md-4 mt-2">
                    <input type="text" class="form-control" name="price" placeholder="Product price" required/>
                </div>
                <div class="col-md-4 mt-2">
                    <input type="text" class="form-control" name="amount" placeholder="Product amount" required/>
                </div>
                <div class="input-group col-md-4 mt-2">
                    <form>
                        <select class="custom-select" id="inputGroupSelect01" name="storeName" required>
                        <#list stores as store>
                            <option value="${store.name}">${store.name}</option>
                        </#list>
                        </select>
                    </form>
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Stores</label>
                    </div>
                </div>

                <div class="col-md-4 mt-2">
                    <div class="custom-file">
                        <input type="file" name="file" class="custom-file-input" id="customFile"
                               onchange="$(this).next().after().text($(this).val().split('\\').slice(-1)[0])" required>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="col-md-4 mt-2">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-light" style="width: 120px">Add</button>
                </div>
            </div>
        </form>
    </div>

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
                            <p>${producttype.price} ₽</p>
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
                                    Lorem Ipsum is simply dummy text of the printing and typesetting industry.
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