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
	
	public List<Item> searchStorageOnArtNumber(int artNumber){
		List<Item> items = new ArrayList<Item>();
		for(Item item : storage) {
			if(item.getArtNumber() == artNumber) {
				if(!items.contains(item)) {
					items.add(item);
				}
			}
		}
		return items;
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
		String noNumbers = searchString.replaceAll("[0-9]+", "");
		String[] splitString = noNumbers.split(" ");
		for(Item item : storage) {
			
			if(item.getDescription().toLowerCase().matches("(.*)"+searchString.toLowerCase()+"(.*)")) {
				if(!items.contains(item)) {
					items.add(item);
				}
			}
			if((!noNumbers.equals(""))&& item.getDescription().toLowerCase().matches("(.*)"+noNumbers.toLowerCase()+"(.*)")) {
				if(!items.contains(item)) {
					items.add(item);
				}		
			}
			for(String words : splitString) {
				if(!words.equals("") && item.getDescription().toLowerCase().matches("(.*)"+words.toLowerCase()+"(.*)")) {
					if(!items.contains(item)) {
						items.add(item);
					}		
				}
			}
			for(Integer integer : foundIntegers) {
				if(item.getArtNumber() == integer) {
					if(!items.contains(item)) {
						items.add(item);
					}
				}
			}	
		}
		return items;		
	}
}
