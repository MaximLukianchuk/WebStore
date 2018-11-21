<#import "parts/common.ftl" as C>
<@C.page>
<h2 class="mb-3">User editor</h2>

<form action="/user" method="post">
    <input type="text" name="username" style="width: 18rem;" class="form-control mt-3 mb-2" value="${user.username}" placeholder="Name">
    <#list roles as role>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} class="custom-control-input" id="${role}">
            <label class="custom-control-label" for="${role}">  ${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name = "userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-light mt-2" style="width: 120px">Save</button>
    <button type="submit" onclick="deleteOrNot();" class="btn btn-light mt-2" style="width: 120px">
        <a href="#" style="color: black; text-decoration: none; display: block">Delete</a>
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
        })
                .then((willDelete) => {
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

</@C.page>