package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public class LastActivities
{
    private final List<Activity> activities = new ArrayList<>();

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public LastActivities(List<Activity>collection)
    {   addActivities(collection); }

    // BUSINESS:
    //--------------------------------------------------------------------------------------------------------

    public void addActivities(List<Activity>collection)
    {   activities.addAll(collection); }

    public void addActivity(Activity activity)
    {   activities.add(activity); }

    public List<Activity>getActivities()
    {   return Collections.unmodifiableList(activities); }

    // CALCULATIONS:
    //--------------------------------------------------------------------------------------------------------

    public Money calculateBalance(AccountId accountId)
    {
        Money depositBalance = activities.stream()
            .filter(a -> a.getTargetAccountId().equals(accountId))
            .map(Activity::getMoney)
            .reduce(Money.ofZero(), Money::plus);

        Money withdrawBalance = activities.stream()
            .filter(a -> a.getSourceAccountId().equals(accountId))
            .map(Activity::getMoney)
            .reduce(Money.ofZero(), Money::plus);

        return depositBalance.minus(withdrawBalance);
    }

    public LocalDateTime getOldestActivity()
    {
        return activities.stream()
            .min(Comparator.comparing(Activity::getTimestamp))
            .map(Activity::getTimestamp)
            .orElse(LocalDateTime.now());
    }

    public LocalDateTime getNewestActivity()
    {
        return activities.stream()
            .max(Comparator.comparing(Activity::getTimestamp))
            .map(Activity::getTimestamp)
            .orElse(LocalDateTime.now());
    }
}