package org.asciidoctor.parser

import org.asciidoctor.parser.antlr.AntlrParser
import spock.lang.Specification

/**
 * Basic testing template
 * @author abelsromero
 */
class ParserSpec extends Specification {


    def "parses a basic AsciiDoc document"() {
            InputStream adocSource = fromClasspath('simple_document.adoc')
            def tree = AntlrParser.parse(adocSource, true)
        expect:
            tree != null
            AntlrParser.isValid(fromClasspath('simple_document.adoc'))
    }

    /**
     * Helps getting resources from classpath
     * TODO: can be improved
     */
    InputStream fromClasspath (String filename) {
        return this.class.classLoader.getResourceAsStream(filename)
    }
}
