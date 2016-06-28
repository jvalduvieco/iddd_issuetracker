package com.saasovation.issuetracker.domain.model.product.release

import java.util.*

import com.saasovation.issuetracker.domain.model.product.*
import com.saasovation.issuetracker.domain.model.tenant.TenantId

class Release(tenantId: TenantId, productId: ProductId, releaseId: ReleaseId, name: String, description: String) {

    private var defectDensityHistory: List<DefectDensity>? = null
    private var description: String? = null
    private var name: String? = null
    private var preferredMeasurements: PreferredMeasurements? = null
    private var productId: ProductId? = null
    private var releaseId: ReleaseId? = null
    private var tenantId: TenantId? = null

    init {

        this.setDefectDensityHistory(ArrayList<DefectDensity>(0))
        this.setDescription(description)
        this.setName(name)
        this.setProductId(productId)
        this.setReleaseId(releaseId)
        this.setTenantId(tenantId)
    }

    fun defectDensityHistory(): Collection<DefectDensity> {
        return Collections.unmodifiableList(defectDensityHistory!!)
    }

    fun description(): String {
        return this.description
    }

    fun name(): String {
        return this.name
    }

    fun preferredMeasurements(): PreferredMeasurements {
        return this.preferredMeasurements
    }

    fun productId(): ProductId {
        return this.productId
    }

    fun recordPreferredMeasurements(preferredMeasurements: PreferredMeasurements) {
        this.setPreferredMeasurements(preferredMeasurements)
    }

    fun tenantId(): TenantId {
        return this.tenantId
    }

    fun releaseId(): ReleaseId {
        return this.releaseId
    }

    private fun allPreferredMeasurements(): List<Measurement> {
        val measurements = ArrayList<Measurement>()

        if (this.preferredMeasurements().backlogItemsMeasurement() != null) {
            measurements.add(this.preferredMeasurements().backlogItemsMeasurement())
        } else if (this.preferredMeasurements().classesMeasurement() != null) {
            measurements.add(this.preferredMeasurements().classesMeasurement())
        } else if (this.preferredMeasurements().klocMeasurement() != null) {
            measurements.add(this.preferredMeasurements().klocMeasurement())
        }

        return measurements
    }

    private fun setDefectDensityHistory(defectDensityHistory: List<DefectDensity>) {
        this.defectDensityHistory = defectDensityHistory
    }

    private fun setDescription(description: String) {
        this.description = description
    }

    private fun setName(name: String) {
        this.name = name
    }

    private fun setProductId(productId: ProductId) {
        this.productId = productId
    }

    private fun setPreferredMeasurements(preferredMeasurements: PreferredMeasurements) {
        this.preferredMeasurements = preferredMeasurements
    }

    private fun setReleaseId(releaseId: ReleaseId) {
        this.releaseId = releaseId
    }

    private fun setTenantId(tenantId: TenantId) {
        this.tenantId = tenantId
    }
}
