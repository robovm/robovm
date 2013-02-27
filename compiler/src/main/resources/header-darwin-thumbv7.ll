%TrycatchContext = type {i8*, i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, double, double, double, double, double, double, double, double}
%BcTrycatchContext = type {%TrycatchContext, i8*}

define private void @checkso() alwaysinline {
  tail call void asm sideeffect "mvns r4, #0; lsls r4, r4, #16; ldr r4, [sp, r4]", "~{r4},~{dirflag},~{fpsr},~{flags},~{cc}"() nounwind
  ret void
}
