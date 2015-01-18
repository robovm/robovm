package org.robovm.apple.foundation;

public abstract class NSDictionaryWrapper {
    protected NSDictionary<NSString, NSObject> data;
    
    protected NSDictionaryWrapper(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSDictionaryWrapper() {
        this.data = new NSMutableDictionary<>();
    }
    
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
}
