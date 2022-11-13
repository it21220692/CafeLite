<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>CafeLite Admin</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="" class="navbar-brand"> Menu
     Management </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/admin/list" class="nav-link">Menu</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Menu</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/admin/new" class="btn btn-success">Add
     New Item</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>menuID</th>
                                <th>foodName</th>
                                <th>foodCategory</th>
                                <th>price</th>
                                <th>availability</th>
                                <th>quantity</th>
                                <th>image</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="menu" items="${listMenu}">

                                <tr>
                                    <td>
                                        <c:out value="${menu.menuID}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.foodName}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.foodCategory}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.price}" />
                                    </td>
                                     <td>
                                        <c:out value="${menu.availability}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.quantity}" />
                                    </td>
                                    <td>
                                        <img src="data:image/jpeg;base64,${menu.image}" width=100 />
                                    </td>
                                    <td>
                                    <a href="/admin/edit?menuID=<c:out value='${menu.menuID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="/admin/delete?menuID=<c:out value='${menu.menuID}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>