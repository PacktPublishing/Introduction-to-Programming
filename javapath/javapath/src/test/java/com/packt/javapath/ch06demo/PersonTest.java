package com.packt.javapath.ch06demo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void equals() {
        LocalDate dob = LocalDate.of(2001, 01, 20);
        LocalDate dob1 = LocalDate.of(2001, 01, 21);

        Person p = new Person("Joe", "Blow", dob);
        assertTrue(p.equals(p));
        assertTrue(p.equals(new Person("Joe", "Blow", dob)));
        //assertTrue(p.equals(new Citizen("Joe", "Blow", dob, "USA")));
        //assertFalse(p.equals(new NonPerson("Joe", "Blow", dob));

        assertFalse(p.equals(new Person("Joe1", "Blow", dob)));
        assertFalse(p.equals(new Person("Joe", "Blow1", dob)));
        assertFalse(p.equals(new Person("Joe", "Blow", dob1)));
        assertFalse(p.equals( new Person("Joe1", "Blow1", dob1)));
       // assertFalse(p.equals(new Citizen("Joe1", "Blow1", dob1, "USA")));


        assertFalse(p.equals(null));
        assertFalse(p.equals(new Person(null, "Blow", dob)));
        assertFalse(p.equals(new Person("Joe", null, dob)));
        assertFalse(p.equals(new Person(null, null, dob)));
        try {
            new Person("Joe", "Blow", null);
        } catch (RuntimeException ex){
            assertNotNull(ex.getMessage());
            //add the record ex.getMessage() to the log here
        }
        try {
            new Citizen("Joe", "Blow", null, "USA");
        } catch (RuntimeException ex){
            assertNotNull(ex.getMessage());
            //add the record ex.getMessage() to the log here
        }

        assertTrue(new Person(null, "Blow", dob)
                .equals(new Person(null, "Blow", dob)));

        assertTrue(new Person("Joe", null, dob)
                .equals(new Person("Joe", null, dob)));

        assertTrue(new Person(null, null, dob)
                .equals(new Person(null, null, dob)));

    }

    class Citizen extends Person {
        private String country;

        public Citizen(String firstName, String lastName, LocalDate dob, String country) {
            super(firstName, lastName, dob);
            this.country = country;
        }

        public String getCountry() {
            return country;
        }
    }

    class NonPerson {
        private String firstName;
        private String lastName;
        private LocalDate dob;

        public NonPerson(String firstName, String lastName, LocalDate dob) {
            this.firstName = firstName;
            this.lastName = lastName;
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

    }


}