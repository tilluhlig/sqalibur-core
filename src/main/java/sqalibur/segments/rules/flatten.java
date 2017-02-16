/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.segments.rules;

import org.jdom.Document;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.treeUsage;
import treeNormalizer.utils.xsltProcessor;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class flatten extends rule {

    @Override
    public boolean perform(transformation context) {
        xsltProcessor processor = new xsltProcessor();
        processor.setSource(context.getTree());
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/wurzel.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/glaetten.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/kopieren.xsl");
        
        int oldDocument = treeUsage.getDocumentHash(context.getTree());
        Document result = processor.transform();
        int newDocument = treeUsage.getDocumentHash(result);
        context.setTree(result);
        return oldDocument!=newDocument;
    }

}
