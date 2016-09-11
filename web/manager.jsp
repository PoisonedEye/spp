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
                <div class="report-name">Transfers report</div>
                <div class="report-info">
                    <div style="overflow:hidden;">
                        <img class="report-img" src="images/5.jpeg"/>
                        <div class="report-text">
                            Report with information about all transfers.
                        </div>                        
                    </div>
                    <div class="button-line">
                        <div class="generate-btn"><a href="<s:url action="XLSReport-transfers"/>">Generate as xls</a></div>
                        <div class="generate-btn"><a href="<s:url action="CSVReport-transfers"/>">Generate as csv</a></div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div> 
                    </div>
                            
                </div>                               
            </div>
            <div class="report-block">
                <div class="report-name">Receivings report</div>
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
                        <div class="generate-btn"><a href="<s:url action="XLSReport-receivings"/>">Generate as xls</a></div>
                        <div class="generate-btn"><a href="<s:url action="CSVReport-receivings"/>">Generate as csv</a></div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div>
                    </div>

                </div>
            </div>
            <div class="report-block">
                <div class="report-name">Acceptors report</div>
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
                        <div class="generate-btn"><a href="<s:url action="XLSReport-acceptors"/>">Generate as xls</a></div>
                        <div class="generate-btn"><a href="<s:url action="CSVReport-acceptors"/>">Generate as csv</a></div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div>
                    </div>

                </div>
            </div>

            <div class="report-block">
                <div class="report-name">Acceptor shifts report</div>
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
                        <div class="generate-btn"><a href="<s:url action="XLSReport-shifts"/>">Generate as xls</a></div>
                        <div class="generate-btn"><a href="<s:url action="CSVReport-shifts"/>">Generate as csv</a></div>
                        <div onclick="Generate()" class="generate-btn">Generate as pdf</div>
                    </div>

                </div>
            </div>

            <div class="report-block">
                <div class="report-name">Cells report</div>
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
                        <div class="generate-btn"><a href="<s:url action="XLSReport-cells"/>">Generate as xls</a></div>
                        <div class="generate-btn"><a href="<s:url action="CSVReport-cells"/>">Generate as csv</a></div>
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