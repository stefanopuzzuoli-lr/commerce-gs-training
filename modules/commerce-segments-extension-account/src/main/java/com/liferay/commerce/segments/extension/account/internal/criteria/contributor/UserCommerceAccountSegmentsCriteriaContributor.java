package com.liferay.commerce.segments.extension.account.internal.criteria.contributor;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.segments.extension.account.internal.field.customizer.CommerceAccountSegmentsFieldCustomizer;
import com.liferay.commerce.segments.extension.account.odata.entity.CommerceAccountEntityModel;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.field.Field;
import com.liferay.segments.odata.retriever.ODataRetriever;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefano Puzzuoli
 */
@Component(immediate = true, property = {
		"segments.criteria.contributor.key=" + UserCommerceAccountSegmentsCriteriaContributor.KEY,
		"segments.criteria.contributor.model.class.name=com.liferay.portal.kernel.model.User",
		"segments.criteria.contributor.priority:Integer=80" }, service = SegmentsCriteriaContributor.class)
public class UserCommerceAccountSegmentsCriteriaContributor implements SegmentsCriteriaContributor {

	public static final String KEY = "user-commerce-account";

	@Override
	public EntityModel getEntityModel() {
		return _entityModel;
	}

	@Override
	public String getEntityName() {
		return CommerceAccountEntityModel.NAME;
	}

	@Override
	public List<Field> getFields(PortletRequest portletRequest)

	{
		return Collections
				.singletonList(new Field("commerceAccountIds", LanguageUtil.get(_portal.getLocale(portletRequest), "Commerce Account"),
						"id", Collections.emptyList(), commerceAccountSegmentsFieldCustomizer.getSelectEntity(portletRequest)));
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public Criteria.Type getType() {
		return Criteria.Type.MODEL;
	}

	private static final EntityModel _entityModel = new CommerceAccountEntityModel();

	@Reference(target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)")
	private ODataRetriever<CommerceAccount> _oDataRetriever;

	@Reference
	private Portal _portal;
	
	private final CommerceAccountSegmentsFieldCustomizer  commerceAccountSegmentsFieldCustomizer = new CommerceAccountSegmentsFieldCustomizer();

}
