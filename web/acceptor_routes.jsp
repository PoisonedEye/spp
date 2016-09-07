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
            <div class="input-block">
                <input placeholder="Product type" />
                <input placeholder="Unical Id" />
                <div class="accept-button">Accept</div>
            </div>
        </div>     
        <div class="block">
            <table class="table">
                <thead>
                    <tr>
                        <th>Product type</th>
                        <th>Unical Id</th>
                        <th>Target Cell</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Type 1</td>
                        <td>1234</td>
                        <td>1-A-1</td>
                    </tr>
                    <tr>
                        <td>Type 2</td>
                        <td>12345</td>
                        <td>1-A-1</td>
                    </tr>
                    <tr>
                        <td>Type 1</td>
                        <td>123456</td>
                        <td>1-A-1</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="block">
            <div class="input-block">
                <input placeholder="Product type" />
                <input placeholder="Unical Id" />
                <input placeholder="Cell number" />
                <div class="accept-button">Accept</div>
            </div>
        </div>
    </div>
</div>
<s:include value="footer.jsp"/>
</body>
</html>