package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.Genre;
import com.infoshareacademy.dreamteam.domain.entity.Kind;
import com.infoshareacademy.dreamteam.domain.pojo.GenrePlain;
import com.infoshareacademy.dreamteam.domain.pojo.KindPlain;
import com.infoshareacademy.dreamteam.domain.view.KindView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class KindMapper {

    public KindView mapEntityToView(Kind kind) {
        KindView kindView = new KindView();
        kindView.setName(kind.getName());
        kindView.setId(kind.getId());
        return kindView;
    }

}
