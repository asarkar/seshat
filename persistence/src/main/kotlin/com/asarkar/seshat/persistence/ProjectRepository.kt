package com.asarkar.seshat.persistence

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ProjectRepository : CrudRepository<Project, Long>