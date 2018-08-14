package app.service;

import app.data.model.Car;
import app.data.model.Item;
import app.data.repository.ItemRepository;
import app.exception.CarDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService {


    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveCar(Car car) {
        return itemRepository.save(car);
    }

    @Override
    public Item findCarById(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new CarDoesNotExistException("The car does not exist"));

    }
}
