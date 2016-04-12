/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author catarina
 */
public class KeyChainTest {

    private KeyChain chain;
    private KeyEntry entryA, entryB;

    public KeyChainTest() {
    }

    @Before
    public void setUp() throws IOException {

        entryA = new KeyEntry();
        entryA.setApplicationName("appx");
        entryA.setUsername("xx");
        entryA.setPassword("secret@@@");

       
        entryB = new KeyEntry();
        entryB.setApplicationName("appy");
        entryB.setUsername("yy");
        entryB.setPassword("secret$$$");

        try{
        chain = KeyChain.from(new File("Keychain.txt"), new CipherTool("#wisper"));

        chain.put(entryA);
        chain.put(entryB);
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() throws FileNotFoundException {
        
        File f = new File("ficheiro.txt");
        try (PrintWriter pw = new PrintWriter(f)) {
            pw.println("");
            pw.close();
        }
        

    }

    @Test
    public void testPut() {
        KeyEntry k = new KeyEntry();
        k.setApplicationName("snapchat");
        k.setUsername("catv");
        k.setPassword("123");

        chain.put(k);
        assertEquals(k, chain.find(k.key()));

    }

    @Test
    public void testFind() {
        KeyEntry kEntry = new KeyEntry();
        kEntry.setApplicationName("snapchat");
        kEntry.setUsername("catv");
        kEntry.setPassword("123");
        chain.put(kEntry);
        assertEquals(null, chain.find("xpto"));
    }

    @Test
    public void testSave() {

        KeyChain key1;
        KeyChain key2;

        try {
            key1 = KeyChain.from(new File("ficheiro.txt"), new CipherTool("#wisper"));
            key1.put(entryA);
            key1.save();

            key2 = KeyChain.from(new File("ficheiro.txt"), new CipherTool("#wisper"));

            assertEquals(key2.find(entryA.key()), entryA);
        } catch (IOException ex) {
            Logger.getLogger(KeyChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testallEntries() {

        Iterable<KeyEntry> i = chain.allEntries();
        Iterator it = i.iterator();

        boolean allEntryA = false;
        boolean allEntryB = false;

        while (it.hasNext()) {
            KeyEntry key = (KeyEntry) it.next();

            if (key == entryA) {
                allEntryA = true;
            }

            if (key == entryB) {
                allEntryB = true;
            }
        }
        assertTrue(allEntryA);
        assertTrue(allEntryB);
    }

    @Test
    public void testsortedEntries() {
        Iterable<KeyEntry> sortedE = chain.sortedEntries();
        KeyEntry tmp = null;
        KeyEntryComparator keyComparator = new KeyEntryComparator();
        boolean keyComp = true;

        for (KeyEntry e : sortedE) {
            if (tmp != null && keyComparator.compare(tmp, e) > 0) {
                keyComp = false;
            }
            tmp = e;
        }
        assertTrue(keyComp);
    }

    @Test
    public void testtoString() {
        String output = "pp\t++\tso5b2ur7pv05v91mui\n"
                + "1\t1\tvv6p8s4kddncnna64k\n"
                + "appy\tyy\tsecret$$$\n"
                + "ua2\tico2\tpassx\n"
                + "appx\txx\tsecret@@@\n"
                + "ua\tico\t6rb5rv3n267cjoe5od\n"
                + "zbd\t123\tudhb30ebgtfppks9gb\n"
                + "b1\tb1\tcnr4d9j3q2r1utth1d\n";
        
        assertEquals(output, chain.toString());
    }

}
