/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

/**
 * Thrown if an {@code enum} constant does not exist for a particular name.
 *
 * @since 1.5
 */
public class EnumConstantNotPresentException extends RuntimeException {

    private static final long serialVersionUID = -6046998521960521108L;

    @SuppressWarnings("unchecked")
    private final Class<? extends Enum> enumType;

    private final String constantName;

    /**
     * Constructs a new {@code EnumConstantNotPresentException} with the current
     * stack trace and a detail message based on the specified enum type and
     * missing constant name.
     *
     * @param enumType
     *            the enum type.
     * @param constantName
     *            the missing constant name.
     */
    @SuppressWarnings("unchecked")
    public EnumConstantNotPresentException(Class<? extends Enum> enumType, String constantName) {
        super("enum constant " + enumType.getName() + "." + constantName + " is missing");
        this.enumType = enumType;
        this.constantName = constantName;
    }

    /**
     * Gets the enum type for which the constant name is missing.
     *
     * @return the enum type for which a constant name has not been found.
     */
    @SuppressWarnings("unchecked")
    public Class<? extends Enum> enumType() {
        return enumType;
    }

    /**
     * Gets the name of the missing constant.
     *
     * @return the name of the constant that has not been found in the enum
     *         type.
     */
    public String constantName() {
        return constantName;
    }
}
