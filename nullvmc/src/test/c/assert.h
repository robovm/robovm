#include <nullvm.h>

extern int total_tests_tests;
extern int failed_tests;

extern void assertEqualsLong(char* test, jlong expected, jlong actual);
extern void assertEqualsInt(char* test, jint expected, jint actual);
extern void assertEqualsFloat(char* test, jfloat expected, jfloat actual);
extern void assertEqualsDouble(char* test, jdouble expected, jdouble actual);
extern void assertEqualsObject(char* test, jobject* expected, jobject* actual);
extern void assertNotNull(char* test, jobject* o);
extern void assertNull(char* test, jobject* o);
extern void print_test_result(void);

