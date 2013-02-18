#!/bin/sh
#
# Run the code in a classes directory on a host-local reference virtual
# machine. The jar should contain a top-level class named Main to run.
#
# Options:
#   --quiet       -- don't chatter
#   --debug       -- wait for debugger to attach
#   --no-verify   -- turn off verification (on by default)
#   --dev         -- development mode

msg() {
    if [ "$QUIET" = "n" ]; then
        echo "$@"
    fi
}

DEBUG="n"
QUIET="n"
VERIFY="y"

while true; do
    if [ "x$1" = "x--quiet" ]; then
        QUIET="y"
        shift
    elif [ "x$1" = "x--debug" ]; then
        DEBUG="y"
        shift
    elif [ "x$1" = "x--no-verify" ]; then
        VERIFY="n"
        shift
    elif [ "x$1" = "x--dev" ]; then
        # not used; ignore
        shift
    elif [ "x$1" = "x--" ]; then
        shift
        break
    elif expr "x$1" : "x--" >/dev/null 2>&1; then
        echo "unknown option: $1" 1>&2
        exit 1
    else
        break
    fi
done

if [ "$VERIFY" = "y" ]; then
    VERIFY_ARG="-Xverify:all"
    msg "Performing verification"
else
    VERIFY_ARG="-Xverify:none"
    msg "Skipping verification"
fi

if [ "$DEBUG" = "y" ]; then
    PORT=8000
    msg "Waiting for debugger to connect on localhost:$PORT"
    DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,address=$PORT,server=y,suspend=y"
fi

${JAVA} ${DEBUG_OPTS} -ea ${VERIFY_ARG} -classpath classes Main "$@"
