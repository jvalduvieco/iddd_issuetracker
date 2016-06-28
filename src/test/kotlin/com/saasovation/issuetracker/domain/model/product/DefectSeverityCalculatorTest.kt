package com.saasovation.issuetracker.domain.model.product

import com.saasovation.issuetracker.domain.model.DomainEventPublisher
import com.saasovation.issuetracker.domain.model.product.issue.IssueId
import com.saasovation.issuetracker.domain.model.product.issue.Severity
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import com.saasovation.issuetracker.port.adapter.FeedDefectSeverityCalculator
import org.junit.Assert.assertEquals
import org.junit.Test

class DefectSeverityCalculatorTest {
    val tenantId: TenantId = TenantId()

    fun createProduct(productId: ProductId): Product {
        return Product(tenantId,
                productId,
                "A product name",
                "A product description",
                ProductManagerId(),
                IssueAssigner())
    }

    @Test
    fun DefectTotalsAreCalculated() {
        val aProduct: Product = createProduct(ProductId())
        val anotherProduct: Product = createProduct(ProductId())
        val defectCalculator: DefectSeverityCalculator = DefectSeverityCalculator()
        val feedDefectSeverityCalculator: FeedDefectSeverityCalculator = FeedDefectSeverityCalculator(defectCalculator)

        DomainEventPublisher.subscribe(feedDefectSeverityCalculator)
        aProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.Low)
        aProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.High)
        aProduct.featureRequest(IssueId(), "a desc", "a summ", Severity.Medium)
        aProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.Medium)
        aProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.Low)
        aProduct.featureRequest(IssueId(), "a desc", "a summ", Severity.Medium)
        anotherProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.Low)
        anotherProduct.reportDefect(IssueId(), "a Description", "a summary", Severity.High)

        assertEquals(4, defectCalculator.defectsByProduct(aProduct.productId))
        assertEquals(2, defectCalculator.defectsByProduct(anotherProduct.productId))
    }
}