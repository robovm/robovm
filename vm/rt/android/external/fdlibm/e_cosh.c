
/* @(#)e_cosh.c 1.3 95/01/18 */
/*
 * ====================================================
 * Copyright (C) 1993 by Sun Microsystems, Inc. All rights reserved.
 *
 * Developed at SunSoft, a Sun Microsystems, Inc. business.
 * Permission to use, copy, modify, and distribute this
 * software is freely granted, provided that this notice 
 * is preserved.
 * ====================================================
 */

/* __ieee754_cosh(x)
 * Method : 
 * mathematically ieee_cosh(x) if defined to be (ieee_exp(x)+ieee_exp(-x))/2
 *	1. Replace x by |x| (ieee_cosh(x) = ieee_cosh(-x)). 
 *	2. 
 *		                                        [ ieee_exp(x) - 1 ]^2 
 *	    0        <= x <= ln2/2  :  ieee_cosh(x) := 1 + -------------------
 *			       			           2*ieee_exp(x)
 *
 *		                                  ieee_exp(x) +  1/ieee_exp(x)
 *	    ln2/2    <= x <= 22     :  ieee_cosh(x) := -------------------
 *			       			          2
 *	    22       <= x <= lnovft :  ieee_cosh(x) := ieee_exp(x)/2 
 *	    lnovft   <= x <= ln2ovft:  ieee_cosh(x) := ieee_exp(x/2)/2 * ieee_exp(x/2)
 *	    ln2ovft  <  x	    :  ieee_cosh(x) := huge*huge (overflow)
 *
 * Special cases:
 *	cosh(x) is |x| if x is +INF, -INF, or NaN.
 *	only ieee_cosh(0)=1 is exact for finite x.
 */

#include "fdlibm.h"

#ifdef __STDC__
static const double one = 1.0, half=0.5, huge = 1.0e300;
#else
static double one = 1.0, half=0.5, huge = 1.0e300;
#endif

#ifdef __STDC__
	double __ieee754_cosh(double x)
#else
	double __ieee754_cosh(x)
	double x;
#endif
{	
	double t,w;
	int ix;
	unsigned lx;

    /* High word of |x|. */
	ix = __HI(x);
	ix &= 0x7fffffff;

    /* x is INF or NaN */
	if(ix>=0x7ff00000) return x*x;	

    /* |x| in [0,0.5*ln2], return 1+ieee_expm1(|x|)^2/(2*ieee_exp(|x|)) */
	if(ix<0x3fd62e43) {
	    t = ieee_expm1(ieee_fabs(x));
	    w = one+t;
	    if (ix<0x3c800000) return w;	/* ieee_cosh(tiny) = 1 */
	    return one+(t*t)/(w+w);
	}

    /* |x| in [0.5*ln2,22], return (ieee_exp(|x|)+1/ieee_exp(|x|)/2; */
	if (ix < 0x40360000) {
		t = __ieee754_exp(ieee_fabs(x));
		return half*t+half/t;
	}

    /* |x| in [22, ieee_log(maxdouble)] return half*ieee_exp(|x|) */
	if (ix < 0x40862E42)  return half*__ieee754_exp(ieee_fabs(x));

    /* |x| in [log(maxdouble), overflowthresold] */
	lx = *( (((*(unsigned*)&one)>>29)) + (unsigned*)&x);
	if (ix<0x408633CE || 
	      (ix==0x408633ce)&&(lx<=(unsigned)0x8fb9f87d)) {
	    w = __ieee754_exp(half*ieee_fabs(x));
	    t = half*w;
	    return t*w;
	}

    /* |x| > overflowthresold, ieee_cosh(x) overflow */
	return huge*huge;
}
