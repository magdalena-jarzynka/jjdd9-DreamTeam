<form action="/api/manage" class="templatemo-login-form" method="post">
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTitle">Tytuł</label>
            <input type="text" class="form-control" name="title">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputAuthor">Autor</label>
            <input type="text" class="form-control" name="author" multiple>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputTranslator">Translator</label>
            <input type="text" class="form-control" name="translator">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputEpoch">Epoka</label>
            <input type="text" class="form-control" name="epoch">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputGenre">Gatunek</label>
            <input type="text" class="form-control" name="genre">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputKind">Rodzaj</label>
            <input type="text" class="form-control" name="kind">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputIsbn">Numer isbn</label>
            <input type="text" class="form-control" name="isbn">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputCover">Okładka</label>
            <input type="text" class="form-control" name="cover">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-12 form-group">
            <label for="inputFragmentData">Fragment</label>
            <input type="text" class="form-control" name="fragment">
        </div>
    </div>
    <div class="row form-group">
        <div class="col-lg-6 col-md-6 form-group">
            <label class="control-label templatemo-block">Single Selection Control</label>
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
