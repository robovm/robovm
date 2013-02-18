/*
 * Copyright (C) 2009 The Android Open Source Project
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

/**
 * Verify that we don't reject this with a LinkageError.
 */
public class AbstractGet extends AbstractBase {
    public DoubledExtendOkay getExtended() {
        return new DoubledExtendOkay();
    }
}

/**
 * Abstract class, does not declare getAbstract.  This cause the VM to
 * generate a "miranda" method.
 */
abstract class AbstractBase extends BaseOkay {
    public abstract DoubledExtendOkay getExtended();
}
