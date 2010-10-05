#ifndef NULLVM_MAP_H
#define NULLVM_MAP_H

extern Map* nvmNewMapWithIntKeys(Env* env, jint initialCapacity);
extern Map* nvmNewMapWithStringKeys(Env* env, jint initialCapacity);
extern void* nvmMapGet(Env* env, Map* m, MapKey key);
extern void* nvmMapPut(Env* env, Map* m, MapKey key, void* value);
extern void nvmMapForEach(Env* env, Map* m, jboolean (*f)(MapEntry*, void*), void* data);

static inline void* nvmMapGetWithIntKey(Env* env, Map* m, jint key) {
    MapKey k;
    k.i = key;
    return nvmMapGet(env, m, k);
}

static inline void* nvmMapGetWithStringKey(Env* env, Map* m, char* key) {
    MapKey k;
    k.p = key;
    return nvmMapGet(env, m, k);
}

static inline void* nvmMapPutWithIntKey(Env* env, Map* m, jint key, void* value) {
    MapKey k;
    k.i = key;
    return nvmMapPut(env, m, k, value);
}

static inline void* nvmMapPutWithStringKey(Env* env, Map* m, char* key, void* value) {
    MapKey k;
    k.p = key;
    return nvmMapPut(env, m, k, value);
}

#endif

