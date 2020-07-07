package com.liferay.commerce.segments.extension.account.internal.portlet.action;

import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.price.list.web.portlet.action.CommercePriceListActionHelper;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.segments.extension.accounts.internal.display.context.SelectCommerceAccountsDisplayContext;
import com.liferay.commerce.segments.extension.accounts.internal.display.context.SelectCommerceAccountsManagementToolbarDisplayContext;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * @author Stefano Puzzuoli
 */
@Component(immediate = true, property = { "javax.portlet.name=" + SegmentsPortletKeys.SEGMENTS,
		"mvc.command.name=selectCommerceAccounts" }, service = MVCRenderCommand.class)
public class SelectCommerceAccountsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		HttpServletRequest httpServletRequest = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

		SelectCommerceAccountsDisplayContext selectCommerceAccountsDisplayContext = new SelectCommerceAccountsDisplayContext(
				_commerceAccountService, httpServletRequest, renderRequest, renderResponse);

		try {
			renderRequest.setAttribute("selectCommerceAccountsManagementToolbarDisplayContext",
					new SelectCommerceAccountsManagementToolbarDisplayContext(
							_portal.getHttpServletRequest(renderRequest),
							_portal.getLiferayPortletRequest(renderRequest),
							_portal.getLiferayPortletResponse(renderResponse), selectCommerceAccountsDisplayContext));
		} catch (Exception e) {
			e.printStackTrace();
		}

		renderRequest.setAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT, selectCommerceAccountsDisplayContext);

		return "/field/select_commerce_accounts.jsp";

	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommercePriceListAccountRelService _commercePriceListAccountRelService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private ItemSelector itemSelector;

	@Reference
	private CommercePriceListActionHelper _commercePriceListActionHelper;

	@Reference
	private Portal _portal;

}