package br.com.dwd.sapataria.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.dwd.sapataria.models.Product;	
import br.com.dwd.sapataria.daos.CategoryDao;	
import br.com.dwd.sapataria.models.Category;	
import br.com.dwd.sapataria.daos.ProductDao;	


@Model
@Transactional
public class ProductController {
	
	@Inject
	private ProductDao productDao;
	private Product product = new Product();
	private List<Product> productList = new ArrayList<>();
	
	@Inject
	private CategoryDao categoryDao;
	private List<Category> categoryList = new ArrayList<>();
	
	private Integer idToEdit;
	
	public Integer getIdToEdit() {
		return idToEdit;
	}

	public void setIdToEdit(Integer idToEdit) {
		this.idToEdit = idToEdit;
	}
	
	public List<Product> getProductList(){
		return this.productList;
	}
	
	public List<Category> getCategoryList(){
		return this.categoryList;
	}	
		
	public void setProduct(Product product){
		this.product = product;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	@PostConstruct
	private void postConstruct() {
		productList.addAll(productDao.all());
		categoryList.addAll(categoryDao.all());
		product.setCategory(new Category());
	}	
	
	public void loadDetails(){
		this.product = productDao.findById(idToEdit);
	}
	
	public String save() {
		productDao.save(product);
		return "/product/list?faces-redirect=true";
	}

	public String update(Integer id) {
		product.setId(id);
		productDao.update(product);
		return "/product/list?faces-redirect=true";
	}

	public String remove(Integer id) {
		Product product = productDao.findById(id);
		productDao.remove(product);
		return "/product/list?faces-redirect=true";
	}		
	
	

}
