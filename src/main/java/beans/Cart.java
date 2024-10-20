package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<LineItem> items;
	
	public Cart() {
		items = new ArrayList<LineItem>();
	}
	
	public Cart(ArrayList<LineItem> items) {
		super();
		this.items = items;
	}
	
	public ArrayList<LineItem> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<LineItem> items) {
		this.items = items;
	}
	
	public void addItem(LineItem item) {
		
		String code = item.getProduct().getCode();
		for(int i=0;i<items.size();i++)
		{
			LineItem lineItem = items.get(i);
			if(lineItem.getProduct().getCode().equals(code))
			{
				int quantity = lineItem.getQuantity() + item.getQuantity();
				lineItem.setQuantity(quantity);
				return;
			}
		}
		
		items.add(item);
	}

	public void removeItem(LineItem item) {
		String code = item.getProduct().getCode();
		for(int i=0;i<items.size();i++)
		{
			LineItem lineItem = items.get(i);
			if(lineItem.getProduct().getCode().equals(code))
			{
				items.remove(i);
				return;
			}
		}
		
	}

	public void updateItem(LineItem item) {
		String code = item.getProduct().getCode();
		for(int i=0;i<items.size();i++)
		{
			LineItem lineItem = items.get(i);
			if(lineItem.getProduct().getCode().equals(code))
			{
				int quantity = item.getQuantity();
				lineItem.setQuantity(quantity);
				return;
			}
		}
		
	}
	
	public double tinhTongTien(LineItem item)
	{
		double tong=0;
		String code = item.getProduct().getCode();
		for(int i=0;i<items.size();i++)
		{
			LineItem lineItem = items.get(i);
			if(lineItem.getProduct().getCode().equals(code))
			{
				int quantity =item.getQuantity();
				tong = item.getProduct().getPrice() * quantity;
			}
		}
		return tong;
		
	}



}
