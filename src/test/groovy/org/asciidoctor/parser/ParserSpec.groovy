package org.asciidoctor.parser

import java.nio.charset.StandardCharsets

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.asciidoctor.parser.antlr.AntlrParser;

import spock.lang.Specification

/**
 * 
 *  @author abelsromero
 */
class ParserSpec extends Specification {

    def "parses maven readme"() {
            InputStream adocSource = fromClasspath('asciidoctor-maven-examples.adoc') 
            def tree = AntlrParser.parse(adocSource, true)
        expect:
            tree != null
            AntlrParser.isValid(fromClasspath('asciidoctor-maven-examples.adoc'))
    }

    InputStream fromClasspath (String filename) {
        return this.class.classLoader.getResourceAsStream(filename)
    }
}
