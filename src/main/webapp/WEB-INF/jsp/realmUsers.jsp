<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>


<div class="generic-container">
		<div class="panel panel-default">
		  	<div class="panel-heading"><span class="lead">List of Realms</span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>ID</th>
				        <th>Name</th>
				        <th>Description</th>
				        <th>Key</th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${realmUsers}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
						<td>${user.description}</td>
						<td>${user.key}</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
        <div class="well">
            <a href="<c:url value='/newrealm' />">Add New Realm</a>
        </div>
   	</div>


</body>

</html>
