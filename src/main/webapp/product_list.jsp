<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Annyeong</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script>
        function submitPagination(formId, page) {
            document.getElementById('paginationPage').value = page;
            document.getElementById(formId).submit();
        }
        function enterKeyPress(e, formId, page){
            if (typeof e == 'undefined' && window.event) {
                e = window.event;
            }
            if (e.keyCode == 13){
                submitPagination(formId, page);
            }
        }
    </script>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="list.html">Annyeong</a>
            </div>
            <div class="collapse navbar-collapse">
                <div class="collapse navbar-collapse navbar-right">
                    <a href="create.html"><button type="button" class="btn btn-default navbar-btn">Create New Product</button></a>
                </div>
            </div>
      </div>
    </nav>

    <div class="container-fluid">
        <c:if test="${updatemessage!=null}">
            <div class="alert alert-success">
              <strong>Success!</strong> ${updatemessage}
            </div>
        </c:if>
        <div class="row">
            <div class="col-md-2">
                <!-- SEARCH -->
                <form:form name="searchProduct" commandName="productSearchForm" action="list.html" method="get">
                    <div class="form-group">
                        <label>Product Name</label>
                        <form:input type="text" class="form-control" path="name" id="name" placeholder="Product Name"/>
                    </div>

                    <div class="form-group">
                        <label>Category</label>
                        <form:select class="form-control" path="categoryId" id="categoryId">
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

                    <div class="form-group">
                        <label>Price</label>
                        <div class="row">
                            <div class="col-xs-6">
                                <form:input type="text" class="form-control" path="priceFrom" id="priceFrom"/>
                            </div>
                            <div class="col-xs-1" style="margin: 5px -20px 0px -20px;">
                                to
                            </div>
                            <div class="col-xs-6">
                                <form:input type="text" class="form-control" path="priceTo" id="priceTo"/>
                            </div>
                        </div>
                    </div>

                    <input type="submit" id="btn_search" value="Search" class="btn btn-info" />

                </form:form>
            </div>
            <div class="col-md-10">
                <!-- CONTENT -->
                <c:choose>
                    <c:when test="${!empty productList}">
                        <table id="tbl_product" class="table table-striped table-hover" >
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Create Date</th>
                                    <th>Category</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${productList}">
                                    <tr id="row_product_${product.id}">
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td>${product.createDateDisplay}</td>
                                        <td>${product.categoryParent} / ${product.category}</td>
                                        <td>
                                            <a href="edit.html?id=${product.id}"><button type="button" class="btn btn-default" aria-label="Edit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button></a>
                                            <a href="delete.html?id=${product.id}"><button type="button" class="btn btn-default" aria-label="Delete" onclick="return confirm('Are you sure to delete?')"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-7">
                                <c:if test="${productSearchForm.lastPage > 1}">
                                    <form id="frmPagination" method="GET">
                                        <input type="hidden" name="paginationPage" id="paginationPage" value="${productSearchForm.currentPage}" />
                                        <input type="hidden" name="name" value="${productSearchForm.name}" />
                                        <input type="hidden" name="categoryId" value="${productSearchForm.categoryId}" />
                                        <input type="hidden" name="priceFrom" value="${productSearchForm.priceFrom}" />
                                        <input type="hidden" name="priceTo" value="${productSearchForm.priceTo}" />
                                        <a class="btn_gray" id="btn_previouspage" href="javascript:void(0);" onclick="submitPagination('frmPagination', '${productSearchForm.previousPage}');">&#171; Previous Page</a>&#160;&#160;
                                        Show Page
                                        &#160;&#160;:&#160;&#160;
                                        <input type="text" id="txtPaginationPage" style="width: 50px;" onkeypress="enterKeyPress(event, 'frmPagination', document.getElementById('txtPaginationPage').value);" value="${productSearchForm.currentPage}" />
                                        &#160;&#160;/&#160;&#160; ${productSearchForm.lastPage}&#160;&#160;
                                        <a class="btn_gray" id="btn_gotopage" href="javascript:void(0);" onclick="submitPagination('frmPagination',  document.getElementById('txtPaginationPage').value);">GO</a>&#160;&#160;
                                        <a class="btn_gray" id="btn_nextpage" href="javascript:void(0);" onclick="submitPagination('frmPagination', '${productSearchForm.nextPage}');">Next Page &#187;</a>
                                    </form>
                                </c:if>
                            </div>
                            <div class="col-md-2" style="float: right;">
                                <span>Found ${productSearchForm.totalSize} record(s)</span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-info">
                          <strong>No Product Found!
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>