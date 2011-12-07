/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003, 2004 Ondrej Lhotak
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

package soot;
import java.util.*;
import java.io.*;
import java.util.zip.*;
import soot.util.*;
import soot.util.queue.*;
import soot.jimple.*;
import soot.jimple.toolkits.base.*;
import soot.jimple.toolkits.typing.*;
import soot.jimple.toolkits.scalar.*;
import soot.jimple.toolkits.scalar.pre.*;
import soot.jimple.toolkits.annotation.arraycheck.*;
import soot.jimple.toolkits.annotation.profiling.*;
import soot.jimple.toolkits.annotation.callgraph.*;
import soot.jimple.toolkits.annotation.parity.*;
import soot.jimple.toolkits.annotation.methods.*;
import soot.jimple.toolkits.annotation.fields.*;
import soot.jimple.toolkits.annotation.qualifiers.*;
import soot.jimple.toolkits.annotation.nullcheck.*;
import soot.jimple.toolkits.annotation.tags.*;
import soot.jimple.toolkits.annotation.defs.*;
import soot.jimple.toolkits.annotation.liveness.*;
import soot.jimple.toolkits.annotation.logic.*;
import soot.jimple.toolkits.annotation.purity.*; // [AM]
//import soot.javaToJimple.toolkits.*; 
import soot.jimple.toolkits.annotation.*;
import soot.jimple.toolkits.pointer.*;
import soot.jimple.toolkits.callgraph.*;
import soot.tagkit.*;
import soot.options.Options;
import soot.toolkits.scalar.*;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.toolkits.graph.interaction.*;

/** Manages the Packs containing the various phases and their options. */
public class PackManager {
	public static boolean DEBUG=false;
    public PackManager( Singletons.Global g ) { PhaseOptions.v().setPackManager(this); init(); }

    public boolean onlyStandardPacks() { return onlyStandardPacks; }
    private boolean onlyStandardPacks = false;
    void notifyAddPack() {
        onlyStandardPacks = false;
    }

