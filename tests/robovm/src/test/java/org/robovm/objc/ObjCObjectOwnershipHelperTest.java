package org.robovm.objc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.apple.corefoundation.CFString;
import org.robovm.apple.foundation.NSCache;
import org.robovm.apple.foundation.NSCacheDelegate;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.uikit.UIView;

public class ObjCObjectOwnershipHelperTest {

    class CustomNSObject extends NSObject {
        String stringValue;
        long longValue;

        public CustomNSObject(String stringValue, long longValue) {
            this.stringValue = stringValue;
            this.longValue = longValue;
        }
    }

    class CustomUIView extends UIView {}

    class CustomNSCacheDelegateAdapter extends NSObject implements NSCacheDelegate {
        @Override
        public void willEvictObject(NSCache cache, NSObject obj) {

        }
    }

    class CustomCFString extends CFString {
        public CustomCFString(String str) {
            super(str);
        }
    }

    /**
     * Every custom class extending from NSObject should be retained on the Java
     * side when retained on the Obj-C side.
     */
    @Test
    public void testIfCustomObjectsAreRetained() {
        CustomUIView c1 = new CustomUIView();
        CustomNSCacheDelegateAdapter c2 = new CustomNSCacheDelegateAdapter();
        CustomCFString c3 = new CustomCFString("");

        NSCache cache = new NSCache();
        cache.put("1", c1);
        cache.put("2", c2);
        cache.put("3", c3.as(NSObject.class));

        assertEquals(true, ObjCObject.ObjectOwnershipHelper.isObjectRetained(c1));
        assertEquals(true, ObjCObject.ObjectOwnershipHelper.isObjectRetained(c2));
        assertEquals(false, ObjCObject.ObjectOwnershipHelper.isObjectRetained(c3.as(NSObject.class)));
    }

    /**
     * Only custom objects should get the hooks to retain and release.
     */
    @Test
    public void testIfOnlyCustomObjectsAreRetained() {
        CustomUIView custom = new CustomUIView();
        NSObject default1 = new NSObject();
        UIView default2 = new UIView();

        NSCache cache = new NSCache();
        cache.put("1", custom);
        cache.put("2", default1);
        cache.put("3", default2);

        assertEquals(true, ObjCObject.ObjectOwnershipHelper.isObjectRetained(custom));
        assertEquals(false, ObjCObject.ObjectOwnershipHelper.isObjectRetained(default1));
        assertEquals(false, ObjCObject.ObjectOwnershipHelper.isObjectRetained(default2));
    }

    /**
     * Every retained object should not lose the values of its variables.
     */
    @Test
    public void testIfCustomObjectsVariablesAreRetained() {
        final String stringValue = "ABC";
        final long longValue = 123;
        
        CustomNSObject custom = new CustomNSObject(stringValue, longValue);
        
        final NSCache cache = new NSCache();
        cache.put("1", custom);
        
        forceGC();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                forceGC();
                
                CustomNSObject custom = (CustomNSObject) cache.get("1");
                assertEquals(stringValue, custom.stringValue);
                assertEquals(longValue, custom.longValue);
            }
        }).start();
    }

    /**
     * Custom objects that have been released shouldn't be referenced any longer
     * on the Java side. But if they are retained again, also reference them
     * again.
     */
    @Test
    public void testIfCustomObjectsAreCorrectlyReferenced() {
        CustomUIView custom = new CustomUIView();

        NSCache cache = new NSCache();
        cache.put("1", custom);
        assertEquals(true, ObjCObject.ObjectOwnershipHelper.isObjectRetained(custom));

        cache.remove("1");
        assertEquals(false, ObjCObject.ObjectOwnershipHelper.isObjectRetained(custom));

        cache.put("2", custom);
        assertEquals(true, ObjCObject.ObjectOwnershipHelper.isObjectRetained(custom));

        cache.remove("2");
        assertEquals(false, ObjCObject.ObjectOwnershipHelper.isObjectRetained(custom));
    }

    private static void forceGC() {
        for (int i = 0; i < 10; i++) {
            System.gc();
        }
    }
}
