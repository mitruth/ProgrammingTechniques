package bll;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.Client;
import model.Product;

public class Bill {

	public void generateBill(Product p, Client c, int q) {
		
		String text = " Bill for: " + c.getName() + ".txt";
		try {
			PrintWriter print = new PrintWriter(text);
			print.println("Client name: " + c.getName());
			print.println("Product name: " + p.getProductName());
			print.println("Quantity: " + q);
			print.println();
			print.println("Feel free to visit us again !");
			print.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
