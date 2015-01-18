package org.robovm.apple.corefoundation;

public abstract class CFDictionaryWrapper {
    protected CFDictionary data;
    
    protected CFDictionaryWrapper(CFDictionary data) {
        this.data = data;
    }
    public CFDictionaryWrapper() {
        this.data = CFMutableDictionary.create();
    }
    
    public CFDictionary getDictionary() {
        return data;
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}
