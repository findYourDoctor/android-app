//package com.abcd.findyourdoctor.messaging.entity
//
//import android.os.Parcel
//import android.os.Parcelable
//
//
//data class SecondUserData(
//    var name : String? = "",
//    var id : String? = "",
//    var imageUrl : String? = "",
//    var timestamp : Long = 0,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readLong()
//    ) {
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        dest?.writeString(name)
//        dest?.writeString(id)
//        dest?.writeString(imageUrl)
//    }
//
//    companion object CREATOR : Parcelable.Creator<SecondUserData> {
//        override fun createFromParcel(parcel: Parcel): SecondUserData {
//            return SecondUserData(parcel)
//        }
//
//        override fun newArray(size: Int): Array<SecondUserData?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
