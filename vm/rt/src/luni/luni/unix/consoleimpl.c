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
 
 #include <termios.h>
 #include "exceptions.h"
 #include "consoleimpl.h"
 
 int fd_stdin;
 int fd_stdout;
 struct termios saved_termios;
 
 /*
 * Whether the standard input stream is attached to a console.
 */
  JNIEXPORT jboolean JNICALL Java_java_io_Console_hasStdInImpl(JNIEnv *env, jclass thiz){
       fd_stdin = 0;
       //stdin is not attached to a console.
       if(!isatty(fd_stdin)){
          return 0;
       }   
    return 1;
  }
    


/*
 * Gets the standard output as an OutputStream if it is attached to a console.
 */
  JNIEXPORT jboolean JNICALL Java_java_io_Console_hasStdOutImpl(JNIEnv *env, jclass thiz){
    fd_stdout = 1;
    //stdout is not attached to a console.
    if(!isatty(fd_stdout)){
       return 0;
    }    
    return 1;
  }
    
/*
 * Sets the standard input echoing off.
 */
  JNIEXPORT void JNICALL Java_java_io_Console_setEchoOffImpl(JNIEnv *env, jclass thiz){
    struct termios new_termios;
    if(tcgetattr(fd_stdin, &saved_termios)<0){
       throwJavaIoIOException(env, "fails to get stdin attributes when echoing off.");
    }    
    memcpy(&new_termios, &saved_termios, sizeof(struct termios));
    
    //set echo off mask
    new_termios.c_lflag &= ~ECHO;
    new_termios.c_lflag |= ECHONL;
    if(tcsetattr(fd_stdin, TCSAFLUSH, &new_termios)<0){
        throwJavaIoIOException(env, "fails to set stdin attributes when echoing off.");
    }  
  }  
    
/*
 * Sets the standard output echoing on.
 */
  JNIEXPORT void JNICALL Java_java_io_Console_setEchoOnImpl(JNIEnv *env, jclass thiz){
    
    if(tcsetattr(fd_stdin, TCSAFLUSH, &saved_termios)<0){
         throwJavaIoIOException(env, "fails to set stdin attributes when echoing on.");
      }
 }
 
