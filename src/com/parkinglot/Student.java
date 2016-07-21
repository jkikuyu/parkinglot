
package com.parkinglot;

/**
 *
 * @author Jude Kikuyu
 */
public class Student extends Person 
{
    private int regnumber;
    private int marks;
    private int parkingFees;
    private String grade;
    //Constructor
    public Student(String name,String number,String email,String street,String city,String state,String postalCode,
            String country,int regnumber,int marks)
    {
    super(name,number,email,street,city,state,postalCode,country);
            this.regnumber=regnumber;
            this.marks=marks;
            System.out.println("STUDENT REGISTRATION NUMBER:\n"+regnumber);
    }
    public int purchaseParking(int time)
    {
        int fees;
    fees= time*20;
    System.out.println("You Parking Fee is KES "+fees+"/-");
    return parkingFees;
    }
    public String getGrade(){
        if(marks>=70 && marks <=100)
            System.out.println("You scored grade: A");
        else if(marks >=60 && marks<70)
            System.out.println("You scored grade: B");
        else if(marks>=50 && marks < 60)
            System.out.println("You scored grade: C");
        else if(marks>=40 &&marks< 50)
            System.out.println("You scored grade: D");
        else if(marks>=0 &&marks < 40)
            System.out.println("You scored grade: E");
        else
            System.err.println("You have entered an invalid mark!");
        return grade;
    }
    public int getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(int regnumber) {
        this.regnumber = regnumber;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getParkingFees() {
        return parkingFees;
    }

    public void setParkingFees(int parkingFees) {
        this.parkingFees = parkingFees;
    }
}
      
    
    


