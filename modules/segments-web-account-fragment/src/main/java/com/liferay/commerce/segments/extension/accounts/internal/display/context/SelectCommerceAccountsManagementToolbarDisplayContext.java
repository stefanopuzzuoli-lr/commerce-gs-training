package com.liferay.commerce.segments.extension.accounts.internal.display.context;


import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class SelectCommerceAccountsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public SelectCommerceAccountsManagementToolbarDisplayContext(
			HttpServletRequest httpServletRequest,
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			SelectCommerceAccountsDisplayContext selectCommerceAccountsDisplayContext)
		throws Exception {

		super(
				 liferayPortletRequest,
				 liferayPortletResponse,
				 httpServletRequest,  selectCommerceAccountsDisplayContext.getSearchContainer());
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	@Override
	public String getComponentId() {
		return "commerceAccountsManagementToolbar";
	}
	
	public String getDefaultEventHandler() {
		return "commerceAccountsManagementToolbarDefaultEventHandler";
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "commerceAccounts";
	}

	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive", "icon"};
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"name"};
	}

}