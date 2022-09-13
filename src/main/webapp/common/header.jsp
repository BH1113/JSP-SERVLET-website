<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header">
	<h1 id="logo"><a href="Index">LearnJsp</a></h1>
	<nav id="nav">
		<ul>
			<li><a href="Index">Home</a></li>
			<li><a href="Board">Board</a></li>
			<%
			    String userId = (String)session.getAttribute("userid");
			    if(userId != null) {%>
			        <li><a href="UserInfo" class="button primary"><%=userId%></a></li>
			        <li><a href="Logout" class="button primary">Logout</a></li>
			  <%} else {%>
			        <li><a href="SignIn" class="button primary">Sign Up</a></li>
                    <li><a href="SignUp" class="button primary">Join</a></li>
              <%}%>
		</ul>
	</nav>
</header>