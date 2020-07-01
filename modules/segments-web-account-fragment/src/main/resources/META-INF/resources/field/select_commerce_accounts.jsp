<%@ include file="../init.jsp" %>
<%@ include file="../init-ext.jsp" %>

<%
SelectCommerceAccountsDisplayContext selectCommerceAccountsDisplayContext = (SelectCommerceAccountsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	displayContext="<%= (SelectCommerceAccountsManagementToolbarDisplayContext)request.getAttribute("selectCommerceAccountsManagementToolbarDisplayContext") %>"
/>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="selectSegmentsEntryCommerceAccounts"
		searchContainer="<%= selectCommerceAccountsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.account.model.CommerceAccount"
			escapedModel="<%= true %>"
			keyProperty="commerceAccountId"
			modelVar="commerceAccount"
		>

			<%
			Map<String, Object> data = HashMapBuilder.<String, Object>put(
				"id", commerceAccount.getCommerceAccountId()
			).put(
				"name", commerceAccount.getName()
			).build();
			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content table-title"
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectCommerceAccountsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>


<liferay-util:include page="/field/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="displayStyle" value="<%= selectCommerceAccountsDisplayContext.getDisplayStyle() %>" />
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryCommerceAccounts" />
	<liferay-util:param name="selectEventName" value="<%= selectCommerceAccountsDisplayContext.getEventName() %>" />
</liferay-util:include>
