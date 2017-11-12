import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> fileLines = new ArrayList<String>();
		ArrayList<Item> groceryItemsList = new ArrayList<Item>();
		ArrayList<Bag> ourBagsList = new ArrayList<Bag>();
		if (checkIfValid(fileLines) == true) {
			fileLines = fileParseReturn(args);
			groceryItemsList = createItemsArray(fileLines);
			ourBagsList = createBagsArray(fileLines);
			addItemConstraints(groceryItemsList);
			arrangeByCardinality(groceryItemsList);
			bagOurItems(groceryItemsList, ourBagsList);
		} else {
			System.out.println("failure!");
		}
	}
	
	
	
	public static void arrangeByCardinality(ArrayList<Item> groceryItemsList) {
		for(int i = 0; i < groceryItemsList.size();i++) {
			for(int j = 0; j < groceryItemsList.size();j++) {
				if(groceryItemsList.get(i).getMask().cardinality() > groceryItemsList.get(j).getMask().cardinality()) {
					Collections.swap(groceryItemsList, i, j);
					System.out.println("we got in here");
				}
			}
		}
	}
	
	
	
	
	public static void bagOurItems(ArrayList<Item> groceryItemsList, ArrayList<Bag> ourUnusedBagsList) {
		ArrayList<Bag> ourUsedBagsList = new ArrayList<Bag>();
		for(int i = 0; i < ourUnusedBagsList.size();i++) {
			for(int j= 0; j< ourUnusedBagsList.size();j++) {
				Collections.swap(ourUnusedBagsList, i, j);
			}
		}
		
		
	}

	public static ArrayList<Bag> createBagsArray(ArrayList<String> inputFile) {
		ArrayList<Bag> ourBagsList = new ArrayList<Bag>();
		int bagsAvailable = Integer.parseInt(inputFile.get(0));
		int currBagNumber = 1;
		int maxBagSize = Integer.parseInt(inputFile.get(1));
		while (currBagNumber <= bagsAvailable) {
			Bag ourBag = new Bag(currBagNumber, maxBagSize);
			//System.out.println(ourBag.getWeight());
			ourBagsList.add(ourBag);
			currBagNumber++;
		}
		return ourBagsList;
	}

	public static void addItemConstraints(ArrayList<Item> groceryItemsList) {
		String plus = "+";
		String minus = "-";
		for (Item itemObject : groceryItemsList) {
			String[] temp = itemObject.getConstraintsArray();
			if (temp.length == 2) {
				// it goes with everything! no constraints added
				itemObject.flipAllBits();
			} else if(temp.length < 2){
				System.out.println("failure");
			}else {
				int i = 0;
				for (int j = 0; j < temp.length; j++) {
					if (i >= 2) {
						if (i == 2 && temp[j].contains(plus)) {
							for (int k = j + 1; k < temp.length; k++) {
								for (Item searchingFor : groceryItemsList) {
									if (temp[k].contains(searchingFor.getName())) {
										// OR together 00000 OR 10001 =  gets us 10001
										itemObject.getConstraintsMask().or(searchingFor.getMask());
										break;
									}
								}
							}
						} else if (i == 2 && temp[j].contains(minus)) {
							for (int k = j + 1; k < temp.length; k++) {
								for (Item searchingFor : groceryItemsList) {
									if (temp[k].contains(searchingFor.getName())) {
										// OR together 00000 OR 10001 =  gets us 10001
										//we set all of our bits first
										itemObject.getConstraintsMask().or(searchingFor.getMask());
										break;
									}
								}
							}
							//at this point we know we set all bits and need to flip all bits
							itemObject.flipAllBits();
						} else if (i == 2) {
							// Something in the item list does not have proper contents
							//System.out.println("failure");
						}
					}
					i++;
					// System.out.println(eachLine);
				}
			}
		}

	}

	public static boolean checkIfValid(ArrayList<String> fileLines) {
		// return 1 if pass else print and return 0
		if (fileLines.size() == 2) {
			// tested and works
			// fails due to no items to bag
			// DO NOT PRINT ANYTHING BUT FAILURE
			System.out.println("failure");
			return false;
		}
		if (fileLines.size() == 3) {
			// tested and works
			// only has one item to bag, just check if it will fit
			String[] words = fileLines.get(2).split("\\s+");
			if (words.length <= 1) {
				System.out.println("failure");
				return false;
			}
			int fileSizeInt = Integer.parseInt(fileLines.get(1));
			int wordsSizeInt = Integer.parseInt(words[1]);
			if (wordsSizeInt > fileSizeInt) {
				// item is too big to bag
				System.out.println("failure");
				return false;
			} else {
				// if its not too big to bag &&
				// its the only item bag it and return
				System.out.println("success");
				System.out.println(words[0]);
				return true;
			}
		}
		return true;
	}

	public static ArrayList<Item> createItemsArray(ArrayList<String> fileLines) throws FileNotFoundException {
		ArrayList<Item> ourGroceryItemsList = new ArrayList<Item>();
		try {

			int itemCount = fileLines.size() - 2;
			int itemCounter = itemCount + 1;
			int maxBagSize = Integer.parseInt(fileLines.get(1));

			int lineCounter = 0;
			int itemMaskNumber = 0;
			while (itemCounter >= 0 && lineCounter <= itemCount + 2) {
				int itemSize;
				String itemName;
				String[] words = fileLines.get(lineCounter).split(" ");
				// how many bags we can fill
				int numOfBags = Integer.parseInt(fileLines.get(0));
				// how big the bag sizes are
				int bagSize = Integer.parseInt(fileLines.get(1));
				// TODO: find new way to get into here this is stopping at the last 3 objects
				if (lineCounter > 1 && lineCounter <= itemCount + 1) {
					if (words.length == 2 && lineCounter == 2) {
						// we only have an item to be bagged with a weight.
						// This is tested and make it in here
						itemName = words[0];
						itemSize = Integer.parseInt(words[1]);
					} else if (words.length == 1 && lineCounter > 1) {
						// if we only have 1 item in our words array it means
						// something is missing to tell what the item is or weighs
						System.out.println("failure");
						break;
					} else if (lineCounter > 1 && words.length > 1) {
						// here we know we have correct file syntax.
						// more than 1 item and we need to make our objects
						itemName = words[0];
						itemSize = Integer.parseInt(words[1]);
						if (itemSize > bagSize) {
							System.out.println("failure");
						}
						Item groceryItem = new Item(itemName, itemSize, itemCount, words, itemMaskNumber);
						itemMaskNumber++;
						// System.out.println(groceryItem.getName());
						ourGroceryItemsList.add(groceryItem);
					}
				}

				lineCounter++;
				itemCounter--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ourGroceryItemsList;
	}

	public static ArrayList fileParseReturn(String[] inputFile) throws FileNotFoundException {
		ArrayList arrayOfLines = new ArrayList();
		FileReader fileInput = new FileReader(inputFile[0]);
		BufferedReader fileReader = new BufferedReader(fileInput);
		String isNextLine = null;
		int itemCountTracker = 0;
		try {
			while ((isNextLine = fileReader.readLine()) != null) {
				arrayOfLines.add(isNextLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayOfLines;
	}

}
