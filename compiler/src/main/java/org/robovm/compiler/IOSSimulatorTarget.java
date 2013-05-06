/*
 * Copyright (C) 2012 Trillian AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;


/**
 * @author niklas
 *
 */
public class IOSSimulatorTarget extends AbstractIOSTarget {

    private File iosSimBinPath;
    private Family family = Family.iPhone;
    
    IOSSimulatorTarget() {
    }
 
    @Override
    public boolean canLaunchInPlace() {
        return false;
    }

    @Override
    protected List<SDK> getSDKs() {
        return listSDKs();
    }
    
    @Override
    protected void customizeInfoPList(NSDictionary dict) {
        dict.put("CFBundleSupportedPlatforms", new NSArray(new NSString("iPhoneSimulator")));
    }
    
    @Override
    protected CommandLine doGenerateCommandLine(LaunchParameters launchParameters) {
        File dir = getAppDir();
        
        String iosSimPath = new File(config.getHome().getBinDir(), "ios-sim").getAbsolutePath();
        if (iosSimBinPath != null) {
            iosSimPath = iosSimBinPath.getAbsolutePath();
        }
        
        List<Object> args = new ArrayList<Object>();
        args.add("launch");
        args.add(dir.getAbsolutePath());
        args.add("--unbuffered");
        if (sdk != null) {
            args.add("--sdk");
            args.add(sdk.getVersion());
        }
        args.add("--family");
        args.add(family.toString().toLowerCase());
        if (launchParameters.getStdoutFifo() != null) {
            args.add("--stdout");
            args.add(launchParameters.getStdoutFifo());
        }
        if (launchParameters.getStderrFifo() != null) {
            args.add("--stderr");
            args.add(launchParameters.getStderrFifo());
        }
        if (!launchParameters.getArguments().isEmpty()) {
            args.add("--args");
            args.addAll(launchParameters.getArguments());
        }
        
        return CompilerUtil.createCommandLine(iosSimPath, args);
    }
    
    public static List<SDK> listSDKs() {
        return SDK.listSDKs("iPhoneSimulator");
    }
    
    public static enum Family {iPhone, iPad}
    
    public static class Builder extends AbstractIOSTarget.Builder {
        private final IOSSimulatorTarget target;

        public Builder() {
            super(new IOSSimulatorTarget());
            this.target = (IOSSimulatorTarget) super.target;
        }
        
        public Builder iosSimBinPath(File iosSimBinPath) {
            target.iosSimBinPath = iosSimBinPath;
            return this;
        }
        
        public Builder family(Family family) {
            target.family = family;
            return this;
        }
        
        public void setup(Config.Builder configBuilder) {
            configBuilder.arch(Arch.x86);
            super.setup(configBuilder);
        }
        
    }
}
