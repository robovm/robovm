%TrycatchContext = type {i8*, i32, i8*, i8*, i8*, i8*, i8*, i8*, i32, i16}
%BcTrycatchContext = type {%TrycatchContext, i8*}

define private void @checkso() alwaysinline {
  tail call void asm sideeffect "mov -0x10000(%esp), %eax", "~{eax},~{dirflag},~{fpsr},~{flags},~{cc}"() nounwind
  ret void
}

define linkonce_odr float @frem(%Env* %env, float %op1, float %op2) alwaysinline {
    %result = frem float %op1, %op2
    ret float %result
}
