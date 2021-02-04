package com.pgbezerra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pgbezerra.entity.Product;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProductTest {

	@Test
	public void productTestFindAny() {
		Assertions.assertEquals(2, Product.count());
	}
	
}
