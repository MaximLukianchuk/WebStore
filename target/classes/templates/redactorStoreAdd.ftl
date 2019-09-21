<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Add store:</h2>
    <div>
        <form method="post" enctype="multipart/form-data">
            <div class="form-row">
                <div class="col-md-4 mt-2">
                    <label>Store name:</label>
                    <input type="text" class="form-control" name="name" placeholder="Store name" required/>
                </div>
                <div class="col-md-4 mt-2">
                    <label>Store description:</label>
                    <input type="text" class="form-control" name="description" placeholder="Store description" required/>
                </div>
                <div class="col-md-4 mt-2">
                    <label>Choose file:</label>
                    <div class="custom-file">
                        <input type="file" name="file" class="custom-file-input" id="customFile"
                               onchange="$(this).next().after().text($(this).val().split('\\').slice(-1)[0])" required>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="col-md-4 mt-2">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm" style="width: 120px">Add</button>
                </div>
            </div>
        </form>
    </div>
</div>
</@C.page>