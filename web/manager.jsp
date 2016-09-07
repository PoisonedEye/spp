<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/manager.css" />
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
</head>
<body>
<div class="content">
    <s:include value="modal-hider.jsp"/>
    <s:include value="header.jsp"/>
	<div class="nav-column">
        <nav>
            <ul>
                <li><a href="#">Navigation 1</a></li>
                <li><a href="#">Navigation 2</a></li>
                <li><a href="#">Navigation 3</a></li>
                <li><a href="#">Navigation 4</a></li>
            </ul>
        </nav>
    </div>
	<div class="main-column">
        <div class="block">
            <div class="report-block">
                <div class="report-name">Report 1 name</div>             
                <div class="report-info">
                    <div style="overflow:hidden;">
                        <img class="report-img" src="images/5.jpeg"/>
                        <div class="report-text">
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                        </div>                        
                    </div>
                    <div class="button-line">
                        <div onclick="Generate()" class="generate-btn">Generate as xls</div>
                        <div onclick="Generate()" class="generate-btn">Generate as csv</div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div> 
                    </div>
                            
                </div>                               
            </div>
            <div class="report-block">
                <div class="report-name">Report 2 name</div>
                <div class="report-info">
                    <div style="overflow:hidden;">
                        <img class="report-img" src="images/5.jpeg" />
                        <div class="report-text">
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                        </div>
                    </div>
                    <div class="button-line">
                        <div onclick="Generate()" class="generate-btn">Generate as xls</div>
                        <div onclick="Generate()" class="generate-btn">Generate as csv</div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div>
                    </div>

                </div>
            </div>
            <div class="report-block">
                <div class="report-name">Report 3 name</div>
                <div class="report-info">
                    <div style="overflow:hidden;">
                        <img class="report-img" src="images/5.jpeg" />
                        <div class="report-text">
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                            text text text text text text text text text text text text
                        </div>
                    </div>
                    <div class="button-line">
                        <div onclick="Generate()" class="generate-btn">Generate as xls</div>
                        <div onclick="Generate()" class="generate-btn">Generate as csv</div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div>
                    </div>

                </div>
            </div>
        </div>
	</div>
</div>
<s:include value="footer.jsp"/>
</body>
</html>