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
		<%@ include file = "./common/title.jsp"%>
	</head>
	<body class="is-preload">
		<div id="page-wrapper">

			<!-- Header -->
			<%@ include file = "./common/header.jsp"%>
			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>Join</h2>
							<p>Ipsum dolor feugiat aliquam tempus sed magna lorem consequat accumsan</p>
						</header>
						<!-- Form -->
						<section>
							<form method="post" action="/webtest/SignUp" id="signup">
								<div class="row gtr-uniform gtr-50">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="name" id="name" value="" placeholder="아이디" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="email" name="email" id="email" value="" placeholder="이메일" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="pass1" id="pass1" value="" placeholder="비밀번호" />
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
										<select name="job" id="job">
											<option value="">- 직업 -</option>
											<option value="1">학생</option>
											<option value="2">프로그래머</option>
											<option value="3">서버엔지니어</option>
											<option value="4">디자이너</option>
										</select>
									</div>
									<div class="col-4 col-12-medium">
										<input type="radio" id="priority-low" name="priority" value="1" checked>
										<label for="priority-low">남</label>
									</div>
									<div class="col-4 col-12-medium">
										<input type="radio" id="priority-normal" name="priority" value="2">
										<label for="priority-normal">여</label>
									</div>
									<div class="col-12">
										<textarea name="message" id="message" placeholder="자기소개" rows="6"></textarea>
									</div>
									<div class="col-6 col-12-medium">
										<input type="checkbox" id="copy" name="copy">
										<label for="copy">개인정보수집에 동의합니다.</label>
										<a href="#">(개인정보수집 및 이용)</a>
									</div>
									<div class="col-12">
										<ul class="actions">
											<li><input type="button" value="가입" class="primary" onclick="formValidator()"/></li>
											<li><input type="reset" value="취소" /></li>
										</ul>
									</div>
								</div>
							</form>
							<script>
							    let $job = document.getElementById("job");
							    let $copy = document.getElementById("copy");

							    let formValidator = function(){
                                    if($name.value == "") {
                                        $name.placeholder = "아이디는 필수 입력사항입니다.";
                                        $name.focus();
                                    }else if ($pass2.value == "") {
                                        $pass2.placeholder = "비밀번호를 확인해 주세요.";
                                        $pass2.focus();
                                    }else if ($email.value == "") {
                                        $email.placeholder = "이메일은 필수 입력사항입니다.";
                                        $email.focus();
                                    }else if ($job.value == "") {
                                        $job.focus();
                                    }else if (!$copy.checked) {
                                        $copy.focus();
                                    }else {
                                        document.getElementById("signup").submit();
                                    }
							    };
							    window.onload = () => {
                                	if(new URL(location.href).searchParams.get('error')){
                                		alert(new URL(location.href).searchParams.get('error'));
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
	</body>
</html>