package com.group.netflixserverapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.repositories.SubscriberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber create(Subscriber subscriber) throws Exception {
        Optional<Subscriber> newSubscriber = subscriberRepository.findByIdentificationNumber(subscriber.getIdentificationNumber());

        if (newSubscriber.isPresent())
            throw new Exception("This subscriber already exists");
        else if (subscriber.getIdentificationNumber() == null) {
            throw new Exception("identificationNumber cannot be null");
        }

        return subscriberRepository.save(subscriber);
    }
}
