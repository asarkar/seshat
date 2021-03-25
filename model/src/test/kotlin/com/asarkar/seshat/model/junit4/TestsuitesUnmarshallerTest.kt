package com.asarkar.seshat.model.junit4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TestsuitesUnmarshallerTest {
    @ParameterizedTest
    @ValueSource(strings = ["/testsuites.xml", "/testsuite.xml"])
    fun testUnmarshallTestsuites(filename: String) {
        val testsuites = javaClass.getResourceAsStream(filename).use {
            TestsuitesUnmarshaller.unmarshal(it)
        }
        assertThat(testsuites).isNotNull
        assertThat(testsuites.testsuite).hasSize(1)

        val testsuite = testsuites.testsuite.first()
        assertThat(testsuite.id).isEqualTo("0")
        assertThat(testsuite.errors).isEqualTo("0")
        assertThat(testsuite.failures).isEqualTo("0")
        assertThat(testsuite.tests).isEqualTo("1")
        assertThat(testsuite.name).isEqualTo("my test suite")
        assertThat(testsuite.testcase).hasSize(1)

        val testcase = testsuite.testcase.first()
        assertThat(testcase.classname).isEqualTo("some.class.name")
        assertThat(testcase.name).isEqualTo("Test1")
        assertThat(testcase.time).isEqualTo("123.345000")
    }
}