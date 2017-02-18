/*
 * Copyright (C) 2017 Till Uhlig <till.uhlig@student.uni-halle.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sqalibur.segments.rules;

import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.xsltProcessor;
import org.jdom.Document;
import treeNormalizer.utils.treeUtilities;

/**
 * diese Regel erzeugt die konjunktive Normalform (unsortiert)
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class knf extends rule {

    @Override
    public boolean perform(transformation context) {
        xsltProcessor processor = new xsltProcessor();
        processor.setSource(context.getTree());
        //  processor.setSourceByResource("com/sqalibur/xsltrules/examples/sample.xml");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/wurzel.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/deMorgan.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/doppelnegation.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/distributivgesetz.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/saeubern.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/glaetten.xsl");
        processor.addResourceToScript("com/sqalibur/xsltrules/rules/kopieren.xsl");

        int oldDocument = treeUtilities.getDocumentHash(context.getTree());
        Document result = processor.transform();
        int newDocument = treeUtilities.getDocumentHash(result);
        context.setTree(result);
        return oldDocument != newDocument;
    }

}
