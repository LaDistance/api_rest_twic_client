<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des villes</title>
    </head>
    
    <body>

    <div>
    <h2> Liste des villes : </h2>
    <table class="table">
    <thead>
    <tr>
    <th scope="col">Code INSEE</th>
    <th scope="col">Nom</th>
    <th scope="col">Code Postal</th>
    <th scope="col">Libellé acheminement</th>
    <th scope="col">Longitude</th>
    <th scope="col">Latitude</th>
    </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${ villes }" var="ville">
    	<!-- <a href="employee-details?id=${relationship[0].getId() }"><c:out value="${ relationship[0].getName()}"></c:out> <c:out value="${ relationship[0].getSurname() }"></c:out> </a>est le supérieur de  -->
    	
    	<tr>
    	
    	<th scope="row">
    	<a href="details?insee=${ ville.getCodeInsee() }">
    	<c:out value="${ ville.getCodeInsee() }"></c:out>
    	</a>
    	</th>
    	<th>
    	<c:out value="${ ville.getNom() }"> </c:out>
    	</th>
    	<th>
    	<c:out value="${ ville.getCodePostal() }"> </c:out>
    	</th>
    	<th>
    	<c:out value="${ ville.getLibelleAcheminement() }"> </c:out>
    	</th>
    	<th>
    	<c:out value="${ ville.getLongitude() }"> </c:out>
    	</th>
    	<th>
    	<c:out value="${ ville.getLatitude() }"> </c:out>
    	</th>
    	
    	</tr>
    	
    	
    </c:forEach>
    </tbody>
    </table>
    </div>
    </body>
</html>