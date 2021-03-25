package com.asarkar.seshat.persistence

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "project", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Project(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "project_id")
    val testSuites: List<TestSuites>
)

@Entity
@Table(name = "test_suites")
data class TestSuites(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String? = null,
    val tests: Int = 0,
    val failures: Int = 0,
    val errors: Int = 0,
    val disabled: Int = 0,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "test_suites_id")
    val testSuites: List<TestSuite>
)

@Entity
@Table(name = "test_suite")
data class TestSuite(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    val tests: Int,
    val failures: Int = 0,
    val errors: Int = 0,
    val disabled: Int = 0,
    val pkg: String? = null,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "test_suite_id")
    val testCases: List<TestCase>
)

@Entity
@Table(name = "test_case")
data class TestCase(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    val classname: String? = null,
    val status: String? = null
)