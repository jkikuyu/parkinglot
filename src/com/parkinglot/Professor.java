
package com.parkinglot;
/**
 *
 * @author Jude Kikuyu
 */
public class Professor extends Person{
private double salary;
//Constructor
    public Professor(String name,String number,String email,String street,String city,String state,String postalCode,String country,double salary){
        super(name,number,email,street,city,state,postalCode,country);
        this.salary=salary;
        System.out.println("GROSS SALARY:\nKES. "+salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public int purchaseParking(int time){
        return time*100;
    }
}
