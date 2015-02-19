/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.foundation;

import org.robovm.rt.bro.annotation.ForceLinkClass;

@ForceLinkClass(NSPOSIXError.class)
public enum NSPOSIXErrorCode implements NSErrorCode {
    /** Operation not permitted. */
    EPERM(1),
    /** No such file or directory. */
    ENOENT(2),
    /** No such process. */
    ESRCH(3),
    /** Interrupted system call. */
    EINTR(4),
    /** Input/output error. */
    EIO(5),
    /** Device not configured. */
    ENXIO(6),
    /** Argument list too long. */
    E2BIG(7),
    /** Exec format error. */
    ENOEXEC(8),
    /** Bad file descriptor. */
    EBADF(9),
    /** No child processes. */
    ECHILD(10),
    /** Resource deadlock avoided. */
    EDEADLK(11),
    /** Cannot allocate memory. */
    ENOMEM(12),
    /** Permission denied. */
    EACCES(13),
    /** Bad address. */
    EFAULT(14),
    /** Block device required. */
    ENOTBLK(15),
    /** Device / Resource busy. */
    EBUSY(16),
    /** File exists. */
    EEXIST(17),
    /** Cross-device link. */
    EXDEV(18),
    /** Operation not supported by device. */
    ENODEV(19),
    /** Not a directory. */
    ENOTDIR(20),
    /** Is a directory. */
    EISDIR(21),
    /** Invalid argument. */
    EINVAL(22),
    /** Too many open files in system. */
    ENFILE(23),
    /** Too many open files. */
    EMFILE(24),
    /** Inappropriate ioctl for device. */
    ENOTTY(25),
    /** Text file busy. */
    ETXTBSY(26),
    /** File too large. */
    EFBIG(27),
    /** No space left on device. */
    ENOSPC(28),
    /** Illegal seek. */
    ESPIPE(29),
    /** Read-only file system. */
    EROFS(30),
    /** Too many links. */
    EMLINK(31),
    /** Broken pipe. */
    EPIPE(32),
    /** Numerical argument out of domain. */
    EDOM(33),
    /** Result too large. */
    ERANGE(34),
    /** Resource temporarily unavailable. */
    EAGAIN(35),
    /** Operation would block. */
    EWOULDBLOCK(35),
    /** Operation now in progress. */
    EINPROGRESS(36),
    /** Operation already in progress. */
    EALREADY(37),
    /** Socket operation on non-socket. */
    ENOTSOCK(38),
    /** Destination address required. */
    EDESTADDRREQ(39),
    /** Message too long. */
    EMSGSIZE(40),
    /** Protocol wrong type for socket. */
    EPROTOTYPE(41),
    /** Protocol not available. */
    ENOPROTOOPT(42),
    /** Protocol not supported. */
    EPROTONOSUPPORT(43),
    /** Socket type not supported. */
    ESOCKTNOSUPPORT(44),
    /** Operation not supported. */
    ENOTSUP(45),
    /** Protocol family not supported. */
    EPFNOSUPPORT(46),
    /** Address family not supported by protocol family. */
    EAFNOSUPPORT(47),
    /** Address already in use. */
    EADDRINUSE(48),
    /** Can't assign requested address. */
    EADDRNOTAVAIL(49),
    /** Network is down. */
    ENETDOWN(50),
    /** Network is unreachable. */
    ENETUNREACH(51),
    /** Network dropped connection on reset. */
    ENETRESET(52),
    /** Software caused connection abort. */
    ECONNABORTED(53),
    /** Connection reset by peer. */
    ECONNRESET(54),
    /** No buffer space available. */
    ENOBUFS(55),
    /** Socket is already connected. */
    EISCONN(56),
    /** Socket is not connected. */
    ENOTCONN(57),
    /** Can't send after socket shutdown. */
    ESHUTDOWN(58),
    /** Too many references: can't splice. */
    ETOOMANYREFS(59),
    /** Operation timed out. */
    ETIMEDOUT(60),
    /** Connection refused. */
    ECONNREFUSED(61),
    /** Too many levels of symbolic links. */
    ELOOP(62),
    /** File name too long. */
    ENAMETOOLONG(63),
    /** Host is down. */
    EHOSTDOWN(64),
    /** No route to host. */
    EHOSTUNREACH(65),
    /** Directory not empty. */
    ENOTEMPTY(66),
    /** Too many processes. */
    EPROCLIM(67),
    /** Too many users. */
    EUSERS(68),
    /** Disc quota exceeded. */
    EDQUOT(69),
    /** Stale NFS file handle. */
    ESTALE(70),
    /** Too many levels of remote in path. */
    EREMOTE(71),
    /** RPC struct is bad. */
    EBADRPC(72),
    /** RPC version wrong. */
    ERPCMISMATCH(73),
    /** RPC prog. not avail. */
    EPROGUNAVAIL(74),
    /** Program version wrong. */
    EPROGMISMATCH(75),
    /** Bad procedure for program. */
    EPROCUNAVAIL(76),
    /** No locks available. */
    ENOLCK(77),
    /** Function not implemented. */
    ENOSYS(78),
    /** Inappropriate file type or format. */
    EFTYPE(79),
    /** Authentication error. */
    EAUTH(80),
    /** Need authenticator. */
    ENEEDAUTH(81),
    /** Device power is off. */
    EPWROFF(82),
    /** Device error, e.g. paper out. */
    EDEVERR(83),
    /** Value too large to be stored in data type. */
    EOVERFLOW(84),
    /** Bad executable. */
    EBADEXEC(85),
    /** Bad CPU type in executable. */
    EBADARCH(86),
    /** Shared library version mismatch. */
    ESHLIBVERS(87),
    /** Malformed Macho file. */
    EBADMACHO(88),
    /** Operation canceled. */
    ECANCELED(89),
    /** Identifier removed. */
    EIDRM(90),
    /** No message of desired type. */
    ENOMSG(91),
    /** Illegal byte sequence. */
    EILSEQ(92),
    /** Attribute not found. */
    ENOATTR(93),
    /** Bad message. */
    EBADMSG(94),
    /** Reserved. */
    EMULTIHOP(95),
    /** No message available on STREAM. */
    ENODATA(96),
    /** Reserved. */
    ENOLINK(97),
    /** No STREAM resources. */
    ENOSR(98),
    /** Not a STREAM. */
    ENOSTR(99),
    /** Protocol error. */
    EPROTO(100),
    /** STREAM ioctl timeout. */
    ETIME(101),
    /** Operation not supported on socket. */
    EOPNOTSUPP(102),
    /** No such policy registered. */
    ENOPOLICY(103),
    /** State not recoverable. */
    ENOTRECOVERABLE(104),
    /** Previous owner died. */
    EOWNERDEAD(105),
    /** Interface output queue is full. */
    EQFULL(106),
    /** Must be equal largest errno. */
    ELAST(106);

    private final long n;

    private /*<name>*/NSPOSIXErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSPOSIXErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSPOSIXErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        return null;
    }
}
