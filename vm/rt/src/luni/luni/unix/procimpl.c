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

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <time.h>

#include <errno.h>

#include <sys/ioctl.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#if defined(DARWIN)
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
extern char **environ;
#endif

#ifdef ZOS
#define FD_BIAS 1000
#include <sys/socket.h>
#else
#define FD_BIAS 0
#endif /* ZOS */

#include <sys/wait.h>

#include "procimpl.h"

int setCloseOnExec(int fd);

void
sleepFor(unsigned int nanoseconds)
{

#if defined(AIX) || defined(ZOS)
/* These platforms don't have nanosleep(). */

  unsigned int microseconds = (nanoseconds + 999) / 1000;
  usleep(microseconds);

#else /* other unix platforms */
  struct timespec delay, remDelay;
  delay.tv_sec = 0;
  delay.tv_nsec = nanoseconds;

  while (nanosleep(&delay, &remDelay) == -1) {
    if (errno == EINTR) {
      delay.tv_nsec = remDelay.tv_nsec; /* tv_sec is zero */
    } else {
      break;                    /* Oops the sleep didn't work ??? */
    }
  }
#endif /* AIX or ZOS */
}

int
termProc(IDATA procHandle)
{
  int rc;

  rc = kill((pid_t) procHandle, SIGTERM);
  return rc;
}

int
waitForProc(IDATA procHandle)
{
  int StatusLocation = -1;

  waitpid((pid_t) procHandle, &StatusLocation, 0);
  if (WIFEXITED(StatusLocation) != 0) {
    StatusLocation = WEXITSTATUS(StatusLocation);
  } else {
    if (WIFSIGNALED(StatusLocation) != 0) {
      StatusLocation = WTERMSIG(StatusLocation);
    } else {
      if (WIFSTOPPED(StatusLocation) != 0) {
        StatusLocation = WSTOPSIG(StatusLocation);
      }
    }
  }

  return StatusLocation;
}


/*
 *  int execProgram()
 * 
 *  does a fork/execvp to launch the program
 * 
 *  returns :
 *     0     successful
 *     1001  fork failure errno = ENOMEM
 *     1002  fork failure errno = EAGAIN
 *     1003  pipe failure errno = EMFILE
 *     1004  chdir failure errno = ENOENT
 *     -1    error, unknown
 * 
 *   Note - there is one error code 'namespace' for execProgram
 *          please coordinate w/ other platform impls
 */
