package java1.Storages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher =  pattern.matcher(searchString);
		List<Integer> foundIntegers = new ArrayList<Integer>();
		while(matcher.find()) {
			int id = Integer.parseInt(matcher.group());
			foundIntegers.add(id);
		}
		for(Item item : storage) {
			if(item.getDescription().toLowerCase().matches("(.*)"+searchString.toLowerCase()+"(.*)")) {
				items.add(item);
			}
			for(Integer integer : foundIntegers) {
				if(item.getArtNumber() == integer) {
					items.add(item);
				}
			}	
		}
		return items;		
	}
}
