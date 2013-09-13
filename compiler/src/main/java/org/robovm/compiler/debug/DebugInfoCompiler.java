/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler.debug;

import org.robovm.compiler.config.Config;

import soot.jimple.Stmt;
import soot.tagkit.LineNumberTag;
import soot.tagkit.Tag;

/**
 * Responsible for generating debug information and soft breakpoints in method
 * bodies. Used in MethodCompiler.
 * @author badlogic
 *
 */
public class DebugInfoCompiler {
	private final Config config;
	
	public DebugInfoCompiler(Config config) {
		this.config = config;
	}
	
	private int getLineNumber(Stmt stmt) {
 		for(Tag tag: stmt.getTags()) {
 			if(tag instanceof LineNumberTag) {
 				return ((LineNumberTag) tag).getLineNumber();
 			}
 		}
 		return -1;
 	}
}
