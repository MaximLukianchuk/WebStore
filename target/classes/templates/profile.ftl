<#import "parts/common.ftl" as c>
<@c.page>

<div class="container">
    <div class="col-sm-12 mt-4">
        <#if success??>
            <script language=JavaScript>
                window.onUnload = swal("Poof! Your profile has been edited!", {
                    icon: "success",
                });
            </script>
        </#if>
    </div>
    <div class="col-sm-12 mt-4">
        <div class="card">
            <div class="card-header">
                <h6>User Profile</h6>
            </div>
            <div class="card-body">
                <h5 class="card-title">${usr.name} ${usr.surname}</h5><span><#list usr.roles as role>${role}<#sep>
                , </#list></span>
            </div>
        </div>
    </div>
    <div class="col-sm-12 mt-4">
        <div class="card">
            <div class="card-header">
                <h6>User info</h6>
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <tr>
                        <th scope="row">Username:</th>
                        <td>${usr.username}</td>
                    </tr>
                    <tr>
                        <th scope="row">City:</th>
                        <td>${usr.city}</td>
                    </tr>
                    <tr>
                        <th scope="row">Email:</th>
                        <td>${usr.email}</td>
                    </tr>
                    <#if usr.isRedactor()>
                        <#list userStores as store>
                        <tr>
                            <#if store??>
                                <th scope="row">Available store:</th>
                                <td>${store.getName()}</td>
                            </#if>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-12 mt-4">
        <a href="/user/profile/edit">
            <button class="btn btn-primary btn-sm" type="submit" style="width: 120px">Edit profile</button>
        </a>
    </div>
</div>

</@c.page>