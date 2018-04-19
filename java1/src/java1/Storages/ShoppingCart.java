package java1.Storages;

import java.util.Optional;

import java1.Item;

public class ShoppingCart extends Storage<Item> {
	
	public String checkOut() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n----Receipt----");
		builder.append(toString());
		builder.append("\n---------------");
		builder.append("\nTotal cost: "+getCostOfShoppingCart());
		storage.clear();
		return builder.toString();
	}
	
	private double getCostOfShoppingCart() {
		return storage.stream().mapToDouble(item -> item.getPrice()).sum();
	}
	
	public Optional<Item> removeItemByArtNumber(int artNumber) {
		for(Item item : storage) {
			if(item.getArtNumber() == artNumber) {
				return Optional.of(removeObject(item));
			}
		}
		return Optional.empty();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Item item : storage) {
			builder.append(item.toString());
		}
		return builder.toString();
	}
	
	public boolean isEmpty() {
		return storage.isEmpty();
	}
}
