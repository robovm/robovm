/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id: SACMediaList.java 477010 2006-11-20 02:54:38Z mrglavas $
 */
package org.w3c.css.sac;

/**
 * @version $Revision: 477010 $
 * @author  Philippe Le Hegaret
 */
public interface SACMediaList {

    /**
     * Returns the length of this media list
     */    
    public int getLength();

    /**
     * Returns the medium at the specified index, or <code>null</code> if this
     * is not a valid index.  
     */
    public String item(int index);
}
