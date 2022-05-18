package com.myrran.cleanarchitecture.configuration;// Created by jhant on 17/05/2022.

import com.myrran.cleanarchitecture.common.Hasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeans
{
    @Bean
    public Hasher getHasher()
    {   return new Hasher(); }
}