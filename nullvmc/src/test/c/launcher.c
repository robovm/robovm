#include <stdio.h>
#include <nullvm.h>

int main(int argc, char* argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Wrong number of arguments\n");
        exit(1);
    }

    nvmStartup();

    Class* c = nvmGetClass(argv[1], argv[2], NULL);
    if (c == NULL) {
        fprintf(stderr, "Class %s not found\n", argv[1]);
        exit(1);
    }
    void* (*f)(Object*) = j_get_method_impl(c, "main", "([Ljava/lang/String;)V", c);
    if (f == NULL) {
        fprintf(stderr, "Method main(String[]) not found in class %s\n", argv[1]);
        exit(1);
    }
    f(NULL);
    return 0;
}

