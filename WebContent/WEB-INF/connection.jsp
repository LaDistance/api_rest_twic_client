<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
      	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Modifier une ville</title>
	</head>

	<body>
	
	<div>
		<h3><a href="villes">Accueil</a></h3>
	</div>
		<form method="post" action="PutVille">
		
			Code INSEE :  		
			<input type="text" name="codeInsee"/><br>		
			
			Nom :  		
			<input type="text" name="nom"/><br>		
		
			Code postal : 
			<input type="text" name="codePostal"/>
			
			Libellé d'acheminement :  		
			<input type="text" name="libelle"/><br>
			
			Longitude:  		
			<input type="text" name="longitude"/><br>
			
			Latitude :  		
			<input type="text" name="latitude"/><br>
			
			<input type="submit" name="send">			
		
		</form>
		<c:if test="${ user.name != null }"> Vous êtes connecté en tant que <c:out value="${ user.name } ${ user.surname }"></c:out>.</c:if>
	</body>
</html>