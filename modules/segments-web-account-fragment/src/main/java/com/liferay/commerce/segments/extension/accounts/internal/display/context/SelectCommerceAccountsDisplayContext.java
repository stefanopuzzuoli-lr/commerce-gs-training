package com.liferay.commerce.segments.extension.accounts.internal.display.context;


import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.item.test.selector.web.internal.display.context.util.CommerceAccountItemSelectorRequestHelper;
import com.liferay.commerce.account.item.test.selector.web.internal.search.CommerceAccountItemSelectorChecker;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
public class SelectCommerceAccountsDisplayContext {

	public SelectCommerceAccountsDisplayContext(CommerceAccountService commerceAccountService,
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {
		
		_commerceAccountService = commerceAccountService;
		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_commerceAccountItemSelectorRequestHelper = new CommerceAccountItemSelectorRequestHelper(_httpServletRequest);
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(
			_httpServletRequest, "displayStyle", "list");

		return _displayStyle;
	}

	public String getEventName() {
		if (Validator.isNotNull(_eventName)) {
			return _eventName;
		}

		_eventName = ParamUtil.getString(
			_httpServletRequest, "eventName",
			_renderResponse.getNamespace() + "selectCommerceAccounts");

		return _eventName;
	}

	public long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_groupId = ParamUtil.getLong(
			_httpServletRequest, "groupId",
			themeDisplay.getSiteGroupIdOrLiveGroupId());

		return _groupId;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_renderRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_renderRequest, "orderByCol", "name");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_renderRequest, "orderByType", "asc");

		return _orderByType;
	}

	
	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/select_commerce_accounts.jsp");
		portletURL.setParameter("groupId", String.valueOf(getGroupId()));
		portletURL.setParameter("eventName", getEventName());

		String displayStyle = getDisplayStyle();

		if (Validator.isNotNull(displayStyle)) {
			portletURL.setParameter("displayStyle", displayStyle);
		}

		String keywords = getKeywords();

		if (Validator.isNotNull(keywords)) {
			portletURL.setParameter("keywords", keywords);
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			portletURL.setParameter("orderByType", orderByType);
		}

		return portletURL;
	}
	
	public SearchContainer<CommerceAccount> getSearchContainer()
			throws PortalException {

			if (_searchContainer != null) {
				return _searchContainer;
			}

			_searchContainer = new SearchContainer<>(
				_commerceAccountItemSelectorRequestHelper.
					getLiferayPortletRequest(),
				getPortletURL(), null, null);

			_searchContainer.setEmptyResultsMessage("there-are-no-accounts");

			_searchContainer.setOrderByCol(getOrderByCol());
			_searchContainer.setOrderByType(getOrderByType());

			RowChecker rowChecker = new CommerceAccountItemSelectorChecker(
				_commerceAccountItemSelectorRequestHelper.getRenderResponse(),
				getCheckedCommerceAccountIds());

			_searchContainer.setRowChecker(rowChecker);

			List<CommerceAccount> results =
				_commerceAccountService.getUserCommerceAccounts(
					_commerceAccountItemSelectorRequestHelper.getUserId(),
					CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
					CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords(),
					_searchContainer.getStart(), _searchContainer.getEnd());

			_searchContainer.setResults(results);

			int total = _commerceAccountService.getUserCommerceAccountsCount(
				_commerceAccountItemSelectorRequestHelper.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				CommerceAccountConstants.SITE_TYPE_B2C_B2B, getKeywords());

			_searchContainer.setTotal(total);

			return _searchContainer;
		}
	
	protected long[] getCheckedCommerceAccountIds() {
		return ParamUtil.getLongValues(
			_commerceAccountItemSelectorRequestHelper.getRenderRequest(),
			"checkedCommerceAccountIds");
	}

	private String _displayStyle;
	private String _eventName;
	private Long _groupId;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CommerceAccount> _searchContainer;
	private CommerceAccountItemSelectorRequestHelper
	_commerceAccountItemSelectorRequestHelper;
	
	@Reference
	private final CommerceAccountService _commerceAccountService;



	

}