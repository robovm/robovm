package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;

// BEGIN android-added
import java.util.logging.Logger;
// END android-added
import org.bouncycastle.util.BigIntegers;

class DHParametersHelper
{
    // BEGIN android-added
    private static final Logger logger = Logger.getLogger(DHParametersHelper.class.getName());
    // END android-added

    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /*
     * Finds a pair of prime BigInteger's {p, q: p = 2q + 1}
     * 
     * (see: Handbook of Applied Cryptography 4.86)
     */
    static BigInteger[] generateSafePrimes(int size, int certainty, SecureRandom random)
    {
        // BEGIN android-added
        logger.info("Generating safe primes. This may take a long time.");
        long start = System.currentTimeMillis();
        int tries = 0;
        // END android-added
        BigInteger p, q;
        int qLength = size - 1;

        for (;;)
        {
            // BEGIN android-added
            tries++;
            // END android-added
            q = new BigInteger(qLength, 2, random);

            // p <- 2q + 1
            p = q.shiftLeft(1).add(ONE);

            if (p.isProbablePrime(certainty) && (certainty <= 2 || q.isProbablePrime(certainty)))
            {
                break;
            }
        }
        // BEGIN android-added
        long end = System.currentTimeMillis();
        long duration = end - start;
        logger.info("Generated safe primes: " + tries + " tries took " + duration + "ms");
        // END android-added

        return new BigInteger[] { p, q };
    }

    /*
     * Select a high order element of the multiplicative group Zp*
     * 
     * p and q must be s.t. p = 2*q + 1, where p and q are prime (see generateSafePrimes)
     */
    static BigInteger selectGenerator(BigInteger p, BigInteger q, SecureRandom random)
    {
        BigInteger pMinusTwo = p.subtract(TWO);
        BigInteger g;

        /*
         * (see: Handbook of Applied Cryptography 4.80)
         */
//        do
//        {
//            g = BigIntegers.createRandomInRange(TWO, pMinusTwo, random);
//        }
//        while (g.modPow(TWO, p).equals(ONE) || g.modPow(q, p).equals(ONE));


        /*
         * RFC 2631 2.2.1.2 (and see: Handbook of Applied Cryptography 4.81)
         */
        do
        {
            BigInteger h = BigIntegers.createRandomInRange(TWO, pMinusTwo, random);

            g = h.modPow(TWO, p);
        }
        while (g.equals(ONE));


        return g;
    }
}
