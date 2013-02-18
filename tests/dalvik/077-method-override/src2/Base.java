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

public class Base {
    public void declaredInBase() {
        System.out.println("declaredInBase: Base");
    }

    public void overridden() {
        System.out.println("overridden: Base");
    }

    /* src2: removed */
    //public void wasOverridden() {
    //    System.out.println("wasOverridden: Base");
    //}

    public void callOverrideWithPublic() {
        overrideWithPublic();
    }
    public void overrideWithPublic() {
        System.out.println("overrideWithPublic: Base");
    }

    public void callOverridePublicWithProtected() {
        overridePublicWithProtected();
    }
    /* src2: public */
    public void overridePublicWithProtected() {
        System.out.println("overridePublicWithProtected: Base");
    }

    public void callOverrideProtectedWithPublic() {
        overrideProtectedWithPublic();
    }
    protected void overrideProtectedWithPublic() {
        System.out.println("overrideProtectedWithPublic: Base");
    }

    public void callOverridePublicWithPrivate() {
        overridePublicWithPrivate();
    }
    /* src2: public */
    public void overridePublicWithPrivate() {
        System.out.println("overridePublicWithPrivate: Base");
    }

    public void callOverridePrivateWithPublic() {
        overridePrivateWithPublic();
    }
    private void overridePrivateWithPublic() {
        System.out.println("overridePrivateWithPublic: Base");
    }

    public void callOverrideVirtualWithStatic() {
        overrideVirtualWithStatic();
    }
    /* src2: non-static */
    public void overrideVirtualWithStatic() {
        System.out.println("overrideVirtualWithStatic: Base");
    }

    public void callOverrideStaticWithVirtual() {
        overrideStaticWithVirtual();
    }
    public static void overrideStaticWithVirtual() {
        System.out.println("overrideStaticWithVirtual: Base");
    }
}
