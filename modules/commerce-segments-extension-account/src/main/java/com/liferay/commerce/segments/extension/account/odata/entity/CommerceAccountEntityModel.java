package com.liferay.commerce.segments.extension.account.odata.entity;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides the entity data model from the Indexed Entity (CommerceAccount).
 *
 * @author Stefano Puzzuoli
 * @review
 */
public class CommerceAccountEntityModel implements EntityModel  {

	public static final String NAME = "CommerceAccount";

	public CommerceAccountEntityModel() {
		_entityFieldsMap = Stream.of(
			new StringEntityField("name", locale -> "name")
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);
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

