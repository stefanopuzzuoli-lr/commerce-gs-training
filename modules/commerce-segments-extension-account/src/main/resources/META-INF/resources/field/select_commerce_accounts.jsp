<%@ include file="../init.jsp" %>

<%
SelectCommerceAccountsDisplayContext SelectCommerceAccountsDisplayContext = (SelectCommerceAccountsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	clearResultsURL="<%= SelectCommerceAccountsDisplayContext.getClearResultsURL() %>"
	componentId="selectSegmentsEntryCommerceAccountsManagementToolbar"
	disabled="<%= SelectCommerceAccountsDisplayContext.isDisabledManagementBar() %>"
	filterDropdownItems="<%= SelectCommerceAccountsDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= SelectCommerceAccountsDisplayContext.getTotalItems() %>"
	searchActionURL="<%= SelectCommerceAccountsDisplayContext.getSearchActionURL() %>"
	searchContainerId="selectSegmentsEntryCommerceAccounts"
	searchFormName="searchFm"
	showSearch="<%= SelectCommerceAccountsDisplayContext.isShowSearch() %>"
	sortingOrder="<%= SelectCommerceAccountsDisplayContext.getOrderByType() %>"
	sortingURL="<%= SelectCommerceAccountsDisplayContext.getSortingURL() %>"
	viewTypeItems="<%= SelectCommerceAccountsDisplayContext.getViewTypeItems() %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="selectSegmentsEntryCommerceAccounts"
		searchContainer="<%= SelectCommerceAccountsDisplayContext.getCommerceAccountSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.account.model.CommerceAccount"
			escapedModel="<%= true %>"
			keyProperty="commerceAccountId"
			modelVar="commerceAccount"
		>

			<%
			Map<String, Object> data = new HashMap<>();
			data.put("id", commerceAccount.getCommerceAccountId());
			data.put("name", commerceAccount.getName());
			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="active-commerceAccount"
				value="<%= HtmlUtil.escape(commerceAccount.isActive()) %>"
			/>


		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= SelectCommerceAccountsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-util:include page="/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryCommerceAccounts" />
	<liferay-util:param name="selectEventName" value="<%= SelectCommerceAccountsDisplayContext.getEventName() %>" />
</liferay-util:include>
