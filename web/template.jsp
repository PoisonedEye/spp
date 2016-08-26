<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/main.css" />
    <script type="text/javascript" src="js/libraries/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="js/home.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/helpers/sha.js"></script>
</head>
<body>
<div class="content">
    <header>
        <div class="user-name"><s:property value="fullName"/></div>
        <div onclick="signModalShow()" class="signin-btn">Sign in</div>
    </header>