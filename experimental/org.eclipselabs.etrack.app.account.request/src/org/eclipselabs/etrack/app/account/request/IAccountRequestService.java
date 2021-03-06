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

package org.eclipselabs.etrack.app.account.request;

import org.eclipselabs.etrack.domain.account.AccountRequest;

/**
 * @author bhunt
 * 
 */
public interface IAccountRequestService
{
	void requestAccount(AccountRequest accountRequest);
}
