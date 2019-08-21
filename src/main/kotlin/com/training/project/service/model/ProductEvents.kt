package com.training.project.service.model

import java.util.UUID


interface ProductEvent

data class ProductRegistered(val productId: UUID, val name: String, val currency: Currency, val amount: Double): ProductEvent
data class ProductDeregistered(val productId: UUID): ProductEvent
data class ProductPriceIncreased(val productId: UUID, val newPrice: Double, val oldPrice: Double)
data class ProductPriceDecreased(val productId: UUID, val newPrice: Double, val oldPrice: Double)
data class ProductRenamed(val productId: UUID, val newName: Double, val oldName: Double)




