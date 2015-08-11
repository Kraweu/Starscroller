package at.kraweu.xmlParser;

import org.w3c.dom.Document;

/**
 * Created by Kraweu on 02.08.2015.
 */
public class MyDocument
{

    Document document;
    String docString;

    public MyDocument(String docString)
    {
        this.docString = docString;
        this.document = XmlParser.getDocument(docString);
        document.getDocumentElement().normalize();
    }

    public Document getDocument()
    {
        return document;
    }

    public String getDocString()
    {
        return docString;
    }

}
