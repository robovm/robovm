/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.text;

/**
 * TODO: type description
 */
public class BidiRun {
    private final int start;

    private final int limit;

    private final int level;

    public BidiRun(int start, int limit, int level) {
        this.start = start;
        this.limit = limit;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getLimit() {
        return limit;
    }

    public int getStart() {
        return start;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BidiRun)) {
            return false;
        }
        BidiRun other = (BidiRun) o;
        return start == other.start && limit == other.limit
                && level == other.level;
    }
    
    @Override
    public int hashCode() {
        return start ^ limit ^ level;
    }
}
