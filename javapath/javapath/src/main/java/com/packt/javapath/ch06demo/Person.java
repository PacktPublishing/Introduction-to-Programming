package com.packt.javapath.ch06demo;

import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public Person(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName == null ? "" : firstName;
        this.lastName = lastName == null ? "" : lastName;
        if(dob == null){
            throw new RuntimeException("Date of birth is null");
        }
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

   @Override
   public boolean equals(Object other) {
       if (other == null) return false;
       if (this == other) return true;
       if (!(other instanceof Person)) return false;
       final Person that = (Person) other;
       return this.getFirstName().equals(that.getFirstName()) &&
               this.getLastName().equals(that.getLastName()) &&
               this.getDob().equals(that.getDob());
   }

}
