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
package org.robovm.eclipse.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.robovm.eclipse.RoboVMPlugin;

public class RoboVMTab extends AbstractLaunchConfigurationTab {

    private Text projectText;
    private Button projectButton;
    private WidgetListener projectListener = new WidgetListener();
    
    private class WidgetListener implements ModifyListener, SelectionListener {

        @Override
        public void modifyText(ModifyEvent e) {
            checkParameters();
            setDirty(true);
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            Object source = e.getSource();
            if (source == projectButton) {
                handleProjectButtonSelected();
            } else {
                checkParameters();
            }
        }
    }
    
    @Override
    public void createControl(Composite parent) {
        Composite root = createRoot(parent);
        createProjectEditor(root);
        setControl(root);
    }

    public Composite createRoot(Composite parent) {
        Composite root = new Composite(parent, SWT.NONE);
        root.setFont(parent.getFont());
        root.setLayout(new GridLayout(1, false));
        root.setLayoutData(new GridData(GridData.FILL_BOTH));
        ((GridData) root.getLayoutData()).horizontalSpan = 1;
        ((GridLayout) root.getLayout()).verticalSpacing = 0;
        return root;
    }
    
    private void handleProjectButtonSelected() {
        IJavaProject project = chooseJavaProject();
        if (project == null) {
            return;
        }
        projectText.setText(project.getElementName());
    }

    protected void createProjectEditor(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText("Project:");
        group.setFont(parent.getFont());
        group.setLayout(new GridLayout(2, false));
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        ((GridData) group.getLayoutData()).horizontalSpan = 1;
        ((GridLayout) group.getLayout()).verticalSpacing = 0;
        
        projectText = new Text(group, SWT.SINGLE | SWT.BORDER);
        projectText.setFont(parent.getFont());
        projectText.setText("");
        projectText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        projectText.addModifyListener(projectListener);

        projectButton = new Button(group, SWT.NONE);
        projectButton.setFont(parent.getFont());
        projectButton.setText("Browse...");
        projectButton.setLayoutData(new GridData());
        projectButton.addSelectionListener(projectListener);
    }
    
    @Override
    public String getName() {
        return "RoboVM";
    }

    @Override
    public void initializeFrom(ILaunchConfiguration config) {
        String projectName = "";
        try {
            projectName = config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
        } catch (CoreException e) {
            RoboVMPlugin.log(e);
        }
        projectText.setText(projectName);
    }

    @Override
    public void performApply(ILaunchConfigurationWorkingCopy wc) {
        wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, projectText.getText());
    }

    @Override
    public void setDefaults(ILaunchConfigurationWorkingCopy wc) {
        wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
    }
    
    private IJavaProject chooseJavaProject() {
        ILabelProvider labelProvider = new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
        dialog.setTitle("Project Secletion"); 
        dialog.setMessage("Select a RoboVM project to launch");
        try {
            IJavaProject[] projects = getRoboVMProjects();
            dialog.setElements(projects);
            
            IJavaProject selectedProject = findProject(projects, projectText.getText());
            if (selectedProject != null) {
                dialog.setInitialSelections(new Object[] { selectedProject });
            }
            if (dialog.open() == Window.OK) {                       
                return (IJavaProject) dialog.getFirstResult();
            }
        } catch (CoreException e) {
            RoboVMPlugin.log(e);
        }
            
        return null;            
    }

    private IJavaProject[] getRoboVMProjects() throws CoreException {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        IJavaModel javaModel = JavaCore.create(workspaceRoot);
        List<IJavaProject> projects = new ArrayList<IJavaProject>();
        for (IJavaProject project : javaModel.getJavaProjects()) {
            if (project.getProject().hasNature(RoboVMNature.ID)) {
                projects.add(project);
            }
        }
        return projects.toArray(new IJavaProject[projects.size()]);
    }
    
    private IJavaProject findProject(IJavaProject[] projects, String name) throws CoreException {
        for (IJavaProject javaProject : getRoboVMProjects()) {
            if (javaProject.getProject().getName().equals(name)) {
                return javaProject;
            }
        }
        return null;
    }
    
    private IProject checkParameters() {
        try {
            //test the project name first!
            String text = projectText.getText();
            if (text.length() == 0) {
                setErrorMessage("Project Name is required!");
            } else if (text.matches("[a-zA-Z0-9_ \\.-]+") == false) {
                setErrorMessage("Project name contains unsupported characters!");
            } else {
                IProject found = null;
                try {
                    IJavaProject javaProject = findProject(getRoboVMProjects(), text);
                    if (javaProject != null) {
                        found = javaProject.getProject();
                    }
                } catch (CoreException e) {
                    RoboVMPlugin.log(e);
                }

                if (found != null) {
                    setErrorMessage(null);
                } else {
                    setErrorMessage(String.format("There is no RoboVM project named '%1$s'",
                            text));
                }

                return found;
            }
        } finally {
            updateLaunchConfigurationDialog();
        }

        return null;
    }
}