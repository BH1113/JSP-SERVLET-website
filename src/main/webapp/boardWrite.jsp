<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.ac.woosuk.webprogramming.models.VO.BoardVO"%>
<%@ page import="kr.ac.woosuk.webprogramming.models.VO.AttacheVO"%>
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
							<h2>게시판 작성</h2>
						</header>
       				<!-- Form -->
						<section>
							<form method="post" action="/webtest/Board" enctype="multipart/form-data" id="board">
								<div class="row gtr-uniform gtr-50">
									<div class="col-12">
										<input type="text" name="title" id="title" value="" placeholder="제목" />
									</div>
									<div class="col-12">
										<input type="text" name="subtitle" id="subtitle" value="" placeholder="소제목" />
									</div>
									<div class="col-12">
										<input type="file" name="file" id="file" value="" placeholder="이미지파일" />
									</div>
									<div class="col-12">
										<textarea name="contents" id="contents" placeholder="내용을 입력하세요." rows="6"></textarea>
									</div>
									<div class="col-12">
										<ul class="actions">
											<li><a href="/webtest/Board" class="button primary">목록</a></li>
											<li></li>
											<li></li>
											<li><input type="button" value="저장" class="primary" onclick="formValidator()"/></li>
											<li><input type="reset" value="작성취소" /></li>
										</ul>
									</div>
								</div>
							</form>
						</section>
					</div>
				</div>
			<!-- Footer -->
			<%@ include file = "./common/footer.jsp"%>
		</div>
		<!-- Scripts -->
		<%@ include file = "./common/script.jsp"%>
		<script>
		    let title = document.getElementById("title");
            let subtitle = document.getElementById("subtitle");
            let file = document.getElementById("file");
            let contents = document.getElementById("contents");


		    let formValidator = () => {
		        if(title.value == "") {
		            title.focus();
		        }else if (subtitle.value == "" ) {
		            subtitle.focus();
		        }else if (contents.value == "") {
		            contents.focus();
		        }else {
		            document.getElementById("board").submit();
		        }
		    };

		    <% BoardVO board = (BoardVO) request.getAttribute("selectedBoard");
               AttacheVO attache = (AttacheVO) request.getAttribute("selectedFile");

		       if(board != null){%>
		        title.placeholder = "<%=board.getTitle()%>";
		        subtitle.placeholder = "<%=board.getSubTitle()%>";
		        contents.placeholder = "<%=board.getContents()%>";
		      <% if(attache != null){%>
		            file.placeholder = "<%=attache.getOriginalFileName()%>";
		         <%}
		      }%>
		</script>
	</body>
</html>