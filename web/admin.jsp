<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/admin.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/admin/dropdown.js"></script>
    <title>Admin</title>
</head>
<body>
<div class="content">
	<s:include value="header.jsp"/>
    <div class="main-column-full">
    	<div class="button-block">
			<div class="dropdown">
			  <button class="drop-btn">Tables</button>
			  <div class="dropdown-content">
			    <div data-table="AcceptorShifts">Acceptor shift</div>
			    <div data-table="Addresses">Address</div>
			    <div data-table="Cells">Cell</div>
				<div data-table="CellTypes">Cell type</div>
				<div data-table="CellVisitings">Cell visiting</div>
				<div data-table="Companies">Company</div>
				<div data-table="Employees">Employee</div>
				<div data-table="Positions">Position</div>
				<div data-table="Products">Product</div>
				<div data-table="ProductTypes">Product type</div>
				<div data-table="Recievings">Receiving</div>
				<div data-table="Transfers">Transfer</div>
				<div data-table="UnitSets">Unit set</div>
			  </div>
			</div>
			<button class="action-btn">Save</button>
			<button class="action-btn">Cancel</button>
			<button class="action-btn">Add</button>	
		</div>
		<table class="admin-table">
		</table>
    </div>	
</div>
<s:include value="footer.jsp"/>
</body>
</html>