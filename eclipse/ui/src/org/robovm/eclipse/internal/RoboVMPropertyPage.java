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

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;
import org.robovm.eclipse.RoboVMPlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author niklas
 *
 */
public class RoboVMPropertyPage extends PropertyPage {

    private ProjectProperties projectProperties = null;
    private IProject project;

    @Override
    protected Control createContents(Composite parent) {
        project = (IProject) getElement().getAdapter(IProject.class);
        projectProperties = new ProjectProperties(parent, true);
        projectProperties.loadPreferences(project);
        return projectProperties.getComposite();
    }

    @Override
    public boolean performOk() {
        try {
            projectProperties.storePreferences(project);
        } catch (BackingStoreException e) {
            MessageDialog.openError(RoboVMPlugin.getShell(), "Failed to store properties", 
                    e.getMessage()); 
            return false;
        }
        return true;
    }

    @Override
    protected void performDefaults() {
        projectProperties.resetToDefaults();
        super.performDefaults();
    }
}
