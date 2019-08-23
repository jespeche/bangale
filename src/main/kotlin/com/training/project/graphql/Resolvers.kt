/*
package com.training.project.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.training.project.service.model.Currency
import com.training.project.service.model.Currency.*
import com.training.project.service.model.Price
import com.training.project.service.model.Product
import org.springframework.stereotype.Component
import java.util.UUID
import javax.persistence.GeneratedValue
import javax.persistence.Id


val product = Product("Keyboard", Price(RUPEES, 100.0))

@Component
class ProductMutationResolver : GraphQLMutationResolver {
    fun createProduct(name: String, price: Price, productId: UUID) = product
}

@Component
class ProductQueryResolver : GraphQLQueryResolver {
    fun product(productId: UUID) = product
}
*/
