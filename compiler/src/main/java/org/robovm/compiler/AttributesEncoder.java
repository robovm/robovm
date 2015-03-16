/*
 * Copyright (C) 2012 RoboVM AB
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
package org.robovm.compiler;

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.robovm.compiler.llvm.ArrayConstant;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.Constant;
import org.robovm.compiler.llvm.FloatingPointConstant;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.PackedStructureConstant;
import org.robovm.compiler.llvm.PackedStructureType;
import org.robovm.compiler.llvm.StructureConstant;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.tagkit.AnnotationAnnotationElem;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationConstants;
import soot.tagkit.AnnotationDefaultTag;
import soot.tagkit.AnnotationDoubleElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationEnumElem;
import soot.tagkit.AnnotationFloatElem;
import soot.tagkit.AnnotationIntElem;
import soot.tagkit.AnnotationLongElem;
import soot.tagkit.AnnotationStringElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.EnclosingMethodTag;
import soot.tagkit.Host;
import soot.tagkit.InnerClassTag;
import soot.tagkit.SignatureTag;
import soot.tagkit.SourceFileTag;
import soot.tagkit.Tag;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;

/**
 * 
 * 
 * @author niklas
 *
 */
public class AttributesEncoder {
    private static final byte SOURCE_FILE = 1;
    private static final byte SIGNATURE = 2;
    private static final byte INNER_CLASS = 3;
    private static final byte ENCLOSING_METHOD = 4;
    private static final byte EXCEPTIONS = 5;
    private static final byte RUNTIME_VISIBLE_ANNOTATIONS = 6;
    private static final byte RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = 7;
    private static final byte ANNOTATION_DEFAULT = 8;

    /*
     * TODO: Generic signatures may contain class names which should be added to dependencies.
     */
    
    private ModuleBuilder mb;
    private Set<String> dependencies;
    private Global classAttributes;
    private Map<SootField, Global> fieldAttributes;
    private Map<SootMethod, Global> methodAttributes;
    
    public void encode(ModuleBuilder mb, SootClass sootClass) {
        this.mb = mb;
        dependencies = new HashSet<String>();
        classAttributes = null;
        fieldAttributes = new HashMap<SootField, Global>();
        methodAttributes = new HashMap<SootMethod, Global>();
        
        encodeAttributes(sootClass);
        Constant classAttributes = encodeAttributes(sootClass);
        if (classAttributes != null) {
            Global g = new Global(Symbols.classAttributesSymbol(sootClass), classAttributes, true);
            mb.addGlobal(g);
            this.classAttributes = g;
        }
        
        for (SootField field : sootClass.getFields()) {
            Constant fieldAttributes = encodeAttributes(field);
            if (fieldAttributes != null) {
                Global g = new Global(Symbols.fieldAttributesSymbol(field), fieldAttributes, true);
                mb.addGlobal(g);
                this.fieldAttributes.put(field, g);
            }
        }
        
        for (SootMethod method : sootClass.getMethods()) {
            Constant methodAttributes = encodeAttributes(method);
            if (methodAttributes != null) {
                Global g = new Global(Symbols.methodAttributesSymbol(method), methodAttributes, true);
                mb.addGlobal(g);
                this.methodAttributes.put(method, g);
            }
        }
    }
    
    public Set<String> getDependencies() {
        return dependencies;
    }
    
    public boolean classHasAttributes() {
        return classAttributes != null;
    }

    public Global getClassAttributes() {
        return classAttributes;
    }
    
    public boolean fieldHasAttributes(SootField f) {
        return fieldAttributes.containsKey(f);
    }
    
    public Global getFieldAttributes(SootField f) {
        return fieldAttributes.get(f);
    }
    
    public boolean methodHasAttributes(SootMethod m) {
        return methodAttributes.containsKey(m);
    }
    
    public Global getMethodAttributes(SootMethod m) {
        return methodAttributes.get(m);
    }
    
