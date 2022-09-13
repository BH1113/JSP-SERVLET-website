<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
		<title>Landed by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
</head>
	<body class="is-preload landing">
		<div id="page-wrapper">

			<!-- Header -->
				<header id="header">
					<h1 id="logo"><a href="index.jsp">Landed</a></h1>
					<nav id="nav">
						<ul>
							<li><a href="index.jsp">Home</a></li>
							<li>
								<a href="board.jsp">Board</a>
							</li>
							<li><a href="signIn.jsp" class="button primary">Sign Up</a></li>
							<li><a href="join.jsp" class="button primary">Join</a></li>
						</ul>
					</nav>
				</header>
			<!-- error -->
            <div id="main" class="wrapper style1">
                오류가 발생하였습니다. <br>
                시스템 관리자에게 문의 하십시오.<br>
                <%
                    out.print(exception + "<br>");
                    for (StackTraceElement err : exception.getStackTrace()){
                        out.print(err.getFileName() + "<br>");
                    }
                %>
			</div>

			<!-- Footer -->
				<footer id="footer">
					<ul class="icons">
						<li><a href="#" class="icon brands alt fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon brands alt fa-facebook-f"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon brands alt fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
						<li><a href="#" class="icon brands alt fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon brands alt fa-github"><span class="label">GitHub</span></a></li>
						<li><a href="#" class="icon solid alt fa-envelope"><span class="label">Email</span></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>
				</footer>

		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>

</body>
</html>