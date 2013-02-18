/*
 * Copyright 2006 The Android Open Source Project
 *
 * Generate a big pile of interface classes.
 */
#include <stdio.h>

/*
 * Create N interface files.
 */
static int createFiles(int count)
{
    FILE* fp;
    int i;

    for (i = 0; i < count; i++) {
        char nameBuf[32];

        sprintf(nameBuf, "src/Interface%03d.java", i);
        fp = fopen(nameBuf, "w");
        if (fp == NULL) {
            fprintf(stderr, "ERROR: unable to open %s\n", nameBuf);
            return -1;
        }

        fprintf(fp, "interface Interface%03d {\n", i);
        if ((i & 0x01) != 0)
            fprintf(fp, "    int func%03d();\n", i);
        fprintf(fp, "}\n");
        fclose(fp);
    }

    fp = fopen("func-decl", "w");
    fprintf(fp, "    implements\n");
    for (i = 0; i < count; i++) {
        fprintf(fp, "        Interface%03d%s\n", i, (i == count-1) ? "" : ",");
    }
    fprintf(fp, "\n");
    for (i = 1; i < count; i += 2) {
        fprintf(fp, "    public int func%03d() { return %d; }\n", i, i);
    }
    fclose(fp);

    return 0;
}

int main()
{
    int result;

    result = createFiles(100);

    return (result != 0);
}
