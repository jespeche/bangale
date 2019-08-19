package com.training.project.service.implementation

import com.training.project.service.ProductManagement
import com.training.project.service.model.Price
import com.training.project.service.model.Product
import com.training.project.service.model.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID
import javax.transaction.Transactional

@Service
@Transactional
class ProductImplementation(private val repository: ProductRepository) : ProductManagement {

    /**
     * Function to find product based on product Id.
     */
    override fun product(productId: UUID): Product = repository.findById(productId).get()

    /**
     * Function to find all products. It returns a list of all products.
     */
    override fun products(): List<Product> = repository.findAll().toList()

    /**
     * Function to register the product.
     */
    override fun registerProduct(name: String, price: Price): Product = repository.save(Product(name, price))

    /**
     * Function to deregister the product.
     */
    override fun deregisterProduct(productId: UUID) = repository.deleteById(productId)

    /**
     * Function to increase the price of the given product by the given percentage.
     */
    override fun increasePrice(productId: UUID, percentage: Int) {
        var foundProduct = product(productId)
        foundProduct.increasePrice(productId, percentage)
        repository.save(foundProduct)

        //ToDo() Mutation function for optimization
    }

    /**
     * Function to decrease the price of the given product by the given percentage.
     */
    override fun decreasePrice(productId: UUID, percentage: Int) {
        var foundProduct = product(productId)
        foundProduct.decreasePrice(productId, percentage)
        repository.save(foundProduct)

        //ToDo() Mutation function for optimization
    }

    /**
     * Function to rename the given product.
     */
    override fun renameProduct(productId: UUID, name: String) {
        var foundProduct = product(productId)
        foundProduct.renameProduct(productId, name)
        repository.save(foundProduct)

        //ToDo() Mutation function for optimization
    }

/*
    override fun toString(): String {
        return productId + " : " + name + " : " + price
    }

 */

}