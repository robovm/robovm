
/* @(#)s_significand.c 1.3 95/01/18 */
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

/*
 * ieee_significand(x) computes just
 * 	scalb(x, (double) -ieee_ilogb(x)),
 * for exercising the fraction-part(F) IEEE 754-1985 test vector.
 */

#include "fdlibm.h"

#ifdef __STDC__
	double ieee_significand(double x)
#else
	double ieee_significand(x)
	double x;
#endif
{
	return __ieee754_scalb(x,(double) -ieee_ilogb(x));
}
