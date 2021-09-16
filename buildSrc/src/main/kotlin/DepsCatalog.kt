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

    const val lombok = "org.projectlombok:lombok:${Versions.lombok}"
    const val loggerApi = "org.slf4j:slf4j-api:1.7.32"
    const val loggerImpl = "org.slf4j:slf4j-simple:1.7.32"
    const val picocli = "info.picocli:picocli:4.6.1"
    const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
}
