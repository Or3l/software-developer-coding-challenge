package app.data.model;

import javax.persistence.Entity;

@Entity
public class Car extends Item {

    public Car(String name, String description) {
        super(name, description);
    }

    public Car() {

    }

}
