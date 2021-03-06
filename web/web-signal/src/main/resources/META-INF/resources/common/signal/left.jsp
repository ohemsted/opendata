<%-- 
    Document   : home
    Created on : Oct 21, 2014, 11:20:44 AM
    Author     : Peter T Mount
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<table class="wikitable" width="100%">
    <caption>Signal Area</caption>
    <tr>
        <th style="text-align: right;">Area</th>
        <td>${area.area}</td>
    </tr>
    <tr>
        <th style="text-align: right;">Description</th>
        <td>${area.comment}</td>
    </tr>
    <tr>
        <th style="text-align: right;">Updated</th>
        <td id="updated"></td>
    </tr>
</table>

<p>
    Data presented here is based on signalling movements and may not necessarily relate to track layout.
</p>
<p>
    Some details are based on observation but berth/station relationships may not be accurate.
</p>
<p>
    The data on this page will update itself every 10 seconds.
</p>

<tiles:insertAttribute name="other"/>