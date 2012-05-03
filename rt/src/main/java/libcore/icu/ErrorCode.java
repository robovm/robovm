/**
******************************************************************************
* Copyright (C) 1996-2005, International Business Machines Corporation and   *
* others. All Rights Reserved.                                               *
******************************************************************************
*
******************************************************************************
*/

package libcore.icu;

/**
 * Error exception class mapping ICU error codes of the enum UErrorCode
 * @author syn wee quek
*/
public final class ErrorCode extends Exception {
    public static boolean isFailure(int error) {
        return error > U_ZERO_ERROR && error < U_ERROR_LIMIT;
    }

    public static RuntimeException throwException(int error) {
        if (error <= U_ZERO_ERROR && error >= U_ERROR_LIMIT) {
            return null;
        }
        switch (error) {
        case U_ILLEGAL_ARGUMENT_ERROR:
            return new IllegalArgumentException(ERROR_NAMES[error]);
        case U_INDEX_OUTOFBOUNDS_ERROR:
        case U_BUFFER_OVERFLOW_ERROR:
            return new ArrayIndexOutOfBoundsException(ERROR_NAMES[error]);
        case U_UNSUPPORTED_ERROR:
            return new UnsupportedOperationException(ERROR_NAMES[error]);
        }
        throw new RuntimeException(ERROR_NAMES[error]);
    }

    // The errors needed by our CharsetDecoderICU/CharsetEncoderICU.
    public static final int U_ZERO_ERROR = 0;
    private static final int U_ILLEGAL_ARGUMENT_ERROR = 1;
    private static final int U_INDEX_OUTOFBOUNDS_ERROR = 8;
    public static final int U_INVALID_CHAR_FOUND = 10;
    public static final int U_TRUNCATED_CHAR_FOUND = 11;
    public static final int U_ILLEGAL_CHAR_FOUND = 12;
    public static final int U_BUFFER_OVERFLOW_ERROR = 15;
    private static final int U_UNSUPPORTED_ERROR = 16;
    private static final int U_ERROR_LIMIT = 21;

    // TODO: this list is incomplete; get these from native code!
    private static final String ERROR_NAMES[] = {
        "U_ZERO_ERROR",
        "U_ILLEGAL_ARGUMENT_ERROR",
        "U_MISSING_RESOURCE_ERROR",
        "U_INVALID_FORMAT_ERROR",
        "U_FILE_ACCESS_ERROR",
        "U_INTERNAL_PROGRAM_ERROR",
        "U_MESSAGE_PARSE_ERROR",
        "U_MEMORY_ALLOCATION_ERROR",
        "U_INDEX_OUTOFBOUNDS_ERROR",
        "U_PARSE_ERROR",
        "U_INVALID_CHAR_FOUND",
        "U_TRUNCATED_CHAR_FOUND",
        "U_ILLEGAL_CHAR_FOUND",
        "U_INVALID_TABLE_FORMAT",
        "U_INVALID_TABLE_FILE",
        "U_BUFFER_OVERFLOW_ERROR",
        "U_UNSUPPORTED_ERROR",
        "U_RESOURCE_TYPE_MISMATCH",
        "U_ILLEGAL_ESCAPE_SEQUENCE",
        "U_UNSUPPORTED_ESCAPE_SEQUENCE"
    };
}
