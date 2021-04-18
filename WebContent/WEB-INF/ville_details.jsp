<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
      	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Modifier une ville</title>
	</head>

	<body>
	
	<div>
		<h3><a href="villes">Accueil</a></h3>
	</div>
		<form method="post" action="PutVille">
			
			Code INSEE :  		
			<input type="text" name="codeInsee" value="${ ville.getCodeInsee() } " /><br>		
			
			Nom :  		
			<input type="text" name="nom" value="${ ville.getNom() }"/><br>		
		
			Code postal : 
			<input type="text" name="codePostal" value="${ ville.getCodePostal() }"/><br>
			
			Libellé d'acheminement :  		
			<input type="text" name="libelle" value="${ ville.getLibelleAcheminement() }"/><br>
			
			Longitude:  		
			<input type="text" name="longitude" value="${ ville.getLongitude() }"/><br>
			
			Latitude :  		
			<input type="text" name="latitude" value="${ ville.getLatitude() }"/><br>
			
			<input type="submit" name="send">			
		
		</form>
		<c:if test="${ user.name != null }"> Vous êtes connecté en tant que <c:out value="${ user.name } ${ user.surname }"></c:out>.</c:if>
	</body>
</html>