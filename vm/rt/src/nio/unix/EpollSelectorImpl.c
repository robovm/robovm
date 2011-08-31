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

#include <jni.h>
#include "hysock.h"
#include "hyport.h"
#include "EpollSelectorImpl.h"

#ifdef LINUX
#include <linux/version.h>
#if (LINUX_VERSION_CODE >= KERNEL_VERSION(2, 6, 0))
#define HAVE_EPOLL
#endif
#endif

#ifdef HAVE_EPOLL

// use kernel 2.6 epoll facility
#include <sys/epoll.h>

#else

// make the stubs for epoll to fail gently

#define EPOLLIN 0
#define EPOLLOUT 0
#define EPOLLPRI 0

#define EPOLL_CTL_ADD 0
#define EPOLL_CTL_DEL 0

typedef union epoll_data {
  int fd;
} epoll_data_t;

struct epoll_event {
   __uint32_t events;  /* Epoll events */
   epoll_data_t data;  /* User data variable */
};


void fail_msg() {
	printf("No epoll facility support, please make sure you have build and run on 2.6.x kernel\n");
	exit(0);
}

int epoll_create(int size) {
	fail_msg();
	return -1;
}

int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event) {
	fail_msg();
	return -1;
}

int epoll_wait(int epfd, struct epoll_event * events, int maxevents, int timeout) {
	fail_msg();
	return -1;
}

#endif
/* Header for class org_apache_harmony_nio_internal_EPollSelectorImpl */

//#define EPOLL_DEBUG 
//#define EPOLL_DEBUG_EXTENSIVE

/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    resolveFD
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_resolveFD
  (JNIEnv * env, jclass clazz, jclass cfd, jobject ofd) 
{
      jfieldID descriptorFID = (*env)->GetFieldID(env, cfd, "descriptor", "J");
      return ((hysocket_t) ((IDATA)((*env)->GetLongField (env, ofd, descriptorFID))))->sock;
} 

/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    prepare
 * Signature: ()I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_prepare
  (JNIEnv * jniEnv, jclass clazz) 
{
    int fd;

#ifdef EPOLL_DEBUG
    printf("epoll(): initializing epoll\n");
#endif
    
    fd = epoll_create(256);

#ifdef EPOLL_DEBUG
    printf("epoll(): epoll_create finished with %d\n", fd);
#endif
    
    // let the Java code handle the exceptions
    return fd;
} 


/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    addFileDescriptor
 * Signature: (L)I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_addFileDescriptor
  (JNIEnv * env, jclass clazz, jlong epollfd, jint mode, jint fd) 
{
    struct epoll_event ev;
    int result;
    
    ev.events = 0;
    if((mode & SOCKET_READ_OP) != 0) {
        ev.events = ev.events | EPOLLIN | EPOLLPRI;
    }
    
    if((mode & SOCKET_WRITE_OP) != 0) {
        ev.events = ev.events | EPOLLOUT;
    }   

    ev.data.fd = fd;

#ifdef EPOLL_DEBUG   
    printf("epoll(): fd=%d, adding %d to filedescriptor list with mode %d and event mask %d\n", epollfd, fd, mode, ev.events);
#endif
    
    result = epoll_ctl(epollfd, EPOLL_CTL_ADD, fd, &ev);

#ifdef EPOLL_DEBUG
    if(result == -1) {
        printf("epoll(): result=%d errno=%d EBADF=%d EPERM=%d EINVAL=%d ENOMEM=%d\n", result, errno, EBADF, EPERM, EINVAL, ENOMEM);
    }
#endif
    
    // let the java layer handle exceptions
    return result;
}    


/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    delFileDescriptor
 * Signature: (L)I
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_delFileDescriptor
  (JNIEnv * jniEnv, jclass clazz, jlong epollfd, jlong fd) 
{
    int result;
    struct epoll_event ev;

    ev.data.fd = fd;

#ifdef EPOLL_DEBUG    
    printf("epoll(): deletin %ld from filedescriptor list\n", fd);
#endif
    
    result = epoll_ctl(epollfd, EPOLL_CTL_DEL, fd, &ev);

#ifdef EPOLL_DEBUG
    if (result == -1) {
        printf("epoll(): result=%d errno=%d EBADF=%d EPERM=%d EINVAL=%d ENOMEM=%d\n", result, errno, EBADF, EPERM, EINVAL, ENOMEM);
    }    
#endif    
     
    // let the Java layer handle exceptions   
    return result;
} 


/*
 * Class:     org_apache_harmony_nio_internal_EpollSelectorImpl
 * Method:    epoll
 * Signature: (L)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_nio_internal_EpollSelectorImpl_epoll
  (JNIEnv * env, jclass clazz, jlong epollfd, jint count, jintArray fds, jintArray ops, jlong timeout) 
{
    struct epoll_event * evs;
    jint * fdsArray;
    jint * opsArray;
    int result;
    jboolean isCopy;
    int c;
    int temp;
    
    evs = malloc(sizeof(struct epoll_event)*count);
    if (!evs) {
        printf("epoll(): error on memory allocation\n");
        return -1;
        
    }
    
#ifdef EPOLL_DEBUG
#ifdef EPOLL_DEBUG_EXTENSIVE    
   printf("epoll(): waiting on %d channels for %d msecs\n", count, timeout);
#endif    
#endif   
  
    // wait! 
    result = epoll_wait(epollfd, evs, count, timeout); 

#ifdef EPOLL_DEBUG
#ifdef EPOLL_DEBUG_EXTENSIVE
    printf("epoll(): %d channels up, %d total\n", result, count);
#endif
#endif
    
#ifdef EPOLL_DEBUG
#ifdef EPOLL_DEBUG_EXTENSIVE
    printf("epoll(): Start parsing epoll() results\n");
#endif
#endif

    // copying out the results, pinning might help here...
    isCopy = JNI_FALSE;
    fdsArray = (*env)->GetIntArrayElements(env, fds, &isCopy);

    isCopy = JNI_FALSE;
    opsArray = (*env)->GetIntArrayElements(env, ops, &isCopy);

    for(c = 0; c < result; c++) {
        fdsArray[c] = evs[c].data.fd;

        temp = SOCKET_NONE_OP;
        if (evs[c].events & (EPOLLIN | EPOLLPRI)) {
            temp = temp + SOCKET_READ_OP;
        }
            
        if (evs[c].events & (EPOLLOUT)) {
            temp = temp + SOCKET_WRITE_OP;
        }
        opsArray[c] = temp;
    }
    (*env)->ReleaseIntArrayElements(env, ops, opsArray, 0);
    (*env)->ReleaseIntArrayElements(env, fds, fdsArray, 0);

#ifdef EPOLL_DEBUG
#ifdef EPOLL_DEBUG_EXTENSIVE
    printf("epoll(): End parsing epoll() results\n");
#endif
#endif
    
    free(evs);    
   
    // let the Java code handle the exceptions 
    return result;
} 
