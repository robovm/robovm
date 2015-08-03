/*
 * Copyright (C) 2012 RoboVM AB
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
package org.robovm.compiler.target;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.robovm.compiler.clazz.Path;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;

/**
 * Builds and launches (if supported) a particular type of binary (e.g. iOS
 * apps, dynamic libraries, etc).
 */
public interface Target {

    /**
     * Returns a unique type id for this {@link Target}.
     */
    String getType();

    /**
     * Returns the {@link OS} this {@link Target} will build for. This is
     * determined by {@link #init(Config)}. If an explicit {@link OS} has been
     * set on the {@link Config} in the call to {@link #init(Config)} that
     * {@link OS} will returned by this method. Otherwise {@link #init(Config)}
     * will determine a default {@link OS} and this method returns that one.
     */
    OS getOs();
    
    /**
     * Returns the {@link Arch} this {@link Target} will build for. This is
     * determined by {@link #init(Config)}. If an explicit {@link Arch} has been
     * set on the {@link Config} in the call to {@link #init(Config)} that
     * {@link Arch} will returned by this method. Otherwise
     * {@link #init(Config)} will determine a default {@link Arch} and this
     * method returns that one.
     */
    Arch getArch();

    /**
     * Returns a list of the default archs to build for if no archs have been
     * specified in the {@link Config}. Returns an empty list if there are no
     * defaults.
     */
    List<Arch> getDefaultArchs();

    String getInstallRelativeArchivePath(Path path);

    /**
     * Returns {@code true} if binaries created by this {@link Target} can be
     * launched, i.e. it produces executable binaries.
     */
    boolean canLaunch();
    
    /**
     * Returns {@code true} if binaries created by this {@link Target} can be
     * launched in place and doesn't have to be copied into some folder
     * structure, e.g. an iOS app bundle.
     */
    boolean canLaunchInPlace();

    /**
     * Builds a binary out of the specified object files.
     */
    void build(List<File> objectFiles) throws IOException;

    /**
     * Builds a fat binary out of the specified slices.
     */
    public void buildFat(Map<Arch, File> slices) throws IOException;

    /**
     * Installs the built binary and any supporting files into the
     * {@link Config#getInstallDir()} directory.
     */
    void install() throws IOException;

    /**
     * Creates an archive suitable for distribution and stores it in the
     * {@link Config#getInstallDir()} directory.
     */
    void archive() throws IOException;

    /**
     * Launches the built binary if supported.
     * 
     * @throws UnsupportedOperationException if binaries built by this
     *             {@link Target} cannot be launched.
     */
    Process launch(LaunchParameters launchParameters) throws IOException;

    /**
     * Creates {@link LaunchParameters} for launching the binary built by this
     * {@link Target}.
     * 
     * @throws UnsupportedOperationException if binaries built by this
     *             {@link Target} cannot be launched.
     */
    LaunchParameters createLaunchParameters();
    
    /**
     * Initializes this {@link Target} from the specified {@link Config}.
     */
    void init(Config config);
}
