#include <robovm.h>
#include "reflection_helpers.h"

void Java_org_robovm_rt_bro_Bro_bind(Env* env, Class* c, Object* methodObject, jlong function) {
    BridgeMethod* method = (BridgeMethod*) getMethodFromMethodObject(env, methodObject);
    if (!method) return;
    *method->targetFnPtr = LONG_TO_PTR(function);
}

