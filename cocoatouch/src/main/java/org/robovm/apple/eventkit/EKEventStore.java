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
package org.robovm.apple.eventkit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKEventStore/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeChanged(EKEventStore object, final VoidBlock1<EKEventStore> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ChangedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((EKEventStore) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class EKEventStorePtr extends Ptr<EKEventStore, EKEventStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKEventStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKEventStore() {}
    protected EKEventStore(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "eventStoreIdentifier")
    public native String getEventStoreIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "calendars")
    public native NSArray<EKCalendar> getCalendars();
    @Property(selector = "defaultCalendarForNewEvents")
    public native EKCalendar getDefaultCalendarForNewEvents();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param calendar
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 5.0 and later.
     */
    public boolean saveCalendar(EKCalendar calendar, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = saveCalendar(calendar, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param calendar
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 5.0 and later.
     */
    public boolean removeCalendar(EKCalendar calendar, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeCalendar(calendar, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param event
     * @param span
     * @return
     * @throws NSErrorException
     * @since Available in iOS 4.0 and later.
     */
    public boolean saveEvent(EKEvent event, EKSpan span) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = saveEvent(event, span, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param event
     * @param span
     * @return
     * @throws NSErrorException
     * @since Available in iOS 4.0 and later.
     */
    public boolean removeEvent(EKEvent event, EKSpan span) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeEvent(event, span, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param event
     * @param span
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 5.0 and later.
     */
    public boolean saveEvent(EKEvent event, EKSpan span, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = saveEvent(event, span, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param event
     * @param span
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 5.0 and later.
     */
    public boolean removeEvent(EKEvent event, EKSpan span, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeEvent(event, span, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param reminder
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 6.0 and later.
     */
    public boolean saveReminder(EKReminder reminder, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = saveReminder(reminder, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param reminder
     * @param commit
     * @return
     * @throws NSErrorException
     * @since Available in iOS 6.0 and later.
     */
    public boolean removeReminder(EKReminder reminder, boolean commit) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeReminder(reminder, commit, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     * @since Available in iOS 5.0 and later.
     */
    public boolean commit() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = commit(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="EKEventStoreChangedNotification", optional=true)
    public static native NSString ChangedNotification();
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "requestAccessToEntityType:completion:")
    public native void requestAccess(EKEntityType entityType, @Block VoidBlock2<Boolean, NSError> completion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "sources")
    public native NSArray<EKSource> getSources();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "sourceWithIdentifier:")
    public native EKSource getSource(String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "calendarsForEntityType:")
    public native NSArray<EKCalendar> getCalendars(EKEntityType entityType);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "defaultCalendarForNewReminders")
    public native EKCalendar getDefaultCalendarForNewReminders();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "calendarWithIdentifier:")
    public native EKCalendar getCalendar(String identifier);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "saveCalendar:commit:error:")
    protected native boolean saveCalendar(EKCalendar calendar, boolean commit, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "removeCalendar:commit:error:")
    protected native boolean removeCalendar(EKCalendar calendar, boolean commit, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "calendarItemWithIdentifier:")
    public native EKCalendarItem getCalendarItem(String identifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "calendarItemsWithExternalIdentifier:")
    public native NSArray<EKCalendarItem> getCalendarItems(String externalIdentifier);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "saveEvent:span:error:")
    protected native boolean saveEvent(EKEvent event, EKSpan span, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "removeEvent:span:error:")
    protected native boolean removeEvent(EKEvent event, EKSpan span, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "saveEvent:span:commit:error:")
    protected native boolean saveEvent(EKEvent event, EKSpan span, boolean commit, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "removeEvent:span:commit:error:")
    protected native boolean removeEvent(EKEvent event, EKSpan span, boolean commit, NSError.NSErrorPtr error);
    @Method(selector = "eventWithIdentifier:")
    public native EKEvent getEvent(String identifier);
    @Method(selector = "eventsMatchingPredicate:")
    public native NSArray<EKEvent> getEvents(NSPredicate predicate);
    @Method(selector = "enumerateEventsMatchingPredicate:usingBlock:")
    public native void enumerateEvents(NSPredicate predicate, @Block VoidBlock2<EKEvent, BooleanPtr> block);
    @Method(selector = "predicateForEventsWithStartDate:endDate:calendars:")
    public native NSPredicate getPredicateForEvents(NSDate startDate, NSDate endDate, NSArray<EKCalendar> calendars);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "saveReminder:commit:error:")
    protected native boolean saveReminder(EKReminder reminder, boolean commit, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "removeReminder:commit:error:")
    protected native boolean removeReminder(EKReminder reminder, boolean commit, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "fetchRemindersMatchingPredicate:completion:")
    public native NSObject fetchReminders(NSPredicate predicate, @Block VoidBlock1<NSArray<EKReminder>> completion);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "cancelFetchRequest:")
    public native void cancelFetchRequest(NSObject fetchIdentifier);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "predicateForRemindersInCalendars:")
    public native NSPredicate getPredicateForReminders(NSArray<EKCalendar> calendars);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "predicateForIncompleteRemindersWithDueDateStarting:ending:calendars:")
    public native NSPredicate getPredicateForIncompleteReminders(NSDate startDate, NSDate endDate, NSArray<EKCalendar> calendars);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "predicateForCompletedRemindersWithCompletionDateStarting:ending:calendars:")
    public native NSPredicate getPredicateForCompletedReminders(NSDate startDate, NSDate endDate, NSArray<EKCalendar> calendars);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "commit:")
    protected native boolean commit(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "reset")
    public native void reset();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "refreshSourcesIfNecessary")
    public native void refreshSourcesIfNecessary();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "authorizationStatusForEntityType:")
    public static native EKAuthorizationStatus getAuthorizationStatusForEntityType(EKEntityType entityType);
    /*</methods>*/
}
