/*
 * Copyright (C) 2012 RoboVM
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
        store.setDefault(PREFERENCE_USE_SYSTEM_ROBOVM, true);
        store.setDefault(PREFERENCE_ROBOVM_HOME_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_GCC, true);
        store.setDefault(PREFERENCE_GCC_BIN_DIR, "");
        store.setDefault(PREFERENCE_USE_SYSTEM_BINUTILS, true);
        store.setDefault(PREFERENCE_BINUTILS_BIN_DIR, "");
    }

}
