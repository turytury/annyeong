<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Annyeong</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="list.html">Annyeong</a>
            </div>
            <div class="collapse navbar-collapse">
                <div class="collapse navbar-collapse navbar-right">
                    <a href="list.html"><button type="button" class="btn btn-default navbar-btn">List Page</button></a>
                </div>
            </div>
      </div>
    </nav>

    <div class="container">
        <c:if test="${updatemessage!=null}">
            <div class="alert alert-success">
              <strong>Success!</strong> ${updatemessage}
            </div>
        </c:if>
        <form:form name="saveProduct" commandName="product" action="save.html?id=${product.id}" method="post">
            <div class="form-group">
                <label>Product Name</label>
                <form:input type="text" class="form-control" path="name" id="name" placeholder="Product Name"/>
            </div>

            <div class="form-group">
                <label>Price</label>
                <form:input type="text" class="form-control" path="price" id="price" placeholder="Price"/>
            </div>

            <div class="form-group">
                <label>Category</label>
                <form:select class="form-control" path="category.id" id="categoryId">
                    <form:option value="0">Please select</form:option>
                    <c:forEach var="parent" items="${categoryMap}">
                        <form:option value="${parent.key.id}" disabled="true">${parent.key.name}</form:option>
                        <c:choose>
                            <c:when test="${!empty parent.value}">
                                <c:forEach var="category" items="${parent.value}">
                                    <form:option value="${category.id}" style="margin-left:10px;">${category.name}</form:option>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>

            <input type="submit" id="btn_search" value="Submit" class="btn btn-info" />

        </form:form>
    </div>
</body>
</html>