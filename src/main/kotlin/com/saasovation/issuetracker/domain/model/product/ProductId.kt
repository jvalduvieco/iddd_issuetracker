package com.saasovation.issuetracker.domain.model.product

import java.util.*

data class ProductId(val id: UUID = UUID.randomUUID()) {
    constructor (id: String) : this(UUID.fromString(id))
}
