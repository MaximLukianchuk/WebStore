<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">
    <h5>${user.username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Username:</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" value="${user.username!''}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" value="${user.name!''}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Surname:</label>
            <div class="col-sm-6">
                <input type="text" name="surname" class="form-control" value="${user.surname!''}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">City:</label>
            <div class="col-sm-6">
                <input type="text" name="city" class="form-control" value="${user.city}" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="example@domain.com" value="${user.email!''}" />
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</div>
</@c.page>