/**
 * 
 */
package it.diegiu.purchaseapp.data;

/**
 * @author Diego Giudici
 *
 */
public class Item {
	
	private String name;
	private double price;
	private int category;
	private int quantity;
	private boolean imported;
	
	private double salesTax;
	
	public Item() {
		
	}
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, double price, int category) {
		this(name, price);
		this.category = category;
	}
	
	public Item(String name, double price, int category, boolean imported) {
		this(name, price);
		this.category = category;
		this.imported = imported;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the imported
	 */
	public boolean isImported() {
		return imported;
	}

	/**
	 * @param imported the imported to set
	 */
	public void setImported(boolean imported) {
		this.imported = imported;
	}

	/**
	 * @return the salesTax
	 */
	public double getSalesTax() {
		return salesTax;
	}

	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

}
