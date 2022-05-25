package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import com.myrran.cleanarchitecture.account.domain.Account;
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
public class AccountDTOAssembler implements RepresentationModelAssembler<Account, EntityModel<AccountDTO>>
{
    @Autowired private final AccountDTOMapper mapper;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public @NonNull EntityModel<AccountDTO> toModel(@NonNull Account account)
    {
        AccountDTO dto = mapper.fromModel(account);
        EntityModel<AccountDTO>model = EntityModel.of(dto);

        Link selfLink = linkTo(methodOn(WebAdapter.class)
            .getAccount(account.getAccountId().getValue()))
            .withSelfRel();

        Link activitiesLink = linkTo(methodOn(WebAdapter.class)
            .getActivities(account.getAccountId().getValue()))
            .withRel("lastActivities");

        model.add(selfLink);
        model.add(activitiesLink);

        return model;
    }

    @Override
    public @NonNull CollectionModel<EntityModel<AccountDTO>> toCollectionModel(@NonNull Iterable<? extends Account> entities)
    {   return RepresentationModelAssembler.super.toCollectionModel(entities); }
}
