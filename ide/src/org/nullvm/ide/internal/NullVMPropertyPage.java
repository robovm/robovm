/**
 * 
 */
package org.nullvm.ide.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;
import org.nullvm.ide.NullVMPlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author niklas
 *
 */
public class NullVMPropertyPage extends PropertyPage {

    private ProjectProperties projectProperties = null;
    private IProject project;

    @Override
    protected Control createContents(Composite parent) {
        project = (IProject) getElement().getAdapter(IProject.class);
        projectProperties = new ProjectProperties(parent, false);
        projectProperties.loadPreferences(project);
        return projectProperties.getComposite();
    }

    @Override
    public boolean performOk() {
        try {
            projectProperties.storePreferences(project);
        } catch (BackingStoreException e) {
            MessageDialog.openError(NullVMPlugin.getShell(), "Failed to store properties", 
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
