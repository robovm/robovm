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
package org.robovm.eclipse.internal;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config.Builder;
import org.robovm.compiler.config.Config.TargetType;
import org.robovm.compiler.config.OS;

/**
 * 
 */
public class NewCocoaTouchProjectWizard extends NewProjectWizard {

    public NewCocoaTouchProjectWizard() {
        super();
        setWindowTitle("New RoboVM Cocoa Touch Project");
    }
    
    @Override
    protected RoboVMPageOne createPageOne() {
        return new IOSPageOne(getDefaultArch(), getDefaultOs());
    }
    
    @Override
    protected String getDefaultArch() {
        return Arch.x86.toString();
    }
    
    @Override
    protected String getDefaultOs() {
        return OS.ios.toString();
    }
    
    @Override
    protected List<IClasspathEntry> customizeClasspath(List<IClasspathEntry> classpath) {
        classpath.add(JavaCore.newContainerEntry(new Path(RoboVMCocoaTouchClasspathContainer.ID)));
        return super.customizeClasspath(classpath);
    }
    
    @Override
    protected void customizeConfig(Builder configBuilder, Properties props) throws Exception {
        super.customizeConfig(configBuilder, props);
        
        IProject project = page2.getJavaProject().getProject();
        
        String mainClass = ((IOSPageOne) page1).mainClassText.getText().trim();
        String appName = ((IOSPageOne) page1).appNameText.getText().trim();
        String appId = ((IOSPageOne) page1).appIdText.getText().trim();
        
        if (mainClass.length() == 0) {
            mainClass = project.getName().replaceAll("\\s", "");
        }
        String mainClassSimpleName = mainClass.substring(mainClass.lastIndexOf('.') + 1);;
        if (appName.length() == 0) {
            appName = mainClassSimpleName;
        }
        if (appId.length() == 0) {
            appId = mainClass;
        }
        
        props.setProperty("app.mainclass", mainClass);
        props.setProperty("app.name", appName);
        props.setProperty("app.executable", mainClassSimpleName);
        props.setProperty("app.id", appId);
        props.setProperty("app.version", "1.0");
        props.setProperty("app.build", "1");
        
        File projectRoot = project.getLocation().toFile();
        File infoPList = new File(projectRoot, "Info.plist.xml");
        FileUtils.copyURLToFile(getClass().getResource("Info.plist.template.xml"), infoPList);
        
        configBuilder.os(OS.ios);
        configBuilder.arch(Arch.thumbv7);
        configBuilder.targetType(TargetType.ios);
        configBuilder.mainClass("${app.mainclass}");
        configBuilder.executableName("${app.executable}");
        configBuilder.iosInfoPList(infoPList);
    }
    
    public static class IOSPageOne extends RoboVMPageOne {
        private Text mainClassText;
        private Text appNameText;
        private Text appIdText;
        
        public IOSPageOne(String defaultArch, String defaultOs) {
            super(defaultArch, defaultOs);
        }
        
        @Override
        protected void addCustomControls(Composite parent) {
            super.addCustomControls(parent);
            createBundleControl(parent);
        }
        
        protected void createBundleControl(Composite parent) {
            Group group = new Group(parent, SWT.NONE);
            group.setText("iOS App Settings:");
            group.setFont(parent.getFont());
            group.setLayout(new GridLayout(2, false));
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            ((GridData) group.getLayoutData()).horizontalSpan = 1;
            GridLayout layout = (GridLayout) group.getLayout();
            GC gc = new GC(parent);
            gc.setFont(JFaceResources.getDialogFont());
            FontMetrics fontMetrics = gc.getFontMetrics();
            gc.dispose();
            layout.horizontalSpacing = Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_SPACING);
            layout.verticalSpacing = Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_SPACING);
            layout.marginWidth = Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_MARGIN);
            layout.marginHeight = Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_MARGIN);

            Label mainClassLabel = new Label(group, NONE);
            mainClassLabel.setFont(group.getFont());
            mainClassLabel.setText("Main class:");
            mainClassLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

            mainClassText = new Text(group, SWT.SINGLE | SWT.BORDER);
            mainClassText.setFont(group.getFont());
            mainClassText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
            
            Label appNameLabel = new Label(group, NONE);
            appNameLabel.setFont(group.getFont());
            appNameLabel.setText("App name:");
            appNameLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

            appNameText = new Text(group, SWT.SINGLE | SWT.BORDER);
            appNameText.setFont(group.getFont());
            appNameText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
            
            Label appIdLabel = new Label(group, NONE);
            appIdLabel.setFont(group.getFont());
            appIdLabel.setText("App id:");
            appIdLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

            appIdText = new Text(group, SWT.SINGLE | SWT.BORDER);
            appIdText.setFont(group.getFont());
            appIdText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        }
    }
}
