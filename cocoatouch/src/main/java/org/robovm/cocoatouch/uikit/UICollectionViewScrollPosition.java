/*
 * Copyright (C) 2012 Trillian Mobile AB
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
package org.robovm.cocoatouch.uikit;

import org.robovm.rt.bro.Bits;

@Deprecated public final class UICollectionViewScrollPosition extends Bits<UICollectionViewScrollPosition> {
	public static final UICollectionViewScrollPosition None = new UICollectionViewScrollPosition(0);
	public static final UICollectionViewScrollPosition Top = new UICollectionViewScrollPosition(1 << 0);
	public static final UICollectionViewScrollPosition CenteredVertically = new UICollectionViewScrollPosition(1 << 1);
	public static final UICollectionViewScrollPosition Bottom = new UICollectionViewScrollPosition(1 << 2);
	public static final UICollectionViewScrollPosition Left = new UICollectionViewScrollPosition(1 << 3);
	public static final UICollectionViewScrollPosition CenteredHorizontally = new UICollectionViewScrollPosition(1 << 4);
	public static final UICollectionViewScrollPosition Right = new UICollectionViewScrollPosition(1 << 5);

    private static final UICollectionViewScrollPosition[] values = _values(UICollectionViewScrollPosition.class);
    
    private UICollectionViewScrollPosition(long value) { super(value); }
    private UICollectionViewScrollPosition(long value, long mask) { super(value, mask); }
    protected UICollectionViewScrollPosition wrap(long value, long mask) {
        return new UICollectionViewScrollPosition(value, mask);
    }
    protected UICollectionViewScrollPosition[] _values() {
        return values;
    }
}
