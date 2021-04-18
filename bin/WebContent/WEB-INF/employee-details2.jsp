<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Personne</title>
    </head>
    
    <body>
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    	<p>Username : </p><c:out type="text" value="${sessionScope.username}" />
    	<p>Nom : </p>
    	<p>Prénom : </p>
    	<p>Bureau : </p>
    	<p>Service : </p>
    	<p>Poste : </p>
    	<p>Dirige X personnes dont X directement. Son chef est X </p>
=======
=======
>>>>>>> Stashed changes
    	<p>Nom : <c:out value="${ employee.surname }">Nom de famille par défaut</c:out></p>
    	<p>Prénom : <c:out value="${ employee.name }">Nom de famille par défaut</c:out></p>
    	<p>Bureau : <c:out value="${ employee.bureau }">Nom de famille par défaut</c:out></p>
    	<p>Service : <c:out value="${ employee.service }">Nom de famille par défaut</c:out></p>
    	<p>Poste : <c:out value="${ employee.poste }">Nom de famille par défaut</c:out></p>
    	<p>Dirige <c:out value="${ totalChildren }">?</c:out> personnes, dont <c:out value="${ directChildren }">?</c:out> directement. </p>
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    
    </body>
</html>