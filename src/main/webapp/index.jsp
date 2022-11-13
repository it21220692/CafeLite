<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>Menu</title>

<!-- CSS FILES -->
<link rel="preconnect" href="https://fonts.googleapis.com">

<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;600;700&display=swap"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">

<link href="<%=request.getContextPath()%>/css/bootstrap-icons.css" rel="stylesheet">

<link href="<%=request.getContextPath()%>css/tooplate-crispy-kitchen.css" rel="stylesheet">


</head>

<body>

	<nav class="navbar navbar-expand-lg bg-white shadow-lg">
		<div class="container">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<a class="navbar-brand" href="index.jsp"> Cafe Lite </a>



			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a>
					</li>

					<li class="nav-item"><a class="nav-link" href="about.html">Branch</a>
					</li>

					<li class="nav-item"><a class="nav-link active"
						href="menu.html">Menu</a></li>

					<li class="nav-item"><a class="nav-link" href="news.html">Cart</a>
					</li>

					<li class="nav-item"><a class="nav-link" href="contact.html">Oder</a>
					</li>
				</ul>
			</div>



		</div>
	</nav>

	<main>

		<header class="site-header site-menu-header">
			<div class="container">
				<div class="row">

					<div class="col-lg-10 col-12 mx-auto">
						<h1 class="text-white">Our Menus</h1>

						<strong class="text-white">Perfect for all Breakfast,
							Lunch and Dinner</strong>
					</div>

				</div>
			</div>

			<div class="overlay"></div>
		</header>

		<section class="menu section-padding">
			<div class="container">
				<div class="row">

					<div class="col-12">
						<h2 class="mb-lg-5 mb-4">Menu</h2>
					</div>
					
					<c:forEach var="menu" items="${listMenu}">
					<div class="col-lg-4 col-md-6 col-12">
						<div class="menu-thumb">
							<img src="data:image/jpeg;base64,${menu.image}"
								class="img-fluid menu-image" alt="" width=250>

							<div class="menu-info d-flex flex-wrap align-items-center">
								<h4 class="mb-0">${menu.foodName}</h4>

								<span class="price-tag bg-white shadow-lg ms-4"><small>Rs</small>${menu.price}</span>

								<div class="d-flex flex-wrap align-items-center w-100 mt-2">
									<p>
										<button>Add to Cart</button>
									</p>
								</div>
							</div>
						</div>
					</div>
					</c:forEach>

				</div>
			</div>
		</section>

	</main>



</body>
</html>
