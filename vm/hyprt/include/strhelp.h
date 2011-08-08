/* Licensed to the Apache Software Foundation (ASF) under one or more
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

#if !defined(strhelp_h)
#define strhelp_h
#include <string.h>
#include "vmi.h"
#include "exceptions.h"

typedef struct key_value_pair
{
    char *key;
    char *value;
} key_value_pair;

char *str_concat (HyPortLibrary *portLibrary, ...);
jint properties_load(HyPortLibrary *portLibrary, const char *filename, 
                          key_value_pair **properties, U_32 *number);
void properties_free(HyPortLibrary *portLibrary, key_value_pair *properties);

#endif /* strhelp_h */
