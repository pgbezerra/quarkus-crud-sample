package com.pgbezerra.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pgbezerra.dto.ProductInsertDTO;
import com.pgbezerra.entity.Product;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	@GET
	public List<Product> findAll() {
		return Product.listAll();
	}

	@GET
	@Path(value = "/{id}")
	public Product findById(@PathParam("id") Long id) {
		Optional<Product> productOptional = Product.findByIdOptional(id);

		if (productOptional.isPresent())
			return productOptional.get();
		
		throw new NotFoundException("Product " + id + " not found");
	}

	@POST
	@Transactional
	public void insert(ProductInsertDTO productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setValue(productDto.getValue());
		product.persist();
	}

	@PUT
	@Transactional
	@Path(value = "/{id}")
	public void update(@PathParam("id") Long id, ProductInsertDTO productDto) {
		Optional<Product> productOptional = Product.findByIdOptional(id);

		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setName(productDto.getName());
			product.setValue(productDto.getValue());
			product.persist();
			return;
		}

		throw new NotFoundException("Product " + id + " not found");
	}
	
	@DELETE
	@Transactional
	@Path(value = "/{id}")
	public void deleteById(@PathParam("id") Long id) {
		Optional<Product> productOptional = Product.findByIdOptional(id);

		productOptional.ifPresentOrElse(Product::delete, () -> { throw new NotFoundException("Product " + id + " not found");});
	}

}
