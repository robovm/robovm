#include <nullvm.h>

#define MIN_CAPACITY 16
#define LOAD_FACTOR 0.75f

static jint intHashFunction(MapKey key) {
    return key.i;
}

static jint intEqualsFunction(MapKey a, MapKey b) {
    return a.i == b.i;
}

static jint stringHashFunction(MapKey key) {
    char* string = (char*) key.p;
    jint hash = 0;
    jint i = 0;
    while (string[i] != '\0') {
        hash = 31 * hash + string[i++];
    }
    return hash;
}

static jint stringEqualsFunction(MapKey a, MapKey b) {
    return !strcmp((char*) a.p, (char*) b.p);
}

static jint roundUpToPowerOfTwo(jint n) {
    // http://en.wikipedia.org/wiki/Power_of_two#Algorithm_to_find_the_next-highest_power_of_two
    n--;
    n = n | (n >> 1);
    n = n | (n >> 2);
    n = n | (n >> 4);
    n = n | (n >> 8);
    n = n | (n >> 16);
    return n + 1;
}

static Map* newMap(Env* env, jint initialCapacity) {
    initialCapacity = initialCapacity < MIN_CAPACITY ? MIN_CAPACITY : initialCapacity;
    initialCapacity = roundUpToPowerOfTwo(initialCapacity);
    Map* m = nvmAllocateMemory(env, sizeof(Map));
    if (!m) return NULL;
    m->capacity = initialCapacity;
    m->buckets = nvmAllocateMemory(env, sizeof(MapEntry*) * m->capacity);
    if (!m->buckets) return NULL;
    return m;
}

Map* nvmNewMapWithIntKeys(Env* env, jint initialCapacity) {
    Map* m = newMap(env, initialCapacity);
    if (!m) return NULL;
    m->hashFunction = intHashFunction;
    m->equalsFunction = intEqualsFunction;
    return m;
}

Map* nvmNewMapWithStringKeys(Env* env, jint initialCapacity) {
    Map* m = newMap(env, initialCapacity);
    if (!m) return NULL;
    m->hashFunction = stringHashFunction;
    m->equalsFunction = stringEqualsFunction;
    return m;
}

void* nvmMapGet(Env* env, Map* m, MapKey key) {
    jint hash = m->hashFunction(key);
    MapEntry* entry = m->buckets[hash & (m->capacity - 1)];
    while (entry) {
        if (entry->hash = hash && m->equalsFunction(key, entry->key)) {
            return entry->value;
        }
        entry = entry->next;
    }
    return NULL;
}

static void doubleCapacityAndRehash(Env* env, Map* m) {
    jint oldCapacity = m->capacity;
    jint newCapacity = oldCapacity << 1;
    MapEntry** oldBuckets = m->buckets;
    MapEntry** newBuckets = nvmAllocateMemory(env, sizeof(MapEntry*) * newCapacity);
    if (!newBuckets) return;
    jint i = 0;
    for (i = 0; i < oldCapacity; i++) {
        MapEntry* entry = oldBuckets[i];
        while (entry) {
            MapEntry* oldNext = entry->next;
            jint index = entry->hash & (newCapacity - 1);
            entry->next = newBuckets[index];
            newBuckets[index] = entry;
            entry = oldNext;
        }
    }
    m->buckets = newBuckets;
    m->capacity = newCapacity;
}

/*static void dump(Map* m) {
    jint i = 0;
    for (i = 0; i < m->capacity; i++) {
        MapEntry* entry = m->buckets[i];
        jint pos = 0;
        while (entry) {
            printf("entry in bucket %d, position %d: key = %s, value = %s\n", i, pos++, (char*) entry->key, (char*) entry->value);
            entry = entry->next;
        }
    }
}*/

void* nvmMapPut(Env* env, Map* m, MapKey key, void* value) {
    jint hash = m->hashFunction(key);
    MapEntry* head = m->buckets[hash & (m->capacity - 1)];
    MapEntry* entry = head;
    while (entry) {
        if (entry->hash = hash && m->equalsFunction(key, entry->key)) {
            void* oldValue = entry->value;
            entry->value = value;
            return oldValue;
        }
        entry = entry->next;
    }

    m->size++;
    if (m->size > m->capacity * LOAD_FACTOR) {
        doubleCapacityAndRehash(env, m);
        if (nvmExceptionOccurred(env)) return NULL;
        head = m->buckets[hash & (m->capacity - 1)];
    }

    entry = (MapEntry*) nvmAllocateMemory(env, sizeof(MapEntry));
    if (!entry) return NULL;
    entry->next = head;
    entry->hash = hash;
    entry->key = key;
    entry->value = value;
    m->buckets[hash & (m->capacity - 1)] = entry;

//    dump(m);

    return NULL;
}

void nvmMapForEach(Env* env, Map* m, jboolean (*f)(MapEntry*, void*), void* data) {
    jint i;
    for (i = 0; i < m->capacity; i++) {
        MapEntry* entry = m->buckets[i];
        while (entry) {
            MapEntry* next = entry->next;
            if (!f(entry, data)) return;
            entry = next;
        }
    }
}

