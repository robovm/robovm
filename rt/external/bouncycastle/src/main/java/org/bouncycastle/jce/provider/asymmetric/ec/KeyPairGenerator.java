package org.bouncycastle.jce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;

import org.bouncycastle.asn1.DERObjectIdentifier;
// BEGIN android-removed
// import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
// END android-removed
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECNamedCurves;
// BEGIN android-removed
// import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
// END android-removed
import org.bouncycastle.asn1.x9.X962NamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.provider.JCEECPrivateKey;
import org.bouncycastle.jce.provider.JCEECPublicKey;
import org.bouncycastle.jce.provider.JDKKeyPairGenerator;
import org.bouncycastle.jce.provider.ProviderUtil;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

public abstract class KeyPairGenerator
    extends JDKKeyPairGenerator
{
    public KeyPairGenerator(String algorithmName)
    {
        super(algorithmName);
    }

    public static class EC
        extends KeyPairGenerator
    {
        ECKeyGenerationParameters   param;
        ECKeyPairGenerator          engine = new ECKeyPairGenerator();
        Object                      ecParams = null;
        int                         strength = 239;
        int                         certainty = 50;
        SecureRandom                random = new SecureRandom();
        boolean                     initialised = false;
        String                      algorithm;

        static private Hashtable    ecParameters;

        static {
            ecParameters = new Hashtable();

            // BEGIN android-changed
            ecParameters.put(Integer.valueOf(192), new ECGenParameterSpec("prime192v1")); // a.k.a P-192
            ecParameters.put(Integer.valueOf(239), new ECGenParameterSpec("prime239v1"));
            ecParameters.put(Integer.valueOf(256), new ECGenParameterSpec("prime256v1")); // a.k.a P-256

            ecParameters.put(Integer.valueOf(224), new ECGenParameterSpec("P-224"));
            ecParameters.put(Integer.valueOf(384), new ECGenParameterSpec("P-384"));
            ecParameters.put(Integer.valueOf(521), new ECGenParameterSpec("P-521"));
            // END android-changed
        }

        public EC()
        {
            super("EC");
            this.algorithm = "EC";
        }

        public EC(
            String  algorithm)
        {
            super(algorithm);
            this.algorithm = algorithm;
        }

        public void initialize(
            int             strength,
            SecureRandom    random)
        {
            this.strength = strength;
            // BEGIN android-added
            if (random != null) {
            // END android-added
            this.random = random;
            // BEGIN android-added
            }
            // END android-added
            // BEGIN android-changed
            this.ecParams = ecParameters.get(Integer.valueOf(strength));
            // END android-changed

            if (ecParams != null)
            {
                try
                {
                    initialize((ECGenParameterSpec)ecParams, random);
                }
                catch (InvalidAlgorithmParameterException e)
                {
                    throw new InvalidParameterException("key size not configurable.");
                }
            }
            else
            {
                throw new InvalidParameterException("unknown key size.");
            }
        }

        public void initialize(
            AlgorithmParameterSpec  params,
            SecureRandom            random)
            throws InvalidAlgorithmParameterException
        {
            // BEGIN android-added
            if (random == null) {
                random = this.random;
            }
            // END android-added
            if (params instanceof ECParameterSpec)
            {
                ECParameterSpec p = (ECParameterSpec)params;
                this.ecParams = params;

                param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);

                engine.init(param);
                initialised = true;
            }
            else if (params instanceof java.security.spec.ECParameterSpec)
            {
                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)params;
                this.ecParams = params;

                ECCurve curve = EC5Util.convertCurve(p.getCurve());
                ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);

                param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);

                engine.init(param);
                initialised = true;
            }
            else if (params instanceof ECGenParameterSpec)
            {
                final String curveName = ((ECGenParameterSpec)params).getName();

                // BEGIN android-removed
                // if (this.algorithm.equals("ECGOST3410"))
                // {
                //     ECDomainParameters  ecP = ECGOST3410NamedCurves.getByName(curveName);
                //     if (ecP == null)
                //     {
                //         throw new InvalidAlgorithmParameterException("unknown curve name: " + curveName);
                //     }
                //
                //     this.ecParams = new ECNamedCurveSpec(
                //                                     curveName,
                //                                     ecP.getCurve(),
                //                                     ecP.getG(),
                //                                     ecP.getN(),
                //                                     ecP.getH(),
                //                                     ecP.getSeed());
                // }
                // else
                // END android-removed
                {
                    X9ECParameters  ecP = X962NamedCurves.getByName(curveName);
                    if (ecP == null)
                    {
                        ecP = SECNamedCurves.getByName(curveName);
                        if (ecP == null)
                        {
                            ecP = NISTNamedCurves.getByName(curveName);
                        }
                        // BEGIN android-removed
                        // if (ecP == null)
                        // {
                        //     ecP = TeleTrusTNamedCurves.getByName(curveName);
                        // }
                        // END android-removed
                        if (ecP == null)
                        {
                            // See if it's actually an OID string (SunJSSE ServerHandshaker setupEphemeralECDHKeys bug)
                            try
                            {
                                DERObjectIdentifier oid = new DERObjectIdentifier(curveName);
                                ecP = X962NamedCurves.getByOID(oid);
                                if (ecP == null)
                                {
                                    ecP = SECNamedCurves.getByOID(oid);
                                }
                                if (ecP == null)
                                {
                                    ecP = NISTNamedCurves.getByOID(oid);
                                }
                                // BEGIN android-removed
                                // if (ecP == null)
                                // {
                                //     ecP = TeleTrusTNamedCurves.getByOID(oid);
                                // }
                                // END android-removed
                                if (ecP == null)
                                {
                                    throw new InvalidAlgorithmParameterException("unknown curve OID: " + curveName);
                                }
                            }
                            catch (IllegalArgumentException ex)
                            {
                                throw new InvalidAlgorithmParameterException("unknown curve name: " + curveName);
                            }
                        }
                    }

                    this.ecParams = new ECNamedCurveSpec(
                            curveName,
                            ecP.getCurve(),
                            ecP.getG(),
                            ecP.getN(),
                            ecP.getH(),
                            null); // ecP.getSeed());   Work-around JDK bug -- it won't look up named curves properly if seed is present 
                }

                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;

                ECCurve curve = EC5Util.convertCurve(p.getCurve());
                ECPoint g = EC5Util.convertPoint(curve, p.getGenerator(), false);

                param = new ECKeyGenerationParameters(new ECDomainParameters(curve, g, p.getOrder(), BigInteger.valueOf(p.getCofactor())), random);

                engine.init(param);
                initialised = true;
            }
            else if (params == null && ProviderUtil.getEcImplicitlyCa() != null)
            {
                ECParameterSpec p = ProviderUtil.getEcImplicitlyCa();
                this.ecParams = params;

                param = new ECKeyGenerationParameters(new ECDomainParameters(p.getCurve(), p.getG(), p.getN()), random);

                engine.init(param);
                initialised = true;
            }
            else if (params == null && ProviderUtil.getEcImplicitlyCa() == null)
            {
                throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
            }
            else
            {
                throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
            }
        }

        public KeyPair generateKeyPair()
        {
            if (!initialised)
            {
                // BEGIN android-removed
                // throw new IllegalStateException("EC Key Pair Generator not initialised");
                // END android-removed
                // BEGIN android-added
                /*
                 * KeyPairGenerator documentation says that a default initialization must be provided
                 */
                initialize(192, random);
                // END android-added
            }

            AsymmetricCipherKeyPair     pair = engine.generateKeyPair();
            ECPublicKeyParameters       pub = (ECPublicKeyParameters)pair.getPublic();
            ECPrivateKeyParameters      priv = (ECPrivateKeyParameters)pair.getPrivate();

            if (ecParams instanceof ECParameterSpec)
            {
                ECParameterSpec p = (ECParameterSpec)ecParams;

                JCEECPublicKey pubKey = new JCEECPublicKey(algorithm, pub, p);
                return new KeyPair(pubKey,
                                   new JCEECPrivateKey(algorithm, priv, pubKey, p));
            }
            else if (ecParams == null)
            {
               return new KeyPair(new JCEECPublicKey(algorithm, pub),
                                   new JCEECPrivateKey(algorithm, priv));
            }
            else
            {
                java.security.spec.ECParameterSpec p = (java.security.spec.ECParameterSpec)ecParams;

                JCEECPublicKey pubKey = new JCEECPublicKey(algorithm, pub, p);
                
                return new KeyPair(pubKey, new JCEECPrivateKey(algorithm, priv, pubKey, p));
            }
        }
    }

    public static class ECDSA
        extends EC
    {
        public ECDSA()
        {
            super("ECDSA");
        }
    }

    // BEGIN android-removed
    // public static class ECGOST3410
    //     extends EC
    // {
    //     public ECGOST3410()
    //     {
    //         super("ECGOST3410");
    //     }
    // }
    // END android-removed

    public static class ECDH
        extends EC
    {
        public ECDH()
        {
            super("ECDH");
        }
    }

    public static class ECDHC
        extends EC
    {
        public ECDHC()
        {
            super("ECDHC");
        }
    }

    public static class ECMQV
        extends EC
    {
        public ECMQV()
        {
            super("ECMQV");
        }
    }
}
