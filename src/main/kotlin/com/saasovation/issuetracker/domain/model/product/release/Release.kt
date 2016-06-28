package com.saasovation.issuetracker.domain.model.product.release

import com.saasovation.issuetracker.domain.model.product.ProductId
import com.saasovation.issuetracker.domain.model.tenant.TenantId
import java.util.*

class Release(var tenantId: TenantId,
              var productId: ProductId,
              var releaseId: ReleaseId,
              var name: String,
              var description: String) {

    private var defectDensityHistory: List<DefectDensity> = ArrayList<DefectDensity>(0)
    private var preferredMeasurements: PreferredMeasurements? = null

    fun defectDensityHistory(): Collection<DefectDensity> {
        return Collections.unmodifiableList(defectDensityHistory!!)
    }

    fun recordPreferredMeasurements(preferredMeasurements: PreferredMeasurements) {
        this.preferredMeasurements = preferredMeasurements
    }


    private fun allPreferredMeasurements(): List<Measurement> {
        val measurements = ArrayList<Measurement>()

        /*if (this.preferredMeasurements.backlogItemsMeasurement != null) {
            measurements.add(this.preferredMeasurements.backlogItemsMeasurement)
        } else if (this.preferredMeasurements.classesMeasurement != null) {
            measurements.add(this.preferredMeasurements.classesMeasurement)
        } else if (this.preferredMeasurements.klocMeasurement != null) {
            measurements.add(this.preferredMeasurements.klocMeasurement)
        }*/
        return measurements
    }

    private fun setDefectDensityHistory(defectDensityHistory: List<DefectDensity>) {
        this.defectDensityHistory = defectDensityHistory
    }

    private fun setPreferredMeasurements(preferredMeasurements: PreferredMeasurements) {
        this.preferredMeasurements = preferredMeasurements
    }
}
