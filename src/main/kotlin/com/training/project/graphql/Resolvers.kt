package com.training.project.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.training.project.service.ProductService
import com.training.project.service.model.Currency
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductQueryResolver(private val service: ProductService) : GraphQLQueryResolver {
    fun product(productId: UUID) = Product(service.product(productId))
    fun products() = service.products().map { Product(it) }.toList()
}

@Component
class ProductMutationResolver(private val service: ProductService) : GraphQLMutationResolver {
    fun registerProduct(input: RegisterProductInput) = RegisterProductPayload(Product(service.registerProduct(input.name, input.currency, input.amount)))
    fun deregisterProduct(input: DeregisterProductInput) = DeregisterProductPayload(Product(service.product(input.productId).apply { service.deregisterProduct(productId) }))
    fun renameProduct(input: RenameProductInput) = renameProductPayload(Product(service.renameProduct(input.productId, input.name)))
    fun setProductPrice(input: SetProductPriceInput) = setProductPricePayload(Product(service.setPrice(input.productId, input.currency, input.amount)))
    fun increaseProductPrice(input: IncreaseProductPriceInput) = IncreaseProductPricePayload(Product(service.increasePrice(input.productId, input.percentage)))
    fun decreaseProductPrice(input: DecreaseProductPriceInput) = DecreaseProductPricePayload(Product(service.decreasePrice(input.productId, input.percentage)))
}

data class RegisterProductInput(val name: String, val currency: Currency, val amount: Double)
data class RegisterProductPayload(val product: Product)

data class DeregisterProductInput(val productId: UUID)
data class DeregisterProductPayload(val product: Product)

data class IncreaseProductPriceInput(val productId: UUID, val percentage: Double)
data class IncreaseProductPricePayload(val product: Product)

data class DecreaseProductPriceInput(val productId: UUID, val percentage: Double)
data class DecreaseProductPricePayload(val product: Product)

data class SetProductPriceInput(val productId: UUID, val currency: Currency, val amount: Double)
data class setProductPricePayload(val product: Product)

data class RenameProductInput(val productId: UUID, val name: String)
data class renameProductPayload(val product: Product)
