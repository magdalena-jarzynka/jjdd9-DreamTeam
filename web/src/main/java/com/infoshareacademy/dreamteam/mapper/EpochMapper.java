package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.Epoch;
import com.infoshareacademy.dreamteam.domain.pojo.EpochPlain;
import com.infoshareacademy.dreamteam.domain.view.EpochView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EpochMapper {

    public EpochView mapEntityToView(Epoch epoch) {
        EpochView epochView = new EpochView();
        epochView.setId(epoch.getId());
        epochView.setName(epoch.getName());
        return epochView;
    }

    public Epoch mapPlainToEntity(EpochPlain epochPlain) {
        Epoch epoch = new Epoch();
        epoch.setId(epochPlain.getId());
        epoch.setName(epochPlain.getName());
        return epoch;
    }

}
