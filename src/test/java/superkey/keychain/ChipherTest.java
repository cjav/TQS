/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superkey.keychain;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author catarina
 */
public class ChipherTest {
    
    private KeyEntry ke;
    
    public ChipherTest() {
    }
    
    @Before
    public void setUp() {
        
        ke = new KeyEntry();
        ke.setApplicationName("app1");
        ke.setUsername("xx1");
        ke.setPassword("secret1"); 
    }
     
    @Test
    public void testWProtectedKeychain() throws IOException{
        KeyChain kc = KeyChain.from(new File("Keychain.txt"), new CipherTool("#wisper"));
        kc.put(ke);
        
        assertEquals(ke, kc.find(ke.key()));
    }
    
    @Test
    public void testRProtectedKeychain() throws IOException{
        KeyChain kc = KeyChain.from(new File("Keychain.txt"), new CipherTool("#wispere"));
    }
   
}
