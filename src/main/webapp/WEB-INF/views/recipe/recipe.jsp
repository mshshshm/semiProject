<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	    .container{
        width: 1300px;
        margin:0 auto;
        margin-top: 100px;
        }
        .container>a>img{
			width: 300px;
		}
        #img_profile{
			width: 30px; height: 30px;
			margin-bottom: 10px;
			margin-right: 5px;
		}
        #menu_top{
			list-style-type: none;		
			text-align: right;
			font-size: 1.2em;
			margin-right: 50px;			
		}
		#menu_top li a{
			
			text-decoration: none;
    		color: black;
		}
		#menu_top li{
			display: inline;
			margin-right: 30px;		
			position: relative;
			top: -55px;
		}
		/*
		#check-btn { 
			display: none;
		}
		#check-btn:checked ~ #tab_list_1 { 
			display: block; 
		} 
		#tab_list_1 { 
			display: none; 
		}
		*/
	    .left-middle ul ul li a {
    		text-decoration: none;
    		color: black;
        }		
		.left-middle {
	        width: 220px;
	        height: 700px;
	        position: relative;
	        left: 5px;
	        margin-top: -40px;

        }
        .left-middle ul {
        	list-style-type: none;
        	padding: 0;
        	font-family: 'Futuristic';
        }

    	.left-middle ul li {
        	margin-bottom: 10px;
        }

        .left-middle input[type="text"] {
        	width: 95%;
        	padding: 5px;
        	margin-top: 20px;
        }
        .left-middle ul ul {
        	padding-left: 0px;
        }
		#btn_more_recipe{
			width: 30px; height: 30px;
			margin-left: 105px;
		}
		#btn_more_recipe_2{
			width: 30px; height: 30px;
			margin-left: 110px;
		}
		#btn_more_recipe_3{
			width: 30px; height: 30px;
			margin-left: 130px;
		}
		.tab_list>li{
			font-family: 'GmarketSansMedium';
		}
	    .left-middle > ul > li {
    		font-size: 1.5em;
        }
        .left-middle ul > ul > li {
    		font-size: 1.2em;
    		margin-bottom: 20px;
    		margin-top: 20px;
        } 
        #menu_top li:nth-child(2) a{
			color: gray;
		}
        #menu_top li:nth-child(4) img{
			position: relative;
			top: 18px;
			left: 30px;
		}
		/* 기존의 d1부터 d8까지의 스타일 */
		.d1>img, .d2>img, .d3>img, .d4>img, .d5>img, .d6>img, .d7>img, .d8>img, .d9>img, .d10>img, .d11>img, .d12>img{
			position: relative;
			width: 210px;
			height: 210px;
			margin: 10px;
		}				
		.middle {
			width: 1000px;
			position: relative;
			left: 250px;
			bottom: 730px;
		}
		.box {
			display: grid;
			grid-template-columns: repeat(4, 1fr);
			grid-gap: 20px;
			grid-row-gap: 0px;
			padding:20px;
		}
		.middle a{
			text-decoration: none;
			color: black;	
		}
		.d1t{
			width: 160px;
			height:15px; line-height:15px;
			position: relative;
			text-align: left;
			font-size: 15px;
			font-weight: bold;
			margin-left: 40px;
			overflow: hidden;
		}
		.d1s{
			width: 160px;
			height:15px; line-height:15px;
			position: relative;
			margin-left: 40px;
			font-size: 13px;
			color: gray;
			overflow: hidden;
		}
		#heartDate{
			display: flex;
		}
		.icon{
			width: 30px;
			height:15px; line-height:15px;
			position: relative;
			font-size: 13px;
			left: 40px;
		}	
		.icon2{
			width: 80px;
			height:15px; line-height:15px;
			position: relative;
			font-size: 13px;
			left: 80px;
		}
        .tab_list {
			font-family: 'GmarketSansMedium';
			font-size: 1.1em;
		}	
		#a{
			font-family: 'SBAggroB';
			color: orange;
			font-size: 3.6em;
			margin: 0; padding:0;
		}
		.sub_title{
			font-family: 'GmarketSansMedium';
		}
</style>
<style>
	@font-face {
    font-family: 'SBAggroB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
	@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}
</style>
</head>
<body>
<div class="container">
	 <c:if test="${logStatus=='Y'}">
     <div id="menu_top">
         <li><a href="write"><b>글쓰기</b></a></li>
     </div>
     </c:if>
     <div class="left-middle">
         <ul>
            <li id="a">Recipe</li>   
            <hr/>
            <li class="sub_title">Food Style</li>          
                <ul class="tab_list" id="tab_list_1">
                   <li><a href="#">한식</a></li>
                   <li><a href="#">중식</a></li>
                   <li><a href="#">양식</a></li>
                   <li><a href="#">일식</a></li>
                </ul>            
            <hr/>
            <li class="sub_title">Food Type</li>
                <ul class="tab_list">
				   <li><a href="#">국 요리</a></li>
				   <li><a href="#">볶음 요리</a></li>
				   <li><a href="#">탕 요리</a></li>
				   <li><a href="#">찌게 요리</a></li>
				   <li><a href="#">찜 요리</a></li>
				   <li><a href="#">바베큐</a></li>
				</ul>
			<hr/>           
            <li class="sub_title">Dessert</li>
         </ul>
        <input type="text" placeholder="검색">
    </div>
    <div class="middle">
		<div class="box">
			<c:forEach var="dVO" items="${list}">
				<a href="/campus/recipe/view/${dVO.recipeno}">
					<div class="d1">
						<img src="#"/>
					</div>
					<div class="d1t">${dVO.subject}</div>
					<div class="d1s">${dVO.userid}</div>
					<div id="heartDate">
						<div class="icon">♥ ${dVO.hit}</div>
						<div class="icon2">${dVO.writedate}</div>
					</div>
				</a>
			</c:forEach>														
		    			
		</div>
    </div>
</div>