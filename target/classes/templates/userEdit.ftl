<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
<h2 class="mb-3">User editor</h2>

<form action="/user" method="post">
    <input type="text" name="username" style="width: 18rem;" class="form-control mt-3 mb-2" value="${user.username}" placeholder="Name">
    <hr>
    <#list roles as role>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} class="custom-control-input" id="${role}">
            <label class="custom-control-label" for="${role}">  ${role}</label>
        </div>
    </#list>
    <hr>
    <div class="custom-control custom-checkbox">
        <input type="checkbox" name="allowedToCreateStores" ${allowedToCreateStores?string("checked", "")} class="custom-control-input" id="allowedToCreateStores">
        <label class="custom-control-label" for="allowedToCreateStores">  Allowed to create stores</label>
    </div>
    <hr>
    <h3 class="mb-3">Available stores:</h3>
    <#list stores as store>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" name="${store.id}" ${userStores?seq_contains(store)?string("checked", "")} class="custom-control-input" id="${store.id}">
            <label class="custom-control-label" for="${store.id}">  ${store.name}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name = "userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary btn-sm mt-2" style="width: 120px">Save</button>
    <button type="submit" onclick="deleteOrNot();" class="btn btn-primary btn-sm mt-2" style="width: 120px">
        <a href="#" style="color: white; text-decoration: none; display: block">Delete</a>
    </button>
</form>

<script type="text/javascript">
    function deleteOrNot() {
        swal({
            title: "Are you sure?",
            text: "Once you delete ${user.username}, you will not be able to recover it.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
                if (willDelete) {
                    swal("Poof! User ${user.username} has been deleted!", {
                        icon: "success",
                    }).then((ok) => {
                        if (ok) {
                            window.location.href = "/user/${user.id}/delete";
                        }
                    });
                } else {
                    window.location.href = "/user/${user.id}";
                }
            });
    }
</script>

</div>
</@C.page>