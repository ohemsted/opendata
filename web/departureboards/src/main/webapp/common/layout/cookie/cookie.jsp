<%-- 
    Document   : topmenu
    Created on : Jun 2, 2014, 9:50:46 AM
    Author     : Peter T Mount
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${requestScope.euCookie}">
    <div id="eu_cookie_notice">
        <img src="/images/cookie.png" class="left" width="48" height="48"/>
        This site uses cookies for session management.
        By continuing to use our website, you are agreeing to our use of cookies.
        [<a href="//uktra.in/Cookies">More Info</a>]
        <div class="clear"></div>
    </div>
    <script>
        $(document).ready(function () {
            $('#eu_cookie_notice').click(function (){
                $('#eu_cookie_notice').hide(200);
            });
            setTimeout(function() {
                $('#eu_cookie_notice').hide(1000);
            }, 5000);
        });
    </script>
</c:if>
