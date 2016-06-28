package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.DomainEventSubscriber
import com.saasovation.issuetracker.domain.model.product.issue.IssueReported
import com.saasovation.issuetracker.domain.model.product.issue.IssueType
import java.util.*

class DefectSeverityCalculator() : DomainEventSubscriber<IssueReported> {
    private val defectsByProduct: HashMap<ProductId, Int> = hashMapOf()

    override fun handleEvent(aDomainEvent: IssueReported) {
        if (aDomainEvent.type == IssueType.Defect)
            defectsByProduct[aDomainEvent.productId] = defectsByProduct.getOrDefault(aDomainEvent.productId, 0) + 1
    }

    fun defectsByProduct(productId: ProductId): Int {
        return defectsByProduct[productId] ?: throw IllegalArgumentException()
    }

    override fun subscribedToEventType(): Class<IssueReported> {
        return IssueReported::class.java
    }

}