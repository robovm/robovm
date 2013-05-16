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

import static org.robovm.eclipse.RoboVMPlugin.*;

import java.io.File;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.eclipse.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class RoboVMPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    public static final String ID = "org.robovm.eclipse.preferences.main";
    
    public RoboVMPreferencePage() {
        super("RoboVM", GRID);
        setPreferenceStore(RoboVMPlugin.getDefault().getPreferenceStore());
    }

    @Override
    public void init(IWorkbench workbench) {
    }

    @Override
    protected void createFieldEditors() {
        final Composite parent = getFieldEditorParent();
        
        ComboFieldEditor archFieldEditor = new ComboFieldEditor(PREFERENCE_INCREMENTAL_BUILD_ARCH, 
                "Default arch:", new String[][] {
                {"Auto (build for current host)", ARCH_AUTO},
                {Arch.armv6.toString(), Arch.armv6.toString()},
                {Arch.armv7.toString(), Arch.armv7.toString()},
                {Arch.thumbv6.toString(), Arch.thumbv6.toString()},
                {Arch.thumbv7.toString(), Arch.thumbv7.toString()},
                {Arch.x86.toString(), Arch.x86.toString()}
        }, parent);
        addField(archFieldEditor);
        
        ComboFieldEditor osFieldEditor = new ComboFieldEditor(PREFERENCE_INCREMENTAL_BUILD_OS, 
                "Default OS:", new String[][] {
                {"Auto (build for current host)", OS_AUTO},
                {OS.macosx.toString(), OS.macosx.toString()},
                {OS.ios.toString(), OS.ios.toString()},
                {OS.linux.toString(), OS.linux.toString()}
        }, parent);
        addField(osFieldEditor);
        
        final BoolFieldEditor useBundledRoboVMEditor = new BoolFieldEditor(PREFERENCE_USE_SYSTEM_ROBOVM, "Use system RoboVM", parent);
        final RequiredDirectoryFieldEditor roboVMHomeFieldEditor = new RequiredDirectoryFieldEditor(PREFERENCE_ROBOVM_HOME_DIR, "RoboVM home:", parent) {
            @Override
            protected boolean validateDir(File dir) {
                try {
                    Config.Home.validate(dir);
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }
        };
        roboVMHomeFieldEditor.setErrorMessage("RoboVM home value is invalid");
        addField(useBundledRoboVMEditor);
        addField(roboVMHomeFieldEditor);
        
        useBundledRoboVMEditor.getCheckbox().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean b = useBundledRoboVMEditor.getCheckbox().getSelection();
                roboVMHomeFieldEditor.setEnabled(!b);
                if (b) {
                    roboVMHomeFieldEditor.setStringValue("");
                } else {
                    roboVMHomeFieldEditor.focus();
                }
                roboVMHomeFieldEditor.revalidate();
                RoboVMPreferencePage.this.checkState();
                if (!roboVMHomeFieldEditor.isValid()) {
                    RoboVMPreferencePage.this.setErrorMessage(roboVMHomeFieldEditor.getErrorMessage());
                }
            }
        });
        
        final BoolFieldEditor useSystemLlvmEditor = new BoolFieldEditor(PREFERENCE_USE_SYSTEM_LLVM, "Use system LLVM", parent);
        final RequiredDirectoryFieldEditor llvmHomeDirEditor = new RequiredDirectoryFieldEditor(PREFERENCE_LLVM_HOME_DIR, "LLVM home:", parent) {
            @Override
            protected boolean validateDir(File dir) {
                File llc = new File(new File(dir, "bin"), "llc");
                File opt = new File(new File(dir, "bin"), "opt");
                return llc.exists() && llc.isFile() && llc.canExecute() 
                    && opt.exists() && opt.isFile() && opt.canExecute();
            }
        };
        llvmHomeDirEditor.setErrorMessage("LLVM home value is invalid");
        addField(useSystemLlvmEditor);
        addField(llvmHomeDirEditor);
        
        useSystemLlvmEditor.getCheckbox().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean b = useSystemLlvmEditor.getCheckbox().getSelection();
                llvmHomeDirEditor.setEnabled(!b);
                if (b) {
                    llvmHomeDirEditor.setStringValue("");
                } else {
                    llvmHomeDirEditor.focus();
                }
                llvmHomeDirEditor.revalidate();
                RoboVMPreferencePage.this.checkState();
            }
        });
        
        if (RoboVMPlugin.getDefault().getPreferenceStore().getBoolean(PREFERENCE_USE_SYSTEM_ROBOVM)) {
            roboVMHomeFieldEditor.setEnabled(false);
            roboVMHomeFieldEditor.setStringValue("");
        }
        if (RoboVMPlugin.getDefault().getPreferenceStore().getBoolean(PREFERENCE_USE_SYSTEM_LLVM)) {
            llvmHomeDirEditor.setEnabled(false);
            llvmHomeDirEditor.setStringValue("");
        }
    }
    
    private static class BoolFieldEditor extends BooleanFieldEditor {
        private final Composite parent;
        
        public BoolFieldEditor(String name, String label, Composite parent) {
            super(name, label, parent);
            this.parent = parent;
        }

        public Button getCheckbox() {
            return getChangeControl(parent);
        }
    }
    
    private static class RequiredDirectoryFieldEditor extends DirectoryFieldEditor {
        private final Composite parent;

        public RequiredDirectoryFieldEditor(String name, String labelText,
                Composite parent) {
            super(name, labelText, parent);
            this.parent = parent;
            getTextControl(parent).addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    revalidate();
                }
            });
        }
        
        public void setEnabled(boolean enabled) {
            super.setEnabled(enabled, parent);
        }
        
        public void focus() {
            getTextControl(parent).setFocus();
        }
        
        public void revalidate() {
            refreshValidState();
        }
        
        @Override
        protected boolean doCheckState() {
            if (getTextControl().isEnabled()) {
                String s = getStringValue();
                if (s == null || s.trim().isEmpty()) {
                    return false;
                }
                File f = new File(s);
                return f.exists() && f.isDirectory() && validateDir(f);
            }
            return true;
        }
        
        protected boolean validateDir(File dir) {
            return true;
        }
    }
}
