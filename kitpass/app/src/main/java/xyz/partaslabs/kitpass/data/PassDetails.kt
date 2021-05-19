package xyz.partaslabs.kitpass.data

import androidx.annotation.DrawableRes

class PassDetails (
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val metadata: Metadata,
    @DrawableRes
    val imageId: Int,
    @DrawableRes
    val imageThumbId: Int
)

data class Metadata(
    val organization: Organization,
    val date: String,
    val readTimeMinutes: Int
)

data class Organization(
    val name: String,
    val url: String? = null
)

