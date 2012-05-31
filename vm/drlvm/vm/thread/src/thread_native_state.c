/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file thread_native_state.c
 * @brief Hythread state related functions
 */

#include <open/hythread_ext.h>
#include "thread_private.h"

/**
 * Returns non-zero if thread is currently alive.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_alive(hythread_t thread) { return thread->state & TM_THREAD_STATE_ALIVE ;  };

/**
 * Returns non-zero if thread is blocked on monitor enter.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_blocked_on_monitor_enter(hythread_t thread) { return thread->state & TM_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER ;  };

/**
 * Returns non-zero if thread is waiting on monitor.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_in_monitor_wait(hythread_t thread) { return thread->state & TM_THREAD_STATE_IN_MONITOR_WAIT ;  };

/**
 * Returns non-zero if thread is in native.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_in_native(hythread_t thread) { return thread->state & TM_THREAD_STATE_IN_NATIVE ;  };

/**
 * Returns non-zero if thread is parked.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_parked(hythread_t thread) { return thread->state & TM_THREAD_STATE_PARKED ;  };

/**
 * Returns non-zero if thread is runnable.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_runnable(hythread_t thread) { return thread->state & TM_THREAD_STATE_RUNNABLE;  };

/**
 * Returns non-zero if thread is sleeping.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_sleeping(hythread_t thread) { return thread->state & TM_THREAD_STATE_SLEEPING ;  };

/**
 * Returns non-zero if thread is suspended.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_suspended(hythread_t thread) { return thread->state & TM_THREAD_STATE_SUSPENDED ;  };

/**
 * Returns non-zero if thread is terminated.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_terminated(hythread_t thread) { return thread->state & TM_THREAD_STATE_TERMINATED ;  };

/**
 * Returns non-zero if thread is waiting.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_waiting(hythread_t thread) { return thread->state & TM_THREAD_STATE_WAITING ;  };

/**
 * Returns non-zero if thread is waiting indefinitely.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_waiting_indefinitely(hythread_t thread) { return thread->state & TM_THREAD_STATE_WAITING_INDEFINITELY ;  };

/**
 * Returns non-zero if thread is waiting with timeout.
 *
 * @param[in] thread those attribute is read
 */
int VMCALL hythread_is_waiting_with_timeout(hythread_t thread) { return thread->state & TM_THREAD_STATE_WAITING_WITH_TIMEOUT ;  };
