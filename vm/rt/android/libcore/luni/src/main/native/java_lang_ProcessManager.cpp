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

#include <dirent.h>
#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <sys/resource.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#include "cutils/log.h"
#include "jni.h"
#include "ExecStrings.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "Portability.h"
#include "ScopedLocalRef.h"
#include "toStringArray.h"

static void CloseNonStandardFds(int status_pipe_fd) {
  // On Cygwin, Linux, and Solaris, the best way to close iterates over "/proc/self/fd/".
  const char* fd_path = "/proc/self/fd";
#ifdef __APPLE__
  // On Mac OS, there's "/dev/fd/" which Linux seems to link to "/proc/self/fd/",
  // but which on Solaris appears to be something quite different.
  fd_path = "/dev/fd";
#endif

  // Keep track of the system properties fd so we don't close it.
  int properties_fd = -1;
  char* properties_fd_string = getenv("ANDROID_PROPERTY_WORKSPACE");
  if (properties_fd_string != NULL) {
    properties_fd = atoi(properties_fd_string);
  }

  DIR* d = opendir(fd_path);
  int dir_fd = dirfd(d);
  dirent* e;
  while ((e = readdir(d)) != NULL) {
    char* end;
    int fd = strtol(e->d_name, &end, 10);
    if (!*end) {
      if (fd > STDERR_FILENO && fd != dir_fd && fd != status_pipe_fd && fd != properties_fd) {
        close(fd);
      }
    }
  }
  closedir(d);
}

#define PIPE_COUNT 4 // Number of pipes used to communicate with child.

static void ClosePipes(int pipes[], int skip_fd) {
  for (int i = 0; i < PIPE_COUNT * 2; i++) {
    int fd = pipes[i];
    if (fd != -1 && fd != skip_fd) {
      close(pipes[i]);
    }
  }
}

static void AbortChild(int status_pipe_fd) {
  int error = errno;
  TEMP_FAILURE_RETRY(write(status_pipe_fd, &error, sizeof(int)));
  close(status_pipe_fd);
  _exit(127);
}

/** Executes a command in a child process. */
static pid_t ExecuteProcess(JNIEnv* env, char** commands, char** environment,
                            const char* workingDirectory, jobject inDescriptor,
                            jobject outDescriptor, jobject errDescriptor,
                            jboolean redirectErrorStream) {

  // Create 4 pipes: stdin, stdout, stderr, and an exec() status pipe.
  int pipes[PIPE_COUNT * 2] = { -1, -1, -1, -1, -1, -1, -1, -1 };
  for (int i = 0; i < PIPE_COUNT; i++) {
    if (pipe(pipes + i * 2) == -1) {
      jniThrowIOException(env, errno);
      ClosePipes(pipes, -1);
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
    ClosePipes(pipes, -1);
    return -1;
  }

  // If this is the child process...
  if (childPid == 0) {
    // Note: We cannot malloc(3) or free(3) after this point!
    // A thread in the parent that no longer exists in the child may have held the heap lock
    // when we forked, so an attempt to malloc(3) or free(3) would result in deadlock.

    // Replace stdin, out, and err with pipes.
    dup2(stdinIn, 0);
    dup2(stdoutOut, 1);
    if (redirectErrorStream) {
      dup2(stdoutOut, 2);
    } else {
      dup2(stderrOut, 2);
    }

    // Close all but statusOut. This saves some work in the next step.
    ClosePipes(pipes, statusOut);

    // Make statusOut automatically close if execvp() succeeds.
    fcntl(statusOut, F_SETFD, FD_CLOEXEC);

    // Close remaining unwanted open fds.
    CloseNonStandardFds(statusOut);

    // Switch to working directory.
    if (workingDirectory != NULL) {
      if (chdir(workingDirectory) == -1) {
        AbortChild(statusOut);
      }
    }

    // Set up environment.
    if (environment != NULL) {
      extern char** environ; // Standard, but not in any header file.
      environ = environment;
    }

    // Execute process. By convention, the first argument in the arg array
    // should be the command itself.
    execvp(commands[0], commands);
    AbortChild(statusOut);
  }

  // This is the parent process.

  // Close child's pipe ends.
  close(stdinIn);
  close(stdoutOut);
  close(stderrOut);
  close(statusOut);

  // Check status pipe for an error code. If execvp(2) succeeds, the other
  // end of the pipe should automatically close, in which case, we'll read
  // nothing.
  int child_errno;
  ssize_t count = TEMP_FAILURE_RETRY(read(statusIn, &child_errno, sizeof(int)));
  close(statusIn);
  if (count > 0) {
    // chdir(2) or execvp(2) in the child failed.
    // TODO: track which so we can be more specific in the detail message.
    jniThrowIOException(env, child_errno);

    close(stdoutIn);
    close(stdinOut);
    close(stderrIn);

    // Reap our zombie child right away.
    int status;
    int rc = TEMP_FAILURE_RETRY(waitpid(childPid, &status, 0));
    if (rc == -1) {
      ALOGW("waitpid on failed exec failed: %s", strerror(errno));
    }

    return -1;
  }

  // Fill in file descriptor wrappers.
  jniSetFileDescriptorOfFD(env, inDescriptor, stdoutIn);
  jniSetFileDescriptorOfFD(env, outDescriptor, stdinOut);
  jniSetFileDescriptorOfFD(env, errDescriptor, stderrIn);

  return childPid;
}

/**
 * Converts Java String[] to char** and delegates to ExecuteProcess().
 */
extern "C" pid_t Java_java_lang_ProcessManager_exec(JNIEnv* env, jclass, jobjectArray javaCommands,
                                 jobjectArray javaEnvironment, jstring javaWorkingDirectory,
                                 jobject inDescriptor, jobject outDescriptor, jobject errDescriptor,
                                 jboolean redirectErrorStream) {

  ExecStrings commands(env, javaCommands);
  ExecStrings environment(env, javaEnvironment);

  // Extract working directory string.
  const char* workingDirectory = NULL;
  if (javaWorkingDirectory != NULL) {
    workingDirectory = env->GetStringUTFChars(javaWorkingDirectory, NULL);
  }

  pid_t result = ExecuteProcess(env, commands.get(), environment.get(), workingDirectory,
                                inDescriptor, outDescriptor, errDescriptor, redirectErrorStream);

  // Clean up working directory string.
  if (javaWorkingDirectory != NULL) {
    env->ReleaseStringUTFChars(javaWorkingDirectory, workingDirectory);
  }

  return result;
}
