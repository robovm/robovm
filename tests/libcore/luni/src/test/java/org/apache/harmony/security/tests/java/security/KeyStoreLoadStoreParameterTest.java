package org.apache.harmony.security.tests.java.security;

import java.security.KeyStore;

public class KeyStoreLoadStoreParameterTest {

    class MyLoadStoreParameter implements KeyStore.LoadStoreParameter {
       public KeyStore.ProtectionParameter getProtectionParameter() {
            return null;
       }
    }



}
