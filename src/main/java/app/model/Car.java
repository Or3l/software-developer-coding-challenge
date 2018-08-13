package app.model;

import java.util.Objects;
import java.util.UUID;

public class Car implements Item {

    private UUID itemId;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car(String name, String description) {
        this.itemId = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }


    public UUID getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(itemId, car.itemId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemId);
    }
}
