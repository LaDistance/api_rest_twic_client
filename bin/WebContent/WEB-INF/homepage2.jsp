<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Organigramme</title>
    </head>
    
    <body>
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    
=======
=======
>>>>>>> Stashed changes
    <p> Homepage</p>
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
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    </body>
</html>