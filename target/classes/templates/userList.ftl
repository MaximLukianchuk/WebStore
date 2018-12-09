<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">List of users</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
<#list users as user>
<tr>
    <td>${user.username}</td>
    <td>${user.email}</td>
    <td><#list user.roles as role>${role}<#sep>, </#list></td>
    <td>
        <button type="submit" class="btn btn-primary btn-sm" style="width: 125px"><a href="/user/${user.id}"
                                                                            style="color: white; text-decoration: none; display: block">Edit</a>
        </button>
    </td>
</tr>
</#list>
        </tbody>
    </table>
</div>
</@C.page>
