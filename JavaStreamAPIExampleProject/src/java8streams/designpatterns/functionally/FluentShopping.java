package java8streams.designpatterns.functionally;

import java8streams.designpatterns.functionally.model.Order;

public class FluentShopping {

	public static void main(String[] args) {
		
				Order.place(order -> order
									.add("shoes")
									.add("Headphones")
									.deliverAt("Street no 45, Jodhpur")
							);
			
		
		
	}

}
