package com.liferay.commerce.account.logo.personalisation.portlet;

import com.liferay.commerce.account.logo.personalisation.constants.CommerceAccountLogoPersonalisationPortletKeys;
import com.liferay.commerce.account.logo.personalisation.internal.display.context.CommerceAccountDisplayContext;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author stefa
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=commerce",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CommerceAccountLogoPersonalisation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceAccountLogoPersonalisationPortletKeys.COMMERCEACCOUNTLOGOPERSONALISATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CommerceAccountLogoPersonalisationPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		CommerceAccountDisplayContext commerceAccountDisplayContext = new CommerceAccountDisplayContext(
				_commerceAccountService, _portal.getHttpServletRequest(renderRequest));

		renderRequest.setAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT, commerceAccountDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private Portal _portal;
}