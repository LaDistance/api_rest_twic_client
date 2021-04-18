<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Employé</title>
    </head>
    
    <body>
	<div>
	<a href="connection">Se connecter</a>
	<p>Utilisateur actuel </p>
	<p>Nom : <c:out value="${sessionScope.surname}" ></c:out>
	<p>Prénom : <c:out value="${sessionScope.name}" ></c:out></p>
	</div>
    <br/>
    <br/>
    <div>
    <h2> Informations relatives à l'employé </h2>
    	<p>Nom : <c:out value="${ employee.surname }">Nom de famille par défaut</c:out></p>
    	<p>Prénom : <c:out value="${ employee.name }">Prénom par défaut</c:out></p>
    	<p>Numéro du bureau : <c:out value="${ employee.bureau }">Bureau par défaut</c:out></p>
    	<p>Service : <c:out value="${ employee.service }">Service par défaut</c:out></p>
    	<p>Poste : <c:out value="${ employee.poste }">Poste par défaut</c:out></p>
    	<p>Superviseur : <c:out value="${ supervisor.getName() }"></c:out> <c:out value="${ supervisor.getSurname() }"></c:out></p>
    	<p>Dirige <c:out value="${ totalChildren }">?</c:out> personnes, dont <c:out value="${ directChildren }">?</c:out> directement. </p>
    </div>
    </body>
</html>