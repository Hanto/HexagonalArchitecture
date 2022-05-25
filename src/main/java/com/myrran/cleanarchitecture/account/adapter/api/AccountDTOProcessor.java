package com.myrran.cleanarchitecture.account.adapter.api;// Created by jhant on 25/05/2022.

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountDTOProcessor implements RepresentationModelProcessor<EntityModel<AccountDTO>>
{
    @Override
    public @NonNull EntityModel<AccountDTO> process(EntityModel<AccountDTO> model)
    {
        AccountDTO account = model.getContent();
        assert account != null;

        Link link = linkTo(methodOn(WebAdapter.class)
            .getAccount(account.getAccountId()))
            .withSelfRel();

        model.add(link);

        return model;
    }
}