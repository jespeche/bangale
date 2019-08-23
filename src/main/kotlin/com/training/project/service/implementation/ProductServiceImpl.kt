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

    override fun setPrice(productId: UUID, currency: Currency, amount: Double) {
        val product = product(productId)
        product.setPrice(currency, amount)
        repository.save(product)
        //ToDo() Mutation function for optimization
    }

    override fun increasePrice(productId: UUID, percentage: Double) {
        val foundProduct = product(productId)
        foundProduct.increasePrice(percentage)
        repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }

    override fun decreasePrice(productId: UUID, percentage: Double) {
        val foundProduct = product(productId)
        foundProduct.decreasePrice(percentage)
        repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }

    override fun renameProduct(productId: UUID, name: String) {
        val foundProduct = product(productId)
        foundProduct.rename(name)
        repository.save(foundProduct)
        //ToDo() Mutation function for optimization
    }

    private fun publish(event: ProductEvent) = publish.publishEvent(event)
}