<form id="add-form" action="/api/manage" class="templatemo-login-form" method="post" accept-charset="UTF-8">
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTitle">Tytuł</label>
            <input type="text" class="form-control" name="title" id="title" autocomplete="off" required>
        </div>
    </div>
    <div id="author_fields">
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputAuthor">Autor/Autorzy</label>
            <div class="form-group multiple-form-group input-group">
                <input type="text" class="form-control" name="author" autocomplete="off" required>
            <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
                </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTranslator">Tłumacz/Tłumacze</label>
            <div class="form-group multiple-form-group input-group">
            <input type="text" class="form-control" name="translator" autocomplete="off">
                <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputEpoch">Epoka/Epoki</label>
            <div class="form-group multiple-form-group input-group">
            <input type="text" class="form-control" name="epoch" autocomplete="off" required>
                <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputGenre">Gatunek/Gatunki</label>
            <div class="form-group multiple-form-group input-group">
            <input type="text" class="form-control" name="genre" autocomplete="off" required>
                <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputKind">Gatunek literacki/Gatunki literackie</label>
            <div class="form-group multiple-form-group input-group">
            <input type="text" class="form-control" name="kind" autocomplete="off" required>
                <span class="input-group-btn">
                            <button type="button" class="btn blue-button btn-add">+</button>
                        </span>
            </div>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputIsbn">Numer isbn</label>
            <input type="text" class="form-control" name="isbn" autocomplete="off">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputCover">Okładka</label>
            <input type="text" class="form-control" name="cover" autocomplete="off">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputFragmentData">Fragment</label>
            <input type="text" class="form-control" name="fragment" autocomplete="off">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-6 col-md-6 form-group">
            <label class="control-label templatemo-block">Wersja audio</label>
            <select class="form-control" name="isAudio">
                <option value="blank">- - - Wybierz - - -</option>
                <option value="true">dostępna</option>
                <option value="false">niedostępna</option>
            </select>
        </div>
    </div>
    <div class="form-group text-right">
        <button type="submit" class="templatemo-blue-button">Dodaj</button>
    </div>
</form>