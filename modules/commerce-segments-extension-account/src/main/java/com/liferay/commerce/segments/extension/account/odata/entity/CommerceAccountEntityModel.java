package com.liferay.commerce.segments.extension.account.odata.entity;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IdEntityField;

import java.util.Map;

/**
 * Provides the entity data model from the Indexed Entity (CommerceAccount).
 *
 * @author Stefano Puzzuoli
 * @review
 */
public class CommerceAccountEntityModel implements EntityModel  {

	public static final String NAME = "CommerceAccount";

	public CommerceAccountEntityModel() {
		_entityFieldsMap  = EntityModel.toEntityFieldsMap(
				new IdEntityField(
						"commerceAccountIds", locale -> "commerceAccountIds", String::valueOf));
				
		}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private final Map<String, EntityField> _entityFieldsMap;

	private static final Log _log = LogFactoryUtil.getLog(
			CommerceAccountEntityModel.class);
	
}

