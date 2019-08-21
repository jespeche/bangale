package com.training.project.service

import com.training.project.service.model.Currency
import com.training.project.service.model.Product
import java.util.UUID

interface ProductService {
    fun product(productId: UUID): Product
    fun products(): List<Product>
    fun registerProduct(name: String, currency: Currency, amount: Double): Product
    fun deregisterProduct(productId: UUID)
    fun setPrice(productId: UUID, currency: Currency, amount: Double)
    fun increasePrice(productId: UUID, percentage: Double)
    fun decreasePrice(productId: UUID, percentage: Double)
    fun renameProduct(productId: UUID, name: String)
}