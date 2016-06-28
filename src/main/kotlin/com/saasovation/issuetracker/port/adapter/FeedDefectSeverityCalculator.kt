package com.saasovation.issuetracker.port.adapter

import com.saasovation.issuetracker.domain.model.DomainEventSubscriber
import com.saasovation.issuetracker.domain.model.product.DefectSeverityCalculator
import com.saasovation.issuetracker.domain.model.product.issue.IssueReported
import com.saasovation.issuetracker.domain.model.product.issue.IssueType

class FeedDefectSeverityCalculator(val defectCalculator: DefectSeverityCalculator) : DomainEventSubscriber<IssueReported> {

    override fun handleEvent(aDomainEvent: IssueReported) {
        if (aDomainEvent.type == IssueType.Defect)
            defectCalculator.issueReported(aDomainEvent.productId)
    }

    override fun subscribedToEventType(): Class<IssueReported> {
        return IssueReported::class.java
    }

}