/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/params/BasicHttpParams.java $
 * $Revision: 610464 $
 * $Date: 2008-01-09 09:10:55 -0800 (Wed, 09 Jan 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.params;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.params.HttpParams;

/**
 * This class represents a collection of HTTP protocol parameters.
 * Protocol parameters may be linked together to form a hierarchy.
 * If a particular parameter value has not been explicitly defined
 * in the collection itself, its value will be drawn from the parent 
 * collection of parameters.
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @version $Revision: 610464 $
 */
public final class BasicHttpParams extends AbstractHttpParams
    implements Serializable, Cloneable {

    private static final long serialVersionUID = -7086398485908701455L;

    /** Map of HTTP parameters that this collection contains. */
    private HashMap parameters;

    public BasicHttpParams() {
        super();
    }

    public Object getParameter(final String name) {
        // See if the parameter has been explicitly defined
        Object param = null;
        if (this.parameters != null) {
            param = this.parameters.get(name);
        }    
        return param;
    }

    public HttpParams setParameter(final String name, final Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.put(name, value);
        return this;
    }
    
    public boolean removeParameter(String name) {
        if (this.parameters == null) {
            return false;
        }
        //this is to avoid the case in which the key has a null value
        if (this.parameters.containsKey(name)) {
            this.parameters.remove(name);
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * Assigns the value to all the parameter with the given names
     * 
     * @param names array of parameter name
     * @param value parameter value
     */ 
    public void setParameters(final String[] names, final Object value) {
        for (int i = 0; i < names.length; i++) {
            setParameter(names[i], value);
        }
    }

    public boolean isParameterSet(final String name) {
        return getParameter(name) != null;
    }
        
    public boolean isParameterSetLocally(final String name) {
        return this.parameters != null && this.parameters.get(name) != null;
    }
        
    /**
     * Removes all parameters from this collection.
     */
    public void clear() {
        this.parameters = null;
    }

    /**
     * Creates a copy of these parameters.
     * The implementation here instantiates {@link BasicHttpParams}, 
     * then calls {@link #copyParams(HttpParams)} to populate the copy.
     *
     * @return  a new set of params holding a copy of the
     *          <i>local</i> parameters in this object.
     */
    public HttpParams copy() {
        BasicHttpParams clone = new BasicHttpParams();
        copyParams(clone);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams clone = (BasicHttpParams) super.clone();
        copyParams(clone);
        return clone;
    }
 
    /**
     * Copies the locally defined parameters to the argument parameters.
     * This method is called from {@link #copy()}.
     *
     * @param target    the parameters to which to copy
     */
    protected void copyParams(HttpParams target) {
        if (this.parameters == null)
            return;

        Iterator iter = parameters.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry me = (Map.Entry) iter.next();
            if (me.getKey() instanceof String)
                target.setParameter((String)me.getKey(), me.getValue());
        }
    }
    
}
