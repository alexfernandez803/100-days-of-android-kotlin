package xyz.partaslabs.kitpass.ui.pass

import xyz.partaslabs.kitpass.R
import xyz.partaslabs.kitpass.data.Organization
import xyz.partaslabs.kitpass.data.PassDetails
import xyz.partaslabs.kitpass.data.Metadata

val flightCenter =
    Organization("Jose Alcérreca", "https://medium.com/@JoseAlcerreca")


val pass1 = PassDetails(
    id = "dc523f0ed25c",
    title = "A Little Thing about Android Module Paths",
    subtitle = "How to configure your module paths, instead of using Gradle’s default.",
    url = "https://medium.com/androiddevelopers/gradle-path-configuration-dc523f0ed25c",
    imageId = R.drawable.post_1,
    imageThumbId = R.drawable.post_1_thumb,
    metadata = Metadata(
        organization = flightCenter,
        date = "August 02",
        readTimeMinutes = 1
    ),
)

val pass2 = PassDetails(
    id = "7446d8dfd7dc",
    title = "Dagger in Kotlin: Gotchas and Optimizations",
    subtitle = "Use Dagger in Kotlin! This article includes best practices to optimize your build time and gotchas you might encounter.",
    url = "https://medium.com/androiddevelopers/dagger-in-kotlin-gotchas-and-optimizations-7446d8dfd7dc",
    imageId = R.drawable.post_2,
    imageThumbId = R.drawable.post_2_thumb,
    metadata = Metadata(
        organization = flightCenter,
        date = "August 02",
        readTimeMinutes = 1
    ),
)

val pass3 = PassDetails(
    id = "ac552dcc1741",
    title = "From Java Programming Language to Kotlin — the idiomatic way",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    url = "https://medium.com/androiddevelopers/from-java-programming-language-to-kotlin-the-idiomatic-way-ac552dcc1741",
    imageId = R.drawable.post_3,
    imageThumbId = R.drawable.post_3_thumb,
    metadata = Metadata(
        organization = flightCenter,
        date = "August 02",
        readTimeMinutes = 1
    ),
)

val pass4 = PassDetails(
    id = "84eb677660d9",
    title = "Locale changes and the AndroidViewModel antipattern",
    subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
    url = "https://medium.com/androiddevelopers/locale-changes-and-the-androidviewmodel-antipattern-84eb677660d9",

    imageId = R.drawable.post_4,
    imageThumbId = R.drawable.post_4_thumb,
    metadata = Metadata(
        organization = flightCenter,
        date = "August 02",
        readTimeMinutes = 1
    ),
)

val pass5 = PassDetails(
    id = "55db18283aca",
    title = "Collections and sequences in Kotlin",
    subtitle = "Working with collections is a common task and the Kotlin Standard Library offers many great utility functions. It also offers two ways of…",
    url = "https://medium.com/androiddevelopers/collections-and-sequences-in-kotlin-55db18283aca",
    imageId = R.drawable.post_5,
    imageThumbId = R.drawable.post_5_thumb,
    metadata = Metadata(
        organization = flightCenter,
        date = "August 02",
        readTimeMinutes = 1
    ),
)
val passes: List<PassDetails> = listOf(
    pass1,
    pass2,
    pass3,
    pass4,
    pass5
)