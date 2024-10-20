package beans;

import java.io.Serializable;

public class LineItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int quantity;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LineItem() {
		super();
	}
	public LineItem(Product product, int quantity) {

		this.product = product;
		this.quantity = quantity;
	}
}
