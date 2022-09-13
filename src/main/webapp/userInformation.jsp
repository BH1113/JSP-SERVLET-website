<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.ac.woosuk.webprogramming.models.DTO.UserDTO"%>
<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<%@ include file = "./common/title.jsp"%>
	</head>
	<body class="is-preload">
		<div id="page-wrapper">

			<!-- Header -->
			<%@ include file = "./common/header.jsp"%>
			<%
			    UserDTO user = (UserDTO) request.getAttribute("userInfo");
			%>
			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>User</h2>
							<p>Ipsum dolor feugiat aliquam tempus sed magna lorem consequat accumsan</p>
						</header>
						<!-- Form -->
						<section>
							<form method="post" action="/webtest/UserInfo" id="signup">
								<div class="row gtr-uniform gtr-50">
									<div class="col-6 col-12-xsmall">
										<input type="email" name="email" id="email" value="" placeholder="<%=user.getEmail()%>" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="pass1" id="pass1" value="" placeholder="<%=user.getPassword()%>" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="pass2" id="pass2" value="" placeholder="비밀번호 확인" />
									</div>
									<script>
									    let $name = document.getElementById("name");
									    let $email = document.getElementById("email");
									    let $pass1 = document.getElementById("pass1");
									    let $pass2 = document.getElementById("pass2");

									    $pass2.addEventListener("keyup", function(event) {
									        if(event !== 'Enter') return;
									        if(this.value !== $pass1.value){
                                                $pass2.value = "";
                                                $pass2.placeholder = "비밀번호를 확인해주세요";
                                                $pass2.focus();
									        }
									    })

									    $pass2.addEventListener("blur", function() {
									        if(this.value !== $pass1.value){
									            $pass2.value = "";
									            $pass2.placeholder = "비밀번호를 확인해주세요";
									            $pass2.focus();
									        }
									    })
									</script>
									<div class="col-12">
										<select name="job" id="job" class="jobSelect" data-id="<%=user.getJob()%>">
										</select>
									</div>
									<div class="col-12">
										<textarea name="message" id="message" placeholder="자기소개" rows="6"><%=user.getIntroduction()%></textarea>
									</div>
									<div class="col-12">
										<ul class="actions">
											<li><input type="button" value="업데이트" class="primary" onclick="formValidator()"/></li>
											<li><input type="button" value="회원탈퇴" onclick="deleteUser()" /></li>
										</ul>
									</div>
								</div>
							</form>
							<script>
							    let $job = document.getElementById("job");
							    let $copy = document.getElementById("copy");

							    let formValidator = function(){
                                    if ($pass2.value == "") {
                                        $pass2.placeholder = "비밀번호를 확인해 주세요.";
                                        $pass2.focus();
                                    }else if ($email.value == "") {
                                        $email.placeholder = "이메일은 필수 입력사항입니다.";
                                        $email.focus();
                                    }else if ($job.value == "") {
                                        $job.focus();
                                    }else {
                                        document.getElementById("signup").submit();
                                    }
							    };

							    let deleteUser = function() {
							        if(confirm("회원 탈퇴를 하시겠습니까?")) {
							            document.getElementById("signup").action = "/webtest/UserInfo?delete=1";
							            document.getElementById("signup").submit();
							        }
							    };

							     window.onload = function() {
                                    if(new URL(location.href).searchParams.get('error')){
                                        if(!confirm("재가입 하시겠습니까?")) {
                                            location.href = "/webtest/Index?notConfirm=1";
                                        }
                                    }
                                 };
							</script>
						</section>
					</div>
				</div>
			<!-- Footer -->
			<%@ include file = "./common/footer.jsp"%>
		</div>

		<!-- Scripts -->
		<%@ include file = "./common/script.jsp"%>
		<script>addJobMenu();</script>
	</body>
</html>