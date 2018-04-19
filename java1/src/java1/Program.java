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
		
		artnr = 6;
		price = 9.50;
		description = "A Yellow-ball3";
		Item yellowball3 = new Item(artnr, price, description);
		
		artnr = 5;
		price = 11.00;
		description = "A Baseballracket 1";
		Item baseballracket = new Item(artnr, price, description);
		
		artnr = 4;
		price = 11.00;
		description = "A Baseballracket 4";
		Item wacko = new Item(artnr, price, description);
		
		storage.addObject(yellowball);
		storage.addObject(ball);
		storage.addObject(apple);
		storage.addObject(baseballracket);
		storage.addObject(wacko);
		storage.addObject(yellowball3);
		
		try(InputHandler handler = new InputHandler(new InputStreamReader(System.in))){
			boolean wantToExit = false;
			
			while(!wantToExit) {
				printMainMenu();
				char choice = handler.getChoiceInput();
				switch(choice) {
				
				case '1':
					System.out.println("Add an item to your shopping cart by entering artNumber: ");
					Optional<Integer> artNumber = handler.getArtNumberInput();
					if(!artNumber.isPresent()) {
						break;
					}
					Optional<Item> storedItem = storage.removeItemByArtNumber(artNumber.get());
					if(!storedItem.isPresent()) {
						System.out.format("\nAn item with artNumber %d does not exits.", artNumber.get());
						break;
					}
					System.out.println("Added the following object to your shopping cart: ");
					System.out.println(storedItem.get().toString());
					cart.addObject(storedItem.get());
					break;
				case '2':
					System.out.println("Remove an item from your shopping cart by entering artNumber: ");
					artNumber = handler.getArtNumberInput();
					if(!artNumber.isPresent()) {
						break;
					}
					storedItem = cart.removeItemByArtNumber(artNumber.get());
					if(!storedItem.isPresent()) {
						System.out.format("\nAn item with artNumber %d does not exits.", artNumber.get());
						break;
					}
					System.out.println("Removed the following object from your shopping cart: ");
					System.out.println(storedItem.get().toString());
					storage.addObject(storedItem.get());
					break;
				case '3':	
					if(cart.isEmpty()) {
						System.out.println("Shopping cart is empty.");
					} else {
						System.out.println("--Shopping cart items--");
						System.out.println(cart.toString());
					}			
					break;
				case '4':	
					searchMenu(handler, storage);
					break;
				case '5':
					System.out.println(cart.checkOut());
					break;
				case '6':
					wantToExit = true;
					break;
				default:
					System.out.println("Invalid menu option.");
					break;
								
				}		
			}	
		} catch (IOException e) {
			System.out.println("An IOException was caught, when reading from System.in");
		}

	}
	
	public static void searchMenu(InputHandler handler, ItemStorage storage) throws IOException {
		
		boolean wantToExit = false;
		while(!wantToExit) {
			
			printSearchMenu();
			char choice = handler.getChoiceInput();
			switch(choice) {
			
			case '1':
				System.out.println("Input artNumber to look for");
				String searchString = handler.getTextInput();
				try {
					int artNumber = Integer.parseInt(searchString);
					List<Item> items = storage.searchStorageOnArtNumber(artNumber);
					if(items.isEmpty()) {
						System.out.println("The search yielded no results.");
						break;
					}
					items.forEach(foundItem -> System.out.println(foundItem.toString()));
					break;
				} catch(NumberFormatException exception) {
					System.out.println("You have to input an integer.");
					break;
				}	
				
			case '2':
				System.out.println("Input search string to search the storage(leave empty for everything)");
				searchString = handler.getTextInput();
				List<Item> items = storage.searchStorage(searchString);
				if(items.isEmpty()) {
					System.out.println("The search yielded no results.");
					break;
				}
				items.forEach(foundItem -> System.out.println(foundItem.toString()));
				break;
			case '3':
				wantToExit = true;
				break;
			default:
				break;
			}
		}
	}
	
	
	public static void printSearchMenu() {
		System.out.println("\n----Search Menu----");
		System.out.println("1. Search for artNumber");
		System.out.println("2. Search with free text");
		System.out.println("3. Main menu");
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
