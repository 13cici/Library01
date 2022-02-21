package Models;

import java.io.Serializable;
import java.util.UUID;

public class Student extends Person implements Serializable {
    // variables
    private Subscription subscription;

    // constructors
    public Student(UUID uuid, String firstName, String lastName, int yearOfBirth, Subscription subscription) {
        super(uuid, firstName, lastName, yearOfBirth);
        this.subscription = subscription;
    }
    public Student(Student original) {
        super(UUID.randomUUID(), original.firstName, original.lastName, original.yearOfBirth);
        this.subscription = original.subscription;
    }

    // methods
    public void changeDepartment() {
        if(this.subscription == Subscription.Yes) {
            this.subscription = Subscription.No;
        } else {
            this.subscription = Subscription.Yes;
        }
    }

    // getters and setters
    public Subscription getSubscription() {
        return subscription;
    }
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
