<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/home.js"></script>
    <script type="text/javascript" src="js/helpers/sha.js"></script>
    <title>Home</title>
</head>
<body>
<div class="content">
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
    <div class="company block">
        <div class="company-name">
            Company Name
        </div>
        <div class="company-phrase">
            So cool phrase, so interesting wow
        </div>
        <div class="scroller">
            <img src="images/3.jpg" />
        </div>
    </div>
    <div class="about block">
        <div class="title">About us:</div>
        Lorem ipsum dolor sit amet, an luptatum adversarium qui. No qui aliquip menandri referrentur, mea et voluptua percipitur. No vel regione maiorum, ut vel eius feugiat efficiendi. Vis mollis noluisse ut, probatus accommodare theophrastus vel ea. Te viris decore abhorreant vix, eu omnium virtute repudiare duo.
        Id oratio maiestatis pri, at labitur explicari pri. Vel cu nostrud dolores adipiscing. Et graeci verear perpetua pri. Verterem platonem mnesarchum ex usu, mazim graeco placerat ei qui.
        Sint gubergren ut vix. Eam et harum impedit. Velit oportere id ius, quo te civibus detraxit. Delenit platonem ne quo.<br /><br />
        Lorem ipsum dolor sit amet, an luptatum adversarium qui. No qui aliquip menandri referrentur, mea et voluptua percipitur. No vel regione maiorum, ut vel eius feugiat efficiendi. Vis mollis noluisse ut, probatus accommodare theophrastus vel ea. Te viris decore abhorreant vix, eu omnium virtute repudiare duo.
        Id oratio maiestatis pri, at labitur explicari pri. Vel cu nostrud dolores adipiscing. Et graeci verear perpetua pri. Verterem platonem mnesarchum ex usu, mazim graeco placerat ei qui.
        Sint gubergren ut vix. Eam et harum impedit. Velit oportere id ius, quo te civibus detraxit. Delenit platonem ne quo.<br /><br />
    </div>
    <div class="why-container block">
        <div class="why-title">
            Why you should choose us?
        </div>
        <ul>
            <li>Reason 1 and text text text text text text</li>
            <li>Reason 2 and text text text text text text </li>
            <li>Reason 3 and text text text text text text </li>
            <li>Reason 4 and text text text text text text </li>
            <li>Reason 5 and text text text text text text </li>
        </ul>
        <img style="float:right; height:300px; margin-right:50px;" src="images/joyful.png" />
    </div>
</div>
</div>
<s:include value="footer.jsp"/>
</body>
</html>
