package org.robovm.compiler.config.tools;

import org.simpleframework.xml.Element;

/**
 * 
 * @author blueriverteam
 *
 */
public class Tools {
    @Element(required = false)
    private TextureAtlas textureAtlas;
    
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }
}
