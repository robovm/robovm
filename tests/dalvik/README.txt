VM test harness.

Use "./run-all-tests" to run all tests, or "./run-test <number>" to run a
single test.  Run "./run-test" with no arguments to see command flags;
in particular, the tests can be run on the desktop, on a USB-attached
device, or using the desktop "reference implementation".


For most tests, the sources are in the "src" subdirectory.  Sources found
in the "src2" directory are compiled separately but to the same output
directory; this can be used to exercise "API mismatch" situations by
replacing class files created in the first pass.  The "src-ex" directory
is built separately, and is intended for exercising class loaders.