    private PackedStructureType getAnnotationElementType(AnnotationElem ae) {
        if (ae instanceof AnnotationIntElem) {
            return new PackedStructureType(I8, I32);
        } else if (ae instanceof AnnotationLongElem) {
            return new PackedStructureType(I8, I64);            
        } else if (ae instanceof AnnotationFloatElem) {
            return new PackedStructureType(I8, FLOAT);            
        } else if (ae instanceof AnnotationDoubleElem) {
            return new PackedStructureType(I8, DOUBLE);            
        } else if (ae instanceof AnnotationStringElem) {
            return new PackedStructureType(I8, I8_PTR);            
        } else if (ae instanceof AnnotationClassElem) {
            return new PackedStructureType(I8, I8_PTR);            
        } else if (ae instanceof AnnotationEnumElem) {
            return new PackedStructureType(I8, I8_PTR, I8_PTR);            
        } else if (ae instanceof AnnotationArrayElem) {
            AnnotationArrayElem aae = (AnnotationArrayElem) ae;
            Type[] types = new Type[aae.getNumValues() + 2];
            types[0] = I8;
            types[1] = I16;
            for (int i = 0; i < aae.getNumValues(); i++) {
                types[i + 2] = getAnnotationElementType(aae.getValueAt(i));
            }
            return new PackedStructureType(types);            
        } else if (ae instanceof AnnotationAnnotationElem) {
            AnnotationAnnotationElem aae = (AnnotationAnnotationElem) ae;
            return new PackedStructureType(I8, getAnnotationTagType(aae.getValue()));            
        }
        throw new IllegalArgumentException("Unknown AnnotationElem type: " + ae.getClass());
    }
    
    private PackedStructureConstant encodeAnnotationElementValue(AnnotationElem ae) {
        PackedStructureType type = getAnnotationElementType(ae);
        Value kind = new IntegerConstant((byte) ae.getKind());
        if (ae instanceof AnnotationIntElem) {
            AnnotationIntElem aie = (AnnotationIntElem) ae;
            return new PackedStructureConstant(type, kind,
                    new IntegerConstant(aie.getValue()));
        } else if (ae instanceof AnnotationLongElem) {
            AnnotationLongElem ale = (AnnotationLongElem) ae;
            return new PackedStructureConstant(type, kind,
                    new IntegerConstant(ale.getValue()));            
        } else if (ae instanceof AnnotationFloatElem) {
            AnnotationFloatElem afe = (AnnotationFloatElem) ae;
            return new PackedStructureConstant(type, kind,
                    new FloatingPointConstant(afe.getValue()));            
        } else if (ae instanceof AnnotationDoubleElem) {
            AnnotationDoubleElem ade = (AnnotationDoubleElem) ae;
            return new PackedStructureConstant(type, kind,
                    new FloatingPointConstant(ade.getValue()));            
        } else if (ae instanceof AnnotationStringElem) {
            AnnotationStringElem ase = (AnnotationStringElem) ae;
            return new PackedStructureConstant(type, kind,
                    getStringOrNull(ase.getValue()));            
        } else if (ae instanceof AnnotationClassElem) {
            AnnotationClassElem ace = (AnnotationClassElem) ae;
            addDependency(ace.getDesc());
            return new PackedStructureConstant(type, kind,
                    getStringOrNull(ace.getDesc()));            
        } else if (ae instanceof AnnotationEnumElem) {
            AnnotationEnumElem aee = (AnnotationEnumElem) ae;
            addDependency(aee.getTypeName());
            return new PackedStructureConstant(type, kind,
                    getStringOrNull(aee.getTypeName()),            
                    getStringOrNull(aee.getConstantName()));            
        } else if (ae instanceof AnnotationArrayElem) {
            AnnotationArrayElem aae = (AnnotationArrayElem) ae;
            Value[] values = new Value[aae.getNumValues() + 2];
            values[0] = kind;
            values[1] = new IntegerConstant((char) aae.getNumValues());
            for (int i = 0; i < aae.getNumValues(); i++) {
                values[i + 2] = encodeAnnotationElementValue(aae.getValueAt(i));
            }
            return new PackedStructureConstant(type, values);
        } else if (ae instanceof AnnotationAnnotationElem) {
            AnnotationAnnotationElem aae = (AnnotationAnnotationElem) ae;
            return new PackedStructureConstant(type, kind, encodeAnnotationTagValue(aae.getValue()));            
        }
        throw new IllegalArgumentException("Unknown AnnotationElem type: " + ae.getClass());
    }
    
