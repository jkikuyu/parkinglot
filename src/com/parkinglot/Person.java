
package com.parkinglot;

/**
 *
 * @author Jude kikuyu
 */
public abstract class Person 
{
    
    private String name,number,email,street,city,state, postalCode, country;

   public Person(String name,String number,String email,String street,String city,String state,String postalCode,String country)
    {
    this.name=name;
    this.number=number;
    this.email=email;
    System.out.println("NAME:\n"+name);
    System.out.println("MOBILE NUMBER:\n"+number);
    System.out.println("EMAIL ADDRESS:\n"+email);
    System.out.println("YOUR ADDRESS IS:\n"+"STREET: "+street+"\nCITY: "+city+"\nSTATE: "+state+"\nPOSTAL CODE: "+postalCode+"\nCOUNTRY: "+country);
    }
    public abstract int purchaseParking(int time);
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

