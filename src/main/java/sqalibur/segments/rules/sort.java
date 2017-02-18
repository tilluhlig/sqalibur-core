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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.treeUtilities;

/**
 * diese Regel sortiert die Kinder von kommutativen Elementen
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class sort extends rule {

    /*
     * hier werden die Muster aufgelistet Label=>Klasse
     */
    private static final Map<String, String> sortPattern = new HashMap<String, String>() {
        {
            put("+", "binop");
            put("*", "binop");
            put("AND", "binop");
            put("OR", "binop");
            put("SELECT", "block");
        }

    };

    @Override
    public boolean perform(transformation context) {
        Document document = context.getTree();
        int oldDocument = treeUtilities.getDocumentHash(document);

        List<Element> elements = treeUtilities.getLeafs(document);

        for (int i = 0; i < elements.size(); i++) {

            Element element = elements.get(i);
            boolean sortMe = false; // soll sortiert werden?

            // nur wer Kinder hat und ein passendes Label besitzt kommt in die engere Auswahl
            if (element.getChildren().size() > 0 && sortPattern.get(element.getAttributeValue("label")) != null) {
                String requiredClass = sortPattern.get(element.getAttributeValue("label"));
                if (element.getAttributeValue("class") == null ? requiredClass == null : element.getAttributeValue("class").equals(requiredClass)) {
                    sortMe = true;
                }
            }

            // wenn sortMe == true, dann soll sortiert werden
            if (sortMe) {
                List<Element> childs = element.removeContent();
                Collections.sort(childs, new Comparator<Element>() {
                    
                    @Override
                    public int compare(Element o1, Element o2) {
                        if (treeUtilities.getElementHash(o1, true) == treeUtilities.getElementHash(o2, true)) {
                            return 0;
                        }
                        return treeUtilities.getElementHash(o1, true) < treeUtilities.getElementHash(o2, true) ? -1 : 1;
                    }
                });

                element.setContent(childs);
            }

            // jetzt bekommt dieses Element seine Signatur
            element.removeAttribute("signature");
            treeUtilities.getElementHashAsString(element, true);

            // nun die Väter noch für die Bearbeitung einfügen
            // (es kann nun einen Vater geben)
            if (!element.isRootElement() && element.getParent().indexOf(element) == 0) {
                elements.add(element.getParentElement());
            }
        }

        context.setTree(document);
        int newDocument = treeUtilities.getDocumentHash(document, true);
        return oldDocument != newDocument;
    }

}
