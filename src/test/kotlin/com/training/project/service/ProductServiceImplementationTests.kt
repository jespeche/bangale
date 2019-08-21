package com.training.project.service

import com.training.project.service.model.Currency.DOLLAR
import com.training.project.service.model.Currency.PESOS
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.IllegalArgumentException

@SpringBootTest
class ProductServiceImplementationTests(@Autowired val service: ProductService) {

    @Test
    fun `Check registration of Product`() {
        val product = service.registerProduct("Pen", PESOS, 50.0)
        assertThat(service.product(product.productId)).isNotNull
    }

    @Test
    fun `Check attributes of registered products`() {
        val product = service.run { registerProduct("Pencil", DOLLAR, 5.0) }
        assertThat(service.product(product.productId).name).isEqualTo("Pencil")
        assertThat(service.product(product.productId).price.currency).isEqualTo(DOLLAR)
        assertThat(service.product(product.productId).price.amount).isEqualTo(5.0)
    }

    @Test
    fun `Check registration of product and it exists in the products list`() {
        val product = service.registerProduct("Pen", PESOS, 50.0)
        assertThat(service.products().contains(product)).isTrue()
    }

    @Test
    fun `Check deregistration of Product`() {
        val registeredProduct = service.registerProduct("Pen", PESOS, 50.0)
        service.deregisterProduct(registeredProduct.productId)
        assertThrows<NoSuchElementException> { service.product(registeredProduct.productId) }
    }

    @Test
    fun `Check set price`() {
        val product = service.registerProduct("Pen", PESOS, 50.0)
        service.setPrice(product.productId, PESOS, 100.0)
        assertThat(service.product(product.productId).price.amount).isEqualTo(100.0)
    }

    @Test
    fun `Check negative price for set price`() {
        val product = service.registerProduct("Pen", PESOS, 50.0)
        assertThrows<IllegalArgumentException> { service.setPrice(product.productId, PESOS, -100.0)}
    }

    @Test
    fun `Check increase price`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        service.increasePrice(product.productId, 50.0)
        assertThat(service.product(product.productId).price.amount).isEqualTo(150.0)
    }

    @Test
    fun `Check negative percentage for increase price`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        assertThrows<IllegalArgumentException> {  service.increasePrice(product.productId, -50.0)}
    }

    @Test
    fun `Check decrease price`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        service.decreasePrice(product.productId, 50.0)
        assertThat(service.product(product.productId).price.amount).isEqualTo(50.0)
    }

    @Test
    fun `Check negative percentage for decrease price`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        assertThrows<IllegalArgumentException> {  service.decreasePrice(product.productId, -50.0)}
    }

    @Test
    fun `Check rename`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        service.renameProduct(product.productId, "Mouse")
        assertThat(service.product(product.productId).name).isEqualTo("Mouse")
    }

    @Test
    fun `Check invalid empty value rename`() {
        val product = service.registerProduct("Laptop", DOLLAR, 100.0)
        assertThrows<IllegalArgumentException> { service.renameProduct(product.productId, "")}
    }
}
