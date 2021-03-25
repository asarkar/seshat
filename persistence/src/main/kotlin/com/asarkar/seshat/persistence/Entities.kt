package com.asarkar.seshat.persistence

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "project", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Project(
    @Id
    @GeneratedValue
    // Mutable because the DB has to set it
    var id: Long = 0,
    val name: String,
    @OneToMany
    val testSuites: List<TestSuites>
)

@Entity
@Table(name = "test_suites")
data class TestSuites(
    @Id
    @GeneratedValue
    // Mutable because the DB has to set it
    var id: Long = 0,
    val name: String? = null,
    val tests: Int = 0,
    val failures: Int = 0,
    val errors: Int = 0,
    val disabled: Int = 0,
    @OneToMany
    val testSuites: List<TestSuite>
)

@Entity
@Table(name = "test_suite")
data class TestSuite(
    @Id
    @GeneratedValue
    // Mutable because the DB has to set it
    var id: Long = 0,
    val name: String,
    val tests: Int,
    val failures: Int = 0,
    val errors: Int = 0,
    val disabled: Int = 0,
    val `package`: String? = null,
    @OneToMany
    val testCases: List<TestCase>
)

@Entity
@Table(name = "test_case")
data class TestCase(
    @Id
    @GeneratedValue
    // Mutable because the DB has to set it
    var id: Long = 0,
    val name: String,
    val `class`: String? = null,
    val status: String? = null
)