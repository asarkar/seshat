package com.asarkar.seshat.model.junit4

import jakarta.xml.bind.JAXBContext
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory


object TestsuitesUnmarshaller {
    fun unmarshal(input: InputStream): Testsuites {
        val dbf = DocumentBuilderFactory.newInstance()
        dbf.isNamespaceAware = true
        val db = dbf.newDocumentBuilder()
        val doc = db.parse(input)

        return if (doc.firstChild.localName == "testsuites") {
            val jaxbContext = JAXBContext.newInstance(Testsuites::class.java)
            jaxbContext.createUnmarshaller().unmarshal(doc) as Testsuites
        } else {
            val jaxbContext = JAXBContext.newInstance(Testsuite::class.java)
            val testsuite = jaxbContext.createUnmarshaller().unmarshal(doc) as Testsuite
            ObjectFactory().createTestsuites().also {
                it.testsuite = listOf(testsuite)
            }
        }
    }
}