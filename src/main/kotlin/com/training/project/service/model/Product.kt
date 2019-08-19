package com.training.project.service.model

import java.util.UUID
import javax.persistence.*

/**
 * Model for Product class. It consists of functions to handle products and changes to the attributes of products.
 */
@Entity
data class Product(
        @Column(name="name")
        var name: String = "",
        @Column(name="price")
        var price: Price = Price("USD", 0),
        @Id @GeneratedValue @Column(name="productId")
        val productId: UUID = UUID.randomUUID()
) {
    /**
     * Function to increase the price of the given product by the given percentage. It verifies if it has received valid arguments to consider the proposed change.
     */
    fun increasePrice(productId: UUID, percentage: Int) {
        if(percentage != 0){
            this.price.amount += price.amount * (percentage / 100)
        }
    }

    /**
     * Function to decrease the price of the given product by the given percentage. It verifies if it has received valid arguments to consider the proposed change.
     */
    fun decreasePrice(productId: UUID, percentage: Int) {
        if(percentage != 0){
            this.price.amount -= price.amount * (percentage / 100)
        }
    }

    /**
     * Function to rename the given product. It verifies if it has received valid arguments to consider the proposed change.
     */
    fun renameProduct(productId: UUID, name: String){
        if(this.name != name){
            this.name = name
        }
    }
}

@Entity
data class Price(val currency: String, var amount: Int)