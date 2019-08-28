package com.training.project.graphql

import com.training.project.service.model.Currency
import com.training.project.service.model.Price as PriceModel
import com.training.project.service.model.Product as ProductModel

class Product(product: ProductModel) {
    val productId = product.productId
    val name = product.name
    val price = Price(product.price)
}

class Price(price: PriceModel) {
    val currency = Currency.valueOf(price.currency.name)
    val amount: Float = price.amount.toFloat()
}

enum class Currency { DOLLAR, PESOS, RUPEES }
