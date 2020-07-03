<%@ include file="/init.jsp" %>

<%
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
CommerceAccount commerceAccount = commerceAccountDisplayContext.getCurrentCommerceAccount();
%>

<div class="row">
	<div class="col-auto">
		<div class="commerce-account-logo-personlisation-container">
			<img alt="account logo" class="account-management__thumbnail commerce-account-logo-personlisation-img" src="<%= commerceAccountDisplayContext.getLogo(commerceAccount) %>" />
		</div>
	</div>
</div>

<aui:script>

Liferay.on('accountSelected',function(event) {
	Liferay.Portlet.refresh('#p_p_id<portlet:namespace/>')
}
);
</aui:script>