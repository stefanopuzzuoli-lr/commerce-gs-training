package com.liferay.commerce.segments.extension.account.internal.field.customizer;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizer;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseSegmentsFieldCustomizer
	implements SegmentsFieldCustomizer {

	protected String getSelectEntityTitle(Locale locale, String className) {
		String title = ResourceActionsUtil.getModelResource(locale, className);

		return LanguageUtil.format(locale, "select-x", title);
	}

}