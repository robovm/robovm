/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;

/**
 * The algorithm parameter specification for the <i>OAEP Padding</i> algorithm.
 * <p>
 * This padding algorithm is defined in the <a
 * href="http://www.ietf.org/rfc/rfc3447.txt">PKCS #1</a> standard.
 */
public class OAEPParameterSpec implements AlgorithmParameterSpec {

    private final String mdName;
    private final String mgfName;
    private final AlgorithmParameterSpec mgfSpec;
    private final PSource pSrc;

    /**
     * The algorithm parameter instance with default values.
     * <p>
     * It uses the following parameters:
     * <ul><li>message digest : <code>"SHA-1"</code></li>
     * <li>mask generation function (<i>mgf</i>) : <code>"MGF1"</code></li>
     * <li>parameters for the <i>mgf</i> : "SHA-1" {@link MGF1ParameterSpec#SHA1}</li>
     * <li>the source of the label <code>L</code>: {@link PSource.PSpecified#DEFAULT}</li>
     * </ul>
     */
    public static final OAEPParameterSpec DEFAULT = new OAEPParameterSpec();

    private OAEPParameterSpec() {
        this.mdName = "SHA-1";
        this.mgfName = "MGF1";
        this.mgfSpec = MGF1ParameterSpec.SHA1;
        this.pSrc = PSource.PSpecified.DEFAULT;
    }

    /**
     * Creates a new <code>OAEPParameterSpec</code> instance with the specified
     * <i>message digest</i> algorithm name, <i>mask generation function</i>
     * (<i>mgf</i>) algorithm name, <i>parameters</i> for the <i>mgf</i>
     * algorithm and the <i>source of the label <code>L</code></i>.
     *
     * @param mdName
     *            the message digest algorithm name.
     * @param mgfName
     *            the mask generation function algorithm name.
     * @param mgfSpec
     *            the algorithm parameter specification for the mask generation
     *            function algorithm.
     * @param pSrc
     *            the source of the label <code>L</code>.
     * @throws NullPointerException
     *             if one of <code>mdName</code>, <code>mgfName</code> or
     *             <code>pSrc</code> is null.
     */
    public OAEPParameterSpec(String mdName, String mgfName,
                                AlgorithmParameterSpec mgfSpec, PSource pSrc) {
        if ((mdName == null) || (mgfName == null) || (pSrc == null)) {
            throw new NullPointerException();
        }
        this.mdName = mdName;
        this.mgfName = mgfName;
        this.mgfSpec = mgfSpec;
        this.pSrc = pSrc;
    }

    /**
     * Returns the algorithm name of the <i>message digest</i>.
     *
     * @return the algorithm name of the message digest.
     */
    public String getDigestAlgorithm() {
        return mdName;
    }

    /**
     * Returns the algorithm name of the <i>mask generation function</i>.
     *
     * @return the algorithm name of the mask generation function.
     */
    public String getMGFAlgorithm() {
        return mgfName;
    }

    /**
     * Returns the algorithm parameter specification for the mask generation
     * function algorithm.
     *
     * @return the algorithm parameter specification for the mask generation
     *         function algorithm.
     */
    public AlgorithmParameterSpec getMGFParameters() {
        return mgfSpec;
    }

    /**
     * Returns the source of the label <code>L</code>.
     *
     * @return the source of the label <code>L</code>.
     */
    public PSource getPSource() {
        return pSrc;
    }
}

