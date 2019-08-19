package com.training.project.service.implementation

import com.training.project.service.ProductService
import com.training.project.service.model.Currency
import com.training.project.service.model.Price
import com.training.project.service.model.Product
import com.training.project.service.model.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
@Transactional
class ProductImpl(private val repository: ProductRepository) : ProductService {

    override fun product(productId: UUID): Product = repository.findById(productId).get()
    override fun products(): List<Product> = repository.findAll().toList()
    override fun registerProduct(name: String, currency: Currency, amount: Int): Product = repository.save(Product(name, Price(currency, amount)))
    override fun deregisterProduct(productId: UUID) = repository.deleteById(productId)
    override fun increasePrice(productId: UUID, percentage: Int) {
        val foundProduct = product(productId)
        foundProduct.increasePrice(percentage)
        repository.save(foundProduct)

        //ToDo() Mutation function for optimization
    }

    override fun decreasePrice(productId: UUID, percentage: Int) {
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
}