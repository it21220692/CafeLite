<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>Menu Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>/admin" class="navbar-brand"> Menu
					Management </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/admin"
					class="nav-link">Menu</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${menu != null}">
					<form action="update" method="post" enctype="multipart/form-data">
				</c:if>
				<c:if test="${menu == null}">
					<form action="insert" method="post" enctype="multipart/form-data">
				</c:if>

				<caption>
					<h2>
						<c:if test="${menu != null}">
                                    Edit Menu
                                </c:if>
						<c:if test="${menu == null}">
                                    Add New Menu
                                </c:if>
					</h2>
				</caption>

				<c:if test="${menu != null}">
					<input type="hidden" name="menuID"
						value="<c:out value='${menu.menuID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Food Name</label> <input type="text"
						value="<c:out value='${menu.foodName}' />" class="form-control"
						name="foodName" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Food Category</label> <input type="text"
						value="<c:out value='${menu.foodCategory}' />"
						class="form-control" name="foodCategory">
				</fieldset>

				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${menu.price}' />" class="form-control"
						name="price">
				</fieldset>

				<fieldset class="form-group">
					<label>Availability</label> <input type="text"
						value="<c:out value='${menu.availability}' />"
						class="form-control" name="availability">
				</fieldset>

				<fieldset class="form-group">
					<label>Quantity</label> <input type="text"
						value="<c:out value='${menu.quantity}' />" class="form-control"
						name="quantity">
				</fieldset>
				<fieldset class="form-group">
					<label>Image</label> <input type="file"
						value="<c:out value='${imageFileNames}' />" class="form-control"
						name="image">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>