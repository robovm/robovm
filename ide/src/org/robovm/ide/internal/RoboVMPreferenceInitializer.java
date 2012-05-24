/**
 * 
 */
package org.robovm.ide.internal;

import static org.robovm.ide.RoboVMPlugin.*;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.robovm.ide.RoboVMPlugin;

/**
 * @author niklas
 *
 */
public class RoboVMPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = RoboVMPlugin.getDefault().getPreferenceStore();
        store.setDefault(PREFERENCE_INCREMENTAL_BUILD_ARCH, ARCH_AUTO);
        store.setDefault(PREFERENCE_INCREMENTAL_BUILD_OS, OS_AUTO);
        store.setDefault(PREFERENCE_USE_SYSTEM_LLVM, true);
        store.setDefault(PREFERENCE_LLVM_HOME_DIR, "");
        store.setDefault(PREFERENCE_USE_BUNDLED_ROBOVM, true);
        store.setDefault(PREFERENCE_ROBOVM_HOME_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_GCC, true);
        store.setDefault(PREFERENCE_GCC_BIN_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_BINUTILS, true);
        store.setDefault(PREFERENCE_BINUTILS_BIN_DIR, "");
    }

}
