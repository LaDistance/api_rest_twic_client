<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Distance entre 2 villes</title>
    </head>
    
    <body>

    <div>
		<h3><a href="index">Distance entre 2 villes</a></h3>
	</div>
		<form method="post" action="CompareVilles">

			Ville 1 :
			<select name="insee1" id="insee1">
				<option value="0">-</option>
				<c:forEach items="${villes}" var="ville" varStatus="status">
					<option value="${ville.getCodeInsee()}">${ville.getNom()}</option>
				</c:forEach>
				
			</select>
			Ville 2 :
			<select name="insee2" id="insee2">
				<option value="0">-</option>
				<c:forEach items="${villes}" var="ville" varStatus="status">
					<option value="${ville.getCodeInsee()}">${ville.getNom()}</option>
				</c:forEach>
				
			</select>
			
			<input type="submit" name="send">			
			
			<c:if test="${ distance != null }"> La distance entre la ville de <c:out value="${ ville1.getNom()}"></c:out> et la ville de
			<c:out value="${ville2.getNom() }"></c:out> est de <c:out value="${ distance }"></c:out> km.</c:if>
		</form>
    </body>
</html>