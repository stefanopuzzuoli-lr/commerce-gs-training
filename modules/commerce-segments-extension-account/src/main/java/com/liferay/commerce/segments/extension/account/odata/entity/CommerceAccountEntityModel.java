package com.liferay.commerce.segments.extension.account.odata.entity;

import com.liferay.portal.kernel.search.Field;
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
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
				new IdEntityField(
					"commerceAccountId", locale -> Field.ENTRY_CLASS_PK,
					String::valueOf));
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
	
	
}

