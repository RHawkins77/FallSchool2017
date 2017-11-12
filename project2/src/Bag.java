import java.util.ArrayList;

public class Bag {
	private int bagNumber;
	private int bagSize;
	private int currBagWeight;
	private ArrayList<Item> itemsInBag;
	
	public Bag(){
		this.bagNumber = 0;
		this.bagSize = 0;
		this.currBagWeight = 0;
		this.itemsInBag = new ArrayList<Item>();
	}
	
	public Bag(int bagNum, int sizeOfBag) {
		this.bagNumber = bagNum;
		this.bagSize = sizeOfBag;
		this.itemsInBag = new ArrayList<Item>();
	}
	
	public void addWeightToBag(int itemSize) {
		currBagWeight += itemSize;
	}
	
	public void setBagNum(int number) {
		bagNumber = number;
	}
	
	public void addItemToBag(Item object) {
		itemsInBag.add(object);
	}
	
	public int getWeight() {
		return bagSize;
	}
}
