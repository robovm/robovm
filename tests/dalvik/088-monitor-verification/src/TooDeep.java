/*
 * Copyright (C) 2010 The Android Open Source Project
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
 * The class has a method with too many levels of nested "synchronized"
 * blocks.  The verifier will reject it.
 *
 * (It would be perfectly okay if the verifier *didn't* reject this.
 * The goal here is just to exercise the failure path.  It also serves
 * as a check to see if the monitor checks are enabled.)
 */
public class TooDeep {

    public static void excessiveNesting() {
        synchronized (TooDeep.class) {   // 1
        synchronized (TooDeep.class) {   // 2
        synchronized (TooDeep.class) {   // 3
        synchronized (TooDeep.class) {   // 4
        synchronized (TooDeep.class) {   // 5
        synchronized (TooDeep.class) {   // 6
        synchronized (TooDeep.class) {   // 7
        synchronized (TooDeep.class) {   // 8
        synchronized (TooDeep.class) {   // 9
        synchronized (TooDeep.class) {   // 10
        synchronized (TooDeep.class) {   // 11
        synchronized (TooDeep.class) {   // 12
        synchronized (TooDeep.class) {   // 13
        synchronized (TooDeep.class) {   // 14
        synchronized (TooDeep.class) {   // 15
        synchronized (TooDeep.class) {   // 16
        synchronized (TooDeep.class) {   // 17
        synchronized (TooDeep.class) {   // 18
        synchronized (TooDeep.class) {   // 19
        synchronized (TooDeep.class) {   // 20
        synchronized (TooDeep.class) {   // 21
        synchronized (TooDeep.class) {   // 22
        synchronized (TooDeep.class) {   // 23
        synchronized (TooDeep.class) {   // 24
        synchronized (TooDeep.class) {   // 25
        synchronized (TooDeep.class) {   // 26
        synchronized (TooDeep.class) {   // 27
        synchronized (TooDeep.class) {   // 28
        synchronized (TooDeep.class) {   // 29
        synchronized (TooDeep.class) {   // 30
        synchronized (TooDeep.class) {   // 31
        synchronized (TooDeep.class) {   // 32
        synchronized (TooDeep.class) {   // 33
        }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
    }
}
