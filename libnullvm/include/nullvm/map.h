#ifndef NULLVM_MAP_H
#define NULLVM_MAP_H

extern Map* nvmNewMapWithIntKeys(Env* env, jint initialCapacity);
extern Map* nvmNewMapWithStringKeys(Env* env, jint initialCapacity);
extern void* nvmMapGet(Env* env, Map* m, MapKey key);
extern void* nvmMapPut(Env* env, Map* m, MapKey key, void* value);

static inline void* nvmMapGetWithIntKey(Env* env, Map* m, jint key) {
    return nvmMapGet(env, m, (MapKey) {.i = key});
}

static inline void* nvmMapGetWithStringKey(Env* env, Map* m, char* key) {
    return nvmMapGet(env, m, (MapKey) {.p = key});
}

static inline void* nvmMapPutWithIntKey(Env* env, Map* m, jint key, void* value) {
    return nvmMapPut(env, m, (MapKey) {.i = key}, value);
}

static inline void* nvmMapPutWithStringKey(Env* env, Map* m, char* key, void* value) {
    return nvmMapPut(env, m, (MapKey) {.p = key}, value);
}

#endif

