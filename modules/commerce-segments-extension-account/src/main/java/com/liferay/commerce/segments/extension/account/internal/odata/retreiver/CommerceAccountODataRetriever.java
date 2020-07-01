package com.liferay.commerce.segments.extension.account.internal.odata.retreiver;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.segments.extension.account.odata.entity.CommerceAccountEntityModel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.filter.FilterParser;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.segments.odata.retriever.ODataRetriever;
import com.liferay.segments.odata.search.ODataSearchAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefano Puzzuoli
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.commerce.account.model.CommerceAccount",
	service = ODataRetriever.class
)
public class CommerceAccountODataRetriever implements ODataRetriever<CommerceAccount> {

	@Override
	public List<CommerceAccount> getResults(
			long companyId, String filterString, Locale locale, int start,
			int end)
		throws PortalException {

		Hits hits = _oDataSearchAdapter.search(
			companyId, _getFilterParser(), filterString, CommerceAccount.class.getName(), _entityModel,
			locale, start, end);
	
		return _getCommerceAccounts(hits);
	}

	@Override
	public int getResultsCount(
			long companyId, String filterString, Locale locale)
		throws PortalException {

		return _oDataSearchAdapter.searchCount(
			companyId, _getFilterParser(), filterString, CommerceAccount.class.getName(), _entityModel,
			 locale);
	}

	private FilterParser _getFilterParser() {
		return _filterParserProvider.provide(_entityModel);
	}

	private CommerceAccount _getCommerceAccount(Document document) throws PortalException {
		long resourcePrimKey = GetterUtil.getLong(
			document.get(Field.ENTRY_CLASS_PK));
		return _commerceAccountLocalService.getCommerceAccount(resourcePrimKey);
	}

	private List<CommerceAccount> _getCommerceAccounts(Hits hits) throws PortalException {
		Document[] documents = hits.getDocs();

		List<CommerceAccount> commerceAccounts = new ArrayList<>(documents.length);
		
		for (Document document : documents) {
			commerceAccounts.add(_getCommerceAccount(document));
		}

		return commerceAccounts;
	}

	private static final EntityModel _entityModel = new CommerceAccountEntityModel();

	@Reference
	private FilterParserProvider _filterParserProvider;
	
	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private ODataSearchAdapter _oDataSearchAdapter;

}
