package my.webapp

import io.kotest.core.spec.style.StringSpec

class SampleTest : StringSpec({
    "try println" {
        println("[try println] test executed.")
    }
})