    private void init()
    {
        Pack p;

        // Jimple body creation
        addPack(p = new JimpleBodyPack());
        {
            p.add(new Transform("jb.tt", soot.toolkits.exceptions.TrapTightener.v()));
            p.add(new Transform("jb.ls", LocalSplitter.v()));
            p.add(new Transform("jb.a", Aggregator.v()));
            p.add(new Transform("jb.ule", UnusedLocalEliminator.v()));
            p.add(new Transform("jb.tr", TypeAssigner.v()));
            p.add(new Transform("jb.ulp", LocalPacker.v()));
            p.add(new Transform("jb.lns", LocalNameStandardizer.v()));
            p.add(new Transform("jb.cp", CopyPropagator.v()));
            p.add(new Transform("jb.dae", DeadAssignmentEliminator.v()));
            p.add(new Transform("jb.cp-ule", UnusedLocalEliminator.v()));
            p.add(new Transform("jb.lp", LocalPacker.v()));
            p.add(new Transform("jb.ne", NopEliminator.v()));
            p.add(new Transform("jb.uce", UnreachableCodeEliminator.v()));
        }
        
        // Jimple transformation pack
        addPack(p = new BodyPack("jtp"));
        
        // Jimple optimization pack
        addPack(p = new BodyPack("jop"));
        {
            p.add(new Transform("jop.cse", CommonSubexpressionEliminator.v()));
            p.add(new Transform("jop.bcm", BusyCodeMotion.v()));
            p.add(new Transform("jop.lcm", LazyCodeMotion.v()));
            p.add(new Transform("jop.cp", CopyPropagator.v()));
            p.add(new Transform("jop.cpf", ConstantPropagatorAndFolder.v()));
            p.add(new Transform("jop.cbf", ConditionalBranchFolder.v()));
            p.add(new Transform("jop.dae", DeadAssignmentEliminator.v()));
            p.add(new Transform("jop.nce", new NullCheckEliminator()));
            p.add(new Transform("jop.uce1", UnreachableCodeEliminator.v()));
            p.add(new Transform("jop.ubf1", UnconditionalBranchFolder.v()));
            p.add(new Transform("jop.uce2", UnreachableCodeEliminator.v()));
            p.add(new Transform("jop.ubf2", UnconditionalBranchFolder.v()));
            p.add(new Transform("jop.ule", UnusedLocalEliminator.v()));
        }

        // Jimple annotation pack
        addPack(p = new BodyPack("jap"));
        {
            p.add(new Transform("jap.npc", NullPointerChecker.v()));
            p.add(new Transform("jap.npcolorer", NullPointerColorer.v()));
            p.add(new Transform("jap.abc", ArrayBoundsChecker.v()));
            p.add(new Transform("jap.profiling", ProfilingGenerator.v()));
            p.add(new Transform("jap.sea", SideEffectTagger.v()));
            p.add(new Transform("jap.cgtagger", CallGraphTagger.v()));
            p.add(new Transform("jap.parity", ParityTagger.v()));
            p.add(new Transform("jap.pat", ParameterAliasTagger.v()));
            p.add(new Transform("jap.rdtagger", ReachingDefsTagger.v()));
            p.add(new Transform("jap.lvtagger", LiveVarsTagger.v()));
            p.add(new Transform("jap.che", CastCheckEliminatorDumper.v()));
            p.add(new Transform("jap.umt", new UnreachableMethodTransformer()));
            p.add(new Transform("jap.lit", LoopInvariantFinder.v()));
            p.add(new Transform("jap.aet", AvailExprTagger.v()));
            p.add(new Transform("jap.dmt", DominatorsTagger.v()));
	       
        }

        // CFG Viewer 
        /*addPack(p = new BodyPack("cfg"));
        {
            p.add(new Transform("cfg.output", CFGPrinter.v()));
        }*/
        
        // Code attribute tag aggregation pack
//        addPack(p = new BodyPack("tag"));
//        {
//            p.add(new Transform("tag.ln", LineNumberTagAggregator.v()));
//            p.add(new Transform("tag.an", ArrayNullTagAggregator.v()));
//            p.add(new Transform("tag.dep", DependenceTagAggregator.v()));
//            p.add(new Transform("tag.fieldrw", FieldTagAggregator.v()));
//        }

        onlyStandardPacks = true;
    }

    public static PackManager v() { 
        return G.v().soot_PackManager();
    }

    private final Map<String, Pack> packNameToPack = new HashMap<String, Pack>();
    private final List<Pack> packList = new LinkedList<Pack>();

    private void addPack( Pack p ) {
        if( packNameToPack.containsKey( p.getPhaseName() ) )
            throw new RuntimeException( "Duplicate pack "+p.getPhaseName() );
        packNameToPack.put( p.getPhaseName(), p );
        packList.add( p );
    }

    public boolean hasPack(String phaseName) {
        return getPhase( phaseName ) != null;
    }

    public Pack getPack(String phaseName) {
        Pack p = packNameToPack.get(phaseName);
        return p;
    }

    public boolean hasPhase(String phaseName) {
        return getPhase(phaseName) != null;
    }

    public HasPhaseOptions getPhase(String phaseName) {
        int index = phaseName.indexOf( "." );
        if( index < 0 ) return getPack( phaseName );
        String packName = phaseName.substring(0,index);
        if( !hasPack( packName ) ) return null;
        return getPack( packName ).get( phaseName );
    }

    public Transform getTransform(String phaseName) {
        return (Transform) getPhase( phaseName );
    }


    public Collection<Pack> allPacks() {
        return Collections.unmodifiableList( packList );
    }

    public void runPacks() {
        if (Options.v().src_prec() == Options.src_prec_class && Options.v().keep_line_number()){
            LineNumberAdder lineNumAdder = LineNumberAdder.v();
            lineNumAdder.internalTransform("", null);
        }
        
        retrieveAllBodies();
        
        if (Options.v().interactive_mode()){
            if (InteractionHandler.v().getInteractionListener() == null){
                G.v().out.println("Cannot run in interactive mode. No listeners available. Continuing in regular mode.");
                Options.v().set_interactive_mode(false);
            }
            else {
                G.v().out.println("Running in interactive mode.");
            }
        }
        
        runBodyPacks();
        handleInnerClasses();
    }
    
