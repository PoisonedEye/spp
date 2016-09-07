<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="styles/modal.css" />
<script type="text/javascript" src="js/modal.js"></script>
<div class="hider" onclick="modalHide()"></div>
<form class="modal">
    <div class="title">Sign in</div>
    <div onclick="modalHide()" class="close">x</div>
    <br />
    <label for="login">Login</label>
    <input id="login"/>
    <label for="password">Password</label>
    <input id="password" type="password"/>
    <div class="message"></div>
    <div class="submit" onclick="signIn()">Submit</div>
</form>