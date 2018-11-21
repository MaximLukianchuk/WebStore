<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
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
    <button type="submit" class="btn btn-light mt-2" style="width: 120px"><a href="/user/${user.id}/delete" style="color: black; text-decoration: none; display: block">Delete</a></button>
</form>
</div>
</@C.page>