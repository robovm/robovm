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

package java.util;

/**
 * The unchecked exception will be thrown out if there are duplicate flags given
 * out in the format specifier.
 *
 * @see java.lang.RuntimeException
 */
public class DuplicateFormatFlagsException extends IllegalFormatException {

    private static final long serialVersionUID = 18890531L;

    private String flags;

    /**
     * Constructs a new {@code DuplicateFormatFlagsException} with the flags
     * containing duplicates.
     * 
     * @param f
     *           the format flags that contain a duplicate flag.
     */
    public DuplicateFormatFlagsException(String f) {
        if (null == f) {
            throw new NullPointerException();
        }
        flags = f;
    }

    /**
     * Returns the format flags that contain a duplicate flag.
     * 
     * @return the format flags that contain a duplicate flag.
     */
    public String getFlags() {
        return flags;
    }

    /**
     * Returns the message string of the DuplicateFormatFlagsException.
     * 
     * @return the message string of the DuplicateFormatFlagsException.
     */
    @Override
    public String getMessage() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Flags of the DuplicateFormatFlagsException is'");
        buffer.append(flags);
        buffer.append("'");
        return buffer.toString();
    }

}
