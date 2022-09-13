<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="kr.ac.woosuk.webprogramming.models.VO.BoardVO"%>
<%@ page import="kr.ac.woosuk.webprogramming.models.VO.AttacheVO"%>
<!DOCTYPE HTML>
<%

%>
<html>
	<head>
		<%@ include file="./common/title.jsp"%>
	</head>
	<body class="is-preload">
	    <%
	        List<BoardVO> boards = (List<BoardVO>) request.getAttribute("boards");
	        int totalIndexCount = boards.size();
            if (totalIndexCount == 0) {
                totalIndexCount = 1;
            }
            int totalPageCount = totalIndexCount / 3;
            if (totalIndexCount % 3 > 0) {
                totalPageCount++;
            }
	    %>
		<div id="page-wrapper">

			<!-- Header -->
            <%@ include file="./common/header.jsp"%>

			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>게시판</h2>
							<p>이미지 게시판입니다.</p>
							<form method="post" action="/webtest/BoardSearch">
							    <div class="col-4 col-12-medium">
                                    <select name="boardSearchType">
                                        <option value="1">제목</option>
                                        <option value="2">글쓴이</option>
                                    </select>
							    </div>
							    <div class="col-4 col-12-medium">
                                    <input type="text" id="boardSearch" name="boardSearch" placeholder="검색">
							    </div>
							    <div>
							        <ul>
							            <li><input type="submit"></li>
							        </ul>
							    </div>
							</form>
						</header>
						<div class="row gtr-150">
							<div class="col-8 col-12-medium">
								<!-- Content -->
									<section id="content">
									    <%
									        String selectTitle = request.getParameter("title");
									        int selectIndex = 0;
									        if (selectTitle != null) {
									            for(BoardVO board : boards) {
									                if(board.getId() == Integer.parseInt(selectTitle)){%>
									                    <h5><%=board.getUsername()%> | <%=board.getCreateDate()%></h5>
                                                        <a href="#" class="image fit"><img class="board-image" src="images/<%=board.getAttacheVO().getSaveFileName() + '.' +board.getAttacheVO().getOriginalFileName()%>" onError=this.src="images/pic01.jpg" /></a>
                                                        <h3><%=board.getTitle()%></h3>
                                                        <p><%=board.getSubTitle()%></p>
                                                        <p><%=board.getContents()%></p>
									               <%}
									            }
									        }else {
									    %>
									    <h5><%=boards.get(selectIndex).getUsername()%> | <%=boards.get(selectIndex).getCreateDate()%></h5>
										<a href="#" class="image fit"><img class="board-image" src="images/<%=boards.get(selectIndex).getAttacheVO().getSaveFileName() + '.' +boards.get(selectIndex).getAttacheVO().getOriginalFileName()%>" onError=this.src="images/pic01.jpg" /></a>
										<h3><%=boards.get(selectIndex).getTitle()%></h3>
										<p><%=boards.get(selectIndex).getSubTitle()%></p>
										<p><%=boards.get(selectIndex).getContents()%></p>
										<%}%>
									</section>
							<hr/>
							</div>
							<div class="col-4 col-12-medium">
								<!-- Sidebar -->
									<section id="sidebar">
									<%
									    String pageNumberStr = request.getParameter("page");
									    int pageNumber = 1;
									    if (pageNumberStr != null) {
                                            pageNumber = Integer.parseInt(pageNumberStr);
									    }
									    int firstRow = (pageNumber - 1) * 3;
									    int endRow = firstRow + 3;
									    while(firstRow < endRow) {
									        if (firstRow >= boards.size()) break;%>
										<section>
											<h3><%=boards.get(firstRow).getTitle()%></h3>
											<p><%=boards.get(firstRow).getContents()%></p>
											<footer>
												<ul class="actions">
													<li><a href="/webtest/Board?title=<%=boards.get(firstRow).getId()%>&page=<%=pageNumber%>" class="button">Learn More</a></li>
												</ul>
											</footer>
										</section>
										<hr/>
									<%      firstRow++;
									    } %>
									</section>

							<div style="text-align: center">
							    <a href="#">&lt;</a>&nbsp;&nbsp;&nbsp;
							    <%
							        int lastIndex = boards.size() - 1;
							        if (selectTitle == null) {
							            selectTitle = String.valueOf(boards.get(lastIndex).getId());
							        }
                                    for (int i = 1; i < totalPageCount + 1; i++) {%>
                                        <a href="/webtest/Board?title=<%=selectTitle%>&page=<%=i%>"><%=i%></a>&nbsp;&nbsp;&nbsp;
                                <% } %>
								<a href="#">&gt;</a>
							</div>
							</div>
							<section>
								<ul class="actions">
									<li><a href="Board/write?write=new" class="button primary">작성하기</a></li>
									<li><a href="BoardUpdate?title=<%=selectTitle%>" class"button primary">수정하기</a></li>
								</ul>
							</section>
						</div>
					</div>
				</div>
			<!-- Footer -->
			<%@ include file = "./common/footer.jsp"%>
		</div>
		<!-- Scripts -->
		<%@ include file = "./common/script.jsp"%>
		<script>
		    window.onload = () => {
		        if(new URL(location.href).searchParams.get('error')){
		            alert(new URL(location.href).searchParams.get('error'));
                }
		    };
		</script>
	</body>
</html>