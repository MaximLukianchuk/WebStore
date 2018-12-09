<#import "parts/common.ftl" as c>
<@c.page>
<div class="container mt-5">
    <h5 class="mt-4">${product.name}</h5>
    <hr>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-6 mt-2">
                <label>Product name:</label>
                <input type="text" name="name" class="form-control" value="<#if product??>${product.name!''}</#if>"
                       required/>
            </div>
            <div class="col-md-5 mt-2">
                <label for="inputGroupSelect01">Select store:</label>
                <form>
                    <select class="custom-select" id="inputGroupSelect01" name="storeName" required>
                    <#list stores as store>
                        <option value="${store.name}">${store.name}</option>
                    </#list>
                    </select>
                </form>
            </div>
            <br>
            <div class="col-md-2 mt-2">
                <label>Product price:</label>
                <input type="text" class="form-control" name="price" placeholder="${product.price}"
                       value="${product.price}" required/>
            </div>
            <div class="col-md-2 mt-2">
                <label>Product amount:</label>
                <input type="text" class="form-control" name="amount" placeholder="${amountofproducts}"
                       value="${amountofproducts}" required/>
            </div>
            <div class="col-md-10 mt-2">
                <img style="max-width: 176px" src="/img/${product.filename}">
            </div>
            <div class="col-md-10 mt-2">
                <label>Product description:</label>
                <textarea type="text" class="form-control" name="description"
                          required>${product.description}</textarea>
            </div>
            <div class="col-md-5 mt-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-sm" style="width: 120px">Save</button>
            </div>
        </div>
    </form>
</div>
</@c.page>