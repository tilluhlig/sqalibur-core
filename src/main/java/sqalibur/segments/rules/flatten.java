/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.segments.rules;

import org.jdom.Document;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.treeUtilities;
import treeNormalizer.utils.xsltProcessor;

/**
 * diese Regel glättet Elemente, sodass also beispielsweise die Darstellung der
 * Assoziativität von AND Operatoren entfernt wird
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class flatten extends rule {

    @Override
    public boolean perform(transformation context) {
        xsltProcessor processor = new xsltProcessor();
        processor.setSource(context.getTree());
        
        // die benötigten Regeln werden geladen
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/wurzel.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/glaetten.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/kopieren.xsl");

        // ab hier wird der XSLT-Prozessor ausgeführt
        int oldDocument = treeUtilities.getDocumentHash(context.getTree());
        Document result = processor.transform();
        int newDocument = treeUtilities.getDocumentHash(result);
        
        // das Ergebnis der Umformung muss wieder zurückgeschrieben werden
        context.setTree(result);
        return oldDocument != newDocument;
    }

}
