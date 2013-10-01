package org.robovm.weaver;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.*;

@Mojo(name="process-classes", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class RoboVMWeaver extends AbstractMojo {
    @Parameter(property = "project")
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        final Log log = getLog();
        log.info("Starting RoboVM weaving process...");
        File targetDir = new File(project.getBuild().getOutputDirectory());
        final File[] files = targetDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });
        for (File f : files) {
            log.info("Processing " + f.getName());
            try {
                processClass(f, getLog());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        log.info("RoboVM weaving process finished");
    }

    public void processClass(File f, Log log) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        RoboVMVisitor visitor = new RoboVMVisitor(cw, log);
        final FileInputStream in = new FileInputStream(f);
        ClassReader cr = new ClassReader(new BufferedInputStream(in));
        cr.accept(visitor, 0);
        in.close();
        final FileOutputStream fos = new FileOutputStream(f);
        fos.write(cw.toByteArray());
        fos.flush();
        fos.close();
    }
}
