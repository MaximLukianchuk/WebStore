<#import "parts/common.ftl" as c>
<@c.page>
<div class="container">
    <h5 class="mt-4">${usr.username}</h5>
    <hr>
    ${message?ifExists}
    <form method="post" id="needs-validation" novalidate>
        <#if username_taken??>
            <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error!</strong> ${username_taken}
            </div>
        </#if>
        <#if email_taken??>
            <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error!</strong> ${email_taken}
            </div>
        </#if>
        <div class="form-row">
            <div class="col-md-6 mt-2">
                <label>Username:</label>
                <input type="text" name="username" class="form-control" value="<#if usr??>${usr.username!''}</#if>"
                       required/>
            </div>
            <div class="col-md-6 mt-2">
                <label>Password:</label>
                <input type="password" name="password" class="form-control" pattern="^[_a-zA-Z0-9\-]{8,20}$"
                       placeholder="Password" required/>
                <small id="passwordHelpInline" class="text-muted">
                    Must be 8-20 characters long.
                </small>
            </div>
            <div class="col-md-6 mt-2">
                <label>Name:</label>
                <input type="text" name="name" class="form-control" value="${usr.name!''}" required/>
            </div>
            <div class="col-md-6 mt-2">
                <label>Surname:</label>
                <input type="text" name="surname" class="form-control" value="${usr.surname!''}" required/>
            </div>
            <div class="col-md-6 mt-2">
                <label>City:</label>
                <input type="text" name="city" class="form-control" value="${usr.city}" required/>
            </div>
            <div class="col-md-6 mt-2">
                <label>Email:</label>
                <input type="email" name="email" class="form-control" placeholder="example@domain.com"
                       value="${usr.email!''}" required/>
            </div>
            <div class="col-md-5 mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary btn-sm" type="submit" style="width: 120px">Save</button>
            <#--<button onclick="profileEdited()" class="btn btn-primary btn-sm" type="submit" style="width: 120px"><a href="#" style="color: white; text-decoration: none; display: block">Save</a></button>-->
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            let form = document.getElementById('needs-validation');
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        }, false);
    })();
</script>
</@c.page>