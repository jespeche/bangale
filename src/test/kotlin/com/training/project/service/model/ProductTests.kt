package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import com.training.project.service.model.Currency.RUPEES
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class ProductTests {

    @Test
    fun `Check Product instantiation`() {
        Product().apply {
            assertThat(name).isEmpty()
            assertThat(price).isEqualTo(Price(DOLLAR, 0.0))
        }
    }

    @Test
    fun `Check rename a product`() {
        Product().apply {
            rename("NEW_NAME")
            assertThat(name).isEqualTo("NEW_NAME")
        }
    }

    @Test
    fun `Check product can not be renamed to empty string`() {
        Product().apply {
            assertThrows<IllegalArgumentException> { rename("") }
        }
    }

    @Test
    fun `Check product price increase percentage is greater than zero`(){
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            assertThrows<IllegalArgumentException> { increasePrice(0.0) }
        }
    }

    @Test
    fun `Check product price increased`() {
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            increasePrice(50.0)
            assertThat(price).isEqualTo(Price(DOLLAR, 150.0))
        }
    }

    @Test
    fun `Check product price decrease percentage is greater than zero`(){
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            assertThrows<IllegalArgumentException> { decreasePrice(0.0) }
        }
    }

    @Test
    fun `Check product price decreased`() {
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            decreasePrice(50.0)
            assertThat(price).isEqualTo(Price(DOLLAR, 50.0))
        }
    }

    @Test
    fun `Check set price is greater than zero`() {
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            assertThrows<IllegalArgumentException> { setPrice(RUPEES, 0.0) }
        }
    }

    @Test
    fun `Check set price`() {
        Product("Laptop", Price(DOLLAR, 100.0)).apply{
            setPrice(RUPEES, 50.0)
            assertThat(price).isEqualTo(Price(RUPEES, 50.0))
        }
    }

}
