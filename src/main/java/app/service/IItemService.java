package app.service;

import app.data.model.Car;
import app.data.model.Item;

import java.util.List;

public interface IItemService {
    Item saveCar(Car car);

    Item findCarById(long id);

    List<Item> getAllItem();
}
