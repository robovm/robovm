/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.security.provider;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PolicySpi;
import java.security.ProtectionDomain;
import java.security.URIParameter;

import org.apache.harmony.security.fortress.DefaultPolicy;

public class PolicySpiImpl extends PolicySpi {
	private DefaultPolicy defaultPolicy = null;
	
	private URIParameter para = null;

	public PolicySpiImpl() {
		defaultPolicy = new DefaultPolicy(false);
		engineRefreshImpl();
	}
	
	public PolicySpiImpl(URIParameter params) {
		defaultPolicy = new DefaultPolicy(false);
		para = params;
		engineRefreshImpl();
	}

	@Override
	protected boolean engineImplies(ProtectionDomain domain,
			Permission permission) {
		return defaultPolicy.implies(domain, permission);
	}

	@Override
	protected PermissionCollection engineGetPermissions(CodeSource codesource) {
		return defaultPolicy.getPermissions(codesource);
	}

	@Override
	public PermissionCollection engineGetPermissions(ProtectionDomain pd) {
		return defaultPolicy.getPermissions(pd);
	}
	
	
	@Override
	public void engineRefresh() {
		engineRefreshImpl();
	}

	private synchronized void engineRefreshImpl() {
		defaultPolicy.refresh(para);
	}
}
