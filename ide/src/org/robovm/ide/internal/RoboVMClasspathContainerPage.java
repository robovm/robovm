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
