package com.training.project.service.implementation

import com.training.project.service.ProductService
import com.training.project.service.model.Currency
import com.training.project.service.model.Price
import com.training.project.service.model.Product
import com.training.project.service.model.ProductDeregistered
import com.training.project.service.model.ProductEvent
import com.training.project.service.model.ProductRegistered
import com.training.project.service.model.ProductRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
@Transactional
class ProductServiceImpl(private val repository: ProductRepository, private val publish: ApplicationEventPublisher) : ProductService {

    override fun product(productId: UUID): Product = repository.findById(productId).get()
    override fun products(): List<Product> = repository.findAll().toList()

    override fun registerProduct(name: String, currency: Currency, amount: Double): Product = repository.save(Product(name, Price(currency, amount))).also { publish(ProductRegistered(it.productId, it.name, it.price)) }

    override fun deregisterProduct(productId: UUID) = repository.deleteById(productId).also { publish(ProductDeregistered(productId)) }

    override fun renameProduct(productId: UUID, name: String): Product = mutate(productId) { it.rename(name) }

    /*{
        val foundProduct = product(productId)
        foundProduct.rename(name)
        return repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }*/

    override fun setPrice(productId: UUID, currency: Currency, amount: Double): Product = mutate(productId) { it.setPrice(currency, amount) }

    /*{
        val product = product(productId)
        product.setPrice(currency, amount)
        return repository.save(product)
    }*/

    override fun increasePrice(productId: UUID, percentage: Double): Product = mutate(productId) { it.increasePrice(percentage) }

    /*{
        val foundProduct = product(productId)
        foundProduct.increasePrice(percentage)
        return repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }*/

    override fun decreasePrice(productId: UUID, percentage: Double): Product = mutate(productId) { it.decreasePrice(percentage) }

    /*{
        val foundProduct = product(productId)
        foundProduct.decreasePrice(percentage)
        return repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }*/

    private fun mutate(productId: UUID, mutation: (Product) -> Unit): Product {
        val product = product(productId)
        mutation(product)
        return repository.save(product)
    }

    private fun publish(event: ProductEvent) = publish.publishEvent(event)
}