package com.group.netflixserverapi.services;

import com.group.netflixserverapi.models.Subscriber;

public interface SubscriberService {
    Subscriber create(Subscriber subscriber) throws Exception;
}
