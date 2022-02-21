package Models;

import java.io.Serializable;

import java.util.Calendar;
import java.util.UUID;

public abstract class Person implements Serializable {
    // variables
    protected UUID uuid;
    protected String firstName;
    protected String lastName;
    protected int yearOfBirth;

    // constructors
    public Person(){};

    public Person(UUID uuid, String firstName, String lastName, int yearOfBirth) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    // getters and setters
    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public int getAge() {
        return  Calendar.getInstance().get(Calendar.YEAR)-yearOfBirth;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
