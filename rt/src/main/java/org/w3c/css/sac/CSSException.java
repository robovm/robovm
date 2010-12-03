/*
 * Copyright (c) 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * The original version of this interface comes from SAX :
 * http://www.megginson.com/SAX/
 *
 * $Id: CSSException.java 477010 2006-11-20 02:54:38Z mrglavas $
 */
package org.w3c.css.sac;

/**
 * @version $Revision: 477010 $
 * @author  Philippe Le Hegaret
 */
public class CSSException extends RuntimeException {

    protected String s;

    /**
     * this error is unspecified.
     */    
    public static final short SAC_UNSPECIFIED_ERR   = 0;

    /**
     * If the operation is not supported
     */    
    public static final short SAC_NOT_SUPPORTED_ERR = 1;

    /**
     * If an invalid or illegal string is specified
     */    
    public static final short SAC_SYNTAX_ERR        = 2;

    /*
     * Default message for unspecified error.
     */
    protected static final String S_SAC_UNSPECIFIED_ERR
	= "unknown error";
    /*
     * Default message for not supported error.
     */
    protected static final String S_SAC_NOT_SUPPORTED_ERR
	= "not supported";
    /*
     * Default message for syntax error.
     */
    protected static final String S_SAC_SYNTAX_ERR
	= "syntax error";

    /**
     * The internal exception.
     */    
    protected Exception e;

    protected short     code;

    /**
     * Creates a new CSSException
     */
    public CSSException() {
    }

    /**
     * Creates a new CSSException
     */
    public CSSException(String s) {
	this.code = SAC_UNSPECIFIED_ERR;
        this.s = s;
    }
    
    /**
     * Creates a new CSSException with an embeded exception.
     * @param a the embeded exception.
     */
    public CSSException(Exception e) {
	this.code = SAC_UNSPECIFIED_ERR;
        this.e = e;
    }

    /**
     * Creates a new CSSException with a specific code.
     * @param a the embeded exception.
     */
    public CSSException(short code) {
        this.code = code;
    }

    /**
     * Creates a new CSSException with an embeded exception and a specified
     * message.
     * @param code the specified code.
     * @param e the embeded exception.  
     */
    public CSSException(short code, String s, Exception e) {
	this.code = code;
	this.s = s;
        this.e = e;
    }

    /**
     * Returns the detail message of this throwable object. 
     *
     * @return the detail message of this Throwable, or null if this Throwable
     *         does not have a detail message.  
     */
    public String getMessage() {
	if (s != null) {
	    return s;
	} else if (e != null) {
	    return e.getMessage();
	} else {
	    switch (code) {
	    case SAC_UNSPECIFIED_ERR:
		return S_SAC_UNSPECIFIED_ERR;
	    case SAC_NOT_SUPPORTED_ERR:
		return S_SAC_NOT_SUPPORTED_ERR;
	    case SAC_SYNTAX_ERR:
		return S_SAC_SYNTAX_ERR;
	    default:
		return null;
	    }
	}
    }

    /**
     * returns the error code for this exception.
     */    
    public short getCode() {
	return code;
    }

    /**
     * Returns the internal exception if any, null otherwise.
     */    
    public Exception getException() {
	return e;
    }

}
