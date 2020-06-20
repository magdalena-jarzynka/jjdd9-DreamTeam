package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.domain.api.dto.*;
import com.infoshareacademy.dreamteam.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/manage")
public class ManageController {

    @Inject
    private BookService bookService;

    @POST
    public Response addBook(@FormParam("title") String title,
                            @FormParam("author") List<String> authors,
                            @FormParam("translator") List<String> translators,
                            @FormParam("epoch") List<String> epochs,
                            @FormParam("genre") List<String> genres,
                            @FormParam("kind") List<String> kinds,
                            @FormParam("isbn") String isbn,
                            @FormParam("cover") String cover,
                            @FormParam("fragment") String fragment,
                            @FormParam("isAudio") boolean isAudio) {

        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        List<AuthorDto> authorDtoList = new ArrayList<>();
        bookDto.setAuthors(authorDtoList);
        for (String author : authors) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(author);
            authorDtoList.add(authorDto);
        }
        List<TranslatorDto> translatorDtoList = new ArrayList<>();
        bookDto.setTranslators(translatorDtoList);
        for (String translator : translators) {
            TranslatorDto translatorDto = new TranslatorDto();
            translatorDto.setName(translator);
            translatorDtoList.add(translatorDto);
        }

        List<EpochDto> epochDtoList = new ArrayList<>();
        bookDto.setEpochs(epochDtoList);
        for(String epoch : epochs) {
            EpochDto epochDto = new EpochDto();
            epochDto.setName(epoch);
            epochDtoList.add(epochDto);
        }
        List<GenreDto> genreDtoList = new ArrayList<>();
        bookDto.setGenres(genreDtoList);
        for (String genre : genres) {
            GenreDto genreDto = new GenreDto();
            genreDto.setName(genre);
            genreDtoList.add(genreDto);
        }
        List<KindDto> kindDtoList = new ArrayList<>();
        bookDto.setKinds(kindDtoList);
        for (String kind : kinds) {
            KindDto kindDto = new KindDto();
            kindDto.setName(kind);
            kindDtoList.add(kindDto);
        }
        bookDto.setIsbn(isbn);
        bookDto.setCover(cover);
        bookDto.setFragmentData(fragment);
        bookDto.setAudio(isAudio);

        bookService.saveBookDto(bookDto);
        return Response.status(Response.Status.CREATED).build();
    }
}
