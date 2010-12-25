#include <nullvm.h>

#define QUOTE_(x) #x
#define QUOTE(x) QUOTE_(x)

static Options options = {0};

int main(int argc, char* argv[]) {

#ifdef NULLVM_MAIN_CLASS
    options.mainClass = QUOTE(NULLVM_MAIN_CLASS);
#endif
    if (nvmInitOptions(argc, argv, &options, FALSE)) {
        fprintf(stderr, "nvmInitOptions(...) failed!\n");
        return 1;
    }
    Env* env = nvmStartup(&options);
    if (!env) {
        fprintf(stderr, "nvmStartup(...) failed!\n");
        return 2;
    }
    nvmRun(env);
    nvmShutdown(env, 0);
    return 0;
}

