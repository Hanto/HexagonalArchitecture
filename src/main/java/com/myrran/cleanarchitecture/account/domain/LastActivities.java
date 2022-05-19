package com.myrran.cleanarchitecture.account.domain;// Created by jhant on 16/05/2022.

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor @ToString
public class LastActivities
{
    private final List<Activity> activities = new ArrayList<>();

    // CONSTRUCTORS:
    //--------------------------------------------------------------------------------------------------------

    public LastActivities(List<Activity>collection)
    {   activities.addAll(collection); }

    public static LastActivities of(List<Activity>collection)
    {   return new LastActivities(collection); }

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