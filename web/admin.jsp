<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/admin.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/admin/dropdown.js"></script>
	<script type="text/javascript" src="js/helpers/sha.js"></script>
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
			    <div data-table="AcceptorShift">Acceptor shift</div>
			    <div data-table="Address">Address</div>
			    <div data-table="Cell">Cell</div>
				<div data-table="CellType">Cell type</div>
				<div data-table="CellVisiting">Cell visiting</div>
				<div data-table="Company">Company</div>
				<div data-table="Employee">Employee</div>
				<div data-table="Position">Position</div>
				<div data-table="Product">Product</div>
				<div data-table="ProductType">Product type</div>
				<div data-table="Receiving">Receiving</div>
				<div data-table="Transfer">Transfer</div>
				<div data-table="UnitSet">Unit set</div>
			  </div>
			</div>
			<button class="action-btn" id="save-btn">Save</button>
			<button class="action-btn" id="cancel-btn">Cancel</button>
			<button class="action-btn" id="add-btn">Add</button>
		</div>
		<table class="admin-table" id="admin-table">
		</table>
    </div>	
</div>
<s:include value="footer.jsp"/>
<s:include value="modal-message.jsp"/>
<s:include value="modal-dialog.jsp"/>
</body>
</html>