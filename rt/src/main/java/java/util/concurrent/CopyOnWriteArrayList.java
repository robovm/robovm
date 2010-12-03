/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implements a {@link java.util.ArrayList} variant that is thread-safe. All
 * write operation result in a new copy of the underlying data being created.
 * Iterators reflect the state of the CopyOnWriteArrayList at the time they were
 * created. They are not updated to reflect subsequent changes to the list. In
 * addition, these iterators cannot be used for modifying the underlying
 * CopyOnWriteArrayList.
 *
 * @param <E> the element type
 */
public class CopyOnWriteArrayList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = 8673264195747942595L;

    private transient volatile E[] arr;

    /**
     * Lock for the queue write methods
     */
    private final transient ReentrantLock lock = new ReentrantLock();

    /**
     * Creates a new, empty instance of CopyOnWriteArrayList.
     */
    public CopyOnWriteArrayList() {
    }

    /**
     * Creates a new instance of CopyOnWriteArrayList and fills it with the
     * contents of a given Collection.
     *
     * @param c     the collection the elements of which are to be copied into
     *              the new instance.
     */
    public CopyOnWriteArrayList(Collection<? extends E> c) {
        this((E[]) c.toArray());
    }

    /**
     * Creates a new instance of CopyOnWriteArrayList and fills it with the
     * contents of a given array.
     *
     * @param array the array the elements of which are to be copied into the
     *              new instance.
     */
    public CopyOnWriteArrayList(E[] array) {
        int size = array.length;
        E[] data = newElementArray(size);
        for (int i = 0; i < size; i++) {
            data[i] = array[i];
        }
        arr = data;
    }

    public boolean add(E e) {
        lock.lock();
        try {
            E[] data;
            E[] old = getData();
            int size = old.length;
            data = newElementArray(size + 1);
            System.arraycopy(old, 0, data, 0, size);
            data[size] = e;
            setData(data);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void add(int index, E e) {
        lock.lock();
        try {
            E[] data;
            E[] old = getData();
            int size = old.length;
            checkIndexInclusive(index, size);
            data = newElementArray(size+1);
            System.arraycopy(old, 0, data, 0, index);
            data[index] = e;
            if (size > index) {
                System.arraycopy(old, index, data, index + 1, size - index);
            }
            setData(data);
        } finally {
            lock.unlock();
        }
    }

    public boolean addAll(Collection<? extends E> c) {
        Iterator it = c.iterator();
        int ssize = c.size();
        lock.lock();
        try {
            int size = size();
            E[] data;
            E[] old = getData();
            int nSize = size + ssize;
            data = newElementArray(nSize);
            System.arraycopy(old, 0, data, 0, size);
            while (it.hasNext()) {
                data[size++] = (E) it.next();
            }
            setData(data);
        } finally {
            lock.unlock();
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        Iterator it = c.iterator();
        int ssize = c.size();
        lock.lock();
        try {
            int size = size();
            checkIndexInclusive(index, size);
            E[] data;
            E[] old = getData();
            int nSize = size + ssize;
            data = newElementArray(nSize);
            System.arraycopy(old, 0, data, 0, index);
            int i = index;
            while (it.hasNext()) {
                data[i++] = (E) it.next();
            }
            if (size > index) {
                System.arraycopy(old, index, data, index + ssize, size - index);
            }
            setData(data);
        } finally {
            lock.unlock();
        }
        return true;
    }

    /**
     * Adds to this CopyOnWriteArrayList all those elements from a given
     * collection that are not yet part of the list.
     *
     * @param c     the collection from which the potential new elements are
     *              taken.
     *
     * @return the number of elements actually added to this list.
     */
    public int addAllAbsent(Collection<? extends E> c) {
        if (c.size() == 0) {
            return 0;
        }
        lock.lock();
        try {
            E[] old = getData();
            int size = old.length;
            E[] toAdd = newElementArray(c.size());
            int i = 0;
            for (Iterator it = c.iterator(); it.hasNext();) {
                E o = (E) it.next();
                if (indexOf(o) < 0) {
                    toAdd[i++] = o;
                }
            }
            E[] data = newElementArray(size + i);
            System.arraycopy(old, 0, data, 0, size);
            System.arraycopy(toAdd, 0, data, size, i);
            setData(data);
            return i;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds to this CopyOnWriteArrayList another element, given that this
     * element is not yet part of the list.
     *
     * @param e     the potential new element.
     *
     * @return true if the element was added, or false otherwise.
     */
    public boolean addIfAbsent(E e) {
        lock.lock();
        try {
            E[] data;
            E[] old = getData();
            int size = old.length;
            if (size != 0) {
                if (indexOf(e) >= 0) {
                    return false;
                }
            }
            data = newElementArray(size + 1);
            System.arraycopy(old, 0, data, 0, size);
            data[size] = e;
            setData(data);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            setData(newElementArray(0));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object clone() {
        try {
            CopyOnWriteArrayList thisClone = (CopyOnWriteArrayList) super.clone();
            thisClone.setData(this.getData());
            return thisClone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("CloneNotSupportedException is not expected here");
        }
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean containsAll(Collection<?> c) {
        E[] data = getData();
        return containsAll(c, data, 0, data.length);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        List l = (List) o;
        Iterator it = l.listIterator();
        Iterator ourIt = listIterator();
        while (it.hasNext()) {
            if (!ourIt.hasNext()) {
                return false;
            }
            Object thisListElem = it.next();
            Object anotherListElem = ourIt.next();
            if (!(thisListElem == null ? anotherListElem == null : thisListElem
                    .equals(anotherListElem))) {
                return false;
            }
        }
        if (ourIt.hasNext()) {
            return false;
        }
        return true;
    }

    public E get(int index) {
        E[] data = getData();
        return data[index];
    }

    public int hashCode() {
        int hashCode = 1;
        Iterator it = listIterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * Returns the index of a given element, starting the search from a given
     * position in the list.
     *
     * @param e     the element to search.
     * @param index the index at which to start the search.
     *
     * @return the index of the element or null, if the element has not been
     * found at or beyond the given start index.
     */
    public int indexOf(E e, int index) {
        E[] data = getData();
        return indexOf(e, data, index, data.length - index);
    }

    public int indexOf(Object o) {
        E[] data = getData();
        return indexOf(o, data, 0, data.length);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<E> iterator() {
        return new ListIteratorImpl(getData(), 0);
    }

    /**
     * Returns the last index of a given element, starting the search from
     * a given position in the list and going backwards.
     *
     * @param e     the element to search.
     * @param index the index at which to start the search.
     *
     * @return the index of the element or null, if the element has not been
     * found at or before the given start index.
     */
    public int lastIndexOf(E e, int index) {
        E[] data = getData();
        return lastIndexOf(e, data, 0, index);
    }

    public int lastIndexOf(Object o) {
        E[] data = getData();
        return lastIndexOf(o, data, 0, data.length);
    }

    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(getData(), 0);
    }

    public ListIterator<E> listIterator(int index) {
        E[] data = getData();
        checkIndexInclusive(index, data.length);
        return new ListIteratorImpl(data, index);
    }

    public E remove(int index) {
        return removeRange(index, 1);
    }

    public boolean remove(Object o) {
        lock.lock();
        try {
            int index = indexOf(o);
            if (index == -1) {
                return false;
            }
            remove(index);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean removeAll(Collection<?> c) {
        lock.lock();
        try {
            return removeAll(c, 0, getData().length) != 0;
        } finally {
            lock.unlock();
        }
    }

    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        lock.lock();
        try {
            return retainAll(c, 0, getData().length) != 0;
        } finally {
            lock.unlock();
        }
    }

    public E set(int index, E e) {
        lock.lock();
        try {
            int size = size();
            checkIndexExlusive(index, size);
            E[] data;
            data = newElementArray(size);
            E[] oldArr = getData();
            System.arraycopy(oldArr, 0, data, 0, size);
            E old = data[index];
            data[index] = e;
            setData(data);
            return old;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return getData().length;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }

    public Object[] toArray() {
        E[] data = getData();
        return toArray(data, 0, data.length);
    }

    public <T> T[] toArray(T[] a) {
        E[] data = getData();
        return (T[]) toArray(a, data, 0, data.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        Iterator it = listIterator();
        while (it.hasNext()) {
            sb.append(String.valueOf(it.next()));
            sb.append(", ");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    // private and package private methods

    @SuppressWarnings("unchecked")
    private final E[] newElementArray(int size) {
        return (E[])new Object[size];
    }

    /**
     * sets the internal data array
     *
     * @param data array to set
     */
    private final void setData(E[] data) {
        arr = data;
    }

    /**
     * gets the internal data array (or a new array if it is null)
     *
     * @return the data array
     */
    final E[] getData() {
        if (arr == null) {
            return newElementArray(0);
        }
        return arr;
    }

    /**
     * Removes from the specified range of this list
     * all the elements that are contained in the specified collection
     * <p/>
     * !should be called under lock
     *
     * @return Returns the number of removed elements
     */
    final int removeAll(Collection c, int start, int size) {
        int ssize = c.size();
        if (ssize == 0) {
            return 0;
        }
        Object[] old = getData();
        int arrsize = old.length;
        if (arrsize == 0) {
            return 0;
        }
        Object[] data = new Object[size];
        int j = 0;
        for (int i = start; i < (start + size); i++) {
            if (!c.contains(old[i])) {
                data[j++] = old[i];
            }
        }
        if (j != size) {
            E[] result = newElementArray(arrsize - (size - j));
            System.arraycopy(old, 0, result, 0, start);
            System.arraycopy(data, 0, result, start, j);
            System.arraycopy(old, start + size, result, start + j, arrsize
                    - (start + size));
            setData(result);
            return (size - j);
        }
        return 0;
    }

    /**
     * Retains only the elements in the specified range of this list
     * that are contained in the specified collection
     *
     * @return Returns the number of removed elements
     */
    // should be called under lock
    int retainAll(Collection c, int start, int size) {
        Object[] old = getData();
        if (size == 0) {
            return 0;
        }
        if (c.size() == 0) {
            E[] data;
            if (size == old.length) {
                data = newElementArray(0);
            } else {
                data = newElementArray(old.length - size);
                System.arraycopy(old, 0, data, 0, start);
                System.arraycopy(old, start + size, data, start, old.length
                        - start - size);
            }
            setData(data);
            return size;
        }
        Object[] temp = new Object[size];
        int pos = 0;
        for (int i = start; i < (start + size); i++) {
            if (c.contains(old[i])) {
                temp[pos++] = old[i];
            }
        }
        if (pos == size) {
            return 0;
        }
        E[] data = newElementArray(pos + old.length - size);
        System.arraycopy(old, 0, data, 0, start);
        System.arraycopy(temp, 0, data, start, pos);
        System.arraycopy(old, start + size, data, start + pos, old.length
                - start - size);
        setData(data);
        return (size - pos);
    }

    /**
     * Removes specified range from this list
     */
    E removeRange(int start, int size) {
        lock.lock();
        try {
            int sizeArr = size();
            checkIndexExlusive(start, sizeArr);
            checkIndexInclusive(start + size, sizeArr);
            E[] data;
            data = newElementArray(sizeArr - size);
            E[] oldArr = getData();
            System.arraycopy(oldArr, 0, data, 0, start);
            E old = oldArr[start];
            if (sizeArr > (start + size)) {
                System.arraycopy(oldArr, start + size, data, start, sizeArr
                        - (start + size));
            }
            setData(data);
            return old;
        } finally {
            lock.unlock();
        }
    }

    // some util static functions to use by iterators and methods
    /**
     * Returns an array containing all of the elements
     * in the specified range of the array in proper sequence
     */
    static Object[] toArray(Object[] data, int start, int size) {
        Object[] result = new Object[size];
        System.arraycopy(data, start, result, 0, size);
        return result;
    }

    /**
     * Returns an array containing all of the elements
     * in the specified range of the array in proper sequence,
     * stores the result in the array, specified by first parameter
     * (as for public instance method toArray(Object[] to)
     */
    static Object[] toArray(Object[] to, Object[] data, int start, int size) {
        int l = data.length;
        if (to.length < l) {
            to = (Object[]) Array.newInstance(to.getClass().getComponentType(),
                    l);
        } else {
            if (to.length > l) {
                to[l] = null;
            }
        }
        System.arraycopy(data, start, to, 0, size);
        return to;
    }

    /**
     * Checks if the specified range of the
     * array contains all of the elements in the collection
     *
     * @param c     collection with elements
     * @param data  array where to search the elements
     * @param start start index
     * @param size  size of the range
     */
    static final boolean containsAll(Collection c, Object[] data, int start,
                                     int size) {
        if (size == 0) {
            return false;
        }
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (indexOf(next, data, start, size) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the index in the specified range of the data array
     * of the last occurrence of the specified element
     *
     * @param o     element to search
     * @param data  array where to search
     * @param start start index
     * @param size  size of the range
     * @return
     */
    static final int lastIndexOf(Object o, Object[] data, int start, int size) {
        if (size == 0) {
            return -1;
        }
        if (o != null) {
            for (int i = start + size - 1; i > start - 1; i--) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        } else {
            for (int i = start + size - 1; i > start - 1; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index in the specified range of the data array
     * of the first occurrence of the specified element
     *
     * @param o     element to search
     * @param data  array where to search
     * @param start start index
     * @param size  end index
     * @return
     */
    static final int indexOf(Object o, Object[] data, int start, int size) {
        if (size == 0) {
            return -1;
        }
        if (o == null) {
            for (int i = start; i < start + size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < start + size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Throws <code>IndexOutOfBoundsException</code> if <code>index</code>
     * is out of the list bounds.
     *
     * @param index element index to check.
     */
    static final void checkIndexInclusive(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is " + index + ", size is " + size);
        }
    }

    /**
     * Throws <code>IndexOutOfBoundsException</code> if <code>index</code>
     * is out of the list bounds. Excluding the last element.
     *
     * @param index element index to check.
     */
    static final void checkIndexExlusive(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is " + index + ", size is " + size);
        }
    }

    /**
     * gets the internal data array
     *
     * @return the data array
     */
    final E[] getArray() {
        return arr;
    }

    /**
     * list iterator implementation,
     * when created gets snapshot of the list,
     * so never throws ConcurrentModificationException
     */
    private static class ListIteratorImpl implements ListIterator {
        private final Object[] arr;

        private int current;

        private final int size;

        final int size() {
            return size;
        }

        public ListIteratorImpl(Object[] data, int current) {
            this.current = current;
            arr = data;
            size = data.length;
        }

        public void add(Object o) {
            throw new UnsupportedOperationException("Unsupported operation add");
        }

        public boolean hasNext() {
            if (current < size) {
                return true;
            }
            return false;
        }

        public boolean hasPrevious() {
            return current > 0;
        }

        public Object next() {
            if (hasNext()) {
                return arr[current++];
            }
            throw new NoSuchElementException("pos is " + current + ", size is " + size);
        }

        public int nextIndex() {
            return current;
        }

        public Object previous() {
            if (hasPrevious()) {
                return arr[--current];
            }
            throw new NoSuchElementException("pos is " + (current-1) + ", size is " + size);
        }

        public int previousIndex() {
            return current - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation remove");
        }

        public void set(Object o) {
            throw new UnsupportedOperationException("Unsupported operation set");
        }

    }

    /**
     * Keeps a state of sublist implementation,
     * size and array declared as final,
     * so we'll never get the unconsistent state
     */
    static final class SubListReadData {
        final int size;

        final Object[] data;

        SubListReadData(int size, Object[] data) {
            this.size = size;
            this.data = data;
        }
    }

    /**
     * Represents a list returned by <code>sublist()</code>.
     */
    static class SubList implements List {
        private final CopyOnWriteArrayList list;

        private volatile SubListReadData read;

        private final int start;

        /**
         * Sublist constructor.
         *
         * @param list    backing list.
         * @param fromIdx startingIndex, inclusive
         * @param toIdx   endIndex, exclusive
         */
        public SubList(CopyOnWriteArrayList list, int fromIdx, int toIdx) {
            this.list = list;
            Object[] data = list.getData();
            int size = toIdx - fromIdx;
            checkIndexExlusive(fromIdx, data.length);
            checkIndexInclusive(toIdx, data.length);
            read = new SubListReadData(size, list.getData());
            start = fromIdx;
        }

        /**
         * throws ConcurrentModificationException when the list
         * is structurally modified in the other way other than via this subList
         * <p/>
         * Should be called under lock!
         */
        private void checkModifications() {
            if (read.data != list.getData()) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * @see java.util.List#listIterator(int)
         */
        public ListIterator listIterator(int startIdx) {
            return new SubListIterator(startIdx, read);
        }

        /**
         * @see java.util.List#set(int, java.lang.Object)
         */
        public Object set(int index, Object obj) {
            list.lock.lock();
            try {
                checkIndexExlusive(index, read.size);
                checkModifications();
                Object result = list.set(index + start, obj);
                read = new SubListReadData(read.size, list.getData());
                return result;
            } finally {
                list.lock.unlock();
            }
        }

        /**
         * @see java.util.List#get(int)
         */
        public Object get(int index) {
            SubListReadData data = read;
            if (data.data != list.getData()) {
                list.lock.lock();
                try {
                    data = read;
                    if (data.data != list.getData()) {
                        throw new ConcurrentModificationException();
                    }
                } finally {
                    list.lock.unlock();
                }
            }
            checkIndexExlusive(index, data.size);
            return data.data[index + start];
        }

        /**
         * @see java.util.Collection#size()
         */
        public int size() {
            return read.size;
        }

        /**
         * @see java.util.List#remove(int)
         */
        public Object remove(int index) {
            list.lock.lock();
            try {
                checkIndexExlusive(index, read.size);
                checkModifications();
                Object obj = list.remove(index + start);
                read = new SubListReadData(read.size - 1, list.getData());
                return obj;
            } finally {
                list.lock.unlock();
            }
        }

        /**
         * @see java.util.List#add(int, java.lang.Object)
         */
        public void add(int index, Object object) {
            list.lock.lock();
            try {
                checkIndexInclusive(index, read.size);
                checkModifications();
                list.add(index + start, object);
                read = new SubListReadData(read.size + 1, list.getData());
            } finally {
                list.lock.unlock();
            }
        }

        public boolean add(Object o) {
            list.lock.lock();
            try {
                checkModifications();
                list.add(start + read.size, o);
                read = new SubListReadData(read.size + 1, list.getData());
                return true;
            } finally {
                list.lock.unlock();
            }
        }

        public boolean addAll(Collection c) {
            list.lock.lock();
            try {
                checkModifications();
                int d = list.size();
                list.addAll(start + read.size, c);
                read = new SubListReadData(read.size + (list.size() - d), list
                        .getData());
                return true;
            } finally {
                list.lock.unlock();
            }
        }

        public void clear() {
            list.lock.lock();
            try {
                checkModifications();
                list.removeRange(start, read.size);
                read = new SubListReadData(0, list.getData());
            } finally {
                list.lock.unlock();
            }
        }

        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }

        public boolean containsAll(Collection c) {
            SubListReadData b = read;
            return CopyOnWriteArrayList.containsAll(c, b.data, start, b.size);
        }

        public int indexOf(Object o) {
            SubListReadData b = read;
            int ind = CopyOnWriteArrayList.indexOf(o, b.data, start, b.size)
                    - start;
            return ind < 0 ? -1 : ind;
        }

        public boolean isEmpty() {
            return read.size == 0;
        }

        public Iterator iterator() {
            return new SubListIterator(0, read);
        }

        public int lastIndexOf(Object o) {
            SubListReadData b = read;
            int ind = CopyOnWriteArrayList
                    .lastIndexOf(o, b.data, start, b.size)
                    - start;
            return ind < 0 ? -1 : ind;
        }

        public ListIterator listIterator() {
            return new SubListIterator(0, read);
        }

        public boolean remove(Object o) {
            list.lock.lock();
            try {
                checkModifications();
                int i = indexOf(o);
                if (i == -1) {
                    return false;
                }
                boolean result = list.remove(i + start) != null;
                if (result) {
                    read = new SubListReadData(read.size - 1, list.getData());
                }
                return result;
            } finally {
                list.lock.unlock();
            }
        }

        public boolean removeAll(Collection c) {
            list.lock.lock();
            try {
                checkModifications();
                int removed = list.removeAll(c, start, read.size);
                if (removed > 0) {
                    read = new SubListReadData(read.size - removed, list
                            .getData());
                    return true;
                }
            } finally {
                list.lock.unlock();
            }
            return false;
        }

        public boolean retainAll(Collection c) {
            list.lock.lock();
            try {
                checkModifications();
                int removed = list.retainAll(c, start, read.size);
                if (removed > 0) {
                    read = new SubListReadData(read.size - removed, list
                            .getData());
                    return true;
                }
                return false;
            } finally {
                list.lock.unlock();
            }
        }

        public List subList(int fromIndex, int toIndex) {
            return new SubList(list, start + fromIndex, start + toIndex);
        }

        public Object[] toArray() {
            SubListReadData r = read;
            return CopyOnWriteArrayList.toArray(r.data, start, r.size);
        }

        public Object[] toArray(Object[] a) {
            SubListReadData r = read;
            return CopyOnWriteArrayList.toArray(a, r.data, start, r.size);
        }

        /**
         * @see java.util.List#addAll(int, java.util.Collection)
         */
        public boolean addAll(int index, Collection collection) {
            list.lock.lock();
            try {
                checkIndexInclusive(index, read.size);
                checkModifications();
                int d = list.size();
                boolean rt = list.addAll(index + start, collection);
                read = new SubListReadData(read.size + list.size() - d, list
                        .getData());
                return rt;
            } finally {
                list.lock.unlock();
            }
        }

        /**
         * Implementation of <code>ListIterator</code> for the
         * <code>SubList</code>
         * gets a snapshot of the sublist,
         * never throws ConcurrentModificationException
         */
        private class SubListIterator extends ListIteratorImpl {
            private final SubListReadData dataR;

            /**
             * Constructs an iterator starting with the given index
             *
             * @param index index of the first element to iterate.
             */
            private SubListIterator(int index, SubListReadData d) {
                super(d.data, index + start);
                this.dataR = d;
            }

            /**
             * @see java.util.ListIterator#nextIndex()
             */
            public int nextIndex() {
                return super.nextIndex() - start;
            }

            /**
             * @see java.util.ListIterator#previousIndex()
             */
            public int previousIndex() {
                return super.previousIndex() - start;
            }

            /**
             * @see java.util.Iterator#hasNext()
             */
            public boolean hasNext() {
                return nextIndex() < dataR.size;
            }

            /**
             * @see java.util.ListIterator#hasPrevious()
             */
            public boolean hasPrevious() {
                return previousIndex() > -1;
            }
        }

    }

    //serialization support
    /**
     * Writes the object state to the ObjectOutputStream.
     *
     * @param oos ObjectOutputStream to write object to.
     * @throws IOException if an I/O error occur.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        E[] back = getData();
        int size = back.length;
        oos.defaultWriteObject();
        oos.writeInt(size);
        for (int i = 0; i < size; i++) {
            oos.writeObject(back[i]);
        }
    }

    /**
     * Reads the object state from the ObjectOutputStream.
     *
     * @param ois ObjectInputStream to read object from.
     * @throws IOException if an I/O error occur.
     */
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
        int length = ois.readInt();
        if (length == 0) {
            setData(newElementArray(0));
        } else {
            E[] back = newElementArray(length);
            for (int i = 0; i < back.length; i++) {
                back[i] = (E) ois.readObject();
            }
            setData(back);
        }
    }

}