package org.robovm.compiler.plugin;

import java.util.List;
import java.util.Objects;

/**
 * Stores the {@link CompilerPluginArgument} along with the prefix to be used
 * for the arguments.
 */
public class CompilerPluginArguments {
    private final String prefix;
    private final List<CompilerPluginArgument> arguments;

    public CompilerPluginArguments(String prefix, List<CompilerPluginArgument> arguments) {
        this.prefix = Objects.requireNonNull(prefix, "Prefix must not be null");
        this.arguments = Objects.requireNonNull(arguments, "arguments must not be null");
    }

    public String getPrefix() {
        return prefix;
    }

    public List<CompilerPluginArgument> getArguments() {
        return arguments;
    }
}
