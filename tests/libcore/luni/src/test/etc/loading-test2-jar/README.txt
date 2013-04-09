This directory contains the source code for the loading-test2 jar and
dex files, which are included as resources in the luni tests. These
files are used for testing the various class loaders.

The Android build system doesn't support dynamically producing
resources in any sane way. To update the resource, use the script
build.sh in this directory, which copies resulting files into the luni
test resources directory.
