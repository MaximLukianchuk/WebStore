<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="container mt-5">
    <h5 class="mt-4">${store.name}</h5>
    <hr>
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-8 mt-2">
                <label>Store name:</label>
                <input type="text" name="name" class="form-control" value="<#if store??>${store.name!''}</#if>"
                       required/>
            </div>
            <br>
            <div class="col-md-8 mt-2">
                <label>Store description:</label>
                <input type="text" class="form-control" name="description" placeholder="${store.description}"
                       value="${store.description}" required/>
            </div>
            <div class="col-md-10 mt-2">
                <img style="max-width: 176px" src="/img/${store.filename}">
            </div>
            <div class="col-md-6 mt-2">
                <div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm" style="width: 120px">
                        <#if isRedactor>
                            Submit
                        <#else>
                            <#if store.published>
                            Save
                            <#else>
                            Accept
                            </#if>
                        </#if>
                    </button>
                </div>
                <div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary btn-sm mt-2" style="width: 120px">
                        <a href="/stores/${store.id}/delete"
                           style="color: white; text-decoration: none; display: block">
                            <#if isRedactor>
                                <#if store.published>
                                Delete
                                <#else>
                                Cancel
                                </#if>
                            <#else>
                                <#if store.published>
                                Delete
                                <#else>
                                Reject
                                </#if>
                            </#if>
                        </a>
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
</@c.page>