package com.training.project.service

import com.training.project.service.model.Currency.PESOS
import com.training.project.service.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceIntegrationTests(@Autowired val service: ProductService) {

    private lateinit var product: Product

    @BeforeEach
    fun setUp() {
        product = service.registerProduct("Pen", PESOS, 50.0)
    }

    @Test
    fun `Check Product registration`() {
        assertThat(service.product(product.productId)).isNotNull
    }

    @Test
    fun `Check attributes of registered products`() {
        assertThat(service.product(product.productId).equals(product))
    }

    @Test
    fun `Check Product registration and it exists in the products list`() {
        assertThat(service.products().contains(product)).isTrue()
    }

    @Test
    fun `Check Product deregistration`() {
        service.deregisterProduct(product.productId)
        assertThrows<NoSuchElementException> { service.product(product.productId) }
    }
}
