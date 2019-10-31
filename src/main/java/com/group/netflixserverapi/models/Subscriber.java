package com.group.netflixserverapi.models;

import javax.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "identification_number", unique = true)
    private String identificationNumber;

    @Column(name = "full_name")
    private String fullName;

    public Subscriber() {
    }

    public Subscriber(String identificationNumber, String fullName) {
        this.identificationNumber = identificationNumber;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }


    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", fullname='" + fullName + '\'' +
                '}';
    }
}
