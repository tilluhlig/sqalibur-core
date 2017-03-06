/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.utils.structures;

import sqalibur.utils.DocumentToJSQL;
import org.jdom.Element;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * testet die DocumentToJSQL-Klasse
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class DocumentToJSQLTest {

    /*
     * der Konstruktor
     */
    public DocumentToJSQLTest() {
        // kein Inhalt
    }

    /**
     * Test of visit method, of class DocumentToJSQL.
     */
    @Test
    public void testVisit_Element() {
        System.out.println("visit");
        Element a = null;
        DocumentToJSQL instance = new DocumentToJSQL();
        Object expResult = null;
        Object result = instance.visit(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
