package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.api.dto.KindDto;
import com.infoshareacademy.dreamteam.domain.entity.Kind;
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

    public Kind mapToEntity(KindDto kindDto) {
        Kind kind = new Kind();
        kind.setName(kindDto.getName());
        return kind;
    }
}
