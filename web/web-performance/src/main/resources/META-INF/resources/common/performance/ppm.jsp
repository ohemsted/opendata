<%-- 
    Document   : home
    Created on : Oct 21, 2014, 11:20:44 AM
    Author     : Peter T Mount
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Public Performance Measure</h1>
<p>
    The Public Performance Measure for ${day}
    <a href="/performance/ppm/${date.year}">${date.year}</a> <a href="/performance/ppm/${date.year}/${date.month.value}">${month}</a> ${date.dayOfMonth}
</p>
<table class="wikitable">
    <thead>
        <tr>
            <th rowspan="2" valign="bottom">Operator</th>
            <th rowspan="2" valign="bottom">PPM</th>
            <th rowspan="2" valign="bottom">Rolling</br>PPM</th>
            <th colspan="4">Trains</th>
        </tr>
        <tr>
            <th>Run</th>
            <th>On Time</th>
            <th>Late</th>
            <th>Canc/VLate</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="perf" items="${performance}">
            <tr>
                <td>${operators.get(perf.operatorId).display}</td>
                <td align="right">${perf.ppm}</td>
                <td align="right">${perf.rollingPpm}</td>
                <td align="right">${perf.run}</td>
                <td align="right">${perf.ontime}</td>
                <td align="right">${perf.late}</td>
                <td align="right">${perf.canceled}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h3>Notes:</h3>
<ul>
    <li>Public Performance Measure (PPM) is calculated as the number of trains on time divided by the total train run.</li>
    <li>The Rolling PPM is the PPM over the last 24 hours, whilst the PPM is for today.</li>
    <li>A train is on time if it arrives at it's destination within 5 minutes (10 for long distance) of the timetable.</li>
    <li>A train is late if it's not on time and not delayed by 30 minutes or more.</li>
    <li>Canc/VLate is the number of trains either cancelled or delayed by 30 minutes or more. These trains are not included in the run total.</li>
    <li>The data covers a Rail Day. A rail day starts at 0200 UK time - GMT or BST in the summer.</li>
    <li>This data is direct from <a href='http://datafeeds.networkrail.co.uk/'>Network Rail's Open Data platform</a> and is updated once every minute.</li>
    <li>If the date being shown is the current Rail Day then the figures will cover the time from 02:00 up to the current time only.</li>
    <li>Data can be incomplete due to downtime upstream. So if the system was unavailable at the end of the day then the data can be incomplete.</li>
</ul>

<c:if test="${refresh=true}">
    <script>
        $(document).ready(function () {
            setTimeout(function () {
                location.reload();
            }, 60000);
        });
    </script>
</c:if>