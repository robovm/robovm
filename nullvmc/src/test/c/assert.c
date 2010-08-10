#include <stdio.h>
#include <inttypes.h>
#include "assert.h"

int total_tests = 0;
int failed_tests = 0;

typedef union {
  jlong bits;
  jdouble d;
} Double;

typedef union {
  jint bits;
  jfloat f;
} Float;

static jlong doubleToLongBits(jdouble val)
{
  Double d;
  d.d = val;
  return isnan(d.d) ? 0x7ff8000000000000 : d.bits;
}

static jint floatToIntBits(jfloat val)
{
  Float f;
  f.f = val;
  return isnan(f.f) ? 0x7fc00000 : f.bits;
}

void assertEqualsLong(char* test, jlong expected, jlong actual)
{
  total_tests++;
  if (expected != actual) {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: %" PRId64 " != %" PRId64 "\n", total_tests, test, expected, actual);
  } else {
    printf("%d: Test '%s' succeeded: %" PRId64 " == %" PRId64 "\n", total_tests, test, expected, actual);
  }
}

void assertEqualsInt(char* test, jint expected, jint actual)
{
  total_tests++;
  if (expected != actual) {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: %d != %d\n", total_tests, test, expected, actual);
  } else {
    printf("%d: Test '%s' succeeded: %d == %d\n", total_tests, test, expected, actual);
  }
}

void assertEqualsFloat(char* test, jfloat expected, jfloat actual)
{
  assertEqualsInt(test, floatToIntBits(expected), floatToIntBits(actual));
/*  total_tests++;
  if (expected == actual || isnan(expected) && isnan(actual)) {
    printf("%d: Test '%s' succeeded: %a == %a\n", total_tests, test, expected, actual);
  } else {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: %a != %a\n", total_tests, test, expected, actual);
  }*/
}

void assertEqualsDouble(char* test, jdouble expected, jdouble actual)
{
  assertEqualsLong(test, doubleToLongBits(expected), doubleToLongBits(actual));
/*  total_tests++;
  if (expected == actual || isnan(expected) && isnan(actual)) {
    printf("%d: Test '%s' succeeded: %a == %a\n", total_tests, test, expected, actual);
  } else {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: %a != %a\n", total_tests, test, expected, actual);
  }*/
}

void assertSameObject(char* test, Object* expected, Object* actual) {
  total_tests++;
  if (expected != actual) {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: o1 != o2\n", total_tests, test);
  } else {
    printf("%d: Test '%s' succeeded: o1 == o2\n", total_tests, test);
  }
}

void assertNotNull(char* test, Object* o) {
  total_tests++;
  if (o == NULL) {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: Object == null\n", total_tests, test);
  } else {
    printf("%d: Test '%s' succeeded: Object != null\n", total_tests, test);
  }
}

void assertNull(char* test, Object* o) {
  total_tests++;
  if (o != NULL) {
    failed_tests++;
    printf("%d: ****** Test '%s' failed: Object != null\n", total_tests, test);
  } else {
    printf("%d: Test '%s' succeeded: Object == null\n", total_tests, test);
  }
}

void print_test_result(void)
{
    printf("\nTest result:\n");
    printf("%d tests in total\n", total_tests);
    printf("%d tests failed\n", failed_tests);
    if (failed_tests == 0) {
        printf("All tests were successful!\n");
    }
}

