package com.liferay.commerce.segments.extension.account.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;


@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.hidden",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=CommerceSegmentsExtensionTest",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=com_liferay_commerce_segments_extension_account_CommerceSegmentsExtensionAccountPortlet",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)

public class CommerceSegmentsExtensionAccountPortlet extends MVCPortlet {

}
