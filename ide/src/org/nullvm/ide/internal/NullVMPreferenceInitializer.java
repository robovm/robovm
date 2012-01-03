/**
 * 
 */
package org.nullvm.ide.internal;

import static org.nullvm.ide.NullVMPlugin.*;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nullvm.ide.NullVMPlugin;

/**
 * @author niklas
 *
 */
public class NullVMPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = NullVMPlugin.getDefault().getPreferenceStore();
        store.setDefault(PREFERENCE_INCREMENTAL_BUILD_ARCH, ARCH_AUTO);
        store.setDefault(PREFERENCE_INCREMENTAL_BUILD_OS, OS_AUTO);
        store.setDefault(PREFERENCE_USE_SYSTEM_LLVM, true);
        store.setDefault(PREFERENCE_LLVM_HOME_DIR, "");
        store.setDefault(PREFERENCE_USE_BUNDLED_NULLVM, true);
        store.setDefault(PREFERENCE_NULLVM_HOME_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_GCC, true);
        store.setDefault(PREFERENCE_GCC_BIN_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_BINUTILS, true);
        store.setDefault(PREFERENCE_BINUTILS_BIN_DIR, "");
    }

}
