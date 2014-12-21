#!/bin/bash

rm -rf llvm-3.5.0.src.tar*
curl -O http://llvm.org/releases/3.5.0/llvm-3.5.0.src.tar.xz
unxz llvm-3.5.0.src.tar.xz
tar xvf llvm-3.5.0.src.tar -C extllvm --strip-components 1
