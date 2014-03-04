%TrycatchContext = type {i8*, i32, i8*, i8*, i8*, i8*, i8*, i8*, i32, i16}
%BcTrycatchContext = type {%TrycatchContext, i8*}

define private void @checkso() alwaysinline {
  tail call void asm sideeffect "mov -0x10000(%esp), %eax", "~{eax},~{dirflag},~{fpsr},~{flags},~{cc}"() nounwind
  ret void
}

define private i8* @getpc() alwaysinline {
  %1 = tail call i8* asm sideeffect "movl $$., $0", "=r,~{dirflag},~{fpsr},~{flags}"() nounwind
  ret i8* %1
}

define private float @frem(%Env* %env, float %op1, float %op2) alwaysinline {
    %result = frem float %op1, %op2
    ret float %result
}
