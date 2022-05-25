package com.myrran.cleanarchitecture.configuration;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.account.application.AccountService;
import com.myrran.cleanarchitecture.account.application.ports.AccountDAO;
import com.myrran.cleanarchitecture.account.application.ports.AccountServiceI;
import com.myrran.cleanarchitecture.common.Hasher;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringBeans
{
    @Bean
    public Hasher getHasher()
    {   return new Hasher(); }

    @Bean
    public AccountServiceI getAccountService(AccountDAO accountDAO)
    {   return new AccountService(accountDAO); }

    @Bean @LoadBalanced
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Traverson.getDefaultMessageConverters(MediaTypes.HAL_JSON));
        return restTemplate;
    }
}