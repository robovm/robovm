package org.robovm.apple.uikit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;

public class CustomUIViewTest {
    private static final String STRONG_REFS_KEY = "org.robovm.objc.ObjCObject$AssociatedObjectHelper.StrongRefs";
    private static final String TEST_STRING_VALUE = "TEST";
    private static final long TEST_LONG_VALUE = 1234567890L;
    private static final int TEST_COUNT = 50;
    private UIView parentView;
    private UIViewController viewController;
    
    private class CustomUIView extends UIView {
        public String stringVar;
        public long longVar;
        
        public CustomUIView(String stringVar, long longVar) {
            super(new CGRect());
            this.stringVar = stringVar;
            this.longVar = longVar;
        }
    }
    
    @Before
    public void setup() {
        parentView = new UIView();
        viewController = new UIViewController();
        for (int i = 0; i < TEST_COUNT; i++) {
            CustomUIView customView = new CustomUIView(TEST_STRING_VALUE, TEST_LONG_VALUE);
            parentView.addSubview(customView);
            CustomUIView customView2 = new CustomUIView(TEST_STRING_VALUE, TEST_LONG_VALUE);
            viewController.getView().addSubview(customView2);
        }
        // Force GC.
        for (int i = 0; i < 5; i++) {
            System.gc();
        }
    }
    
    @Test
    public void testIfCustomUIViewVariablesOfParentViewAreRetained() {
        NSArray<UIView> subviews = parentView.getSubviews();
        for (int i = 0; i < TEST_COUNT; i++) {
            CustomUIView customView = (CustomUIView)subviews.get(i);
            assertEquals(TEST_LONG_VALUE, customView.longVar);
            assertEquals(TEST_STRING_VALUE, customView.stringVar);
        }
    }
    
    @Test
    public void testIfRemovedSubviewsOfParentViewAreNotRetained() {
        List associatedObjects = (List)parentView.getAssociatedObject(STRONG_REFS_KEY);
        assertEquals("Strong references have been lost unexpectedly!", TEST_COUNT, associatedObjects.size());
        NSArray<UIView> subviews = parentView.getSubviews();
        for (int i = 0; i < TEST_COUNT; i++) {
            CustomUIView customView = (CustomUIView)subviews.get(i);
            customView.removeFromSuperview();
        }
        associatedObjects = (List)parentView.getAssociatedObject(STRONG_REFS_KEY);
        assertEquals(null, associatedObjects);
    }
    
    @Test
    public void testIfCustomUIViewVariablesOfViewControllerAreRetained() {
        NSArray<UIView> subviews = viewController.getView().getSubviews();
        for (int i = 0; i < TEST_COUNT; i++) {
            CustomUIView customView = (CustomUIView)subviews.get(i);
            assertEquals(TEST_LONG_VALUE, customView.longVar);
            assertEquals(TEST_STRING_VALUE, customView.stringVar);
        }
    }
    
    @Test
    public void testIfRemovedSubviewsOfViewControllerAreNotRetained() {
        List associatedObjects = (List)viewController.getView().getAssociatedObject(STRONG_REFS_KEY);
        assertEquals("Strong references have been lost unexpectedly!", TEST_COUNT, associatedObjects.size());
        NSArray<UIView> subviews = viewController.getView().getSubviews();
        for (int i = 0; i < TEST_COUNT; i++) {
            CustomUIView customView = (CustomUIView)subviews.get(i);
            customView.removeFromSuperview();
        }
        associatedObjects = (List)viewController.getView().getAssociatedObject(STRONG_REFS_KEY);
        assertEquals(null, associatedObjects);
    }
}
