package java1.Storages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java1.Item;

public class ItemStorage extends Storage<Item> {
	
	public Optional<Item> removeItemByArtNumber(int artNumber) {
		for(Item item : storage) {
			if(item.getArtNumber() == artNumber) {
				return Optional.of(removeObject(item));
			}
		}
		return Optional.empty();
	}
	
	public List<Item> searchStorage(String searchString){
		List<Item> items = new ArrayList<Item>();
		for(Item item : storage) {
			if(item.getDescription().toLowerCase().matches("(.*)"+searchString.toLowerCase()+"(.*)")) {
				items.add(item);
			}
		}
		return items;		
	}
}
