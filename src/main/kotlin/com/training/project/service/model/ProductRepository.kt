package com.training.project.service.model

import org.springframework.data.repository.CrudRepository
import java.util.UUID

/**
 * Interface to facilitate JPA repository and data storage for products
 */
interface ProductRepository : CrudRepository<Product, UUID>