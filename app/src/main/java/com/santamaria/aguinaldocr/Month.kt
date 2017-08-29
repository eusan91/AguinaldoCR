package com.santamaria.aguinaldocr

import kotlin.properties.Delegates

/**
 * Created by Santamaria on 28/08/2017.
 */

class Month(val month: String) {

    var amount : Double = 0.0

    constructor(month: String, amount: Double) : this(month) {
        this.amount = amount
    }

}