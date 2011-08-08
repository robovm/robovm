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


#include <string.h>
#include "hyport.h"
#include "strhelp.h"
#ifdef ZOS
#include "atoe.h"
#endif

static int prop_alloc(HyPortLibrary * portLibrary, key_value_pair* property,
                      char* start, char* delim, char* end);

/**
* Concatenates a variable number of null-terminated strings into a single string
* using the specified port library to allocate memory.  The variable number of
* strings arguments must be terminated by a single NULL value.
*
* @param portLibrary - The port library used to allocate memory.
* @return The concatenated string or NULL if no memory can be allocated.
*/
char *
str_concat (HyPortLibrary * portLibrary, ...)
{
    PORT_ACCESS_FROM_PORT (portLibrary);
    va_list argp;
    char *concatenated;
    UDATA concatenatedSize = 0;

    /* Walk the variable arguments once to compute the final size */
    va_start (argp, portLibrary);
    while (1)
    {
        char *chunk = va_arg (argp, char *);
        if (chunk)
        {
            concatenatedSize += (UDATA)strlen (chunk);
        }
        else
        {
            break;
        }
    }
    va_end (argp);

    /* Allocate concatenated space */
    concatenated =
        hymem_allocate_memory (concatenatedSize + 1 /* for null terminator */ );
    if (!concatenated)
    {
        return NULL;
    }
    concatenated[0] = '\0';

    /* Walk again concatenating the pieces */
    va_start (argp, portLibrary);
    while (1)
    {
        char *chunk = va_arg (argp, char *);
        if (chunk)
        {
            strcat (concatenated, chunk);
        }
        else
        {
            break;
        }
    }
    va_end (argp);

    return concatenated;
}

/**
* Frees data of all elements and the array itself.
* @param portLibrary - The port library used to interact with the platform.
* @param properties - The array instance no longer in use.
*/
void 
properties_free(HyPortLibrary * portLibrary, key_value_pair * properties)
{
    PORT_ACCESS_FROM_PORT (portLibrary);
    if (properties) {
        unsigned i = 0;
        while (properties[i].key) {
            hymem_free_memory(properties[i].key);
            if (properties[i].value)
                hymem_free_memory(properties[i].value);
            ++i;
        }
        hymem_free_memory(properties);
    }
}

static int 
prop_alloc(HyPortLibrary * portLibrary, key_value_pair* property,
                      char* start, char* delim, char* end) 
{
    PORT_ACCESS_FROM_PORT (portLibrary);
    /* missing delimiter means the whole line is the key and value is empty */
    size_t keyLength = (delim ? delim : end) - start;
    size_t valueLength = delim ? end - delim - 1 : 0;
    property->key = hymem_allocate_memory ((UDATA)(keyLength + 1));
    property->value = hymem_allocate_memory ((UDATA)(valueLength + 1));
    if (!property->key || !property->value)
    {
        return 0;
    }
    memcpy (property->key, start, keyLength);
    property->key[keyLength] = '\0';
    if (delim) {
        memcpy (property->value, delim + 1, valueLength);
    }
    property->value[valueLength] = '\0';

    return 1;
}

/**
* Read the properties file specified by <tt>filename</tt> 
* into the array of <tt>properties</tt>, 
* using the specified port library to allocate memory. 
* The array is terminated with null-keyed element, 
* though one can obtain number of elements directly
* via last argument.
*
* @param[in] portLibrary - The port library used to interact with the platform.
* @param[in] filename - The file from which to read data using hyfile* functions.
* @param[out] properties - An array that will contain property file entries.
* @param[out] number - Optional parameter, number of elements in the returned array.
*
* @return JNI_OK on success, or a JNI error code on failure.
*/
jint 
properties_load(HyPortLibrary * portLibrary, const char *filename, 
                          key_value_pair** properties, U_32 *number)
{
    PORT_ACCESS_FROM_PORT (portLibrary);    
    void *handle;
    I_64 seekResult;
    IDATA fileSize;
    char *scanCursor, *scanLimit;
    char *start, *delim, *end;
    key_value_pair *props;
    unsigned arraySize;
    unsigned count = 0;
    jint status = JNI_OK;

    /* Determine the file size, fail if > 2G */
    seekResult = hyfile_length (filename);
    if ((seekResult <= 0) || (seekResult > 0x7FFFFFFF))
    {
        return JNI_ERR;
    }
    scanCursor = hymmap_map_file(filename, &handle);
    if (!scanCursor) {
        return JNI_ERR;
    }

#ifdef ZOS
    /* Convert the scan buffer into ASCII */
    scanCursor = e2a(scanCursor, seekResult);
#endif

    fileSize = (IDATA) seekResult;
    arraySize = fileSize/50 + 1;
    props = hymem_allocate_memory(sizeof(key_value_pair)*(arraySize + 1));
    if (!props) {
        status = JNI_ENOMEM;
        goto finish;
    }

    start = end = scanCursor;
    delim = NULL;
    scanLimit = scanCursor + fileSize;

    do {
        while (scanCursor < scanLimit) {
            switch(*scanCursor) {
                case '\r': 
                case '\n': 
                    end = scanCursor;
                    goto read_line;
                case '=':
                    /* remember only first occurrence which is not key itself */
                    if (delim == NULL && scanCursor > start) {
                        delim = scanCursor;
                    }
                default:
                    ++scanCursor;
                    continue;
            }
        }

read_line:
        if (scanCursor > start && start != delim && *start != '#' && *start != '!')  
            /* line is not empty, well formed and not commented out */
        {
            if (end == start) {
                /* the last line ends with EOF */
                end = scanLimit;
            }
            if (count == arraySize) 
            {
                void* re_props;
                arraySize += arraySize/2 + 1;
                re_props = hymem_reallocate_memory(props, 
                    sizeof(key_value_pair)*(arraySize + 1));
                if (!re_props) {
                    status = JNI_ENOMEM;
                    goto finish;
                }
                props = re_props;
            }
            if (!prop_alloc(portLibrary, props + count, start, delim, end)) {
                status = JNI_ENOMEM;
                goto finish;
            }
            ++count;
        }   
        start = end = ++scanCursor;
        delim = NULL;
    }
    while (scanCursor < scanLimit);

    /*set terminating NULL*/
    props[count].key = NULL; 

finish:
    hymmap_unmap_file(handle);
    if (status != JNI_OK) 
    {
        properties_free(portLibrary, props);
    }
    else 
    {
        *properties = props;
        if (number){
            *number = count;
        }
    }
    return status;
}
