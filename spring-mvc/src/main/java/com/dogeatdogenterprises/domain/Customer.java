package com.dogeatdogenterprises.domain;

import javax.persistence.*;

/**
 * Created by donaldsmallidge on 9/20/16.
 *
 * 1. first name,
 * last name,
 * email,
 * phone number,
 * address line one,
 * address line two,
 * city,
 * state,
 * zip code.
 * All properties are strings. All should be private and have getters and setters.
 *
 * 2. Add an id value to your customer object.
 * The Id should be an Integer and have a getter and setter.
 */
@Entity
public class Customer implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
//    private String addressLine1;
//    private String addressLine2;
//    private String city;
//    private String state;
//    private String zipCode;
    @Embedded
    private Address billingAddress;

    @Embedded
    private Address shippingAddress;

    @OneToOne
    private User user;

    @Override
    public Integer getId() {

        return id;
    }

    @Override
    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }
/*
    public String getAddressLine1() {

        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {

        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {

        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {

        this.addressLine2 = addressLine2;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getZipCode() {

        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }
*/
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
