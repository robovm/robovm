#include <nullvm.h>

#define QUOTE_(x) #x
#define QUOTE(x) QUOTE_(x)

int main(int argc, char* argv[]) {
    Options options;

#ifdef NULLVM_MAIN_CLASS
    options.mainClass = QUOTE(NULLVM_MAIN_CLASS);
#endif
    nvmParseArgs(argc, argv, &options);
    Env* env = nvmStartup(&options);
    nvmRun(env, &options);
    nvmShutdown();

    return 0;
}

