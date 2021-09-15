const val kotlinVersion = "1.4.31"
const val hiltVersion = "2.33-beta"
/**
 * This object gathers dependencies declarations in one place.
 */
object DepsCatalog {
    private object Versions {
        const val junit = "5.6.2"
        const val lombok = "1.18.20"
    }
    const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"

    const val lombok = "org.projectlombok:lombok:${Versions.lombok}"

    const val picocli = "info.picocli:picocli:4.6.1"
}
