package com.saasovation.issuetracker.domain.model.product.release

import com.saasovation.issuetracker.domain.model.product.SeverityWeights
import java.util.*

class DefectDensity(val date: Date,
                    val indexFigure: Double,
                    val measurement: Measurement,
                    val weights: SeverityWeights)
