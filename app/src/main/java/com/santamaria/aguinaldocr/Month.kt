package com.santamaria.aguinaldocr

import android.os.Parcel
import android.os.Parcelable
import kotlin.properties.Delegates

/**
 * Created by Santamaria on 28/08/2017.
 */


class Month(var month: String) : Parcelable {

    var amount : Double = 0.0

    constructor(parcel: Parcel) : this(parcel.readString()) {
        amount = parcel.readDouble()
    }

    constructor(month: String, amount: Double) : this(month) {
        this.amount = amount
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(month)
        parcel.writeDouble(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Month> {
        override fun createFromParcel(parcel: Parcel): Month {
            return Month(parcel)
        }

        override fun newArray(size: Int): Array<Month?> {
            return arrayOfNulls(size)
        }
    }

}