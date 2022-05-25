package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
import com.myrran.cleanarchitecture.account.domain.Activity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class ActivityDTOAssembler implements RepresentationModelAssembler<Activity, EntityModel<ActivityDTO>>
{
    @Autowired private final AccountDTOMapper mapper;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public @NonNull EntityModel<ActivityDTO> toModel(@NonNull Activity activity)
    {
        ActivityDTO dto = mapper.fromModel(activity);
        EntityModel<ActivityDTO> model = EntityModel.of(dto);

        Link ownerLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(dto.getOwnerAccountId()))
            .withRel("ownerAccount");

        Link sourceLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(dto.getSourceAccountId()))
            .withRel("sourceAccount");

        Link targetLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(dto.getTargetAccountId()))
            .withRel("targetAccount");

        model.add(ownerLink);
        model.add(sourceLink);
        model.add(targetLink);

        return model;
    }

    public @NonNull CollectionModel<EntityModel<ActivityDTO>> toCollectionModel(@NonNull Account account)
    {
        CollectionModel<EntityModel<ActivityDTO>> model = toCollectionModel(account.getLastActivities().getActivities());

        Link selfLink = linkTo(methodOn(WebAdapter.class)
            .getActivities(account.getAccountId().getValue()))
            .withSelfRel();

        model.add(selfLink);

        return model;
    }

    @Override
    public @NonNull CollectionModel<EntityModel<ActivityDTO>> toCollectionModel(@NonNull Iterable<? extends Activity> entities)
    {   return RepresentationModelAssembler.super.toCollectionModel(entities); }
}
