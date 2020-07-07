package com.liferay.commerce.account.item.test.selector.web.internal.search;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.util.SetUtil;

import java.util.Set;

import javax.portlet.RenderResponse;

/**
 * @author Stefano Puzzuoli
 */
public class CommerceAccountItemSelectorChecker extends EmptyOnClickRowChecker {

	public CommerceAccountItemSelectorChecker(
		RenderResponse renderResponse, long[] checkedCommerceAccountIds) {

		super(renderResponse);

		_checkedCommerceAccountIds = SetUtil.fromArray(
			checkedCommerceAccountIds);
	}

	@Override
	public boolean isChecked(Object obj) {
		CommerceAccount commerceAccount = (CommerceAccount)obj;

		return _checkedCommerceAccountIds.contains(
			commerceAccount.getCommerceAccountId());
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private final Set<Long> _checkedCommerceAccountIds;

}