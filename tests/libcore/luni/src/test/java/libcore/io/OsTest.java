/*
 * Copyright (C) 2011 The Android Open Source Project
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

package libcore.io;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InetUnixAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.Locale;
import junit.framework.TestCase;

import static libcore.io.OsConstants.*;

public class OsTest extends TestCase {
  public void testIsSocket() throws Exception {
    File f = new File("/dev/null");
    FileInputStream fis = new FileInputStream(f);
    assertFalse(S_ISSOCK(Libcore.os.fstat(fis.getFD()).st_mode));
    fis.close();

    ServerSocket s = new ServerSocket();
    assertTrue(S_ISSOCK(Libcore.os.fstat(s.getImpl$().getFD$()).st_mode));
    s.close();
  }

  public void testUnixDomainSockets_in_file_system() throws Exception {
    String path = System.getProperty("java.io.tmpdir") + "/test_unix_socket";
    new File(path).delete();
    checkUnixDomainSocket(new InetUnixAddress(path), false);
  }

  public void testUnixDomainSocket_abstract_name() throws Exception {
    // Linux treats a sun_path starting with a NUL byte as an abstract name. See unix(7).
    byte[] path = "/abstract_name_unix_socket".getBytes("UTF-8");
    path[0] = 0;
    checkUnixDomainSocket(new InetUnixAddress(path), true);
  }

  private void checkUnixDomainSocket(final InetUnixAddress address, final boolean isAbstract) throws Exception {
    final FileDescriptor serverFd = Libcore.os.socket(AF_UNIX, SOCK_STREAM, 0);
    Libcore.os.bind(serverFd, address, 0);
    Libcore.os.listen(serverFd, 5);

    checkSockName(serverFd, isAbstract, address);

    Thread server = new Thread(new Runnable() {
      public void run() {
        try {
          InetSocketAddress peerAddress = new InetSocketAddress();
          FileDescriptor clientFd = Libcore.os.accept(serverFd, peerAddress);
          checkSockName(clientFd, isAbstract, address);
          checkNoName(peerAddress);

          checkNoPeerName(clientFd);

          StructUcred credentials = Libcore.os.getsockoptUcred(clientFd, SOL_SOCKET, SO_PEERCRED);
          assertEquals(Libcore.os.getpid(), credentials.pid);
          assertEquals(Libcore.os.getuid(), credentials.uid);
          assertEquals(Libcore.os.getgid(), credentials.gid);

          byte[] request = new byte[256];
          int requestLength = Libcore.os.read(clientFd, request, 0, request.length);

          String s = new String(request, "UTF-8");
          byte[] response = s.toUpperCase(Locale.ROOT).getBytes("UTF-8");
          Libcore.os.write(clientFd, response, 0, response.length);

          Libcore.os.close(clientFd);
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    server.start();

    FileDescriptor clientFd = Libcore.os.socket(AF_UNIX, SOCK_STREAM, 0);

    Libcore.os.connect(clientFd, address, 0);
    checkNoSockName(clientFd);

    String string = "hello, world!";

    byte[] request = string.getBytes("UTF-8");
    assertEquals(request.length, Libcore.os.write(clientFd, request, 0, request.length));

    byte[] response = new byte[request.length];
    assertEquals(response.length, Libcore.os.read(clientFd, response, 0, response.length));

    assertEquals(string.toUpperCase(Locale.ROOT), new String(response, "UTF-8"));

    Libcore.os.close(clientFd);
  }

  private void checkSockName(FileDescriptor fd, boolean isAbstract, InetAddress address) throws Exception {
    InetSocketAddress isa = (InetSocketAddress) Libcore.os.getsockname(fd);
    if (isAbstract) {
      checkNoName(isa);
    } else {
      assertEquals(address, isa.getAddress());
    }
  }

  private void checkNoName(SocketAddress sa) {
    InetSocketAddress isa = (InetSocketAddress) sa;
    assertEquals(0, isa.getAddress().getAddress().length);
  }

  private void checkNoPeerName(FileDescriptor fd) throws Exception {
    checkNoName(Libcore.os.getpeername(fd));
  }

  private void checkNoSockName(FileDescriptor fd) throws Exception {
    checkNoName(Libcore.os.getsockname(fd));
  }

  public void test_strsignal() throws Exception {
    assertEquals("Killed", Libcore.os.strsignal(9));
    assertEquals("Unknown signal -1", Libcore.os.strsignal(-1));
  }
}
