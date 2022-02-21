package Models;

import java.io.Serializable;
import java.util.UUID;

public class Buyer extends Person implements Serializable {
    // variables
    private Boolean hasBought;

    // constructors
    public Buyer(UUID uuid, String firstName, String lastName, int yearOfBirth, Boolean hasBought) {
        super(uuid, firstName, lastName, yearOfBirth);
        this.hasBought = hasBought;
    }
    public Buyer(Buyer original) {
        super(UUID.randomUUID(), original.firstName, original.lastName, original.yearOfBirth);
        this.hasBought = original.hasBought;
    }

    // methods
    public void setHasBought() {
        this.hasBought = true;
    }

    public void setNotHasBought() {
        this.hasBought = false;
    }

    // getters and setters
    public Boolean getHasBought() {
        return hasBought;
    }
    public void setHasBought(Boolean bought) {
        hasBought = bought;
    }
}

