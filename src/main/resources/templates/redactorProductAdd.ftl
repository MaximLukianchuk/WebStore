<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Add new product</h2>
    ${addProductError?if_exists}
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-5 mt-2">
                <label>Product name:</label>
                <input type="text" class="form-control" name="name" placeholder="Product name" required/>
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
            <div class="col-md-6 mt-2">
                <label>Choose file:</label>
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="customFile"
                           onchange="$(this).next().after().text($(this).val().split('\\').slice(-1)[0])" required>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <div class="col-md-2 mt-2">
                <label>Product price:</label>
                <input type="text" class="form-control" name="price" placeholder="Product price" required/>
            </div>
            <div class="col-md-2 mt-2">
                <label>Product amount:</label>
                <input type="text" class="form-control" name="amount" placeholder="Product amount" required/>
            </div>
            <br>
            <div class="col-md-10 mt-2">
                <label>Product description:</label>
                <textarea type="text" class="form-control" name="description" placeholder="Product description"
                          required></textarea>
            </div>
            <br>
            <div class="col-md-5 mt-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-sm" style="width: 120px">Add</button>
            </div>
        </div>
    </form>
</div>
</@C.page>