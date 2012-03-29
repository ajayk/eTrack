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

package org.eclipse.etrack.web.project.providers;

import org.eclipselabs.etrack.web.storage.providers.StorageFinder;
import org.eclipselabs.etrack.web.storage.providers.StorageResourceProvider;
import org.eclipselabs.etrack.web.storage.resources.StorageResource;
import org.restlet.Context;
import org.restlet.resource.Finder;

/**
 * @author bhunt
 * 
 */
public class ProjectResourceProvider extends StorageResourceProvider
{
	@Override
	protected Finder createFinder(Context context)
	{
		return new StorageFinder(context, StorageResource.class, this);
	}
}
