package org.bouncycastle.jcajce.provider.symmetric;

// BEGIN android-removed
// import org.bouncycastle.crypto.BlockCipher;
// import org.bouncycastle.crypto.CipherKeyGenerator;
// END android-removed
import org.bouncycastle.crypto.engines.TwofishEngine;
// BEGIN android-removed
// import org.bouncycastle.crypto.macs.GMac;
// END android-removed
import org.bouncycastle.crypto.modes.CBCBlockCipher;
// BEGIN android-removed
// import org.bouncycastle.crypto.modes.GCMBlockCipher;
// END android-removed
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
// BEGIN android-removed
// import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
// import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
// import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
// import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
// END android-removed
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;

public final class Twofish
{
    private Twofish()
    {
    }

    // BEGIN android-removed
    // public static class ECB
    //     extends BaseBlockCipher
    // {
    //     public ECB()
    //     {
    //         super(new BlockCipherProvider()
    //         {
    //             public BlockCipher get()
    //             {
    //                 return new TwofishEngine();
    //             }
    //         });
    //     }
    // }
    //
    // public static class KeyGen
    //     extends BaseKeyGenerator
    // {
    //     public KeyGen()
    //     {
    //         super("Twofish", 256, new CipherKeyGenerator());
    //     }
    // }
    //
    // public static class GMAC
    //     extends BaseMac
    // {
    //     public GMAC()
    //     {
    //         super(new GMac(new GCMBlockCipher(new TwofishEngine())));
    //     }
    // }
    // END android-removed

    /**
     * PBEWithSHAAndTwofish-CBC
     */
    static public class PBEWithSHAKeyFactory
        extends PBESecretKeyFactory
    {
        public PBEWithSHAKeyFactory()
        {
            super("PBEwithSHAandTwofish-CBC", null, true, PKCS12, SHA1, 256, 128);
        }
    }

    /**
     * PBEWithSHAAndTwofish-CBC
     */
    static public class PBEWithSHA
        extends BaseBlockCipher
    {
        public PBEWithSHA()
        {
            super(new CBCBlockCipher(new TwofishEngine()));
        }
    }

    // BEGIN android-removed
    // public static class AlgParams
    //     extends IvAlgorithmParameters
    // {
    //     protected String engineToString()
    //     {
    //         return "Twofish IV";
    //     }
    // }
    // END android-removed

    public static class Mappings
        extends SymmetricAlgorithmProvider
    {
        private static final String PREFIX = Twofish.class.getName();

        public Mappings()
        {
        }

        public void configure(ConfigurableProvider provider)
        {
            // BEGIN android-removed
            // provider.addAlgorithm("Cipher.Twofish", PREFIX + "$ECB");
            // provider.addAlgorithm("KeyGenerator.Twofish", PREFIX + "$KeyGen");
            // provider.addAlgorithm("AlgorithmParameters.Twofish", PREFIX + "$AlgParams");
            // END android-removed

            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH-CBC", "PKCS12PBE");
            provider.addAlgorithm("Cipher.PBEWITHSHAANDTWOFISH-CBC",  PREFIX + "$PBEWithSHA");
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAANDTWOFISH-CBC", PREFIX + "$PBEWithSHAKeyFactory");

            // BEGIN android-removed
            // addGMacAlgorithm(provider, "Twofish", PREFIX + "$GMAC", PREFIX + "$KeyGen");
            // END android-removed
        }
    }
}