int
execProgram(JNIEnv * vmthread, jobject recv,
            char *command[], int commandLineLength,
            char *env[], int envSize, char *dir, IDATA * procHandle,
            IDATA * inHandle, IDATA * outHandle, IDATA * errHandle)
{
  /* It is illegal to pass JNIEnv accross threads, so get the vm while
   * we will go across another thread. The javaObject recv is used in
   * the new thread  ==> make it a globalRef.
   */

  int result = -1;
  char *cmd;
  int grdpid, rc = 0;
  int newFD[3][2] = { {0,0}, {0,0}, {0,0} };
  int execvFailure[2] = {0,0};
  int forkedChildIsRunning[2] = {0,0};
  int error = 0;
  int writeRC = 0;

  #ifdef ZOS
    /* Build the new io pipes (in/out/err) */
    if(socketpair(AF_UNIX,SOCK_STREAM,0,newFD[0]) == -1) goto error;
    if(socketpair(AF_UNIX,SOCK_STREAM,0,newFD[1]) == -1) goto error;
    if(socketpair(AF_UNIX,SOCK_STREAM,0,newFD[2]) == -1) goto error;

    /* pipes for synchronization */
    if(socketpair(AF_UNIX,SOCK_STREAM,0,forkedChildIsRunning) == -1) goto error;
    if(socketpair(AF_UNIX,SOCK_STREAM,0,execvFailure) == -1) goto error;       
  #else
     /* Build the new io pipes (in/out/err) */
    if (pipe(newFD[0]) == -1) goto error;
    if (pipe(newFD[1]) == -1) goto error;
    if (pipe(newFD[2]) == -1) goto error;

    /* pipes for synchronization */
    if (pipe(forkedChildIsRunning) == -1) goto error;
    if (pipe(execvFailure) == -1) goto error;  
  #endif /* ZOS */

  cmd = command[0];

  grdpid = fork();

  /*
   *   if we fail, lets clean up and bail right here
   */

  if (grdpid == -1) goto error;

  if (grdpid == 0) {
    char dummy = '\0';

    /* Close file descriptors that are not used */
    close(newFD[0][1]);
    close(newFD[1][0]);
    close(newFD[2][0]);
    close(forkedChildIsRunning[0]);
    close(execvFailure[0]);

    /* Make sure the others close if the exec succeeds */
    setCloseOnExec(newFD[0][0]); /* dup2 removes this on the new handle */
    setCloseOnExec(newFD[1][1]);
    setCloseOnExec(newFD[2][1]);
    setCloseOnExec(forkedChildIsRunning[1]);
    setCloseOnExec(execvFailure[1]);

    /* Redirect pipes so grand-child inherits new pipes */
    dup2(newFD[0][0], 0);
    dup2(newFD[1][1], 1);
    dup2(newFD[2][1], 2);

    /* tells the parent that that very process is running */
    if (-1 == write(forkedChildIsRunning[1], &dummy, 1)) {
      goto error;
    }

    if (dir) {
      if (chdir(dir) == -1) {
        /* capture return code to fix compiler warning */
        writeRC = write(execvFailure[1], &errno, sizeof(errno));
        exit(-1);
      }
    }

    /* ===try to perform the execv : on success, it does not return ===== */
    if (envSize != 0) {
      environ = env;
    }

    rc = execvp(cmd, command);

    /* ===================================================== */

    /* if we get here ==> tell the parent that the execv failed ! */
    /* capture return code to fix compiler warning */
    writeRC = write(execvFailure[1], &errno, sizeof(errno));
    /* If the exec failed, we must exit or there will be two VM processes running. */
    exit(rc);
  } else {
    /* in the child-thread (not the grand-child) */
    char dummy;
    int avail = 0;
    int noDataInThePipe;
    int nbLoop;

    close(newFD[0][0]);
    close(newFD[1][1]);
    close(newFD[2][1]);
    /* Store the rw handles to the childs io */
    *(inHandle) = (IDATA) newFD[0][1] + FD_BIAS;
    *(outHandle) = (IDATA) newFD[1][0] + FD_BIAS;
    *(errHandle) = (IDATA) newFD[2][0] + FD_BIAS;
    *(procHandle) = (IDATA) grdpid;

    /* let the forked child start. */
    if (-1 == read(forkedChildIsRunning[0], &dummy, 1)) {
    	goto error;
    }
    close(forkedChildIsRunning[0]);
    close(forkedChildIsRunning[1]);

    /* Use the POSIX setpgid and its errno EACCES to detect the success of the execv function. When the feature is
       not present on the platform, a delay is provided after which we conclude that if the execv didn't fail, it
       must have propably succeeded. We loop on reading a pipe which will receive a byte if the execv fails. We
       also break from the loop, if we have detected the success of the execv (or past a delay if the functionaly
       is not present) */

    rc = 0;                     /* at first glance, the execv will succeed (-1 is for failure) */
    noDataInThePipe = 1;
    ioctl(execvFailure[0], FIONREAD, &avail);
    if (avail > 0) {
      rc = -1;                  /* failure of the execv */
      noDataInThePipe = 0;
      if (read(execvFailure[0], &error, sizeof(error)) == sizeof(error)) {
        goto error_with_error_set;
      }
    }
    nbLoop = 0;
    while (noDataInThePipe) {
      int setgpidResult;
      /* =======give the child a chance to run=========== */
      sleepFor(10000000);       /* 10 ms */
          /*========== probe the child for success of the execv ========*/
      setgpidResult = setpgid(grdpid, grdpid);
      if (setgpidResult == -1) {
        if (errno == EACCES) {
          /* fprintf(stdout,"\nSUCCESS DETECTED\n");fflush(stdout); */
          break;                /* success of the execv */
        } else {
          /* setgpid is probably not supported . Give some a bit of time to the child to tell us if it has
             failed to launch the execv */
          nbLoop++;
          if (nbLoop > 10) {
            break;              /* well, execv has probably succeeded */
          }
        }
      }
      /* =========Has a byte arrived in the pipe ? (failure test) ========= */
      ioctl(execvFailure[0], FIONREAD, &avail);
      if (avail > 0) {
        rc = -1;                /* failure of the execv */
        noDataInThePipe = 0;
        if (read(execvFailure[0], &error, sizeof(error)) == sizeof(error)) {
          goto error_with_error_set;
        }
      }
    }                           /* end of the loop. rc==-1 iff the execv failed */

    /* if (rc==-1){ fprintf(stdout,"\nFAILURE DETECTED\n");fflush(stdout); } */

    close(execvFailure[0]);
    close(execvFailure[1]);

    if (rc != -1) {
      result = 0;
    }
  }

  return result;

 error:

  error = errno;

 error_with_error_set:

  if (execvFailure[0]) close(execvFailure[0]);
  if (execvFailure[1]) close(execvFailure[1]);

  if (forkedChildIsRunning[0]) close(forkedChildIsRunning[0]);
  if (forkedChildIsRunning[1]) close(forkedChildIsRunning[1]);

  if (newFD[2][0]) close(newFD[2][0]);
  if (newFD[2][1]) close(newFD[2][1]);

  if (newFD[1][0]) close(newFD[1][0]);
  if (newFD[1][1]) close(newFD[1][1]);

  if (newFD[0][0]) close(newFD[0][0]);
  if (newFD[0][1]) close(newFD[0][1]);

  switch (error) {
  case ENOMEM:
    result = 1001;
    break;
  case EAGAIN:
    result = 1002;
    break;
  case EMFILE:
    result = 1003;
    break;
  case ENOENT:
    result = 1004;
    break;
  }

  return result;
}

/* Stream handling support */
/* Return the number of bytes available to be read without blocking */
int
getAvailable(IDATA sHandle)
{
  int avail, rc;
  rc = ioctl((int) sHandle - FD_BIAS, FIONREAD, &avail);
  if (rc == -1)
    return -2;
  return avail;
}

int
closeProc(IDATA procHandle)
{
  /* The procHandle (Process ID) should not be closed, as it isn't a file descriptor. */
  return 0;
}

int
setCloseOnExec(int fd)
{
  int flags = fcntl(fd, F_GETFD);
  if (flags == -1) return -1;
  flags |= FD_CLOEXEC;
  return fcntl(fd, F_SETFD, flags);
}
