/*
 *  Tool.java
 *
 *  Copyright (C) 1999 by Kresten Krab Thorup <krab@daimi.au.dk>
 *
 *  This file is part of "Kresten's Verifier for Java Byte Codes"
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 */


package verify;

import verify.classfile.ClassFile;
import java.io.FileInputStream;
import java.io.File;
import java.util.Hashtable;
import verify.path.SearchPath;
import verify.type.ClassInfoProvider;
import verify.type.ClassInfo;

class Tool 
{
//  ClassFileLoader provider;
//  
//
//  static long total_checking = 0L;
//
//  public static void main (String[] args)
//  {
//    String filename = "";
//     
//    int files_loaded = 0;
//
//    Hashtable options = new Hashtable ();
//
//    options.put ("verbose", "false");
//    options.put ("stats", "false");
//    options.put ("banner", "true");
//    options.put ("classpath", System.getProperty ("java.class.path", "."));
//
//    int i;
//    for (i = 0; i < args.length; i++) {
//      String arg = args[i];
//
//      if (arg.startsWith ("--")) {
//	int eq = arg.indexOf ('=');
//	if (eq != -1)
//	  {
//	    options.put (arg.substring (2, eq), 
//			 arg.substring (eq+1));
//	  }
//	else
//	  {
//	    options.put (arg.substring (2), "true");
//	  }
//
//	/* skip to next arg */
//	continue;
//      } else {
//	break;
//      }
//
//    }
//
//
//    if ((new Boolean ((String) options.get ("banner"))).booleanValue ())
//      {
//	System.err.println (  "------------------------------"
//			    + "------------------------------");
//	System.err.println (  "Kresten's Verifier for Java Byte Codes, 0.1");
//	System.err.println (  "Copyright (C) 1999 Kresten Krab Thorup " 
//			    + "<krab@gnu.org>");
//	System.err.println (  "This is free software with "
//			    + "ABSOLUTELY NO WARRANTY");
//	System.err.println (  "------------------------------"
//			    + "------------------------------");
//      }
//
//    if (i == args.length)
//      {
//	System.err.println (  "usage: java verify.Tool "
//			    + "[options] class-name...\n");
//	System.err.println ("    --classpath=path "
//			    + "      (defaults to built-in class path)");
//	System.err.println ("    --verbose=false ");
//	System.err.println ("    --banner=true ");
//	System.err.println ("    --stats=false ");
//	System.err.println ("");
//	System.err.println ("If you pass names of .class files, it will "
//			    +"try to be clever\nand guess what you mean."
//			    +"  For a program designed to report\nerrors, "
//			    +"this one currently does a poor job.  You may "
//			    +"find\nyourself looking at wierd stack traces "
//			    +"and stuff.  Checking\nmany classes in one run "
//			    +"is faster than running the tool\nfor each file "
//			    +"in turn.  ");
//	System.err.println (  "------------------------------"
//			    + "------------------------------");
//	System.exit (0);
//      }
//
//    ClassFileLoader provider 
//      = new ClassFileLoader ((String) options.get ("classpath"));
//
//    verify.type.TypeContext context
//      = new verify.type.TypeContext (provider);
//
//    verify.bytecode.BytecodeVerifier verifier 
//      = new verify.bytecode.BytecodeVerifier (context);
//
//    for (; i < args.length; i++)
//      {
//	String cls = args[i];
//
//	long iccount 
//	  = verify.bytecode.BytecodeVerifier.instruction_check_count;
//	long insn_pr_sec = (iccount*1000L)/(total_checking+1);
//
//	System.err.print (cls+":");
//
//	// Try to make a class name out of a file name.
//	// Hackish, but it works for me...
//	if (cls.endsWith (".class"))
//	  {
//	    int idx;
//
//	    if (cls.startsWith ("./"))
//	      cls = cls.substring (2, cls.length ()-6);
//	    else if ((idx=cls.indexOf ("/classes/")) != -1)
//	      cls = cls.substring (idx+9, cls.length()-6);
//	    else
//	      cls = cls.substring (0, cls.length ()-6);
//
//	    cls = cls.replace ('/', '.');
//	  }
//
//	long then = System.currentTimeMillis ();
//
//	ClassFile cf = provider.load (cls);
//	if (cf == null)
//	  { System.err.println ("not found."); continue; }
//
//	files_loaded += 1;
//
//	long hence = System.currentTimeMillis ();
//
//	verifier.verify (cf, options);
//
//	provider.unload (args[i]);
//
//	long now = System.currentTimeMillis ();
//	System.err.println (" ["+(hence-then)+"+"+(now-hence)+"ms]");
//
//	total_checking += (now-hence);
//    }
//
//    long total_loading = ClassFileLoader.total_loading;
//    long total = total_checking+total_loading;
//
//    if ((new Boolean ((String) options.get ("stats"))).booleanValue ())
//      {
//	System.out.println ("=================================");
//	System.out.println ("          total times");
//	System.out.println ("=================================");
//	System.out.println (" loading  : "
//			    + (total_loading*100/total) + "% ("
//			    + total_loading  + "ms) "
//			    );
//
//	System.out.println (" average  : " 
//			    + (total_loading/files_loaded) + " msec/file");
//
//	System.out.println ("=================================");
//
//	System.out.println (" checking : " 
//			    + (total_checking*100/total) + "% ("
//			    + total_checking + "ms) "
//			    );
//	long icount
//	  = verify.bytecode.BytecodeVerifier.instruction_count;
//
//	long iccount 
//	  = verify.bytecode.BytecodeVerifier.instruction_check_count;
//
//	long nsec_pr_insn = (total_checking*1000L)/iccount;
//
//	long insn_pr_sec = (iccount*1000L)/total_checking;
//
//	/*
//	System.out.println (" total    : " + icount + " insns");
//
//	System.out.println (" checked  : " + iccount + " insns");
//	*/
//
//	System.out.println (" mainloop : " 
//			    + insn_pr_sec + " insn/sec");
//
//
//	System.out.println ("=================================");
//
//	System.out.println (" over all : " 
//			    + (total_loading+total_checking) 
//			    + "ms");
//
//	System.out.println ("       or : " 
//			    + ((files_loaded*1000L)
//			       /(total_loading+total_checking))
//			    + " classes/sec");
//	System.out.println ("=================================");
//
//
//      } 
//
//  }
//
//
//}
//
//
//class ClassFileLoader implements ClassInfoProvider
//{
//  final SearchPath path;
//  char filesep = System.getProperty ("file.separator", "/").charAt (0);
//
//  Hashtable table = new Hashtable ();
//
//  ClassFileLoader (String loadpath)
//  {
//    path = new SearchPath (loadpath);
//  }
//
//  static long total_loading = 0L;
//
//  /* this is the method made available to the system */
//  public ClassFile load (String class_name)
//  {
//    ClassFile result = (ClassFile)table.get (class_name);
//    
//    long now = System.currentTimeMillis ();
//
//    if (result == null)
//      {
//	String filename = class_name.replace ('.', filesep) + ".class";
//
//	byte[] data = path.getBytes (filename);
//
//	if (data == null)
//	  {
//	    return null;
//	  }
//
//	try {
//
//	  result = new ClassFile (data);
//
//	} catch (ClassFormatError err) {
//	  
//	  System.err.println ("error with class format of "+class_name
//			      + "\n"+err);
//	  return null;
//	}
//
//	table.put (class_name, result);
//      }
//    else
//      {
//	unload (class_name);
//      }
//
//    total_loading += (System.currentTimeMillis () - now);
//
//    return result;
//  }
//
//  public void unload (String class_name)
//  {
//    table.remove (class_name);
//  }
//
//
//  public ClassInfo provide (String name)
//  {
//    ClassFile file = load (name);
//    if (file != null)
//      return new ClassFileInfo (file);
//    else
//      return null;
//  }
//
//

}


