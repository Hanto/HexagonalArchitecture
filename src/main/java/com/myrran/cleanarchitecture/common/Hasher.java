package com.myrran.cleanarchitecture.common;// Created by jhant on 17/05/2022.

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hasher
{
    public int orderIndependentHash(String...values)
    {
        Set<String>set = new HashSet<>(Arrays.asList(values));

        return set.hashCode();
    }

    public int orderIndependentHash(List<String> values)
    {   return orderIndependentHash(values.toArray(new String[0])); }
}