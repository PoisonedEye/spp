<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<header>
    <div class="user-name"><s:property value="fullName"/></div>
    <div onclick="signModalShow()" class="signin-btn">Sign in</div>
</header>
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