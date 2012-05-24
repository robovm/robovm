/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.robovm.compiler.Arch;
import org.robovm.compiler.OS;
import org.robovm.ide.RoboVMPlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author niklas
 *
 */
public class ProjectProperties {

    private Composite composite;
    private String arch = null;
    private String os = null;
    private FontMetrics fontMetrics = null;
    private Combo archCombo;
    private Combo osCombo;
    
    public ProjectProperties(Composite parent, boolean showBorder) {
        GC gc = new GC(parent);
        gc.setFont(JFaceResources.getDialogFont());
        fontMetrics = gc.getFontMetrics();
        gc.dispose();
        
        if (showBorder) {
            Group group = new Group(parent, SWT.NONE);
            group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            group.setFont(parent.getFont());
            group.setLayout(initGridLayout(new GridLayout(2, false), true));
            group.setText("RoboVM settings");
            composite = group;
        } else {
            composite = new Composite(parent, SWT.NONE);
            composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            composite.setFont(parent.getFont());
            composite.setLayout(initGridLayout(new GridLayout(2, false), false));
        }
        createControls(composite);
    }

    public Composite getComposite() {
        return composite;
    }
    
    private void setArch(String arch) {
        this.arch = arch;
        if (arch == null) {
            archCombo.select(0);
        } else if (arch.equals(RoboVMPlugin.ARCH_AUTO)) {
            archCombo.select(1);
        } else {
            for (int i = 0; i < archCombo.getItemCount(); i++) {
                if (arch.equals(archCombo.getItem(i))) {
                    archCombo.select(i);
                    break;
                }
            }
        }
    }
    
    private void setOs(String os) {
        this.os = os;
        if (os == null) {
            osCombo.select(0);
        } else if (os.equals(RoboVMPlugin.OS_AUTO)) {
            osCombo.select(1);
        } else {
            for (int i = 0; i < osCombo.getItemCount(); i++) {
                if (os.equals(osCombo.getItem(i))) {
                    osCombo.select(i);
                    break;
                }
            }
        }
    }
    
    protected void createControls(Composite parent) {
        Label archLabel = new Label(parent, SWT.NONE);
        archLabel.setFont(parent.getFont());
        archLabel.setText("Arch for incremental builds:");
        archLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
        
        archCombo = new Combo(parent, SWT.READ_ONLY | SWT.BORDER);
        archCombo.setItems(new String[] {
                "Use workspace default", 
                "Auto (build for current host)",
                Arch.armv6.toString(),
                Arch.armv7.toString(),
                Arch.thumbv6.toString(),
                Arch.thumbv7.toString(),
                Arch.x86.toString()
        });
        archCombo.select(0);
        archCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        archCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int index = archCombo.getSelectionIndex();
                if (index == 0) {
                    arch = null;
                } else if (index == 1) {
                    arch = RoboVMPlugin.ARCH_AUTO;
                } else {
                    arch = archCombo.getItem(index);                        
                }
            }
        });

        Label osLabel = new Label(parent, SWT.NONE);
        osLabel.setFont(parent.getFont());
        osLabel.setText("OS for incremental builds:");
        osLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
        
        osCombo = new Combo(parent, SWT.READ_ONLY | SWT.BORDER);
        osCombo.setItems(new String[] {
                "Use workspace default", 
                "Auto (build for current host)",
                OS.macosx.toString(),
                OS.ios.toString(),
                OS.linux.toString()
        });
        osCombo.select(0);
        osCombo.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
        osCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int index = osCombo.getSelectionIndex();
                if (index == 0) {
                    os = null;
                } else if (index == 1) {
                    os = RoboVMPlugin.OS_AUTO;
                } else {
                    os = osCombo.getItem(index);                        
                }
            }
        });
        
        Link prefsLink = new Link(parent, SWT.NONE);
        prefsLink.setText("<a>Configure default RoboVM settings...</a>");
        GridData prefsLinkGridData = new GridData(GridData.END, GridData.END, false, false);
        prefsLinkGridData.horizontalSpan = 2;
        prefsLink.setLayoutData(prefsLinkGridData);
        prefsLink.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }
            @Override
            public void widgetSelected(SelectionEvent e) {
                String id = RoboVMPreferencePage.ID;
                PreferencesUtil.createPreferenceDialogOn(RoboVMPlugin.getShell(), id, new String[] { id }, null).open();
            }
        });
    }
    
    private GridLayout initGridLayout(GridLayout layout, boolean margins) {
        layout.horizontalSpacing = Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_SPACING);
        layout.verticalSpacing = Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_SPACING);
        if (margins) {
            layout.marginWidth = Dialog.convertHorizontalDLUsToPixels(fontMetrics, IDialogConstants.HORIZONTAL_MARGIN);
            layout.marginHeight = Dialog.convertVerticalDLUsToPixels(fontMetrics, IDialogConstants.VERTICAL_MARGIN);
        } else {
            layout.marginWidth = 0;
            layout.marginHeight = 0;
        }
        return layout;
    }

    public void resetToDefaults() {
        setArch(null);
        setOs(null);
    }
    
    public void loadPreferences(IProject project) {
        IEclipsePreferences node = new ProjectScope(project).getNode(RoboVMPlugin.PLUGIN_ID);
        setArch(node.get(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_ARCH, null));
        setOs(node.get(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_OS, null));
    }
    
    public void storePreferences(IProject project) throws BackingStoreException {
        IEclipsePreferences node = new ProjectScope(project).getNode(RoboVMPlugin.PLUGIN_ID);
        if (arch != null) {
            node.put(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_ARCH, arch);
        } else {
            node.remove(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_ARCH);
        }
        if (os != null) {
            node.put(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_OS, os);
        } else {
            node.remove(RoboVMPlugin.PREFERENCE_INCREMENTAL_BUILD_OS);
        }
        node.flush();
    }
}
