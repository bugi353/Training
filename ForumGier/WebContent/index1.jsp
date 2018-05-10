<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Ta aplikacja webowa nie jest w pełni skończona, aplikacja zapożycza część kodu z kursu YT: https://www.youtube.com/watch?v=PpOwbYgaodQ&index=14&list=PL_nu3rOfoPo438XaqXGVHxQhLGLp8uiac -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gry komputerowe i planszowe</title>
</head>
<style>
	#mcontainer
	{
		margin: auto;
		width: auto;
		background-color: rgba(230,60,70,1);
	}
	
	#header
	{
		padding: 15px;
		background-image: url(chmury.jpg);
		background-size: cover;
		background-repeat:  no-repeat;
		width:auto;
		text-align: center;
		color: rgba(200, 10, 70, 1);
		cursor: pointer;
		
	}
	
	#menupanel
	{
		float: left;
		width: 15%;
		background-color: rgba(230,60,70,1);
		padding-top: 5px;
		height: auto;
		text-align: center;
		height:100%;
		
	}
	
	#content
	{
		float:left;
		width: 85%;
		background-color: rgba(255, 226, 160, 1);
		
		
	}
	
	#footer
	{
		clear:both;
		width: auto;
		background-color: rgba(1, 100, 270, 0.7);
	
	}
	
	#divspace
	{
		padding-top: 3px;
	}
	
	.p1
	{	
		padding: 5px;
		text-align: center;
		background-color: white;
		width:130px;
		border: 2px solid #73AD21;
		border-radius:35px;
		cursor:pointer;		
	}
	
</style>
<body>
	
	<div id = mcontainer>

		<a href="index1.jsp" style="text-decoration:none"><div id="header">
			<h1>Witaj na stronie o grach planszowych i komputerowych</h1>
		</div></a>
		
		
		<div id = "menupanel">
		
			 <center><a href="index1.jsp"><button class="p1"> Strona główna</button></a></center>
			<div id="divspace"></div>
			<center><button class = "p1"> Gry planszowe</button></center>
			<div id="divspace"></div>
			<center><button class = "p1"> Gry komputerowe</button></center>
			<div id="divspace"></div>
			<center><a href="/login"><button class = "p1"> Forum</button></a></center>
			<div id="divspace"></div>
			<center><button class = "p1"> O nas</button></center><br/>
		
			
		
		</div>
	
		<div id="content">
			
		
		</div>
		
		
		
		<div id="footer">
				
				<p>Strona wykonana przez Piotra Jażyńskiego</p>
				
		</div>
	
	
	
	</div>		
</body>
</html>