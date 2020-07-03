package com.liferay.commerce.account.logo.personalisation.internal.display.context;

import com.liferay.commerce.account.logo.personalisation.internal.display.context.util.CommerceAccountRequestHelper;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import javax.servlet.http.HttpServletRequest;

public class CommerceAccountDisplayContext {
	
	public CommerceAccountDisplayContext(
			CommerceAccountService commerceAccountService,
			HttpServletRequest httpServletRequest) {

			_commerceAccountService = commerceAccountService;

			_commerceAccountRequestHelper = new CommerceAccountRequestHelper(
				httpServletRequest);
			
			_commerceContext = (CommerceContext)httpServletRequest.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

		}

	
	
	public CommerceAccount getCurrentCommerceAccount() throws PortalException {
		long commerceAccountId = ParamUtil.getLong(
			_commerceAccountRequestHelper.getRequest(), "commerceAccountId");

		if (commerceAccountId > 0) {
			return _commerceAccountService.getCommerceAccount(
				commerceAccountId);
		}

		return getCurrentAccount();
	}
	
	protected CommerceAccount getCurrentAccount() throws PortalException {
		return _commerceContext.getCommerceAccount();
	}
	
	public String getLogo(CommerceAccount commerceAccount) {
		ThemeDisplay themeDisplay =
			_commerceAccountRequestHelper.getThemeDisplay();

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPathImage());
		sb.append("/organization_logo?img_id=");
		sb.append(commerceAccount.getLogoId());
		sb.append("&t=");
		sb.append(
			WebServerServletTokenUtil.getToken(commerceAccount.getLogoId()));

		return sb.toString();
	}
	
	private final CommerceAccountRequestHelper _commerceAccountRequestHelper;
	private final CommerceAccountService _commerceAccountService;
	private final CommerceContext _commerceContext;

}
