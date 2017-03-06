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
