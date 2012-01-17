/**
 * 
 */
package org.nullvm.ide.internal;

import static org.nullvm.ide.NullVMPlugin.*;

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
import org.nullvm.compiler.Arch;
import org.nullvm.compiler.OS;
import org.nullvm.ide.NullVMPlugin;

/**
 * @author niklas
 *
 */
public class NullVMPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    public static final String ID = "org.nullvm.ide.preferences.main";
    
    public NullVMPreferencePage() {
        super("NullVM", GRID);
        setPreferenceStore(NullVMPlugin.getDefault().getPreferenceStore());
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
                {Arch.arm.toString(), Arch.arm.toString()},
                {Arch.i386.toString(), Arch.i386.toString()},
                {Arch.x86_64.toString(), Arch.x86_64.toString()}
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
        
        final BoolFieldEditor useBundledNullVMEditor = new BoolFieldEditor(PREFERENCE_USE_BUNDLED_NULLVM, "Use bundled NullVM", parent);
        final RequiredDirectoryFieldEditor nullVMHomeFieldEditor = new RequiredDirectoryFieldEditor(PREFERENCE_NULLVM_HOME_DIR, "NullVM home:", parent) {
            @Override
            protected boolean validateDir(File dir) {
                File lib = new File(dir, "lib");
                File rt = new File(lib, "nullvm-rt.jar");
                return lib.exists() && lib.isDirectory() && rt.exists() && rt.isFile();
            }
        };
        nullVMHomeFieldEditor.setErrorMessage("NullVM home value is invalid");
        addField(useBundledNullVMEditor);
        addField(nullVMHomeFieldEditor);
        
        useBundledNullVMEditor.getCheckbox().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean b = useBundledNullVMEditor.getCheckbox().getSelection();
                nullVMHomeFieldEditor.setEnabled(!b);
                if (b) {
                    nullVMHomeFieldEditor.setStringValue("");
                } else {
                    nullVMHomeFieldEditor.focus();
                }
                nullVMHomeFieldEditor.revalidate();
                NullVMPreferencePage.this.checkState();
                if (!nullVMHomeFieldEditor.isValid()) {
                    NullVMPreferencePage.this.setErrorMessage(nullVMHomeFieldEditor.getErrorMessage());
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
                NullVMPreferencePage.this.checkState();
            }
        });
        
        if (NullVMPlugin.getDefault().getPreferenceStore().getBoolean(PREFERENCE_USE_BUNDLED_NULLVM)) {
            nullVMHomeFieldEditor.setEnabled(false);
            nullVMHomeFieldEditor.setStringValue("");
        }
        if (NullVMPlugin.getDefault().getPreferenceStore().getBoolean(PREFERENCE_USE_SYSTEM_LLVM)) {
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
