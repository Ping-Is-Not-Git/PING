/*******************************************************************************
 *  Copyright (c) 2011 GitHub Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *    Kevin Sawicki (GitHub Inc.) - initial API and implementation
 *******************************************************************************/

package org.ping.test;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.junit.Before;

/**
 * WARNING: Code from this class is borrowed from the eclipse egit github testing module.
 * The reason for this is because their code is not in a maven repository, however we need their
 * code to create a live git implementation.
 * 
 * @author schristou88
 *
 */
public class LiveTest {
	protected GitHubClient client;
	
	@Before
	protected void setUp() {
		String user = System.getProperty("github.test.user");
		String password = System.getProperty("github.test.password");
		client.setCredentials(user, password);
	}
}
