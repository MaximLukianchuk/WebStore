<#macro login path isRegisterForm>
<form action="${path}" method="post" id="needs-validation" novalidate>
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
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:  </label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Sign Out</button>
</form>
</#macro>