package com.saasovation.issuetracker.domain.model.product.release

data class PreferredMeasurements(
        val klocMeasurement: KLOCMeasurement,
        val classesMeasurement: ClassesMeasurement,
        val backlogItemsMeasurement: BacklogItemsMeasurement)
