package com.training.project.service.model

import com.training.project.service.model.Currency.DOLLAR
import java.util.UUID
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Product(
        var name: String = "",
        var price: Price = Price(DOLLAR, 0),
        @Id @GeneratedValue
        val productId: UUID = UUID.randomUUID()
) {

    fun rename(name: String) {
        if (this.name != name) {
            this.name = name
        }
    }

    fun setPrice() = println()

    fun increasePrice(percentage: Int) {
        if (percentage != 0) {
            val amount = price.amount * (1 + percentage / 100)
            price = Price(price.currency, amount)
        }
    }

    fun decreasePrice(percentage: Int) {
        if (percentage != 0) {
            val amount = price.amount * (1 - percentage / 100)
            price = Price(price.currency, amount)
        }
    }
}

@Embeddable
data class Price(val currency: Currency, val amount: Int)

enum class Currency { DOLLAR, PESOS, RUPEES }