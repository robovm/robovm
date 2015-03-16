/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.compiler.config.tools;

import org.simpleframework.xml.Element;

/**
 * 
 * @author blueriverteam
 *
 */
public class TextureAtlas {
    @Element(required = false)
    private OutputFormat outputFormat = OutputFormat.RGBA8888_PNG;
    
    @Element(required = false)
    private String maximumTextureDimension = "2048";
    
    @Element(required = false)
    private boolean powerOfTwo;
    
    public OutputFormat getOutputFormat() {
        return outputFormat;
    }
    
    public MaximumTextureDimension getMaximumTextureDimension() {
        return MaximumTextureDimension.getByName(maximumTextureDimension);
    }
    
    public boolean usePowerOfTwo() {
        return powerOfTwo;
    }
    
    public enum OutputFormat {
        RGBA8888_PNG, RGBA8888_COMPRESSED, RGBA4444_COMPRESSED, RGBA5551_COMPRESSED, RGB565_COMPRESSED
    }
    
    public enum MaximumTextureDimension {
        _2048("2048"), _4096("4096");
        
        private String name;
        
        MaximumTextureDimension(String name) {
            this.name = name;
        }
        
        public static MaximumTextureDimension getByName(String name) {
            for (MaximumTextureDimension dimension : values()) {
                if (name.contains(dimension.name)) {
                    return dimension;
                }
            }
            return _2048;
        }
    }
}
