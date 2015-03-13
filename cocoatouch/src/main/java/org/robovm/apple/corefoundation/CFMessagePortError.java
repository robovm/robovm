/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.corefoundation;

import org.robovm.apple.foundation.NSError;

public class CFMessagePortError extends NSError {

    protected CFMessagePortError(CFMessagePortErrorCode code) {
        super("CFMessagePortError", code.value(), null);
    }

    public CFMessagePortErrorCode getErrorCode() {
        CFMessagePortErrorCode code = null;
        try {
            code = CFMessagePortErrorCode.valueOf(getCode());
        } catch (IllegalArgumentException e) {
            // ignore
        }
        return code;
    }
}
