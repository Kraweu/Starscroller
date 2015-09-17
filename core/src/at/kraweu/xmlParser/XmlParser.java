package at.kraweu.xmlParser;

// Simple API for XML only allows you to read from an XML
// file. SAX doesn't require you to read the document into memory

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// Used to read an XML file into memory where the doc is
// stored as a bunch of objects. You can write back to the file
// Used to gather XML elements into DOM objects

/**
 * Copied by Kraweu on 01.08.2015.
 */

public class XmlParser
{

    // Reads an XML file into a DOM document

    public static Document getDocument(String docString)
    {

        try
        {

            // API used to convert XML into a DOM object tree

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Ignore all of the comments

            factory.setIgnoringComments(true);

            // Ignore white space in elements

            factory.setIgnoringElementContentWhitespace(true);

            // Validate the XML as it is parsed

            factory.setValidating(false);

            // Provides access to the documents data

            DocumentBuilder builder = factory.newDocumentBuilder();

            // Takes the document

            return builder.parse(new InputSource(docString));

        } catch (Exception ex)
        {

            System.out.println(ex.getMessage());

        }

        return null;
    }
}