    public void coffiMetrics() {
      int tV = 0, tE = 0, hM = 0;
      double aM = 0;
      HashMap<SootMethod, int[]> hashVem = soot.coffi.CFG.methodsToVEM;
      Iterator<SootMethod> it = hashVem.keySet().iterator();
      while (it.hasNext()) {
        int vem[] = hashVem.get(it.next());
        tV+= vem[0];
        tE+= vem[1];
        aM+= vem[2];
        if (vem[2]>hM) hM = vem[2];
      }
      if (hashVem.size()>0)
        aM/=hashVem.size();
      
      G.v().out.println("Vertices, Edges, Avg Degree, Highest Deg:    "+tV+"  "+tE+"  "+aM+"  "+hM);
    }

    public void runBodyPacks() {
        runBodyPacks( reachableClasses() );
    }

    private void runBodyPacks( Iterator classes ) {
        while( classes.hasNext() ) {
            SootClass cl = (SootClass) classes.next();
            runBodyPacks( cl );
        }
    }

    private void handleInnerClasses(){
       InnerClassTagAggregator agg = InnerClassTagAggregator.v();
       agg.internalTransform("", null);
    }

    private void releaseBodies( Iterator classes ) {
        while( classes.hasNext() ) {
            SootClass cl = (SootClass) classes.next();
            releaseBodies( cl );
        }
    }

    private Iterator reachableClasses() {
        if( false && (Options.v().whole_program() ||
                      Options.v().whole_shimple())) {
            QueueReader methods = Scene.v().getReachableMethods().listener();
            HashSet reachableClasses = new HashSet();
            
            while(true) {
                    SootMethod m = (SootMethod) methods.next();
                    if(m == null) break;
                    SootClass c = m.getDeclaringClass();
                    if( !c.isApplicationClass() ) continue;
                    reachableClasses.add( c );
            }
            return reachableClasses.iterator();
        } else {
            return Scene.v().getApplicationClasses().iterator();
        }
    }

    private void runBodyPacks(SootClass c) {
        boolean produceJimple = true;

        Iterator methodIt = c.methodIterator();
        while (methodIt.hasNext()) {
            SootMethod m = (SootMethod) methodIt.next();
            
            if(DEBUG){
            	if(m.getExceptions().size()!=0)
            		System.out.println("PackManager printing out jimple body exceptions for method "+m.toString()+" " + m.getExceptions().toString());
            }
            
            if (!m.isConcrete()) continue;

            if (produceJimple) {
                JimpleBody body =(JimpleBody) m.retrieveActiveBody();
                PackManager.v().getPack("jtp").apply(body);
                if( Options.v().validate() ) {
                    body.validate();
                }
                PackManager.v().getPack("jop").apply(body);
                PackManager.v().getPack("jap").apply(body);
            }
            
            //PackManager.v().getPack("cfg").apply(m.retrieveActiveBody());
        }
            
    }

    private void releaseBodies( SootClass cl ) {
        Iterator methodIt = cl.methodIterator();
        while (methodIt.hasNext()) {
            SootMethod m = (SootMethod) methodIt.next();

            if (m.hasActiveBody())
                m.releaseActiveBody();
        }
    }

    private void retrieveAllBodies() {
        Iterator clIt = reachableClasses();
        while( clIt.hasNext() ) {
            SootClass cl = (SootClass) clIt.next();
            Iterator methodIt = cl.methodIterator();
            while (methodIt.hasNext()) {
                SootMethod m = (SootMethod) methodIt.next();
                if(DEBUG && cl.isApplicationClass()){                	
                	if(m.getExceptions().size()!=0)
                		System.out.println("PackManager printing out from within retrieveAllBodies exceptions for method "+m.toString()+" " + m.getExceptions().toString());
                	else
                		System.out.println("in retrieveAllBodies......Currently Method "+ m.toString() +" has no exceptions ");
                }

                if( m.isConcrete() ) {
                    m.retrieveActiveBody();
                }
            }
        }
    }
}
