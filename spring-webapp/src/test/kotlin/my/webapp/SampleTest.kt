package my.webapp

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class SampleTest : StringSpec({
    "try println" {
        println("[try println] test executed.")
    }

    "this test fails." {
        val one = 1
        val two = 2

        one shouldBeExactly two
    }

    "this test succeeds" {
        val one = 1

        one shouldBeExactly one
    }
})
