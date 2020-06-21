<form id="add-form" action="/api/manage" class="templatemo-login-form" method="post" accept-charset="UTF-8">
    <#if book??>
        <input type="hidden" name="bookId" value="${book.id}">
    </#if>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTitle">Tytuł</label>
            <input type="text" class="form-control" name="title" id="title" autocomplete="off" required
                   value="${(book.title)!""}">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputAuthor">Autor/Autorzy</label>
            <#if book?? && book?has_content>
                <#list book.authorViews as author>
                    <div class="form-group multiple-form-group input-group">
                        <input type="text" class="form-control" name="author" autocomplete="off" required
                               value="${(author.name)!""}">
                        <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                    </div>
                </#list>
            <#else>
                <div class="form-group multiple-form-group input-group">
                    <input type="text" class="form-control" name="author" autocomplete="off" required>
                    <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                </div>
            </#if>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTranslator">Tłumacz</label>
                <input type="text" class="form-control" name="translator" autocomplete="off"
                       value="${(book.translators)!""}">
            <p>Jeżeli chcesz dodać więcej tłumaczy wymień ich po przecinku.</p>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputEpoch">Epoka/Epoki</label>
            <#if book?? && book?has_content>
                <#list book.epochViews as epoch>
                    <div class="form-group multiple-form-group input-group">
                        <input type="text" class="form-control" name="epoch" autocomplete="off" required
                               value="${(epoch.name)!""}">
                        <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                    </div>
                </#list>
            <#else>
                <div class="form-group multiple-form-group input-group">
                    <input type="text" class="form-control" name="epoch" autocomplete="off" required>
                    <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                </div>
            </#if>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputGenre">Gatunek/Gatunki</label>
            <#if book?? && book?has_content>
                <#list book.genreViews as genre>
                    <div class="form-group multiple-form-group input-group">
                        <input type="text" class="form-control" name="genre" autocomplete="off" required
                               value="${(genre.name)!""}">
                        <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                    </div>
                </#list>
            <#else>
                <div class="form-group multiple-form-group input-group">
                    <input type="text" class="form-control" name="genre" autocomplete="off" required>
                    <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                </div>
            </#if>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputKind">Gatunek literacki/Gatunki literackie</label>
            <#if book?? && book?has_content>
                <#list book.kindViews as kind>
                    <div class="form-group multiple-form-group input-group">
                        <input type="text" class="form-control" name="kind" autocomplete="off" required
                               value="${(kind.name)!""}">
                        <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                    </div>
                </#list>
            <#else>
                <div class="form-group multiple-form-group input-group">
                    <input type="text" class="form-control" name="kind" autocomplete="off" required>
                    <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                </div>
            </#if>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputIsbn">Numer isbn</label>
            <input type="text" class="form-control" name="isbn" autocomplete="off" value="${(book.isbn)!""}">
        </div>
    </div>
    <#--    <div class="row form-group">-->
    <#--        <div class="col-lg-12 form-group">-->
    <#--            <label for="inputCover">Okładka</label>-->
    <#--            <input type="text" class="form-control" name="cover" autocomplete="off" value="${(book.cover)!""}">-->
    <#--        </div>-->
    <#--    </div>-->
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputFragmentData">Fragment</label>
            <input type="text" class="form-control" name="fragment" autocomplete="off" value="${(book.fragment)!""}">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-6 col-md-6 form-group">
            <label class="control-label templatemo-block">Wersja audio</label>
            <select class="form-control" name="isAudio">
                <option value="blank">- - - Wybierz - - -</option>
                <option value="true" <#if book?? && book.audio>selected</#if>>dostępna</option>
                <option value="false" <#if book?? !book.audio>selected</#if>>niedostępna</option>
            </select>
        </div>
    </div>
    <div class="form-group text-right">
        <#if book??>
        <button type="submit" class="templatemo-blue-button save">Zapisz</button>
        <#else>
        <button type="submit" class="templatemo-blue-button">Dodaj</button>
        </#if>
    </div>
</form>