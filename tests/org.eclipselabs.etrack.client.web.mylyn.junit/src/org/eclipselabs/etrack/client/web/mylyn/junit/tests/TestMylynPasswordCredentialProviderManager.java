/*******************************************************************************
 * Copyright (c) 2012 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.etrack.client.web.mylyn.junit.tests;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipselabs.etrack.client.web.mylyn.Activator;
import org.eclipselabs.etrack.client.web.mylyn.junit.support.BundleContextTestHarness;
import org.eclipselabs.etrack.client.web.mylyn.junit.support.MylynPasswordCredentialProviderManagerTestHarness;
import org.eclipselabs.etrack.client.web.mylyn.junit.support.MylynPasswordCredentialProviderTestHarness;
import org.eclipselabs.etrack.util.security.IPasswordCredentialProvider;
import org.eclipselabs.etrack.util.security.IServerConnection;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author bhunt
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMylynPasswordCredentialProviderManager
{
	private TaskRepository taskRepository;
	private BundleContext bundleContext;
	private MylynPasswordCredentialProviderManagerTestHarness mylynPasswordCredentialProviderManager;
	private MylynPasswordCredentialProviderTestHarness mylynPasswordCredentialProvider;

	@Mock
	private ServiceRegistration<IPasswordCredentialProvider> providerRegistration;
	@Mock
	private ServiceRegistration<IServerConnection> connectionRegistration;

	@BeforeClass
	public static void globalSetup()
	{
		assertThat(Activator.getBundleContext(), is(notNullValue()));
	}

	@Before
	public void setUp()
	{

		taskRepository = new TaskRepository("junit", "http://localhost/junit");
		bundleContext = new BundleContextTestHarness(providerRegistration, connectionRegistration);

		mylynPasswordCredentialProvider = spy(new MylynPasswordCredentialProviderTestHarness(taskRepository));
		mylynPasswordCredentialProviderManager = spy(new MylynPasswordCredentialProviderManagerTestHarness(bundleContext));
		doReturn(mylynPasswordCredentialProvider).when(mylynPasswordCredentialProviderManager).createMylynPasswordCredentialProvider(taskRepository);
		mylynPasswordCredentialProviderManager.repositorySettingsChanged(taskRepository);

		assertThat(mylynPasswordCredentialProviderManager.getRealBundleContext(), is(notNullValue()));
		assertThat(mylynPasswordCredentialProviderManager.createRealMylynPasswordCredentialProvider(taskRepository), is(notNullValue()));
	}

	@Test
	public void testRepositoryAdded()
	{
		// --- Test

		mylynPasswordCredentialProviderManager.repositoryAdded(taskRepository);

		// --- Verify

		verify(mylynPasswordCredentialProvider).setRegistrations(providerRegistration, connectionRegistration);
	}

	@Test
	public void testRepositoryReAdded()
	{
		// --- Setup

		mylynPasswordCredentialProviderManager.repositoryAdded(taskRepository);

		// --- Test

		mylynPasswordCredentialProviderManager.repositoryAdded(taskRepository);

		// --- Verify

		verify(mylynPasswordCredentialProvider).setRegistrations(providerRegistration, connectionRegistration);
	}

	@Test
	public void testRepositoryRemoved()
	{
		// --- Setup

		mylynPasswordCredentialProviderManager.repositoryAdded(taskRepository);

		// --- Test

		mylynPasswordCredentialProviderManager.repositoryRemoved(taskRepository);

		// --- Verify

		verify(mylynPasswordCredentialProvider).dispose();
	}

	@Test
	public void testRepositoryUrlChanged()
	{
		// --- Setup

		mylynPasswordCredentialProviderManager.repositoryAdded(taskRepository);
		String url = taskRepository.getRepositoryUrl();
		taskRepository.setRepositoryUrl("http://localhost/junit/test");

		// --- Test

		mylynPasswordCredentialProviderManager.repositoryUrlChanged(taskRepository, url);

		// --- Verify

		verify(mylynPasswordCredentialProvider).dispose();
		verify(mylynPasswordCredentialProvider, times(2)).setRegistrations(providerRegistration, connectionRegistration);
	}
}
