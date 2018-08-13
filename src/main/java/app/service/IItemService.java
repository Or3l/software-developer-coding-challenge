package app.service;

import app.data.model.Car;
import app.data.model.Item;

public interface IItemService {
    Item saveCar(Car car);
    Item findCarById(long id);
}
