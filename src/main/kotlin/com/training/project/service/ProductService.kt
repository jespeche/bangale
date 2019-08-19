package com.training.project.service

import com.training.project.service.model.Currency
import com.training.project.service.model.Price
import com.training.project.service.model.Product
import java.util.UUID

interface ProductService {
    fun product(productId: UUID): Product
    fun products(): List<Product>
    fun registerProduct(name: String, currency: Currency, amount: Int): Product
    fun deregisterProduct(productId: UUID)
    // ToDo: Add set price or similar.
    fun increasePrice(productId: UUID, percentage: Int)
    fun decreasePrice(productId: UUID, percentage: Int)
    fun renameProduct(productId: UUID, name: String)
}