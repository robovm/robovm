%TrycatchContext = type {i8*, i32, i8*, i8*, i8*, i8*, i8*, i8*}
%BcTrycatchContext = type {%TrycatchContext, i8*}

define private void @checkso() alwaysinline {
  tail call void asm sideeffect "mov -0x10000(%esp), %eax", "~{eax},~{dirflag},~{fpsr},~{flags}"() nounwind
  ret void
}
