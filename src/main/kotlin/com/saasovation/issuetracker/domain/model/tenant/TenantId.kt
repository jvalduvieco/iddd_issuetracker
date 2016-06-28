package com.saasovation.issuetracker.domain.model.tenant

import java.util.*

data class TenantId(val Id: UUID = UUID.randomUUID()) {
    constructor (id: String) : this(UUID.fromString(id))
}
