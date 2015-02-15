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
