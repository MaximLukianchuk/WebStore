<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">
    <h5 class="mt-4">${user.username}</h5>
    <hr>
    ${message?ifExists}
    <form method="post">
        <div class="form-row">
            <div class="col-md-6 mt-2">
                <label>Username:</label>
                <input type="text" name="username" class="form-control" value="${user.username!''}" />
            </div>
            <div class="col-md-6 mt-2">
                <label>Password:</label>
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
            <div class="col-md-6 mt-2">
                <label>Name:</label>
                <input type="text" name="name" class="form-control" value="${user.name!''}" />
            </div>
            <div class="col-md-6 mt-2">
                <label>Surname:</label>
                <input type="text" name="surname" class="form-control" value="${user.surname!''}" />
            </div>
            <div class="col-md-6 mt-2">
                <label>City:</label>
                <input type="text" name="city" class="form-control" value="${user.city}" />
            </div>
            <div class="col-md-6 mt-2">
                <label>Email:</label>
                <input type="email" name="email" class="form-control" placeholder="example@domain.com" value="${user.email!''}" />
            </div>
            <div class="col-md-5 mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary btn-sm" type="submit" style="width: 120px">Save</button>
            </div>
        </div>
    </form>
</div>
</@c.page>