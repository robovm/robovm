/*
 * Copyright (C) 2007 The Android Open Source Project
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

#define LOG_TAG "ProcessManager"

#include <sys/resource.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#include "jni.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedLocalRef.h"
#include "cutils/log.h"

/** Close all open fds > 2 (i.e. everything but stdin/out/err), != skipFd. */
static void closeNonStandardFds(int skipFd1, int skipFd2) {
    // TODO: rather than close all these non-open files, we could look in /proc/self/fd.
    rlimit rlimit;
    getrlimit(RLIMIT_NOFILE, &rlimit);
    const int max_fd = rlimit.rlim_max;
    for (int fd = 3; fd < max_fd; ++fd) {
        if (fd != skipFd1 && fd != skipFd2) {
            close(fd);
        }
    }
}

#define PIPE_COUNT (4) // number of pipes used to communicate with child proc

/** Closes all pipes in the given array. */
static void closePipes(int pipes[], int skipFd) {
    for (int i = 0; i < PIPE_COUNT * 2; i++) {
        int fd = pipes[i];
        if (fd == -1) {
            return;
        }
        if (fd != skipFd) {
            close(pipes[i]);
        }
    }
}

/** Executes a command in a child process. */
static pid_t executeProcess(JNIEnv* env, char** commands, char** environment,
        const char* workingDirectory, jobject inDescriptor,
        jobject outDescriptor, jobject errDescriptor,
        jboolean redirectErrorStream) {

    // Keep track of the system properties fd so we don't close it.
    int androidSystemPropertiesFd = -1;
    char* fdString = getenv("ANDROID_PROPERTY_WORKSPACE");
    if (fdString) {
        androidSystemPropertiesFd = atoi(fdString);
    }

    // Create 4 pipes: stdin, stdout, stderr, and an exec() status pipe.
    int pipes[PIPE_COUNT * 2] = { -1, -1, -1, -1, -1, -1, -1, -1 };
    for (int i = 0; i < PIPE_COUNT; i++) {
        if (pipe(pipes + i * 2) == -1) {
            jniThrowIOException(env, errno);
            closePipes(pipes, -1);
            return -1;
        }
    }
    int stdinIn = pipes[0];
    int stdinOut = pipes[1];
    int stdoutIn = pipes[2];
    int stdoutOut = pipes[3];
    int stderrIn = pipes[4];
    int stderrOut = pipes[5];
    int statusIn = pipes[6];
    int statusOut = pipes[7];

    pid_t childPid = fork();

    // If fork() failed...
    if (childPid == -1) {
        jniThrowIOException(env, errno);
        closePipes(pipes, -1);
        return -1;
    }

    // If this is the child process...
    if (childPid == 0) {
        /*
         * Note: We cannot malloc() or free() after this point!
         * A no-longer-running thread may be holding on to the heap lock, and
         * an attempt to malloc() or free() would result in deadlock.
         */

        // Replace stdin, out, and err with pipes.
        dup2(stdinIn, 0);
        dup2(stdoutOut, 1);
        if (redirectErrorStream) {
            dup2(stdoutOut, 2);
        } else {
            dup2(stderrOut, 2);
        }

        // Close all but statusOut. This saves some work in the next step.
        closePipes(pipes, statusOut);

        // Make statusOut automatically close if execvp() succeeds.
        fcntl(statusOut, F_SETFD, FD_CLOEXEC);

        // Close remaining unwanted open fds.
        closeNonStandardFds(statusOut, androidSystemPropertiesFd);

        // Switch to working directory.
        if (workingDirectory != NULL) {
            if (chdir(workingDirectory) == -1) {
                goto execFailed;
            }
        }

        // Set up environment.
        if (environment != NULL) {
            extern char** environ; // Standard, but not in any header file.
            environ = environment;
        }

        // Execute process. By convention, the first argument in the arg array
        // should be the command itself. In fact, I get segfaults when this
        // isn't the case.
        execvp(commands[0], commands);

        // If we got here, execvp() failed or the working dir was invalid.
        execFailed:
            int error = errno;
            write(statusOut, &error, sizeof(int));
            close(statusOut);
            exit(error);
    }

    // This is the parent process.

    // Close child's pipe ends.
    close(stdinIn);
    close(stdoutOut);
    close(stderrOut);
    close(statusOut);

    // Check status pipe for an error code. If execvp() succeeds, the other
    // end of the pipe should automatically close, in which case, we'll read
    // nothing.
    int result;
    int count = read(statusIn, &result, sizeof(int));
    close(statusIn);
    if (count > 0) {
        jniThrowIOException(env, result);

        close(stdoutIn);
        close(stdinOut);
        close(stderrIn);

        return -1;
    }

    // Fill in file descriptor wrappers.
    jniSetFileDescriptorOfFD(env, inDescriptor, stdoutIn);
    jniSetFileDescriptorOfFD(env, outDescriptor, stdinOut);
    jniSetFileDescriptorOfFD(env, errDescriptor, stderrIn);

    return childPid;
}

