/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Ilya S. Okomin
 */
package java.awt.font;

import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.HashMap;
import java.util.Map;

public final class TextAttribute extends Attribute {
    private static final long serialVersionUID = 7744112784117861702L;

    // set of available text attributes
    private static final Map<String, TextAttribute> attrMap = new HashMap<String, TextAttribute>();

    protected TextAttribute(String name) {
        super(name);
        attrMap.put(name, this);
    }

    @Override
    protected Object readResolve() throws InvalidObjectException {
        TextAttribute result = attrMap.get(this.getName());
        if (result != null) {
            return result;
        }
        // awt.194=Unknown attribute name
        throw new InvalidObjectException("Unknown attribute name"); //$NON-NLS-1$
    }

    public static final TextAttribute BACKGROUND = new TextAttribute("background"); //$NON-NLS-1$

    public static final TextAttribute BIDI_EMBEDDING = new TextAttribute("bidi_embedding"); //$NON-NLS-1$

    public static final TextAttribute CHAR_REPLACEMENT = new TextAttribute("char_replacement"); //$NON-NLS-1$

    public static final TextAttribute FAMILY = new TextAttribute("family"); //$NON-NLS-1$

    public static final TextAttribute FONT = new TextAttribute("font"); //$NON-NLS-1$

    public static final TextAttribute FOREGROUND = new TextAttribute("foreground"); //$NON-NLS-1$

    public static final TextAttribute INPUT_METHOD_HIGHLIGHT = new TextAttribute(
            "input method highlight"); //$NON-NLS-1$

    public static final TextAttribute INPUT_METHOD_UNDERLINE = new TextAttribute(
            "input method underline"); //$NON-NLS-1$

    public static final TextAttribute JUSTIFICATION = new TextAttribute("justification"); //$NON-NLS-1$

    public static final Float JUSTIFICATION_FULL = new Float(1.0f);

    public static final Float JUSTIFICATION_NONE = new Float(0.0f);

    public static final TextAttribute NUMERIC_SHAPING = new TextAttribute("numeric_shaping"); //$NON-NLS-1$

    public static final TextAttribute POSTURE = new TextAttribute("posture"); //$NON-NLS-1$

    public static final Float POSTURE_REGULAR = new Float(0.0f);

    public static final Float POSTURE_OBLIQUE = new Float(0.20f);

    public static final TextAttribute RUN_DIRECTION = new TextAttribute("run_direction"); //$NON-NLS-1$

    public static final Boolean RUN_DIRECTION_LTR = new Boolean(false);

    public static final Boolean RUN_DIRECTION_RTL = new Boolean(true);

    public static final TextAttribute SIZE = new TextAttribute("size"); //$NON-NLS-1$

    public static final TextAttribute STRIKETHROUGH = new TextAttribute("strikethrough"); //$NON-NLS-1$

    public static final Boolean STRIKETHROUGH_ON = new Boolean(true);

    public static final TextAttribute SUPERSCRIPT = new TextAttribute("superscript"); //$NON-NLS-1$

    public static final Integer SUPERSCRIPT_SUB = new Integer(-1);

    public static final Integer SUPERSCRIPT_SUPER = new Integer(1);

    public static final TextAttribute SWAP_COLORS = new TextAttribute("swap_colors"); //$NON-NLS-1$

    public static final Boolean SWAP_COLORS_ON = new Boolean(true);

    public static final TextAttribute TRANSFORM = new TextAttribute("transform"); //$NON-NLS-1$

    public static final TextAttribute UNDERLINE = new TextAttribute("underline"); //$NON-NLS-1$

    public static final Integer UNDERLINE_ON = new Integer(0);

    public static final Integer UNDERLINE_LOW_ONE_PIXEL = new Integer(1);

    public static final Integer UNDERLINE_LOW_TWO_PIXEL = new Integer(2);

    public static final Integer UNDERLINE_LOW_DOTTED = new Integer(3);

    public static final Integer UNDERLINE_LOW_GRAY = new Integer(4);

    public static final Integer UNDERLINE_LOW_DASHED = new Integer(5);

    public static final TextAttribute WEIGHT = new TextAttribute("weight"); //$NON-NLS-1$

    public static final Float WEIGHT_EXTRA_LIGHT = new Float(0.5f);

    public static final Float WEIGHT_LIGHT = new Float(0.75f);

    public static final Float WEIGHT_DEMILIGHT = new Float(0.875f);

    public static final Float WEIGHT_REGULAR = new Float(1.0f);

    public static final Float WEIGHT_SEMIBOLD = new Float(1.25f);

    public static final Float WEIGHT_MEDIUM = new Float(1.5f);

    public static final Float WEIGHT_DEMIBOLD = new Float(1.75f);

    public static final Float WEIGHT_BOLD = new Float(2.0f);

    public static final Float WEIGHT_HEAVY = new Float(2.25f);

    public static final Float WEIGHT_EXTRABOLD = new Float(2.5f);

    public static final Float WEIGHT_ULTRABOLD = new Float(2.75f);

    public static final TextAttribute WIDTH = new TextAttribute("width"); //$NON-NLS-1$

    public static final Float WIDTH_CONDENSED = new Float(0.75f);

    public static final Float WIDTH_SEMI_CONDENSED = new Float(0.875f);

    public static final Float WIDTH_REGULAR = new Float(1.0f);

    public static final Float WIDTH_SEMI_EXTENDED = new Float(1.25f);

    public static final Float WIDTH_EXTENDED = new Float(1.5f);

}
