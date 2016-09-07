<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/storekeeper.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/storekeeper/common.js"></script>
    <script type="text/javascript" src="js/storekeeper/classes/availableProductStorage.js"></script>
    <script type="text/javascript" src="js/storekeeper/transfer.js"></script>
    <title>New transfer</title>
</head>
<body>
    <div class="content">
        <s:include value="modal-hider.jsp"/>
        <s:include value="header.jsp"/>
        <s:include value="storekeeper-nav.jsp"/>
        <div class="main-column-wide">
            <div class="edit-container block">
                <div class="edit-title">
                    New transfer
                </div>
                <div class="button-line">
                    <div class="btn" onclick="toggleAcceptors()">Acceptors</div>
                    <div class="btn" onclick="toggleProducts()">Products</div>
                </div>               
                <div class="acceptor-table">
                    Acceptors for transfer:
                    <div class="acceptor-row">
                        <div class="acceptor-name">Semen semenovich</div>
                        <div class="acceptor-status">free</div>
                    </div>                  
                </div>
                <div class="product-zone">
                    <div class="table-title">Available products:</div>                    
                    <table class="product-table product-available-table">
                        <colgroup>
                            <col />
                            <col />
                            <col />
                            <col />
                        </colgroup>
                        <tr>
                            <th class="full-model-name">Full model name</th>
                            <th class="barcode">Barcode</th>
                            <th class="barcode">Available count</th>
                            <th class="button-cell">Count for move</th>
                        </tr>
                        <tr class="product-row for-copy">
                            <td class="full-model-name">Name</td>
                            <td class="barcode">1</td>
                            <td class="count">1</td>
                            <td class="button-cell">
                                <input class="table-count-edit" value="0"/>
                                <div class="move-btn">To tansfer</div>
                            </td>
                        </tr>                      
                    </table>
                    <div class="table-title">Products for transfer:</div>                   
                    <table class="product-table product-selected-table">
                        <colgroup>
                            <col />
                            <col />
                            <col />
                        </colgroup>
                        <tr>
                            <th class="full-model-name">Full model name</th>
                            <th class="barcode">Barcode</th>
                            <th class="barcode">Count</th>
                            <th class="button-cell"></th>
                        </tr>
                        <tr class="product-row for-copy">
                            <td class="full-model-name">Name</td>
                            <td class="barcode">1</td>
                            <td class="count">1</td>
                            <td class="button-cell">
                                <div class="remove-btn">Remove</div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>              
            <div class="create-btn">
                Begin transfer
            </div>        
        </div>

    </div>
    <footer>
        Phone number: +1 111 111 111<br />
        Company Name 2016 @ all rights reserved.
    </footer>
</body>
</html>