<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/acceptor.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
</head>
<body>
<div class="content">
    <s:include value="modal-hider.jsp"/>
    <s:include value="header.jsp"/>
    <s:include value="acceptor-nav.jsp"/>
    <div class="main-column">
        <div class="block">
            <table class="table">
                <thead>
                    <tr>
                       <th>Product type</th>
                       <th>Count</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Type 1</td>
                        <td>100</td>                        
                    </tr>
                    <tr>
                        <td>Type 1</td>
                        <td>100</td>
                    </tr>
                </tbody>            
            </table>
        </div>
    </div>
</div>
<s:include value="footer.jsp"/>
</body>
</html>