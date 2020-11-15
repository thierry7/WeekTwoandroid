package com.thierryoke.weektwoassignment

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MusicResponse(
    val results : List<MusicItem>
): Parcelable

data class MusicItem(

    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double? = null,
    val previewUrl: String


):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "N/A",
        parcel.readString()?: "N/A",
        parcel.readString()?: "N/A",
        parcel.readDouble()
        ,parcel.readString()?:"N/A"
    )

    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let { parcel -> parcel.writeString(artistName)
        parcel.writeString(collectionName)
        parcel.writeString(artworkUrl60)
        parcel.writeDouble(trackPrice!!)
        parcel.writeString(previewUrl)}
    }

    companion object CREATOR : Parcelable.Creator<MusicItem> {
        override fun createFromParcel(parcel: Parcel): MusicItem {
            return MusicItem(parcel)
        }

        override fun newArray(size: Int): Array<MusicItem?> {
            return arrayOfNulls(size)
        }
    }

}



