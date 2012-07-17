/*******************************************************************************
 * Copyright (c) 2012 NVIDIA CORPORATION.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    NVIDIA - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.etrack.server.web.entity.ldap.providers;

import org.eclipselabs.etrack.server.web.entity.ldap.resources.EntityResource;
import org.eclipselabs.etrack.server.web.storage.AbstractStorageResourceProvider;
import org.restlet.Context;
import org.restlet.resource.Finder;

/**
 * @author bhunt
 * 
 */
public class EntityResourceProvider extends AbstractStorageResourceProvider
{
	@Override
	protected Finder createFinder(Context context)
	{
		return new Finder(context, EntityResource.class);
	}
}
