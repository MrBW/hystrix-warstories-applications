package com.codecentric.hystrix.warstories.shared.dto;

import com.codecentric.hystrix.warstories.shared.enums.AdressType;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class AdressDTO {

    public AdressType adressType;

    public String country;

    public String city;

    public String street;

    public String postcode;

    public String name;

    public AdressType getAdressType() {
        return adressType;
    }

    public void setAdressType(AdressType adressType) {
        this.adressType = adressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
