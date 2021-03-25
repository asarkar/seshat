package com.asarkar.seshat.persistence

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@MicronautTest(
    propertySources = ["classpath:persistence.yml"],
    transactional = false
)
class ProjectRepositoryTest(private val projectRepository: ProjectRepository) {
    @Test
    fun testCrud() {
        val project = Project(
            name = "project",
            testSuites = listOf(
                TestSuites(testSuites = listOf(
                    TestSuite(name = "testSuite", tests = 1, testCases = listOf(TestCase(name = "testCase")))
                ))
            )
        )
        val saved = projectRepository.save(project)
        assertThat(saved.id).isGreaterThan(0L)
        assertThat(projectRepository.existsById(saved.id)).isTrue

        val testSuites = saved.testSuites
        assertThat(testSuites).hasSize(1)
        val testSuite = testSuites.first()
        assertThat(testSuite.id).isGreaterThan(0L)
        assertThat(testSuite.testSuites).hasSize(1)

        val firstSuite = testSuite.testSuites.first()
        assertThat(firstSuite.id).isGreaterThan(0L)
        assertThat(firstSuite.name).isEqualTo("testSuite")
        assertThat(firstSuite.tests).isEqualTo(1)
        assertThat(firstSuite.testCases).hasSize(1)

        val testCase = firstSuite.testCases.first()
        assertThat(testCase.id).isGreaterThan(0L)
        assertThat(testCase.name).isEqualTo("testCase")
    }
}