/** Converts a Java String[] to a 0-terminated char**. */
static char** convertStrings(JNIEnv* env, jobjectArray javaArray) {
    if (javaArray == NULL) {
        return NULL;
    }

    jsize length = env->GetArrayLength(javaArray);
    char** array = new char*[length + 1];
    array[length] = 0;
    for (jsize i = 0; i < length; ++i) {
        ScopedLocalRef<jstring> javaEntry(env, reinterpret_cast<jstring>(env->GetObjectArrayElement(javaArray, i)));
        // We need to pass these strings to const-unfriendly code.
        char* entry = const_cast<char*>(env->GetStringUTFChars(javaEntry.get(), NULL));
        array[i] = entry;
    }

    return array;
}

/** Frees a char** which was converted from a Java String[]. */
static void freeStrings(JNIEnv* env, jobjectArray javaArray, char** array) {
    if (javaArray == NULL) {
        return;
    }

    jsize length = env->GetArrayLength(javaArray);
    for (jsize i = 0; i < length; ++i) {
        ScopedLocalRef<jstring> javaEntry(env, reinterpret_cast<jstring>(env->GetObjectArrayElement(javaArray, i)));
        env->ReleaseStringUTFChars(javaEntry.get(), array[i]);
    }

    delete[] array;
}

/**
 * Converts Java String[] to char** and delegates to executeProcess().
 */
extern "C" pid_t Java_java_lang_ProcessManager_exec(JNIEnv* env, jclass, jobjectArray javaCommands,
        jobjectArray javaEnvironment, jstring javaWorkingDirectory,
        jobject inDescriptor, jobject outDescriptor, jobject errDescriptor,
        jboolean redirectErrorStream) {

    // Copy commands into char*[].
    char** commands = convertStrings(env, javaCommands);

    // Extract working directory string.
    const char* workingDirectory = NULL;
    if (javaWorkingDirectory != NULL) {
        workingDirectory = env->GetStringUTFChars(javaWorkingDirectory, NULL);
    }

    // Convert environment array.
    char** environment = convertStrings(env, javaEnvironment);

    pid_t result = executeProcess(env, commands, environment, workingDirectory,
            inDescriptor, outDescriptor, errDescriptor, redirectErrorStream);

    // Temporarily clear exception so we can clean up.
    jthrowable exception = env->ExceptionOccurred();
    env->ExceptionClear();

    freeStrings(env, javaEnvironment, environment);

    // Clean up working directory string.
    if (javaWorkingDirectory != NULL) {
        env->ReleaseStringUTFChars(javaWorkingDirectory, workingDirectory);
    }

    freeStrings(env, javaCommands, commands);

    // Re-throw exception if present.
    if (exception != NULL) {
        if (env->Throw(exception) < 0) {
            ALOGE("Error rethrowing exception!");
        }
    }

    return result;
}

