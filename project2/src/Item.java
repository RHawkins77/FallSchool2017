import java.util.ArrayList;
import java.util.BitSet;

public class Item {
	private String itemName;
	private int itemSize;
	private int totalItems;
	private BitSet itemMask;
	private BitSet itemConstraintsMask;
	private ArrayList<String> constraintsArray;
	private String[] itemInputLine;

	
	public Item(String name, int size, int itemCount, String[] constraintsString, int itemCountMask) {
		this.itemName = name;
		this.itemSize = size;
		this.totalItems = itemCount;
		this.itemMask = new BitSet(itemCount);
		this.constraintsArray =  new ArrayList<String>();
		this.itemConstraintsMask = new BitSet(itemCount);
		this.itemInputLine = constraintsString;
		itemMask.set(itemCountMask);
	}
	
	public void setMaskBit(int maskItemNumber) {
		itemMask.set(maskItemNumber);
	}
	
	public void flipAllBits() {
		int size = itemSize;
		itemConstraintsMask.flip(0,size);
	}

	public void setConstraintsMask() {
		itemConstraintsMask.set(0);
	}
	
	public BitSet getConstraintsMask() {
		return itemConstraintsMask;
	}
	
	public int getSize() {
		return itemSize;
	}
	
	public String getName() {
		return itemName;
	}
	public BitSet getMask() {
		return itemMask;
	}
	
	public String[] getConstraintsArray() {
		return itemInputLine;
	}
	
}
