/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.segments.rules;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.treeUtilities;

/**
 * testet die knf-Klasse
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class knfTest {

    /**
     * der Konstruktor
     */
    public knfTest() {
        // kein Inhalt
    }

    /**
     * Test of perform method, of class knf.
     */
    @Test
    public void testPerform() {
    }

    private void run(String input, String expected) throws JDOMException {
        try {
            String content = input;
            InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            Document document = new SAXBuilder().build(stream);
            transformation b = new transformation();
            b.setTree(document);
            rule a = new knf();
            a.perform(b);

            assertEquals(expected, treeUtilities.getDocumentHashAsString(b.getTree()));
        } catch (IOException ex) {
            Logger.getLogger(flattenTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
