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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.treeUtilities;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class sortTest {
    
    public sortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * leere Eingabe
     */
    @Test(expected=JDOMException.class)
    public void testPerform() throws JDOMException {
        run("",
                "");
    }
    
    @Test
    public void testPerform2() throws JDOMException {
        run("<root><node label=\"AND\" class=\"binop\"><node label=\"1\"/><node label=\"2\"/><node label=\"3\"/></node></root>"
                ,"{root_{node_AND_binop_{node_1}{node_2}{node_3}}}");
    }
    
    @Test
    public void testPerform3() throws JDOMException {
        run("<root><node label=\"AND\" class=\"binop\"><node label=\"3\"/><node label=\"1\"/><node label=\"2\"/></node></root>"
                ,"{root_{node_AND_binop_{node_1}{node_2}{node_3}}}");
    }
    
    @Test
    public void testPerform4() throws JDOMException {
        run("<root><node label=\"AND\" class=\"binop\"><node label=\"1\"/><node label=\"OR\" class=\"binop\"><node label=\"2\"/><node label=\"4\"/></node><node label=\"3\"/></node></root>"
                ,"{root_{node_AND_binop_{node_OR_binop_{node_2}{node_4}}{node_1}{node_3}}}");
    }
    
    private void run(String input, String expected) throws JDOMException{
        try {
            String content = input;
            InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            Document document = new SAXBuilder().build(stream);
            transformation b = new transformation();
            b.setTree(document);
            rule a = new sort();
            a.perform(b);

            assertEquals(expected, treeUtilities.getDocumentHashAsString(b.getTree()));
        } catch (IOException ex) {
            Logger.getLogger(flattenTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
