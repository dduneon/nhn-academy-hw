<%--
  Created by IntelliJ IDEA.
  User: nhn
  Date: 2023/11/08
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row row-cols-1 row-cols-sm-4 g-3">
    <c:forEach var="item" items="${requestScope.PAGE_CONTENTS.getContent()}">
        <div class="col">
            <div class="card shadow-sm">
                <img src="${item.getProductImage()}" alt="${item.getProductId()}" width="100%"
                     height="300" role="img">

                <div class="card-body">
                    <itemtitle class="card-text">${item.getModelName()}</itemtitle>
                    <description>${item.getDescription()}</description>
                    <price><fmt:formatNumber value="${item.getUnitCost()}" type="number"
                                             maxFractionDigits="3"/>원
                    </price>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-outline-secondary">View
                            </button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">Edit
                            </button>
                        </div>
                        <small class="text-muted">9 mins</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>
        <!-- todo -->
        <c:forEach var="item" begin="1" end="${}">
            <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
        </c:forEach>
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Next</a>
        </li>
    </ul>
</nav>

<style>
  itemtitle {
    font-weight: bold;
  }

  description {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  price {
    font-weight: bold;
  }

</style>