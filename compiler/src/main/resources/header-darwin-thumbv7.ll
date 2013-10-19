%TrycatchContext = type {i8*, i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, double, double, double, double, double, double, double, double}
%BcTrycatchContext = type {%TrycatchContext, i8*}

define private void @checkso() alwaysinline {
  tail call void asm sideeffect "mvns r4, #0; lsls r4, r4, #16; ldr r4, [sp, r4]", "~{r4},~{dirflag},~{fpsr},~{flags},~{cc}"() nounwind
  ret void
}

define private i8* @getpc() alwaysinline {
  %1 = tail call i8* asm sideeffect "mov $0, pc", "=r,~{dirflag},~{fpsr},~{flags}"() nounwind
  ret i8* %1
}

declare double @fmod(double, double)

define private float @frem(%Env* %env, float %op1, float %op2) alwaysinline {
    ; frem compiles down to fmod() for doubles and fmodf() for floats on iOS ARM.
    ; fmodf() returns incorrect results for values close to zero. Converting the
    ; operands to doubles and using fmod() returns the expected result. See
    ; https://github.com/robovm/robovm/issues/89
    %dop1 = fpext float %op1 to double
    %dop2 = fpext float %op2 to double
    %dresult = call double @fmod(double %dop1, double %dop2)
    %result = fptrunc double %dresult to float
    ret float %result
}
