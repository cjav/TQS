/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ico
 */
public class KeyEntryTest {
    private KeyEntry entryA, entryEmpty;
    
    public KeyEntryTest() {
    }
    
    @Before
    public void setUp() {
        entryA = new KeyEntry();
        entryA.setApplicationName("appx");
        entryA.setUsername("xx");
        entryA.setPassword("secret@@@");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testgetPassword(){
        String pass = "secret@@@";
        assertEquals(pass, entryA.getPassword());
    }
    
    @Test
    public void testsetPassword(){
        String password = "secret@@@";
        entryA.setPassword(password);
        assertEquals(password, entryA.getPassword());
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void testsetPasswordWithNull() {
        entryA.setPassword( null);
    }
   
 

    @Test
    public void testgetUsername(){
        String user = "xx";
        assertEquals(user, entryA.getUsername());
    }
    
    @Test
    public void testsetUsername(){
        String username = "xx";
        entryA.setUsername(username);
        assertEquals(username, entryA.getUsername());
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void testsetUsernameWithNull() {
        entryA.setUsername( null);
    }
    
    @Test
    public void testgetApplicationName(){
        String appName = "appx";
        assertEquals(appName, entryA.getApplicationName());
    }
    
    @Test
    public void testsetApplicationName(){
        String applicationName = "appx";
        entryA.setPassword(applicationName);
        assertEquals(applicationName, entryA.getApplicationName());
    }
    
    @Test( expected = IllegalArgumentException.class)
    public void testSetApplicationNameWithNull() {
        entryA.setApplicationName( null);
    }
    
    @Test
    public void testKey() {
        // the key is the application name
        assertEquals("failed to get existing key field", entryA.getApplicationName(), "appx");
    }

    @Test
    public void testFormatAsCsv() {
        String expects = "appx" + KeyEntry.FIELDS_DELIMITER + "xx" + KeyEntry.FIELDS_DELIMITER + "secret@@@";
        assertEquals("failed to format entry to delimited string", entryA.formatAsCsv(), expects);
                   
    }

    @Test
    public void testToString() {
        String inputString = "appx\txx\tsecret@@@";
        assertEquals(inputString, entryA.toString());
        
    }

    @Test
    public void testParse() {
        String inputTest = "App;username;password";
        KeyEntry keyEntryb = entryA.parse(inputTest);
        KeyEntry test = new KeyEntry();
        test.setApplicationName("App");
        test.setUsername("username");
        test.setPassword("password");
        assertEquals(keyEntryb.toString(), test.toString());
    }
    
    
    
    
    
    

}
