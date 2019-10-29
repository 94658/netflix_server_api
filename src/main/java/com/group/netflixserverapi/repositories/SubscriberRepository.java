package com.group.netflixserverapi.repositories;

import com.group.netflixserverapi.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByIdentificationNumber(String identificationNumber);
}
