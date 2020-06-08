<div class="col-lg-6 col-md-6 form-group">
    <label class="control-label templatemo-block">Wersja audio</label>
    <select class="form-control" id="isAudio" onchange="toPage(1)">
        <option value="blank">- - - Wybierz - - -</option>
        <option value="true">dostępna</option>
        <option value="false">niedostępna</option>
    </select>
</div>
<div class="col-lg-6 col-md-6 form-group">
    <label class="control-label templatemo-block">Gatunek</label>
    <select class="form-control" id="genre" onchange="toPage(1)">
        <option value="blank">- - - Wybierz - - -</option>
        <#list genres as genre>
            <option value="${genre.name}">
                ${genre.name}
            </option>
        </#list>
    </select>
</div>
    <div class="panel panel-default table-responsive">
        <table class="table table-striped table-bordered templatemo-user-table">
            <thead>
            <tr>
                <td></td>
                <td>Tytuł</td>
                <td>Autor</td>
                <td>Gatunek</td>
                <td>Wersja audio</td>
                <td>Zobacz szczegóły</td>
            </tr>
            </thead>
            <tbody>
            <#list books as book>
                <tr>
                    <td>
                        <img src="${book.cover!}" class="img-responsive" width="180" height="250">
                    </td>
                    <td>${book.title!}</td>
                    <td>
                        <#if book.authorViews?has_content>
                        <#list book.authorViews>
                            <#items as authorView>
                                ${authorView.name}<#sep>,
                            </#items>
                        </#list>
                        </#if>
                    </td>
                    <td>
                        <#if book.genreViews?has_content>
                        <#list book.genreViews>
                            <#items as genreView>
                                ${genreView.name}<#sep>,
                            </#items>
                        </#list>
                        </#if>
                    </td>
                    <td>
<#--                    <#if book.audio??>-->
                    ${book.audio?string("dostępna","niedostępna")}
<#--                    </#if>-->
                    </td>
                    <td>
                        <a href="/single?id=${book.id}" class="templatemo-edit-btn" style="white-space: nowrap">SZCZEGÓŁY >>></a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
<#if numberOfPages gt 1>
    <div class="pagination-wrap">
        <ul class="pagination">
            <li>
                <a onclick="toPage(1)" href="#" aria-label="FirstPage">
                    <span aria-hidden="true">Pierwsza strona</span>
                </a>
            </li>
            <#if pageNum !=1>
                <li>
                    <a onclick="toPage(${pageNum-1})" href="#" aria-label="Previous">
                        <span aria-hidden="true">&ltrif;</span>
                    </a>
                </li>
            </#if>

            <#list 1..numberOfPages as i>
                <#if i = pageNum>
                    <li class="active"><a onclick="toPage(${pageNum})" href="#"><span
                                    class="sr-only">(current)</span>${pageNum}
                        </a>
                    </li>
                <#elseif (i gt pageNum-3) && (i lt pageNum + 3)>
                    <li><a onclick="toPage(${i})" href="#">${i}</a></li>
                </#if>
            </#list>
            <#if pageNum != numberOfPages>
                <li>
                    <a onclick="toPage(${pageNum+1})" href="#" aria-label="Next">
                        <span aria-hidden="true">&rtrif;</span>
                    </a>
                </li>
            </#if>
            <li>
                <a onclick="toPage(${numberOfPages})" href="#" aria-label="Last Page">
                    <span aria-hidden="true">Ostatnia strona</span>
                </a>
            </li>
        </ul>
    </div>
</#if>