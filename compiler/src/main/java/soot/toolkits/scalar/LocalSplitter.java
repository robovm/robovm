/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Raja Vallee-Rai
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */





package soot.toolkits.scalar;
import soot.options.*;

import soot.*;
import soot.toolkits.graph.*;
import soot.util.*;
import java.util.*;

/**
 *    A BodyTransformer that attemps to indentify and separate uses of a local
 *    varible that are independent of each other. Conceptually the inverse transform
 *    with respect to the LocalPacker transform.
 *
 *    For example the code:
 *
 *    for(int i; i < k; i++);
 *    for(int i; i < k; i++);
 *
 *    would be transformed into:
 *    for(int i; i < k; i++);
 *    for(int j; j < k; j++);
 *
 *
 *    @see BodyTransformer
 *    @see LocalPacker
 *    @see Body 
 */
public class LocalSplitter extends BodyTransformer
{
    public LocalSplitter( Singletons.Global g ) {}
    public static LocalSplitter v() { return G.v().soot_toolkits_scalar_LocalSplitter(); }

    protected void internalTransform(Body body, String phaseName, Map options)
    {
        Chain units = body.getUnits();
        List<List> webs = new ArrayList<List>();

        if(Options.v().verbose())
            G.v().out.println("[" + body.getMethod().getName() + "] Splitting locals...");

        Map boxToSet = new HashMap(units.size() * 2 + 1, 0.7f);

        if(Options.v().time())
                Timers.v().splitPhase1Timer.start();

        // Go through the definitions, building the webs
        {
            ExceptionalUnitGraph graph = new ExceptionalUnitGraph(body);

            LocalDefs localDefs;
            
            localDefs = new SmartLocalDefs(graph, new SimpleLiveLocals(graph));

            LocalUses localUses = new SimpleLocalUses(graph, localDefs);
            
            if(Options.v().time())
                Timers.v().splitPhase1Timer.end();
    
            if(Options.v().time())
                Timers.v().splitPhase2Timer.start();

            Set<ValueBox> markedBoxes = new HashSet<ValueBox>();
            Map<ValueBox, Unit> boxToUnit = new HashMap<ValueBox, Unit>(units.size() * 2 + 1, 0.7f);
            
            Iterator codeIt = units.iterator();

            while(codeIt.hasNext())
            {
                Unit s = (Unit) codeIt.next();

                if (s.getDefBoxes().size() > 1)
                    throw new RuntimeException("stmt with more than 1 defbox!");

                if (s.getDefBoxes().size() < 1)
                    continue;

                ValueBox loBox = (ValueBox)s.getDefBoxes().get(0);
                Value lo = loBox.getValue();

                if(lo instanceof Local && !markedBoxes.contains(loBox))
                {
                    LinkedList<Unit> defsToVisit = new LinkedList<Unit>();
                    LinkedList<ValueBox> boxesToVisit = new LinkedList<ValueBox>();

                    List web = new ArrayList();
                    webs.add(web);
                                        
                    defsToVisit.add(s);
                    markedBoxes.add(loBox);
                    boxToUnit.put(loBox, s); // RoboVM note: Added
                    
                    while(!boxesToVisit.isEmpty() || !defsToVisit.isEmpty())
                    {
                        if(!defsToVisit.isEmpty())
                        {
                            Unit d = defsToVisit.removeFirst();

                            web.add(d.getDefBoxes().get(0));

                            // Add all the uses of this definition to the queue
                            {
                                List uses = localUses.getUsesOf(d);
                                Iterator useIt = uses.iterator();
    
                                while(useIt.hasNext())
                                {
                                    UnitValueBoxPair use = (UnitValueBoxPair) useIt.next();
    
                                    if(!markedBoxes.contains(use.valueBox))
                                    {
                                        markedBoxes.add(use.valueBox);
                                        boxesToVisit.addLast(use.valueBox);
                                        boxToUnit.put(use.valueBox, use.unit);
                                    }
                                }
                            }
                        }
                        else {
                            ValueBox box = boxesToVisit.removeFirst();

                            web.add(box);

                            // Add all the definitions of this use to the queue.
                            {               
                                List<Unit> defs = localDefs.getDefsOfAt((Local) box.getValue(),
                                    boxToUnit.get(box));
                                Iterator<Unit> defIt = defs.iterator();
    
                                while(defIt.hasNext())
                                {
                                    Unit u = defIt.next();

                                    Iterator defBoxesIter = u.getDefBoxes().iterator();
                                    ValueBox b;

                                    for (; defBoxesIter.hasNext(); )
                                    {
                                        b = (ValueBox)defBoxesIter.next();
                                        if(!markedBoxes.contains(b))
                                        {
                                            markedBoxes.add(b);
                                            defsToVisit.addLast(u);
                                            boxToUnit.put(b, u); // RoboVM note: Added
                                        }
                                    }    
                                }
                            }
                        }
                    }
                }
            }

            // RoboVM note: Added in RoboVM to merge webs referring to the same
            // local variable in the original source code. This prevents 
            // splitting of such Locals.
            webs = mergeWebs(body, webs, boxToUnit, localUses);
        }

        // Assign locals appropriately.
        {
            Map<Local, Integer> localToUseCount = new HashMap<Local, Integer>(body.getLocalCount() * 2 + 1, 0.7f);
            Iterator<List> webIt = webs.iterator();

            while(webIt.hasNext())
            {
                List web = webIt.next();

                ValueBox rep = (ValueBox) web.get(0);
                Local desiredLocal = (Local) rep.getValue();

                if(!localToUseCount.containsKey(desiredLocal))
                {
                    // claim this local for this set

                    localToUseCount.put(desiredLocal, new Integer(1));
                }
                else {
                    // generate a new local

                    int useCount = localToUseCount.get(desiredLocal).intValue() + 1;
                    localToUseCount.put(desiredLocal, new Integer(useCount));
        
                    Local local = (Local) desiredLocal.clone();
                    local.setName(desiredLocal.getName() + "#" + useCount);
                    
                    body.getLocals().add(local);

                    // Change all boxes to point to this new local
                    {
                        Iterator j = web.iterator();

                        while(j.hasNext())
                        {
                            ValueBox box = (ValueBox) j.next();

                            box.setValue(local);
                        }
                    }
                }
            }
        }
        
        if(Options.v().time())
            Timers.v().splitPhase2Timer.end();

    }   

