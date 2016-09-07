<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="application/javascript" src="js/header.js"></script>
<header>
    <div class="user-name"><s:property value="fullName"/></div>
    <s:if test="isLogined()">
        <div onclick="signOut()" class="signin-btn">Sign Out</div>
    </s:if>
    <s:if test="!isLogined()">
        <div onclick="modalShow()" class="signin-btn">Sign In</div>
    </s:if>
</header>
<s:include value="modal-signin.jsp"/>