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

public class Derived extends Base {
    public static void notDeclaredInBase() {
        System.out.println("notDeclaredInBase: Derived");
    }

    public void overridden() {
        System.out.println("overridden: Derived");
    }

    public void wasOverridden() {
        System.out.println("wasOverridden: Derived");
    }

    public void overrideWithPublic() {
        System.out.println("overrideWithPublic: Derived");
    }

    protected void overridePublicWithProtected() {
        System.out.println("overridePublicWithProtected: Derived");
    }

    public void overrideProtectedWithPublic() {
        System.out.println("overrideProtectedWithPublic: Derived");
    }

    private void overridePublicWithPrivate() {
        System.out.println("overridePublicWithPrivate: Derived");
    }

    public void overridePrivateWithPublic() {
        System.out.println("overridePrivateWithPublic: Derived");
    }

    /* not really an "override"; just has same method signature */
    public static void overrideVirtualWithStatic() {
        System.out.println("overrideVirtualWithStatic: Derived");
    }

    /* not really an "override"; just has same method signature */
    public void overrideStaticWithVirtual() {
        System.out.println("overrideStaticWithVirtual: Derived");
    }
}
