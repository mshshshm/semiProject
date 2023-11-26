<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel = "stylesheet" href ="/campus/css/search.css">
    <link rel = "stylesheet" href ="/campus/css/view.css">
    <link rel = "stylesheet" href ="/campus/css/write.css">
    <link rel = "stylesheet" href ="/campus/css/ckeditor.css" type="text/css">
    <link rel = "stylesheet" href ="/campus/css/media.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
    <script src="/campus/js/ckeditor.js"></script>
    <script src="board_add.js"></script>
    <script>
        $(function() {
            //CKEDITOR
            CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
        });
    </script>
    <style>
    	* {
		      margin: 0;
		      padding: 0;
		      box-sizing: border-box;
		  }
    </style>
</head>
<body>
    <main>
        <div class="board_wrap">
            <div class="container2">
                <form id="searchForm" action="https://www.google.com/search" method="GET">
                    <div class="search">
                        <div class="icon"></div>
                        <div class="input">
                            <input type = "text" placeholder="Search" id = "mysearch">
                        </div>
                        <span class="clear" onclick = "document.getElementById('mysearch').value = ''"></span>
                    </div>
                </form>
            </div>
            <div class="board_list_wrap">
                <div class="category">
                    <ul>
                        <li>
                            <button><a href="/campus/board/list">자유</button>
                        </li>
                        <li>
                            <button><a href="/campus/qna_board/list">QnA</a></button>
                        </li>
                        <li>
                            <button><a href="/campus/media_board/list">미디어</a></button>
                        </li>
                        <li>
                            <button><a href="/campus/tip_board/list">Tip</a></button>
                        </li>
                    </ul>
                </div>
                <form method="post" action="${pageContext.servletContext.contextPath}/qna_board/writeOk" id="frm" onsubmit="return validateForm()">
                    <div class="board_write_wrap">
                        <div class="board_write">
                            <div class="title">
                                <dl>
                                    <dt><select id = "category" name="category">
                                        <option value="qna">QnA</option>
                                    </select></dt>
                                    <dd><input type="text" id="subject" name="subject" placeholder="제목 입력"></dd>
                                </dl>
                            </div>
                                <li><textarea name="content" id="content"></textarea></li>
                        </div>
                        <div class="bt_wrap">
                            <input type = "submit" value = "작성" class = "btn2">
                            <input type = "button" value = "취소" class = "btn2" onclick="history.back();">
                        </div>
                    </div>
                </form>
        <script src = "/campus/js/script.js"></script>
        <script>
            function validateForm() {
                var title = document.getElementById("subject").value;
                var content = document.getElementById("content").value;
        
                if (!title) {
                    alert("제목을 입력하세요.");
                    return false;
                }

                if (!content) {
                    alert("내용을 입력하세요.");
                    return false;
                }

                if (content.length < 10) {
                    alert("내용은 최소 10자 이상이어야 합니다.");
                    return false;
                }
        
                return true;
            }
        </script>
</body>
</html>