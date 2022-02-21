package Models;


import java.io.Serializable;
import java.util.UUID;

public class Professor extends Person implements Serializable {
    // variables
    private Subject subject;

    // constructors
    public Professor(UUID uuid, String firstName, String lastName, int yearOfBirth, Subject subject) {
        super(uuid, firstName, lastName, yearOfBirth);
        this.subject = subject;
    }
    public Professor(Professor original) {
        super(UUID.randomUUID(), original.firstName, original.lastName, original.yearOfBirth);
        this.subject = subject;
    }

    //methods
    @Override
    public String toString() {
        return "Professor{" +
                "subject=" + subject +
                ", uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }


    // getters and setters
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }


}
