<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<c:set var="cxt" value="${pageContext.request.contextPath}" />
<c:set var="img_service" value="http://yst-images.img-cn-hangzhou.aliyuncs.com" />
<c:set var="img_local" value="http://123.206.47.26:5889" />
<%-- <c:set var="shangchuan" value="localhost:8085" /> --%>
<c:set var="shangchuan" value="123.206.47.26:5888" />
<script src="${cxt}/js/jquery-1.7.2.js"></script>
<script src="${cxt }/js/jedate/jquery.jedate.js"></script>
