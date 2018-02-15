package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.ProductDAO;
import model.Product;

public class ProductBLL {
	
	public Product findProductById(int id) {
		Product p = ProductDAO.findById(id);
		if (p == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return p;
	}
	
	public int insertProduct(Product p) {
		return ProductDAO.insert(p);
	}
	
	public List<Product> findAll() {
		List<Product> products = ProductDAO.findAll();
		return products;
	}
	
	public void deleteProduct(int id) {
		Product p = ProductDAO.findById(id);
		if (p == null) {
			throw new NoSuchElementException("The product with id =" + id + " can not be deleted because it is not in the database!");
		}
		ProductDAO.delete(id);
	}
	
	public void updateProduct(int q, String name, int stock, int id) {
		Product p = ProductDAO.findById(id);
		ProductDAO.update(q, name, stock, id);
	}
}
