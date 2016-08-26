<%@page contentType="text/html; charset=UTF-8" %>
</div>
<footer>
    Phone number: +1 111 111 111<br/>
    Company Name 2016 @ all rights reserved.
</footer>

<div class="hider"></div>
<form class="signin-modal">
    <div class="title">Sign in</div>
    <div onclick="signModalHide()" class="close">x</div>
    <br />
    <label for="login">Login</label>
    <input id="login"/>
    <label for="password">Password</label>
    <input id="password"/>
    <div class="submit" onclick="login()">Submit</div>
</form>
</body>
</html>