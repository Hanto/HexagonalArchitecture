package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActivityDTOProcessor implements RepresentationModelProcessor<EntityModel<ActivityDTO>>
{
    @Override
    public @NonNull EntityModel<ActivityDTO> process(EntityModel<ActivityDTO> model)
    {
        ActivityDTO activity = model.getContent();
        assert activity != null;

        Link ownerLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(activity.getOwnerAccountId()))
            .withRel("ownedAccount");

        Link sourceLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(activity.getSourceAccountId()))
            .withRel("sourceAccount");

        Link targetLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(activity.getTargetAccountId()))
            .withRel("targetAccount");

        model.add(ownerLink);
        model.add(targetLink);

        return model;
    }
}