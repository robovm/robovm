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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.robovm.compiler.target.ios.IOSSimulatorLaunchParameters.Family;
import org.robovm.compiler.target.ios.SDK;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class IOSSimulatorLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

    @Override
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        setTabs(new ILaunchConfigurationTab[] {
                new SimulatorTab(),
                new CommonTab()
        });
    }

    private static List<SDK> listSDKs() {
        List<SDK> sdks = SDK.listSimulatorSDKs();
        Collections.sort(sdks, Collections.reverseOrder());
        return sdks;
    }
    
    public static class SimulatorTab extends RoboVMTab {

        private Combo familyCombo;
        private Combo sdkCombo;
        
        @Override
        public void createControl(Composite parent) {
            Composite root = createRoot(parent);
            createProjectEditor(root);
            createSimulatorEditor(root);
            setControl(root);
        }
        
        protected void createSimulatorEditor(Composite parent) {
            Group group = new Group(parent, SWT.NONE);
            group.setText("iOS Simulator:");
            group.setFont(group.getFont());
            group.setLayout(new GridLayout(2, false));
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            ((GridData) group.getLayoutData()).horizontalSpan = 1;
            ((GridLayout) group.getLayout()).verticalSpacing = 0;
            
            Label familyLabel = new Label(group, SWT.NONE);
            familyLabel.setFont(group.getFont());
            familyLabel.setText("Device family:");
            familyLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

            familyCombo = new Combo(group, SWT.READ_ONLY | SWT.BORDER);
            familyCombo.setItems(new String[] {
                    "iPhone", 
                    "iPad"
            });
            familyCombo.select(0);
            familyCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
            familyCombo.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    updateLaunchConfigurationDialog();
                }
            });

            Label sdkLabel = new Label(group, SWT.NONE);
            sdkLabel.setFont(group.getFont());
            sdkLabel.setText("SDK version:");
            sdkLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

            sdkCombo = new Combo(group, SWT.READ_ONLY | SWT.BORDER);
            ArrayList<String> sdks = new ArrayList<String>();
            sdks.add("Latest");
            for (SDK sdk : listSDKs()) {
                sdks.add(sdk.getVersion());
            }
            sdkCombo.setItems(sdks.toArray(new String[sdks.size()]));
            sdkCombo.select(0);
            sdkCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
            sdkCombo.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent event) {
                    updateLaunchConfigurationDialog();
                }
            });

            setControl(group);
        }

        @Override
        public void initializeFrom(ILaunchConfiguration config) {
            super.initializeFrom(config);
            try {
                String v = config.getAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_FAMILY, Family.iphone.toString());
                String[] items = familyCombo.getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i].equalsIgnoreCase(v)) {
                        familyCombo.select(i);
                        break;
                    }
                }
            } catch (CoreException e) {
                RoboVMPlugin.log(e);
            }
            try {
                String v = config.getAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_SDK, (String) null);
                sdkCombo.select(0);
                String[] items = sdkCombo.getItems();
                for (int i = 0; i < items.length; i++) {
                    if (items[i].equals(v)) {
                        sdkCombo.select(i);
                        break;
                    }
                }
            } catch (CoreException e) {
                RoboVMPlugin.log(e);
            }
        }

        @Override
        public void performApply(ILaunchConfigurationWorkingCopy wc) {
            super.performApply(wc);
            wc.setAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_FAMILY, 
                    familyCombo.getItem(familyCombo.getSelectionIndex()).toLowerCase());
            int sdkIndex = sdkCombo.getSelectionIndex();
            String sdk = sdkIndex == 0 ? null : sdkCombo.getItem(sdkIndex);
            wc.setAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_SDK, sdk);
        }

        @Override
        public void setDefaults(ILaunchConfigurationWorkingCopy wc) {
            super.setDefaults(wc);
            wc.setAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_FAMILY, Family.iphone.toString());
            wc.setAttribute(IOSSimulatorLaunchConfigurationDelegate.ATTR_IOS_SIM_SDK, (String) null);
        }
        
    }
    
}
