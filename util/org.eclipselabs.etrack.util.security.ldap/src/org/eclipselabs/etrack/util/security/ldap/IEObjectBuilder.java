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

package org.eclipselabs.etrack.util.security.ldap;

import javax.naming.directory.Attributes;

import org.eclipse.emf.ecore.EObject;

/**
 * @author bhunt
 * 
 */
public interface IEObjectBuilder
{
	EObject buildEObject(Attributes attributes, boolean isProxy);
}
