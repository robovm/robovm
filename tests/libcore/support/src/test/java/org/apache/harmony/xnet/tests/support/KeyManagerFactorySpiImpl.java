package org.apache.harmony.xnet.tests.support;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.ManagerFactoryParameters;

public class KeyManagerFactorySpiImpl extends MyKeyManagerFactorySpi {

    private boolean isInitialized = false;

    public void engineInit(KeyStore ks, char[] password)
            throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverableKeyException {

        if (ks == null && password == null) {
            throw new NoSuchAlgorithmException();
        }

        if (ks == null) {
            throw new KeyStoreException();
        }

        if (password == null) {
            throw new UnrecoverableKeyException();
        }

        isInitialized = true;
    }

    public void engineInit(ManagerFactoryParameters spec)
            throws InvalidAlgorithmParameterException {
        if (spec == null) {
            throw new InvalidAlgorithmParameterException("Incorrect parameter");
        }
        isInitialized = true;
    }

    public KeyManager[] engineGetKeyManagers() {
        if(!isInitialized)
            throw new IllegalStateException("KeyManagerFactoryImpl is not initialized");
        else
            return null;
    }
}
