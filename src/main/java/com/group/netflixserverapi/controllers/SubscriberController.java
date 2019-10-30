package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.repositories.SubscriberRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "subscribers")
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;

    public SubscriberController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    /**
     * To register new Subscribers
     * @param subscriber
     * @return String
     */
    @PostMapping
    public String register(@RequestBody Subscriber subscriber) {
        Optional<Subscriber> newSubscriber = subscriberRepository.findByIdentificationNumber(subscriber.getIdentificationNumber());
        if (newSubscriber.isPresent())
            return "Subscriber:" + subscriber.getIdentificationNumber() + " already registered.Cannot register again";
        else {
            if(subscriber.getIdentificationNumber()==null){
                return "Identification number can not be null.Check identificationNumber: " + subscriber.getIdentificationNumber() ;
            }
            else{
                subscriberRepository.save(subscriber);
                return "Subscriber:" + subscriber.getIdentificationNumber() + " saved";
            }
        }
    }
}