    private PackedStructureType getAnnotationTagType(AnnotationTag tag) {
        Type[] types = new Type[tag.getNumElems() * 2 + 2];
        types[0] = I8_PTR;
        types[1] = I32;
        for (int i = 0; i < tag.getNumElems(); i++) {
            types[i * 2 + 2] = I8_PTR;
            types[i * 2 + 2 + 1] = getAnnotationElementType(tag.getElemAt(i));
        }
        return new PackedStructureType(types);            
    }
    
    private PackedStructureConstant encodeAnnotationTagValue(AnnotationTag tag) {
        Value[] values = new Value[tag.getNumElems() * 2 + 2];
        values[0] = getString(tag.getType());
        addDependency(tag.getType());
        values[1] = new IntegerConstant(tag.getNumElems());
        for (int i = 0; i < tag.getNumElems(); i++) {
            values[i * 2 + 2] = getString(tag.getElemAt(i).getName());
            values[i * 2 + 2 + 1] = encodeAnnotationElementValue(tag.getElemAt(i));
        }
        return new PackedStructureConstant(getAnnotationTagType(tag), values);
    }
    
    private Constant encodeAttributes(Host host) {
        List<Value> attributes = new ArrayList<Value>();
        for (Tag tag : host.getTags()) {
            if (tag instanceof SourceFileTag) {
                // Skip. We don't need this at this time.
                Value sourceFile = getString(((SourceFileTag) tag).getSourceFile());
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I8_PTR), 
                        new IntegerConstant(SOURCE_FILE), sourceFile));
            } else if (tag instanceof EnclosingMethodTag) {
                EnclosingMethodTag emt = (EnclosingMethodTag) tag;
                Value eClass = getString(emt.getEnclosingClass());
                addDependency(emt.getEnclosingClass());
                Value eMethod = getStringOrNull(emt.getEnclosingMethod());
                Value eDesc = getStringOrNull(emt.getEnclosingMethodSig());
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I8_PTR, I8_PTR, I8_PTR), 
                        new IntegerConstant(ENCLOSING_METHOD), eClass, eMethod, eDesc));
            } else if (tag instanceof SignatureTag) {
                Value signature = getString(((SignatureTag) tag).getSignature());
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I8_PTR), 
                        new IntegerConstant(SIGNATURE), signature));
            } else if (tag instanceof InnerClassTag) {
                InnerClassTag ict = (InnerClassTag) tag;
                Value innerClass = getStringOrNull(ict.getInnerClass());
                addDependency(ict.getInnerClass());
                Value outerClass = getStringOrNull(ict.getOuterClass());
                addDependency(ict.getOuterClass());
                Value innerName = getStringOrNull(ict.getShortName());
                Value innerClassAccess = new IntegerConstant(ict.getAccessFlags());
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I8_PTR, I8_PTR, I8_PTR, I32), 
                        new IntegerConstant(INNER_CLASS), innerClass, outerClass, innerName, innerClassAccess));
            } else if (tag instanceof AnnotationDefaultTag) {
                StructureConstant value = encodeAnnotationElementValue(((AnnotationDefaultTag) tag).getDefaultVal());
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, value.getType()), 
                        new IntegerConstant(ANNOTATION_DEFAULT), value));
            } else if (tag instanceof VisibilityAnnotationTag) {
                VisibilityAnnotationTag vat = (VisibilityAnnotationTag) tag;
                if (vat.getVisibility() == AnnotationConstants.RUNTIME_VISIBLE) {
                    Type[] types = new Type[vat.getAnnotations().size()];
                    Value[] values = new Value[vat.getAnnotations().size()];
                    int i = 0;
                    for (AnnotationTag at : vat.getAnnotations()) {
                        values[i] = encodeAnnotationTagValue(at);
                        types[i] = values[i].getType();
                        i++;
                    }
                    attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I32, new PackedStructureType(types)),
                            new IntegerConstant(RUNTIME_VISIBLE_ANNOTATIONS), new IntegerConstant(vat.getAnnotations().size()),
                            new PackedStructureConstant(new PackedStructureType(types), values)));
                }
            } else if (tag instanceof VisibilityParameterAnnotationTag) {
                VisibilityParameterAnnotationTag vpat = (VisibilityParameterAnnotationTag) tag;
                List<Type> typesList = new ArrayList<Type>();
                List<Value> valuesList = new ArrayList<Value>();
                boolean hasRuntimeVisible = false;
                for (VisibilityAnnotationTag vat : vpat.getVisibilityAnnotations()) {
                    typesList.add(I32);
                    if (vat.getVisibility() == AnnotationConstants.RUNTIME_VISIBLE
                            && vat.getAnnotations() != null && !vat.getAnnotations().isEmpty()) {
                        
                        hasRuntimeVisible = true;
                        valuesList.add(new IntegerConstant(vat.getAnnotations().size()));
                        for (AnnotationTag at : vat.getAnnotations()) {
                            valuesList.add(encodeAnnotationTagValue(at));
                            typesList.add(valuesList.get(valuesList.size() - 1).getType());
                        }
                    } else {
                        valuesList.add(new IntegerConstant(0));                        
                    }
                }
                if (hasRuntimeVisible) {
                    Type[] types = typesList.toArray(new Type[typesList.size()]);
                    Value[] values = valuesList.toArray(new Value[valuesList.size()]);
                    attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I32, new PackedStructureType(types)),
                            new IntegerConstant(RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS), new IntegerConstant(vpat.getVisibilityAnnotations().size()),
                            new PackedStructureConstant(new PackedStructureType(types), values)));
                }
            }
        }
        if (host instanceof SootMethod) {
            List<SootClass> exceptions = ((SootMethod) host).getExceptions();
            if (!exceptions.isEmpty()) {
                Value[] values = new Value[exceptions.size()];
                for (int i = 0; i < exceptions.size(); i++) {
                    String exName = getInternalName(exceptions.get(i));
                    values[i] = getString(exName);
                    addDependency(exName);
                }
                attributes.add(new PackedStructureConstant(new PackedStructureType(I8, I32, new ArrayType(exceptions.size(), I8_PTR)), 
                        new IntegerConstant(EXCEPTIONS), new IntegerConstant(exceptions.size()), 
                        new ArrayConstant(new ArrayType(exceptions.size(), I8_PTR), values)));
            }
        }
        
        if (attributes.isEmpty()) {
            return null;
        }
        
        attributes.add(0, new IntegerConstant(attributes.size()));
        
        Type[] types = new Type[attributes.size()];
        for (int i = 0; i < types.length; i++) {
            types[i] = attributes.get(i).getType();
        }
        return new PackedStructureConstant(new PackedStructureType(types), attributes.toArray(new Value[0]));
    }
    
    private Constant getString(String string) {
        return mb.getString(string);
    }
    
    private Constant getStringOrNull(String string) {
        return mb.getStringOrNull(string);
    }
    
    private void addDependency(String s) {
        if (s == null || isArray(s) && isPrimitiveBaseType(s)) {
            return;
        }
        int start = 0;
        int end = s.length();
        if (isArray(s)) {
            start = s.indexOf('L') + 1;
            end--;
        } else if (s.charAt(0) == 'L' && s.charAt(end - 1) == ';') {
            start = 1;
            end--;
        }
        dependencies.add(s.substring(start, end));
    }
}
