/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.objc.block;


/**
 * Block which takes no arguments and returns a {@code boolean}. This is used 
 * to map the Objective-C {@code BOOL (^)(void)} block type.
 */
public interface BooleanBlock {

    /**
     * Runs this block.
     * 
     * @return the block return value.
     */
    boolean invoke();
}
