/*
 * Copyright (C) 2011 The RoboVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.robovm.ide.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.actions.OpenJavaPerspectiveAction;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageTwo;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.osgi.service.prefs.BackingStoreException;

/**
 *
 * @version $Id$
 */
public class NewProjectWizard extends Wizard implements INewWizard {

    private RoboVMPageOne page1;
    private NewJavaProjectWizardPageTwo page2;
    
    public NewProjectWizard() {
        setWindowTitle("New RoboVM Project");
    }
    
    @Override
    public void addPages() {
        if (page1 == null) {
            page1 = new RoboVMPageOne();
            page1.setTitle(page1.getTitle().replace("Java", "RoboVM"));
        }
        addPage(page1);
        if (page2 == null) {
            page2 = new NewJavaProjectWizardPageTwo(page1);
        }
        addPage(page2);
    }
    
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setHelpAvailable(false);
    }

    @Override
    public boolean performFinish() {
        try {
            page2.performFinish(new NullProgressMonitor());
            IJavaProject javaProject = page2.getJavaProject();
            IProject project = javaProject.getProject();
            page1.storePreferences(project);
            IClasspathEntry[] oldClasspath = javaProject.getRawClasspath();
            List<IClasspathEntry> newClasspath = new ArrayList<IClasspathEntry>();
            for (IClasspathEntry entry : oldClasspath) {
                if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER
                        && entry.getPath().toString().equals("org.eclipse.jdt.launching.JRE_CONTAINER")) {
                    
                    newClasspath.add(JavaCore.newContainerEntry(new Path(RoboVMClasspathContainer.ID)));
                } else {
                    newClasspath.add(entry);
                }
            }
            javaProject.setRawClasspath(newClasspath.toArray(
                    new IClasspathEntry[newClasspath.size()]), 
                    new NullProgressMonitor());
            RoboVMNature.configureNatures(project, new NullProgressMonitor());
        } catch (Exception e) {
            return false;
        }
        OpenJavaPerspectiveAction action = new OpenJavaPerspectiveAction();
        action.run();
        return true;
    }
    
    private static class RoboVMPageOne extends NewJavaProjectWizardPageOne {
        
        private ProjectProperties projectProperties = null;
        
        @Override
        public String getCompilerCompliance() {
            return JavaCore.VERSION_1_6;
        }
        @Override
        public IClasspathEntry[] getDefaultClasspathEntries() {
            return new IClasspathEntry[] {
                JavaCore.newContainerEntry(new Path(RoboVMClasspathContainer.ID))
            };
        }
        @Override
        protected Control createJRESelectionControl(Composite composite) {
            // Hide the JRE selection control
            Label l = new Label(composite, NONE);
            l.setSize(1, 1);
            return l;
        }
        @Override
        protected Control createInfoControl(Composite composite) {
            addCustomControls(composite);
            return super.createInfoControl(composite);
        }
        protected void addCustomControls(Composite parent) {
            projectProperties = new ProjectProperties(parent, true);
        }
        
        public void storePreferences(IProject project) throws BackingStoreException {
            projectProperties.storePreferences(project);
        }
    }
}