    /**
     * Merges webs referring to the same local variable in the original source 
     * code. This prevents splitting of Locals which correspond to local 
     * variables. Splitting would otherwise interfere with debug info
     * generation.
     * RoboVM note: Added in RoboVM.
     */
    private List<List> mergeWebs(Body body, List<List> webs, Map<ValueBox, Unit> boxToUnit, LocalUses localUses) {
        List<List> result = new ArrayList<>();
        LinkedList<List> websCopy = new LinkedList<>(webs);
        while (!websCopy.isEmpty()) {
            // Compare the local variable of the first web with the rest. If two
            // webs refer to the same local variable we merge them into one.
            List<ValueBox> web1 = websCopy.removeFirst();
            Local local1 = (Local) web1.get(0).getValue();
            if (local1.getIndex() == -1) {
                // The Local doesn't refer to a local variable in the bytecode but rather a stack slot.
                result.add(web1);
                continue;
            }
            List<ValueBox> mergedWeb = new ArrayList<>(web1);
            Set<LocalVariable> lvs1 = findLocalVariables(web1, local1, null, boxToUnit, body);
            String expectedType = lvs1.isEmpty()? null: lvs1.iterator().next().getDescriptor();

            for (Iterator<List> it2 = websCopy.iterator(); it2.hasNext();) {
                List<ValueBox> web2 = it2.next();
                Local local2 = (Local) web2.get(0).getValue();
                if (!local1.equals(local2)) {
                    continue;
                }
                Set<LocalVariable> lvs2 = findLocalVariables(web2, local2, expectedType, boxToUnit, body);
                if (!lvs1.isEmpty() && lvs1.equals(lvs2)) {
                    mergedWeb.addAll(web2);
                    it2.remove();
                }
            }
            result.add(mergedWeb);
        }

        return result;
    }

    /**
     * RoboVM note: Added in RoboVM.
     */
    private Set<LocalVariable> findLocalVariables(List<ValueBox> web, Local local, String expectedTypeDescriptor, Map<ValueBox, Unit> boxToUnit, Body body) {
        Set<LocalVariable> lvs = new HashSet<>();
        if (local.getIndex() != -1) {
            boolean incompatibleType = false;
            String firstType = null;
            for (ValueBox box : web) {
                LocalVariable lv = findLocalVariable(body, local.getIndex(), boxToUnit.get(box));
                if (lv != null) {
                    lvs.add(lv);
                    // does this local have a compatible type with the other web?
                    // if not we'll return an empty set and the webs don't get merged
                    if (expectedTypeDescriptor != null && lv.getDescriptor().equals(expectedTypeDescriptor)) {
                        incompatibleType = true;
                    }

                    // are the types of this web's locals consistent?
                    // if not we'll return an empty set and the
                    // webs don't get merged, see #920
                    if (firstType == null) {
                        firstType = lv.getDescriptor();
                    } else if (!firstType.equals(lv.getDescriptor())) {
                        incompatibleType = true;
                    }
                }
            }

            // if we encountered incompatible types, either
            // between the webs or inside the web, we
            // return an empty set so the webs don't get
            // merged
            if (incompatibleType) {
                lvs.clear();
                return lvs;
            }

            if (lvs.size() > 1) {
                // Verify that all LocalVariables refer to a variable with the same name.
                String name = null;
                for (LocalVariable lv : lvs) {
                    if (name == null) {
                        name = lv.getName();
                    } else {
                        if (!lv.getName().equals(name)) {
                            throw new IllegalStateException("Found LocalVariables do " 
                                    + "not refer to a variable with the same name: " + lvs);
                        }
                    }
                }
            }
        }
        return lvs;
    }

    /**
     * Finds a {@link LocalVariable} with the specified local variable index
     * at the specified {@link Unit} or {@code null} if none was found.
     * RoboVM note: Added in RoboVM.
     */
    private LocalVariable findLocalVariable(Body body, int index, Unit unit) {
        PatchingChain<Unit> units = body.getUnits();
        for (LocalVariable lv : body.getLocalVariables()) {
            if (lv.getIndex() == index) {
                if ((unit == lv.getStartUnit() || units.follows(unit, lv.getStartUnit()))
                        && (lv.getEndUnit() == null || units.follows(lv.getEndUnit(), unit))) {
                    return lv;
                }
            }
        }
        return null;
    }
}
