/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "LocalArray.h"
#include "readlink.h"

#include <string>
#include <unistd.h>

bool readlink(const char* path, std::string& result) {
    // We can't know how big a buffer readlink(2) will need, so we need to
    // loop until it says "that fit".
    size_t bufSize = 512;
    while (true) {
        LocalArray<512> buf(bufSize);
        ssize_t len = readlink(path, &buf[0], buf.size());
        if (len == -1) {
            // An error occurred.
            return false;
        }
        if (static_cast<size_t>(len) < buf.size()) {
            // The buffer was big enough.
            result.assign(&buf[0], len);
            return true;
        }
        // Try again with a bigger buffer.
        bufSize *= 2;
    }
}
