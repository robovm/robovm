/*
 * Copyright (C) 2013 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.llvm;

/**
 * 
 */
@SuppressWarnings("serial")
public class LlvmException extends RuntimeException {

    public LlvmException() {
    }

    public LlvmException(String message) {
        super(message);
    }

    public LlvmException(Throwable cause) {
        super(cause);
    }

    public LlvmException(String message, Throwable cause) {
        super(message, cause);
    }
}
