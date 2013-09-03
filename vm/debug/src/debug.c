/*
 * Copyright (C) 2013 Trillian AB
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

#if defined(DARWIN)

#include <robovm.h>
#include <mach/mach.h>
#include <mach/mach_error.h>
#include <mach/exception.h>
#include <mach/task.h>

// We only want the code in here to be linked into debug builds since some of the code below
// calls private functions (exc_sever).

/*
 * Install a mach exception handler which intercepts EXC_BAD_ACCESS and prevents GDB from
 * seeing it. If we don't do this GDB will not pass the EXC_BAD_ACCESS along to the OS so
 * that it can be converted into SIGBUS/SIGSEGV and handled by our signal handler. Instead
 * the EXC_BAD_ACCESS will just be raised again and again and again...
 * The code here has been inspired by the GC's code in os_dep.c and the mailing list post at
 * http://lists.apple.com/archives/darwin-dev/2006/Oct/msg00122.html.
 */
extern boolean_t exc_server(mach_msg_header_t* request, mach_msg_header_t* reply);
static mach_port_t mach_ex_port = MACH_PORT_NULL;

kern_return_t catch_exception_raise(mach_port_t exception_port, mach_port_t thread, mach_port_t task,
        exception_type_t exception, exception_data_t code, mach_msg_type_number_t code_count) __attribute__((visibility("default")));
/*
 * This is called by exc_server. exc_server uses dlsym to find this function so it must be public and exported during linking. The
 * AbstractTarget class in the compiler makes sure this is exported when linking.
 */
kern_return_t catch_exception_raise(mach_port_t exception_port, mach_port_t thread, mach_port_t task,
        exception_type_t exception, exception_data_t code, mach_msg_type_number_t code_count) {
    return KERN_FAILURE;
}

static void* exceptionHandlerEntryPoint(void* arg) {
    // mach_msg_server() never returns
    mach_msg_server(exc_server, sizeof(mach_msg_base_t) + 1024, mach_ex_port,  0);
    return NULL;
}

void registerDarwinExceptionHandler(void) {
    // Create the Mach exception port
    mach_port_t self = mach_task_self();
    assert(mach_port_allocate(self, MACH_PORT_RIGHT_RECEIVE, &mach_ex_port) == KERN_SUCCESS);
    assert(mach_port_insert_right(self, mach_ex_port, mach_ex_port, MACH_MSG_TYPE_MAKE_SEND) == KERN_SUCCESS);
    
    // Create the thread which receives the exceptions
    pthread_t thread;
    pthread_attr_t attr;
    assert(!pthread_attr_init(&attr));
    assert(!pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED));
    assert(!pthread_create(&thread, &attr, exceptionHandlerEntryPoint, NULL));
    pthread_attr_destroy(&attr);
    
    assert(task_set_exception_ports(self, EXC_MASK_BAD_ACCESS, mach_ex_port, EXCEPTION_DEFAULT, MACHINE_THREAD_STATE) == KERN_SUCCESS);
}
#endif
