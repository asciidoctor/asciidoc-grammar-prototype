grammar Asciidoc;

@header {
    package org.asciidoctor.parser;
}

// TODO replace with actual AsciiDoc grammar!
r  : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;
