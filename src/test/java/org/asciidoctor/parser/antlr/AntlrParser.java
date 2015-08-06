package org.asciidoctor.parser.antlr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.asciidoctor.parser.AsciidocLexer;
import org.asciidoctor.parser.AsciidocParser;
import org.asciidoctor.parser.exception.AsciidoctorParsingException;

/**
 * Utils class to ease parsing files with Antlr4. <br>
 * Mainly used to ease testing.
 * 
 * @author abelsromero
 */
public class AntlrParser {

    /**
     * Parses a file and prints some information
     * 
     * @param text
     *            String containing AsciiDoc text
     *            
     * @return Parsing resulting tree
     */
    public static ParseTree parse(String text, boolean printTokens) {
        InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        return parse(stream, printTokens);
    }

    /**
     * Parses a file and prints some information
     * 
     * @param text
     *            A Stream containing and AsciiDoc source
     * @param printTokens
     *            Prints fount token to the standard output
     * @return Parsing resulting tree
     */
    public static ParseTree parse(InputStream text, boolean printTokens) {
        ANTLRInputStream is;
        try {
            is = new ANTLRInputStream(text);
        } catch (IOException e) {
            throw new AsciidoctorParsingException(e);
        }
        AsciidocLexer lexer = new AsciidocLexer(is);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        AsciidocParser parser = new AsciidocParser(tokens);
        ParseTree tree = parser.document();
        System.out.println("Errors: " + parser.getNumberOfSyntaxErrors());
        System.out.println("Tree {\n    " + tree.toStringTree(parser) + "\n}");
        
        if (printTokens) {
            TokenStream s = parser.getTokenStream();
            System.out.println("Tokens (found " + s.size() + ") {");
            for (int i = 0; i < s.size(); i++) {
                System.out.println("    " + s.get(i));
            }
            System.out.println("}");
        }

        return tree;
    }

    /**
     * Returns true if the text is 100% valid and contains no errors
     * 
     * @param text
     *            String containing AsciiDoc text
     * @return true if the text is 100% valid and contains no errors
     */
    public static boolean isValid(String text) {
        InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        return isValid(stream);
    }

    /**
     * Returns true if the text is 100% valid and contains no errors
     * 
     * @param text
     *            A Stream containing and AsciiDoc source
     * @return true if the text is 100% valid and contains no errors
     */
    public static boolean isValid(InputStream text) {
        ANTLRInputStream is;
        try {
            is = new ANTLRInputStream(text);
        } catch (IOException e) {
            throw new AsciidoctorParsingException(e);
        }
        AsciidocLexer lexer = new AsciidocLexer(is);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        AsciidocParser parser = new AsciidocParser(tokens);
        parser.document();
        return (parser.getNumberOfSyntaxErrors() == 0);
    }

}
