/*
 * Copyright (c) 2003 Constantin S. Svintsoff <kostik@iclub.nsu.ru>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The names of the authors may not be used to endorse or promote
 *    products derived from this software without specific prior written
 *    permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

#include "readlink.h"

#include <string>

#include <errno.h>
#include <sys/param.h>
#include <sys/stat.h>
#include <unistd.h>

/**
 * This differs from realpath(3) mainly in its behavior when a path element does not exist or can
 * not be searched. realpath(3) treats that as an error and gives up, but we have Java-compatible
 * behavior where we just assume the path element was not a symbolic link. This leads to a textual
 * treatment of ".." from that point in the path, which may actually lead us back to a path we
 * can resolve (as in "/tmp/does-not-exist/../blah.txt" which would be an error for realpath(3)
 * but "/tmp/blah.txt" under the traditional Java interpretation).
 *
 * This implementation also removes all the fixed-length buffers of the C original.
 */
bool realpath(const char* path, std::string& resolved) {
    // 'path' must be an absolute path.
    if (path[0] != '/') {
        errno = EINVAL;
        return false;
    }

    resolved = "/";
    if (path[1] == '\0') {
        return true;
    }

    // Iterate over path components in 'left'.
    int symlinkCount = 0;
    std::string left(path + 1);
    while (!left.empty()) {
        // Extract the next path component.
        size_t nextSlash = left.find('/');
        std::string nextPathComponent = left.substr(0, nextSlash);
        if (nextSlash != std::string::npos) {
            left.erase(0, nextSlash + 1);
        } else {
            left.clear();
        }
        if (nextPathComponent.empty()) {
            continue;
        } else if (nextPathComponent == ".") {
            continue;
        } else if (nextPathComponent == "..") {
            // Strip the last path component except when we have single "/".
            if (resolved.size() > 1) {
                resolved.erase(resolved.rfind('/'));
            }
            continue;
        }

        // Append the next path component.
        if (resolved[resolved.size() - 1] != '/') {
            resolved += '/';
        }
        resolved += nextPathComponent;

        // See if we've got a symbolic link, and resolve it if so.
        struct stat sb;
        if (lstat(resolved.c_str(), &sb) == 0 && S_ISLNK(sb.st_mode)) {
            if (symlinkCount++ > MAXSYMLINKS) {
                errno = ELOOP;
                return false;
            }

            std::string symlink;
            if (!readlink(resolved.c_str(), symlink)) {
                return false;
            }
            if (symlink[0] == '/') {
                // The symbolic link is absolute, so we need to start from scratch.
                resolved = "/";
            } else if (resolved.size() > 1) {
                // The symbolic link is relative, so we just lose the last path component (which
                // was the link).
                resolved.erase(resolved.rfind('/'));
            }

            if (!left.empty()) {
                const char* maybeSlash = (symlink[symlink.size() - 1] != '/') ? "/" : "";
                left = symlink + maybeSlash + left;
            } else {
                left = symlink;
            }
        }
    }

    // Remove trailing slash except when the resolved pathname is a single "/".
    if (resolved.size() > 1 && resolved[resolved.size() - 1] == '/') {
        resolved.erase(resolved.size() - 1, 1);
    }
    return true;
}
