package com.myrran.cleanarchitecture.configuration;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.AccountService;
import com.myrran.cleanarchitecture.account.application.ports.AccountDAO;
import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import com.myrran.cleanarchitecture.common.Hasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeans
{
    @Bean
    public Hasher getHasher()
    {   return new Hasher(); }

    @Bean
    public AccountServiceI getAccountService(AccountDAO accountDAO)
    {   return new AccountService(accountDAO); }
}