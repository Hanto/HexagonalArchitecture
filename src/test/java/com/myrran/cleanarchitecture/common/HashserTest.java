package com.myrran.cleanarchitecture.common;// Created by jhant on 17/05/2022.

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HashserTest
{
    @Test
    public void orderIndependentHashTest()
    {
        Hasher hasher = new Hasher();

        List<String> elemements = Arrays.asList("Ivan", "Johana", "Pingüito", "Pollo", "Phreak", "sourceAccount", "targetAccount");
        int expected = hasher.orderIndependentHash(elemements);

        for (int i=0; i<50; i++)
        {
            Collections.shuffle(elemements);
            int result = hasher.orderIndependentHash(elemements);
            assertThat(result).isEqualTo(expected);
        }
    }
}