/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.compiler.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.robovm.compiler.target.Target;
import org.robovm.compiler.util.AntPathMatcher;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Specifies resources needed by the application the compiler produces. A resource can be 
 * specified using a single {@link File}:
 * <pre>&lt;resource&gt;path/to/the/resource.txt&lt;/resource&gt;</pre>
 * If the path specifies a directory the directory including its contents (except for the default
 * excludes, see below) will be copied. If the path specifies a file, that file will be copied.
 * <p>
 * A resource be also be specified with a base directory, a target path and include and exclude
 * filters (similar to Maven's &lt;resource&gt; element):
 * <pre>&lt;resource&gt;
 *  &lt;targetPath&gt;data&lt;/targetPath&gt;
 *  &lt;directory&gt;resources&lt;/directory&gt;
 *  &lt;includes&gt;
 *    &lt;include&gt;**&#47;*&lt;/include&gt;
 *  &lt;/includes&gt;
 *  &lt;excludes&gt;
 *    &lt;exclude&gt;**&#47;*.bak&lt;/exclude&gt;
 *  &lt;/excludes&gt;
 *  &lt;flatten&gt;false&lt;/flatten&gt;
 *  &lt;ignoreDefaultExcludes&gt;false&lt;/ignoreDefaultExcludes&gt;
 *  &lt;skipPngCrush&gt;false&lt;/skipPngCrush&gt;
 *&lt;/resource&gt;</pre>
 * Each element represents a property in this class. Please see the documentation for each 
 * property's getter method for more information.
 * </p>
 * <p>
 * The current {@link Target} may transform and rename a resource while being copied (e.g. running
 * {@code pngcrush} or converting {@code xib} files to {@code nib} files).
 * </p>
 * <h2><a name="defexcludes">Default excludes</a></h2>
 * <p>(The same as those used by ANT 1.9)</p>
 * <p>
 * Miscellaneous typical temporary files:
 * <ul>
 * <li>**&#47;*~</li>
 * <li>**&#47;#*#</li>
 * <li>**&#47;.#*</li>
 * <li>**&#47;%*%</li>
 * <li>**&#47;._*</li>
 *  </ul>
 * CVS:
 * <ul>
 * <li>**&#47;CVS</li>
 * <li>**&#47;CVS/**</li>
 * <li>**&#47;.cvsignore</li>
 * </ul>
 * SCCS:
 * <ul>
 * <li>**&#47;SCCS</li>
 * <li>**&#47;SCCS/**</li>
 * </ul>
 * Visual SourceSafe:
 * <ul>
 * <li>**&#47;vssver.scc</li>
 * </ul>
 * Subversion:
 * <ul>
 * <li>**&#47;.svn</li>
 * <li>**&#47;.svn/**</li>
 * </ul>
 * Git:
 * <ul>
 * <li>**&#47;.git</li>
 * <li>**&#47;.git/**</li>
 * <li>**&#47;.gitattributes</li>
 * <li>**&#47;.gitignore</li>
 * <li>**&#47;.gitmodules</li>
 * </ul>
 * Mercurial:
 * <ul>
 * <li>**&#47;.hg</li>
 * <li>**&#47;.hg/**</li>
 * <li>**&#47;.hgignore</li>
 * <li>**&#47;.hgsub</li>
 * <li>**&#47;.hgsubstate</li>
 * <li>**&#47;.hgtags</li>
 * </ul>
 * Bazaar:
 * <ul>
 * <li>**&#47;.bzr</li>
 * <li>**&#47;.bzr/**</li>
 * <li>**&#47;.bzrignore</li>
 * </ul>
 * Mac:
 * <ul>
 * <li>**&#47;.DS_Store</li>
 * </ul>
 * </p>
 */
public class Resource {
    
    /**
     * Interface used by {@link Resource#walk(Walker, File)} to walk the paths matched by a 
     * {@link Resource}.
     */
    public interface Walker {
        /**
         * Processes the specified file. This typically copies the file to the destination directory
         * possibly renaming/transforming the file in some way in the process.
         */
        void process(Resource resource, File file, File destDir) throws IOException;
    }
    
    private static final AntPathMatcher MATCH_ALL_MATCHER = new AntPathMatcher("**/*");
    private static final String[] DEFAULTEXCLUDES = {
        // Miscellaneous typical temporary files
        "**/*~",
        "**/#*#",
        "**/.#*",
        "**/%*%",
        "**/._*",

        // CVS
        "**/CVS",
        "**/CVS/**",
        "**/.cvsignore",

        // SCCS
        "**/SCCS",
        "**/SCCS/**",

        // Visual SourceSafe
        "**/vssver.scc",

        // Subversion
        "**/.svn",
        "**/.svn/**",

        // Git
        "**/.git",
        "**/.git/**",
        "**/.gitattributes",
        "**/.gitignore",
        "**/.gitmodules",

        // Mercurial
        "**/.hg",
        "**/.hg/**",
        "**/.hgignore",
        "**/.hgsub",
        "**/.hgsubstate",
        "**/.hgtags",

        // Bazaar
        "**/.bzr",
        "**/.bzr/**",
        "**/.bzrignore",

        // Mac
        "**/.DS_Store"
    };
    private static final List<AntPathMatcher> DEFAULTEXCLUDESMATCHERS;
    
    static {
        DEFAULTEXCLUDESMATCHERS = toAntPathMatchers(Arrays.asList(DEFAULTEXCLUDES));
    }
    
    @Element(required = false)
    private String targetPath;
    @Element(required = false)
    private File directory;
    @ElementList(required = false, entry = "include")
    private ArrayList<String> includes;
    @ElementList(required = false, entry = "exclude")
    private ArrayList<String> excludes;
    @Element(required = false)
    private Boolean flatten;
    @Element(required = false)
    private Boolean ignoreDefaultExcludes;
    @Element(required = false)
    private Boolean skipPngCrush;

    @Element(required = false)
    private File path;
    
    protected Resource() {}
    
    /**
     * Creates a new simple {@link Resource} which will copy the specified file or directory.
     * 
     * @param path the {@link File} which will be copied.
     */
    public Resource(File path) {
        this.path = path;
    }

    /**
     * Creates a new {@link Resource} which will copy all files in the specified base directory
     * to the specified target path in the application directory.
     * 
     * @param directory the base directory.
     * @param targetPath the target path.
     */
    public Resource(File directory, String targetPath) {
        this.directory = directory;
        this.targetPath = targetPath;
    }
    
    /**
     * Returns the path which will be copied for simple {@link Resource}s or {@code null} if this
     * isn't a simple {@link Resource}.
     * 
     * @return the path.
     */
    public File getPath() {
        return path;
    }
    
    /**
     * Returns the target path relative to the application directory (i.e. app bundle directory for
     * iOS apps) where paths matching this {@link Resource} will be copied. If not specified paths
     * will be copied directly to the application directory.
     * 
     * @return the target path or {@code null} if not specified.
     */
    public String getTargetPath() {
        return targetPath;
    }
    
    /**
     * Returns the base directory containing the files and directories copied by the 
     * {@link Resource}.
     * 
     * @return the base directory.
     */
    public File getDirectory() {
        return directory;
    }
    
    /**
     * Returns a list of Ant-style patterns (using **, *, ? as wildcards) matching files which will
     * be included when copying this {@link Resource}.
     * 
     * @return the include patterns.
     * @see AntPathMatcher
     */
    public List<String> getIncludes() {
        return includes != null ? Collections.unmodifiableList(includes) : Collections.<String>emptyList();
    }
    
    /**
     * Adds include patterns.
     * 
     * @return {@code this}
     * @see #getIncludes()
     */
    public Resource include(String ... patterns) {
        if (includes == null) {
            includes = new ArrayList<String>();
        }
        includes.addAll(Arrays.asList(patterns));
        return this;
    }
    
    /**
     * Returns a list of Ant-style patterns (using **, *, ? as wildcards) matching files which will
     * be excluded when copying this {@link Resource}.
     * 
     * @return the exclude patterns.
     * @see AntPathMatcher
     */
    public List<String> getExcludes() {
        return excludes != null ? Collections.unmodifiableList(excludes) : Collections.<String>emptyList();
    }
    
    /**
     * Adds exclude patterns.
     * 
     * @return {@code this}
     * @see #getExcludes()
     */
    public Resource exclude(String ... patterns) {
        if (excludes == null) {
            excludes = new ArrayList<String>();
        }
        excludes.addAll(Arrays.asList(patterns));
        return this;
    }
    
    /**
     * Returns <code>true</code> if the files matched by this {@link Resource} should be copied
     * directly into the application directory without preserving the directory structure of the
     * source directory. The default is <code>false</code>.
     * 
     * @return <code>true</code> if the directory structure should be flattened.
     */
    public boolean isFlatten() {
        return flatten == null ? false : flatten.booleanValue();
    }

    /**
     * Sets the {@code flatten} property and returns {@code this}.
     * 
     * @param b the new value.
     * @return {@code this}
     * @see #isFlatten()
     */
    public Resource flatten(boolean b) {
        this.flatten = b;
        return this;
    }

    /**
     * Returns <code>true</code> if the <a href="#defexcludes">default excludes</a> should be 
     * ignored and copied for this {@link Resource}. The default is <code>false</code>, i.e. don't
     * copy files matching the default excludes.
     * 
     * @return <code>true</code> if default excludes should be ignored.
     */
    public boolean isIgnoreDefaultExcludes() {
        return ignoreDefaultExcludes == null ? false : ignoreDefaultExcludes.booleanValue();
    }
    
    /**
     * Sets the {@code ignoreDefaultExcludes} property and returns {@code this}.
     * 
     * @param b the new value.
     * @return {@code this}
     * @see #isIgnoreDefaultExcludes()
     */
    public Resource ignoreDefaultExcludes(boolean b) {
        this.ignoreDefaultExcludes = b;
        return this;
    }
    
    /**
     * Returns <code>true</code> if {@code pngcrush} should not be called for PNG files matching
     * this {@link Resource} when targeting iOS. The default is <code>false</code>, i.e. 
     * {@code pngcrush} WILL be called for PNG files.
     * 
     * @return <code>true</code> if {@code pngcrush} should not be called.
     */
    public boolean isSkipPngCrush() {
        return skipPngCrush == null ? false : skipPngCrush.booleanValue();
    }
    
    /**
     * Sets the {@code skipPngCrush} property and returns {@code this}.
     * 
     * @param b the new value.
     * @return {@code this}
     * @see #isSkipPngCrush()
     */
    public Resource skipPngCrush(boolean b) {
        this.skipPngCrush = b;
        return this;
    }
    
    public void walk(Walker walker, File destDir) throws IOException {
        if (targetPath != null && targetPath.trim().length() > 0) {
            destDir = new File(destDir, targetPath);
        }
        if (path != null) {
            // Walk path and all its descendants (if a directory) including everything except the 
            // default excludes.
            walk(walker, path.getParentFile(), path.getName(), destDir,
                    Collections.singletonList(MATCH_ALL_MATCHER), DEFAULTEXCLUDESMATCHERS);
        } else {
            List<AntPathMatcher> inc = toAntPathMatchers(includes);
            if (inc.isEmpty()) {
                inc.add(MATCH_ALL_MATCHER);
            }
            List<AntPathMatcher> exc = toAntPathMatchers(excludes);
            if (!isIgnoreDefaultExcludes()) {
                exc.addAll(DEFAULTEXCLUDESMATCHERS);
            }
            File dir = null;
            if (directory != null) {
                dir = directory.getCanonicalFile();
            } else {
                dir = new File("").getCanonicalFile();
            }
            if (dir.exists() && dir.isDirectory()) {
                for (File f : dir.listFiles()) {
                    walk(walker, dir, f.getName(), destDir, inc, exc);
                }
            }
        }
    }
    
    private void walk(Walker walker, File baseDir, String path, File destDir, 
            List<AntPathMatcher> inc, List<AntPathMatcher> exc) throws IOException {
        
        File f = new File(baseDir, path);
        // Always descend into directories unless explicitly excluded.
        if ((f.isDirectory() || matches(path, inc)) && !matches(path, exc)) {
            if (f.isFile()) {
                walker.process(this, f, destDir);
            } else if (f.isDirectory()) {
                File newDestDir = destDir;
                if (!isFlatten()) {
                    newDestDir = new File(destDir, f.getName());
                }
                for (File child : f.listFiles()) {
                    walk(walker, baseDir, path + File.separator + child.getName(), newDestDir, inc, exc);
                }
            }
        }
    }

    private static ArrayList<AntPathMatcher> toAntPathMatchers(List<String> listOfStrings) {
        ArrayList<AntPathMatcher> l = new ArrayList<AntPathMatcher>();
        if (listOfStrings != null) {
            for (String pattern : listOfStrings) {
                l.add(new AntPathMatcher(pattern));
            }
        }
        return l;
    }

    private boolean matches(String path, List<AntPathMatcher> matchers) {
        for (AntPathMatcher matcher : matchers) {
            if (matcher.matches(path)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((directory == null) ? 0 : directory.hashCode());
        result = prime * result
                + ((excludes == null) ? 0 : excludes.hashCode());
        result = prime * result + ((flatten == null) ? 0 : flatten.hashCode());
        result = prime
                * result
                + ((ignoreDefaultExcludes == null) ? 0 : ignoreDefaultExcludes
                        .hashCode());
        result = prime * result
                + ((includes == null) ? 0 : includes.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result
                + ((skipPngCrush == null) ? 0 : skipPngCrush.hashCode());
        result = prime * result
                + ((targetPath == null) ? 0 : targetPath.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Resource other = (Resource) obj;
        if (directory == null) {
            if (other.directory != null) {
                return false;
            }
        } else if (!directory.equals(other.directory)) {
            return false;
        }
        if (excludes == null) {
            if (other.excludes != null) {
                return false;
            }
        } else if (!excludes.equals(other.excludes)) {
            return false;
        }
        if (flatten == null) {
            if (other.flatten != null) {
                return false;
            }
        } else if (!flatten.equals(other.flatten)) {
            return false;
        }
        if (ignoreDefaultExcludes == null) {
            if (other.ignoreDefaultExcludes != null) {
                return false;
            }
        } else if (!ignoreDefaultExcludes.equals(other.ignoreDefaultExcludes)) {
            return false;
        }
        if (includes == null) {
            if (other.includes != null) {
                return false;
            }
        } else if (!includes.equals(other.includes)) {
            return false;
        }
        if (path == null) {
            if (other.path != null) {
                return false;
            }
        } else if (!path.equals(other.path)) {
            return false;
        }
        if (skipPngCrush == null) {
            if (other.skipPngCrush != null) {
                return false;
            }
        } else if (!skipPngCrush.equals(other.skipPngCrush)) {
            return false;
        }
        if (targetPath == null) {
            if (other.targetPath != null) {
                return false;
            }
        } else if (!targetPath.equals(other.targetPath)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Resource [targetPath=").append(targetPath)
                .append(", directory=").append(directory).append(", includes=")
                .append(includes).append(", excludes=").append(excludes)
                .append(", flatten=").append(flatten)
                .append(", ignoreDefaultExcludes=")
                .append(ignoreDefaultExcludes).append(", skipPngCrush=")
                .append(skipPngCrush).append(", path=").append(path)
                .append("]");
        return builder.toString();
    }
}
