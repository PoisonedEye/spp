<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <link rel="stylesheet" type="text/css" href="styles/storekeeper.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/storekeeper/common.js"></script>
    <script type="text/javascript" src="js/storekeeper/classes/productStorage.js"></script>
    <script type="text/javascript" src="js/storekeeper/receiving.js"></script>
    <title>New receiving</title>
</head>
<body>
    <div class="content">
        <s:include value="header.jsp"/>
        <s:include value="storekeeper-nav.jsp"/>
        <div class="main-column-wide">
            <div class="edit-container block">
                <div class="edit-title">
                    New receiving
                </div>
                <div class="button-line">
                    <div class="btn" onclick="toggleAcceptors()">Acceptors</div>
                    <div class="btn" onclick="toggleProducts()">Products</div>
                </div>
                <div class="acceptor-table">
                    <div class="acceptor-row">
                        <div class="acceptor-name">Semen semenovich</div>
                        <div class="acceptor-status">free</div>
                    </div>                  
                </div>
                <div class="product-zone">
                    <form class="product-edit">
                        <fieldset>
                            <label for="full-model-name-edit">Full model name</label>
                            <input id="full-model-name-edit" />
                        </fieldset>
                        <fieldset>
                            <label for="barcode-edit">Barcode</label>
                            <input id="barcode-edit" />
                        </fieldset>                      
                        <fieldset>
                            <label for="producer-edit">Producer</label>
                            <input id="producer-edit" />
                        </fieldset>                      
                        <fieldset>
                            <label for="model-edit">Model</label>
                            <input id="model-edit" />
                        </fieldset>                      
                        <fieldset class="small-edits">
                            <fieldset>
                                <label for="weight-edit">Weight</label>
                                <input id="weight-edit" />
                            </fieldset>
                            
                            <fieldset>
                                <label for="pack-width-edit">Width</label>
                                <input id="pack-width-edit" />
                            </fieldset>                           
                            <fieldset>
                                <label for="pack-height-edit">Height</label>
                                <input id="pack-height-edit" />
                            </fieldset>                            
                            <fieldset>
                                <label for="pack-length-edit">Length</label>
                                <input id="pack-length-edit" />
                            </fieldset>                            
                            <fieldset>
                                <label for="count-edit">Count </label>
                                <input id="count-edit" />
                            </fieldset>                          
                        </fieldset>                        
                        <div class="add-btn">Add product type</div>
                    </form>
                    <div class="message-block"></div>
                    <table class="product-table">
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
                                <div class="edit-btn">To edit</div>
                                <div class="delete-btn">Delete</div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>              
            <div class="create-btn">
                Begin recieving
            </div>        
        </div>

    </div>
    <footer>
        Phone number: +1 111 111 111<br />
        Company Name 2016 @ all rights reserved.
    </footer>
</body>
</html>