<#import "parts/common.ftl" as C>
<@C.page>
<div class="container mt-5">
    <h2 class="mb-3">Product list</h2>
    <div class="ui-206">
        <#list producttypes as producttype>
            <div class="ui-outer">
                <div class="container">
                    <div class="row row1">
                        <div class="col-md-3 col-sm-3 col-xs-3 col-pad">
                            <div class="ui-logo table-img">
                                <a href="">
                                     <#if producttype.filename??>
                                        <img class="card-img-top img-responsive" src="/img/${producttype.filename}" alt="" >
                                     </#if>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-3 col-pad text-vertical-al">
                            <div class="ui-content">
                                <span><a href="">${producttype.name}</a></span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2 col-xs-2 col-pad text-vertical-al">
                            <div class="ui-content">
                                <span>${producttype.price} $</span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2 col-xs-2 col-pad text-vertical-al">
                            <div class="ui-content">
                                <span class="badge badge-success white">Published</span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2 col-xs-2 col-pad text-vertical-al">
                            <div class="ui-btn">
                                <a href="#" class="btn btn-primary btn-sm">Edit</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
</@C.page>