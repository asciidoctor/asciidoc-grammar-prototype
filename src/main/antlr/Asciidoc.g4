grammar Asciidoc;

@header {
package org.asciidoctor.parser;
}

document        : header preamble body ;

// NOTE: "You cannot have a revision line without an author line."
header          : document_title author_line? revision_line? attributes? NEW_LINE  ;

document_title  : '=' sentence NEW_LINE ;
author_line     : CHARS CHARS* email_address* NEW_LINE ;
email_address   :  '<' email '>' ;
email           : CHARS '@' CHARS '.' CHARS ; //

 // based on example v1.0, 2014-01-01
revision_line   : 'v' NUMBERS '.' NUMBERS NEW_LINE ;
attributes      : ':' '!'? atribute_name '!'? ':' atribute_value NEW_LINE ;
atribute_name   : word ;
atribute_value  : ANY_CHAR ;

preamble        : paragraph  ;
paragraph       : phrase+ NEW_LINE;
phrase          : word+ '.'? NEW_LINE? | properties NEW_LINE | predef_attributes NEW_LINE ;

body            : section* ;
section         : section_header section_body ;
section_header  : '=='+ phrase NEW_LINE;
section_body    : phrase+ NEW_LINE ;

inline_macro    : method ':' target '[' params ']' ;
params          : param* ;
method          : 'link' | 'include' | IDENTIFIER ;
target          : IDENTIFIER ;
param           : word ;

// See http://www.regular-expressions.info/email.html for a better representation of an email
sentence        : word* ;
special_characters : '$' | '/' | '-'; // Temporal fix to avoid failing on URLs
attribute_substitution :  '{' preset_attributes '}' ;
// See http://asciidoctor.org/docs/user-manual/#built-in-data-attributes
preset_attributes : 'author' | 'email' | 'backend' | 'doctitle' ;
word            : CHARS PUNCTUATION? | CHARS SPLPUNCTUATION CHARS;
predef_attributes: '{' (word | word special_characters word) '}';
properties       : word+ '::' (predef_attributes | word+) ;

NEW_LINE        : '\n' ;
LINE_BREAK      : '+' ;
NUMBERS          : [0-9]+ ;

CHARS           : [0-9a-zA-Z]+ ;
IDENTIFIER      : [a-zA-Z]+ ;
//BEGIN_PUNCTUATION : [¿¡] ; // Some languages have beginning punctuation (e.g. Spanish)
PUNCTUATION     : [,;?:] ;
SPLPUNCTUATION  : ['_] ;
//word            : ~[\n\t ]+ ;
ANY_CHAR         : ~[\n\t ] ; // PH
WS              : [ \t\r]+ -> skip ;
