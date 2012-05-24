/**
 * 
 */
package org.robovm.ide.internal;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author niklas
 *
 */
public class RoboVMClasspathContainerPage extends WizardPage implements IClasspathContainerPage {

    private IClasspathEntry classpathEntry = null;
    
    public RoboVMClasspathContainerPage() {
        super("RoboVM Runtime Library Container");
    }

    public boolean finish() {
        classpathEntry = JavaCore.newContainerEntry(RoboVMClasspathContainer.PATH);
        return true;
    }

    public IClasspathEntry getSelection() {
        return classpathEntry;
    }

    public void setSelection(IClasspathEntry containerEntry) {
        this.classpathEntry = containerEntry;
    }

    public void createControl(Composite parent) {
        setTitle("RoboVM Runtime Library Container");
        Label label = new Label(parent, SWT.NONE);
        label.setText("Press Finish to add the RoboVM Runtime Library Container");
        label.setFont(parent.getFont());
        setControl(label);
    }


}
