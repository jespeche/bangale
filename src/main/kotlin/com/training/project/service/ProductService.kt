package com.training.project.service

import com.training.project.service.model.Price
import com.training.project.service.model.Product
import java.util.UUID

/**
 * Interface to manage products and its attributes
 */
interface ProductManagement {
    fun product(productId: UUID): Product

    fun products(): List<Product>

    fun registerProduct(name: String, price: Price): Product

    fun deregisterProduct(productId: UUID)

    fun increasePrice(productId: UUID, percentage: Int)

    fun decreasePrice(productId: UUID, percentage: Int)

    fun renameProduct(productId: UUID, name: String)

}