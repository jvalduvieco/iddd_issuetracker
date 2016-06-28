package com.saasovation.issuetracker.domain.model

import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.product.issue.IssueAssignedToExistingBacklogItem
import com.saasovation.issuetracker.domain.model.product.issue.IssueId
import com.saasovation.issuetracker.domain.model.product.issue.IssueType
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import org.junit.Assert.assertTrue
import org.junit.Test

class Subscriber() : DomainEventSubscriber<IssueAssignedToExistingBacklogItem> {
    var called: Boolean = false
    override fun handleEvent(aDomainEvent: IssueAssignedToExistingBacklogItem) {
        called = true
    }

    override fun subscribedToEventType(): Class<IssueAssignedToExistingBacklogItem> {
        return IssueAssignedToExistingBacklogItem::class.java
    }

}

class DomainEventPublisherTest {
    @Test
    fun iCanPublishAnEvent() {
        var tenantId: TenantId = TenantId()
        var productId: ProductId = ProductId()
        var issueId: IssueId = IssueId()
        var aSubscriber: Subscriber = Subscriber()
        DomainEventPublisher.subscribe(aSubscriber)

        DomainEventPublisher.publish(
                IssueAssignedToExistingBacklogItem(tenantId, productId, issueId, IssueType.Defect, "www"))

        assertTrue(aSubscriber.called)
    }
}