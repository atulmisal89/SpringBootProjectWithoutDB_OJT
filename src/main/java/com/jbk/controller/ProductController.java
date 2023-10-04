package com.jbk.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exceptions.ProductAlreadyExistsException;
import com.jbk.exceptions.ResourceNotExistsExceptions;
import com.jbk.exceptions.SupplierAlreadyExistsException;
import com.jbk.model.FinalProduct;
import com.jbk.model.Product;
import com.jbk.model.Supplier;
import com.jbk.service.ProductService;
import com.jbk.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	@Autowired
	private ProductService service;
	
	@PostMapping("/add-product")
	public String addProduct(@RequestBody @Valid Product product) 
	{
		int status = service.addProduct(product);

		if (status == 1) 
		{
			return "product Added !!";
		}
		else
			if (status == 2) 
			{
				throw new ProductAlreadyExistsException("product Already Exists");
			}
			else 
			{
				return "Invalid Data";
			}
	}
	
	@GetMapping("/get-product-by-id/{pid}")
    public ResponseEntity<?> getProductById(@PathVariable long pid)
	{
        Product product = service.getProductById(pid);
        
        System.out.println(product);

        if (product == null) 
        {
            // Return a 404 Not Found response with a custom message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Product ID");
        }

        // Return a 200 OK response with the product
        return ResponseEntity.ok(product);
    }
	
	@GetMapping("/get-final-product-by-id/{pid}")
    public ResponseEntity<?> getFinalProductById(@PathVariable long pid)
	{
        FinalProduct finalproduct = service.getFinalProductById(pid);
        
        System.out.println(finalproduct);

        if (finalproduct == null) 
        {
            // Return a 404 Not Found response with a custom message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Product ID");
        }

        // Return a 200 OK response with the product
        return ResponseEntity.ok(finalproduct);
    }

	@GetMapping("/get-all-product")
	public List<Product> getAllProduct()
	{
		List<Product> list = service.getAllProduct();

		if (!list.isEmpty())
		{
			return list;
		}
		else
		{
			throw new ResourceNotExistsExceptions("Product Not Exists");
		}
		
	}
	
	@DeleteMapping("/delete-product-by-id")
	public List<Product> deleteProduct(@RequestParam long productId)
	{
		Object obj = service.deleteProduct(productId);

		if(obj instanceof Integer)
		{
			int i=(int)obj;
			if(i==1)
			{
				throw new ResourceNotExistsExceptions("product not exits");
			}
			else
			{
				throw new ResourceNotExistsExceptions("List is Empty");
			}
		}
		else			
		{
			return (List<Product>) obj;
		}
	
	}
	
	@PutMapping("/update-product")
	public Product updateProduct(@RequestBody @Valid Product clientProduct)
	{
		Product product = service.updateProduct(clientProduct);
		
		if (product != null) 
		{
			return product;
		}
		else 
		{
			throw new ResourceNotExistsExceptions("Product Not Exists With Id = " + clientProduct.getProductId());
		}
		
	}
}
