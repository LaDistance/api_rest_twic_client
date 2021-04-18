<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
      	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Se connecter</title>
	</head>

	<body>
	
	<div>
		<h3><a href="">Accueil</a></h3>
	</div>
		<form method="post" action="ConnectionServlet">

			Pseudo :  		
			<input type="text" name="username"/><br>		
		
			Mot de passe : 
			<input type="text" name="password"/>
			
			<input type="submit" name="connect">			
		
		</form>
		<c:if test="${ user.name != null }"> Vous êtes connecté en tant que <c:out value="${ user.name } ${ user.surname }"></c:out>.</c:if>
	</body>
</html>