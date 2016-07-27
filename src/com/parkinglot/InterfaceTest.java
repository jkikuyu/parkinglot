
package com.parkinglot;

/**
 *
 * @author Jude Kikuyu
 */
public class InterfaceTest 
{
    
    public static void main(String args[])
    {
        Student s1 = new Student("John Kamau","+254756634468","john.kamau@strathmore.edu","Moi Avenue","Nairobi","Nairobi",
                "P.O. BOX 85931-00200","KENYA",89563,95);
        s1.getGrade();
        s1.purchaseParking(12); 
               
        Professor p1=new Professor("Joe Doe","+2544345125645","joe.doe@strathmore.edu","Loita Street","Nairobi","Nairobi","P.O. BOX 21567-00100","KENYA",200000);
        p1.purchaseParking(12); 
    }
}

