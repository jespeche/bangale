package com.training.project.service.model

import com.google.common.base.Preconditions.checkArgument
import com.training.project.service.model.Currency.DOLLAR
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Product(
        var name: String = "",
        var price: Price = Price(DOLLAR, 0.0),
        @Id @GeneratedValue
        val productId: UUID = UUID.randomUUID()
) {

    fun rename(newName: String) {
        checkArgument(newName.isNotEmpty())
        if (name != newName) {
            name = newName
        }
    }

    fun setPrice(currency: Currency, amount: Double) {
        checkArgument(amount > 0)
        setNewPrice(Price(currency, amount))
    }

    fun increasePrice(percentage: Double) {
        checkArgument(percentage > 0)
        setNewPrice(price.applyPercentage(percentage))
    }

    fun decreasePrice(percentage: Double) {
        checkArgument(percentage > 0)
        setNewPrice(price.applyPercentage(percentage * -1))
    }

    private fun setNewPrice(price: Price) {
        if (this.price != price) {
            this.price = price
            // ToDo: Sunny, please send an event
        }
    }
}
