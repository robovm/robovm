package org.robovm.compiler.plugin;

import java.io.InputStream;

import org.robovm.compiler.plugin.LaunchPlugin;

/**
 * Implemented by LaunchPlugins that require an input stream
 * (keyboard input) to operate. The method of this interface
 * is supposed to be called after {@link LaunchPlugin#beforeLaunch(org.robovm.compiler.config.Config, org.robovm.compiler.target.LaunchParameters)}
 * and before {@link LaunchPlugin#afterLaunch(org.robovm.compiler.config.Config, org.robovm.compiler.target.LaunchParameters, Process)}
 * @author badlogic
 *
 */
public interface RequiresInputStream {
    public void setInputStream(InputStream in);
}
