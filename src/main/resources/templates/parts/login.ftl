<#macro login path isRegisterForm>
<div id="formWrapper">
    <div id="form">
        <#if !isRegisterForm>
            <div class="logo mt-5">
                <h1 class="text-center head">WebStore</h1>
            </div>
        </#if>
        <form action="${path}" method="post" class="mt-4" id="needs-validation" novalidate>
            <#if messages2??>
            <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error!</strong> ${messages2} Try to <a href="/login" class="alert-link">log in</a>.
            </div>
            </#if>
            <#if isRegisterForm>
                <div class="form-group row">
                    <label for="validationServer01" class="col-sm-2 col-form-label"> Name: </label>
                    <div class="col-sm-6">
                        <input type="text" name="name" id="validationServer01" class="form-control" placeholder="Name" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please choose a name.
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="validationServer02" class="col-sm-2 col-form-label"> Surname:  </label>
                    <div class="col-sm-6">
                        <input type="text" name="surname" id="validationServer02" class="form-control" placeholder="Surname" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please choose a surname.
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="validationServer03" class="col-sm-2 col-form-label"> Email:  </label>
                    <div class="col-sm-6 input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupPrepend3">@</span>
                        </div>
                        <input type="email" name="email" id="validationServer03" class="form-control" placeholder="example@domain.com" aria-describedby="inputGroupPrepend3" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please choose an email.
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="validationServer04" class="col-sm-2 col-form-label"> City:  </label>
                    <div class="col-sm-6">
                        <input type="text" id="validationServer04" name="city" class="form-control" placeholder="City" required/>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                        <div class="invalid-feedback">
                            Please choose a city.
                        </div>
                    </div>
                </div>
            </#if>
            <#if info??>
                <div class="alert alert-info alert-dismissible fade show mt-3" role="alert">
                    <button type="button" class="close" data-turbolinks="false" data-dismiss="alert">&times;</button>
                    ${info}
                </div>
            </#if>
            <div class="form-group row">
                <#if isRegisterForm>
                    <label for="validationServer05" class="col-sm-2 col-form-label"> Username: </label>
                </#if>
                <div class="col-sm-6">
                    <input type="text" name="username" id="validationServer05" class="form-control width" placeholder="Username" required/>
                    <#if isRegisterForm>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </#if>
                    <div class="invalid-feedback">
                        Please choose a username.
                    </div>
                    <#if messages??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Error!</strong> ${messages}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <#if isRegisterForm>
                    <label for="validationServer06" class="col-sm-2 col-form-label"> Password:  </label>
                </#if>
                <div class="col-sm-6">
                    <input type="password" name="password" id="validationServer06" required pattern="^[_a-zA-Z0-9\-]{8,20}$" class="form-control width" placeholder="Password" required/>
                    <#if isRegisterForm>
                        <div id="pass">
                            <small id="passwordHelpInline" class="text-muted">
                                Must be 8-20 characters long.
                            </small>
                        </div>
                        <div class="valid-feedback">
                            Looks good!
                        </div>
                    </#if>
                    <div class="invalid-feedback">
                        Please enter a password.
                    </div>
                    <#if error??>
                        <div class="alert alert-danger alert-dismissible fade show mt-3 width" role="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Error!</strong> ${error}
                        </div>
                    </#if>
                </div>
            </div>
            <#if isRegisterForm>
                <div class="form-group row col">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="invalidCheck3" required>
                        <label class="form-check-label" for="invalidCheck3">
                            Agree to terms and conditions
                        </label>
                        <div class="invalid-feedback">
                            You must agree before submitting.
                        </div>
                    </div>
                </div>
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <#if !isRegisterForm><a href="/registration" style="text-decoration: none">Sign Up</a></#if>
            <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
            <#if !isRegisterForm>
                <button class="btn btn-light" type="button"><a href="/products" style="text-decoration: none; display: block">Menu</a></button>
            </#if>
        </form>
    </div>
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
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Sign Out</button>
</form>
</#macro>

<#macro login2>
<form method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <a href="/registration" class="mr-3" style="text-decoration: none">Sign Up</a>
    <button class="btn btn-primary"><a href="/login" style="color: white; display: block; text-decoration: none;">Sign In</a></button>
</form>
</#macro>