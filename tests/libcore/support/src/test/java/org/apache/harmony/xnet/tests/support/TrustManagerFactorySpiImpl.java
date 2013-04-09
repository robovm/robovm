package org.apache.harmony.xnet.tests.support;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;

public class TrustManagerFactorySpiImpl extends MyTrustManagerFactorySpi {

    private static boolean isengineInitCalled = false;
    private static boolean isEngineGetTrustManagersCalled = false;
    private static KeyStore ks = null;
    private static ManagerFactoryParameters spec = null;

    public void engineInit(KeyStore ks) throws KeyStoreException {
        isengineInitCalled = true;
        this.ks = ks;
    }

    public void engineInit(ManagerFactoryParameters spec) throws InvalidAlgorithmParameterException {
        isengineInitCalled = true;
        this.spec = spec;
    }

    public TrustManager[] engineGetTrustManagers() {
        isEngineGetTrustManagersCalled = true;
        return null;
    }

    public void reset() {
        isengineInitCalled = false;
        isEngineGetTrustManagersCalled = false;
    }

    public boolean isEngineGetTrustManagersCalled() {
        return isEngineGetTrustManagersCalled;
    }

    public boolean isEngineInitCalled() {
        return isengineInitCalled;
    }

    public Object getKs() {
        return ks;
    }

    public Object getSpec() {
        return spec;
    }
}
