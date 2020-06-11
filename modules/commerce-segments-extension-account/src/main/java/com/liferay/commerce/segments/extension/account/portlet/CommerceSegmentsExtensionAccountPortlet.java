package com.liferay.commerce.segments.extension.account.portlet;

import com.liferay.commerce.segments.extension.account.constants.CommerceSegmentsExtensionAccountPortletKeys;
import com.liferay.commerce.segments.extension.account.internal.field.customizer.CommerceAccountSegmentsFieldCustomizer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author stefa
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CommerceSegmentsExtensionAccount",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CommerceSegmentsExtensionAccountPortletKeys.COMMERCESEGMENTSEXTENSIONACCOUNT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CommerceSegmentsExtensionAccountPortlet extends MVCPortlet {
}