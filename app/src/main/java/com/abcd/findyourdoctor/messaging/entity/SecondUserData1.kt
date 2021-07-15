package com.abcd.findyourdoctor.messaging.entity

import android.os.Parcel
import android.os.Parcelable

open class SecondUserData : Parcelable {
    var name: String? = null
    var id: String? = null
    var imageUrl: String? = null
    var timestamp: Long = 0

    constructor()
    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        id = `in`.readString()
        imageUrl = `in`.readString()
        timestamp = `in`.readLong()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(imageUrl)
        dest.writeLong(timestamp)
    }

    companion object {
        val CREATOR: Parcelable.Creator<SecondUserData?> =
            object : Parcelable.Creator<SecondUserData?> {
                override fun createFromParcel(`in`: Parcel): SecondUserData? {
                    return SecondUserData(`in`)
                }

                override fun newArray(size: Int): Array<SecondUserData?> {
                    return arrayOfNulls(size)
                }
            }
    }
}