<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Full Screen Menu</title>
		<link rel="stylesheet" href="static/css/style.css">
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
		<script type="application/javascript" src="https://code.jquery.com/jquery-3.2.1.js"></script>
		
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.toggle a').hover(function(){
					$('.active').removeClass('active');
					$('.menu').toggleClass('active');
				});
				$('.menu li').click(function() {
      		$('.menu').toggleClass('active');

				//$('.toggle a').hover(function(){
				//	$('.menu').hide();
				//});
    		});
    	})
		</script>
		
		
	</head>
	<body>
		<!--img src="images/background.jpg" alt="bckgrnd" class="bg" /> -->
			<div class="toggle">
				<a><i class="fa fa-bars" aria-hidden="true"></i></a>
			</div>
		<div class="menu"> 
			<ul>
				<li><a href="start">Reload Start</a></li>
				<li><a href="list">Albums</a></li>
				<li><a class="info" href="#">Close Overlay</a></li>
			</ul>
		</div>
	<body>
</html>