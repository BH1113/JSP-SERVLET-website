<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
        <%@ include file="./common/title.jsp"%>
	</head>
	<body class="is-preload">
		<div id="page-wrapper">
			<!-- Header -->
            <%@ include file="./common/header.jsp"%>

			<!-- Main -->
			<div id="main" class="wrapper style1">
				<div class="container">
					<header class="major">
						<h2>Log in</h2>
						<p>Ipsum dolor feugiat aliquam tempus sed magna lorem consequat accumsan</p>
					</header>
					<!-- Form -->
					<section>
						<form method="post" action="/webtest/SignIn" id="signIn">
							<div class="row gtr-uniform gtr-50">
								<div class="col-6 col-6-xsmall">
									<input type="text" name="name" id="name" value="" placeholder="아이디" />
								</div>
								<div class="col-6 col-6-xsmall" id="errMessage">
									<input type="password" name="pass" id="pass" value="" placeholder="비밀번호" />
								</div>
								<pre></pre>
								<div class="col-12">
									<ul class="actions">
										<li><input id="login_btn" type="button" value="Send Message" class="primary" /></li>
										<li><input id="reset_btn" type="reset" value="Reset" /></li>
									</ul>
								</div>
							</div>
						</form>
					</section>
				</div>
			</div>
			<!-- Footer -->
            <%@ include file="./common/footer.jsp"%>
		</div>
		<!-- Scripts -->
        <%@ include file="./common/script.jsp"%>

        <script type="text/javascript">
            let id = document.getElementById("name");
            let pass = document.getElementById("pass");
            let errMessage = document.getElementById("errMessage");

            id.addEventListener("keyup", function(event) {
                if(event.key !== "Enter") return;
                if(id.value == "" || id.value.length == 0) {
                    errMessage.nextSibling.textContent = "아이디를 입력해주세요.";
                    this.focus();
                } else {
                    errMessage.nextSibling.textContent = "";
                    pass.focus();
                }
            })

            id.addEventListener("blur", function() {
                if(id.value == "" || id.value.length == 0) {
                    errMessage.nextSibling.textContent = "아이디를 입력해주세요.";
                    this.focus();
                } else {
                    errMessage.nextSibling.textContent = "";
                    pass.focus();
                }
            })

            pass.addEventListener("keyup", function(event) {
                if(event.key !== "Enter") return;
                if(pass.value == "" || pass.value.length == 0) {
                    errMessage.nextSibling.textContent = "비밀번호를 입력해주세요.";
                    this.focus();
                }else {
                    errMessage.nextSibling.textContent = "";
                }
            })

            pass.addEventListener("blur", function(event) {
                if(pass.value == "" || pass.value.length == 0) {
                     errMessage.nextSibling.textContent = "비밀번호를 입력해주세요.";
                     this.focus();
                }else {
                    errMessage.nextSibling.textContent = "";
                }
            })

            document.getElementById("login_btn").addEventListener("click", function(event) {
                if(id.value == "" || id.value.length == 0) {
                    event.preventDefault();
                    errMessage.nextSibling.textContent = "아이디 혹은 비밀번호를 입력해주세요.";
                }else {
                    errMessage.nextSibling.textContent = "";
                }

                if(pass.value == "" || pass.value.length == 0) {
                    event.preventDefault();
                    errMessage.nextSibling.textContent = "아이디 혹은 비밀번호를 입력해주세요.";
                } else {
                    errMessage.nextSibling.textContent = "";
                }

                if(errMessage.nextSibling.textContent == "") {
                    event.preventDefault();
                    document.getElementById("signIn").submit();
                }
            })

            document.getElementById("reset_btn").addEventListener("click", function(event) {
                event.preventDefault();
                document.location = "/webtest/signIn.jsp";
            })
            window.onload = function() {
                if(new URL(location.href).searchParams.get('error')){
                    alert(new URL(location.href).searchParams.get('error'));
                }
            };
        </script>
	</body>
</html>