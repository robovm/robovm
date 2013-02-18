/*
 * Copyright (C) 2012 The Android Open Source Project
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
 * Create a hierarchy of interfaces to check if that overflows the LinearAlloc
 * with iftable entries.
 */
public class Main {
    interface A1 {}
    interface A2 {}
    interface A3 {}
    interface A4 {}
    interface A5 {}

    interface B1 extends A1, A2, A3, A4, A5 {}
    interface B2 extends A1, A2, A3, A4, A5 {}
    interface B3 extends A1, A2, A3, A4, A5 {}
    interface B4 extends A1, A2, A3, A4, A5 {}
    interface B5 extends A1, A2, A3, A4, A5 {}

    interface C1 extends B1, B2, B3, B4, B5 {}
    interface C2 extends B1, B2, B3, B4, B5 {}
    interface C3 extends B1, B2, B3, B4, B5 {}
    interface C4 extends B1, B2, B3, B4, B5 {}
    interface C5 extends B1, B2, B3, B4, B5 {}

    interface D1 extends C1, C2, C3, C4, C5 {}
    interface D2 extends C1, C2, C3, C4, C5 {}
    interface D3 extends C1, C2, C3, C4, C5 {}
    interface D4 extends C1, C2, C3, C4, C5 {}
    interface D5 extends C1, C2, C3, C4, C5 {}

    interface E1 extends D1, D2, D3, D4, D5 {}
    interface E2 extends D1, D2, D3, D4, D5 {}
    interface E3 extends D1, D2, D3, D4, D5 {}
    interface E4 extends D1, D2, D3, D4, D5 {}
    interface E5 extends D1, D2, D3, D4, D5 {}

    interface F1 extends E1, E2, E3, E4, E5 {}
    interface F2 extends E1, E2, E3, E4, E5 {}
    interface F3 extends E1, E2, E3, E4, E5 {}
    interface F4 extends E1, E2, E3, E4, E5 {}
    interface F5 extends E1, E2, E3, E4, E5 {}

    interface G1 extends F1, F2, F3, F4, F5 {}
    interface G2 extends F1, F2, F3, F4, F5 {}
    interface G3 extends F1, F2, F3, F4, F5 {}
    interface G4 extends F1, F2, F3, F4, F5 {}
    interface G5 extends F1, F2, F3, F4, F5 {}

    interface H1 extends G1, G2, G3, G4, G5 {}
    interface H2 extends G1, G2, G3, G4, G5 {}
    interface H3 extends G1, G2, G3, G4, G5 {}
    interface H4 extends G1, G2, G3, G4, G5 {}
    interface H5 extends G1, G2, G3, G4, G5 {}

    interface Z extends H1, H2, H3, H4, H5 {}

    public static void main(String[] args) {
        Z instance = new Z() {};
        System.out.println("A new instance of Z was created successfully");
    }
}
