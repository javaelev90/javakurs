package java1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import java1.Storages.ItemStorage;
import java1.Storages.ShoppingCart;

public class Program {

	public static void main(String[] args) {
		
		ShoppingCart cart = new ShoppingCart();
		ItemStorage storage = new ItemStorage();	
		
		int artnr = 1;
		double price = 20.00;
		String description = "An apple";
		Item apple = new Item(artnr, price, description);
		
		artnr = 2;
		price = 10.50;
		description = "A ball";
		Item ball = new Item(artnr, price, description);
		
		artnr = 3;
		price = 9.50;
		description = "A Yellow-ball";
		Item yellowball = new Item(artnr, price, description);
		
		artnr = 4;
		price = 11.00;
		description = "A Baseballracket";
		Item baseballracket = new Item(artnr, price, description);
		
		storage.addObject(yellowball);
		storage.addObject(ball);
		storage.addObject(apple);
		storage.addObject(baseballracket);
		
		try(InputHandler handler = new InputHandler(new InputStreamReader(System.in))){
			boolean wantToExit = false;
			
			while(!wantToExit) {
				printMainMenu();
				char choice = handler.getChoiceInput();
				switch(choice) {
				
				case '1':
					System.out.println("Add an item to shopping cart by entering artNumber: ");
					Optional<Integer> artNumber = handler.getArtNumberInput();
					if(!artNumber.isPresent()) {
						break;
					}
					Optional<Item> storedItem = storage.removeItemByArtNumber(artNumber.get());
					if(!storedItem.isPresent()) {
						System.out.format("\nAn item with artNumber %d does not exits.", artNumber.get());
						break;
					}
					cart.addObject(storedItem.get());
					break;
				case '2':
					System.out.println("Remove an item from shopping cart by entering artNumber: ");
					artNumber = handler.getArtNumberInput();
					if(!artNumber.isPresent()) {
						break;
					}
					storedItem = cart.removeItemByArtNumber(artNumber.get());
					if(!storedItem.isPresent()) {
						System.out.format("\nAn item with artNumber %d does not exits.", artNumber.get());
						break;
					}
					storage.addObject(storedItem.get());
					break;
				case '3':
					System.out.println(cart.toString());
					break;
				case '4':
					System.out.println("Input search string to search storage(leave empty for everything)");
					String searchString = handler.getTextInput();
					List<Item> items = storage.searchStorage(searchString);
					if(items.isEmpty()) {
						System.out.println("The search yielded no results.");
						break;
					}
					items.forEach(foundItem -> System.out.println(foundItem.toString()));
					break;
				case '5':
					System.out.println(cart.checkOut());
					break;
				case '6':
					wantToExit = true;
					break;
				default:
					break;
								
				}		
			}	
		} catch (IOException e) {
			System.out.println("An IOException was caught, when reading from System.in");
		}

	}
	
	public static void printMainMenu() {
		System.out.println("\n----Main Menu----");
		System.out.println("1. Add item to shopping cart");
		System.out.println("2. Remove item from shopping cart");
		System.out.println("3. Show shopping cart");
		System.out.println("4. Search for items in storage");
		System.out.println("5. Check out");
		System.out.println("6. Exit");
	}
	

}
