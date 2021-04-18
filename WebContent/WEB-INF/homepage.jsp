<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Organigramme</title>
    </head>
    
    <body>
	<div>
	<c:if test="${ empty sessionScope.name }"><a href="connection">Se connecter</a></c:if>
	
	
	<c:if test="${ !empty sessionScope.name }">
	<h4><c:out value="Profil connecté : ${ sessionScope.name } ${ sessionScope.surname }" /></h4>
		<form method="post" action="ConnectionServlet">
			<input type="submit" value="Se déconnecter" name="disconnect"/>
		</form>
	</c:if>
	

	</div>
    
    <div>
    <h2> Organigramme de l'entreprise </h2>
    <c:forEach items="${ relationships }" var="relationship">
    	<p><a href="employee-details?id=${relationship[0].getId() }"><c:out value="${ relationship[0].getName()}"></c:out> <c:out value="${ relationship[0].getSurname() }"></c:out> </a>est le supérieur de 
    	<c:forEach items="${ relationship[1] }" var="child">
    	<ul>
    	<li>
    	<a href="employee-details?id=${ child.getId() }"><c:out value="${ child.getName() }"> </c:out> <c:out value="${ child.getSurname() }"></c:out></a>
    	</li>
    	
    	</ul>
    		
    	</c:forEach>
    	</p>
    </c:forEach>
    </div>
    </body>
</html>