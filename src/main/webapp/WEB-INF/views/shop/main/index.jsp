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

<!-- 카테고리 넣기 (카테고리별) -->
<div class="row row-cols-1 row-cols-sm-4 g-3">
    <c:forEach var="item" items="${requestScope.PAGE_CONTENTS.getContent()}">
        <div class="col">
            <!-- -->
            <a href="<c:url value="/product/detail.do?id=${item.getProductId()}"/>">
                <div class="card shadow-sm">
                    <div class="text-center">
                        <img src="${item.getProductImage()}" alt="${item.getProductId()}"
                             width="100%"
                             height="300" role="img">
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <itemtitle class="card-text">${item.getModelName()}</itemtitle>
                            <description>${item.getDescription()}</description>
                            <price><fmt:formatNumber value="${item.getUnitCost()}" type="number"
                                                     maxFractionDigits="3"/>원
                            </price>
                        </div>
                        <div class="d-flex justify-content-end align-items-center">
                            <!-- todo category -->
                            <small class="text-muted">Category</small>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </c:forEach>
</div>

<!-- todo 사이 여백 두기 -->

<nav aria-label="Page navigation" style="padding-top: 20px">
    <ul class="pagination justify-content-center">
        <c:choose>
        <c:when test="${requestScope.PAGE_CONTENTS.getCurrentIndex() eq 1}">
        <li class="page-item disabled">
            </c:when>
            <c:otherwise>
        <li class="page-item">
            </c:otherwise>
            </c:choose>
            <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>
        <!-- todo index Link 넣기(최대 아이템 10개 해놓고 이동하도록)-->
        <c:forEach var="item" begin="1" end="${requestScope.PAGE_CONTENTS.getTotalPage()}">
            <c:choose>
                <c:when test="${item eq ((param.page == null) ? 1 : requestScope.PAGE_CONTENTS.getCurrentIndex())}">
                    <li class="page-item active" aria-current="page">
                        <span class="page-link">${item}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="?page=${item}">${item}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
        <c:when test="${requestScope.PAGE_CONTENTS.getCurrentIndex() eq requestScope.PAGE_CONTENTS.getTotalPage()}">
        <li class="page-item disabled">
            </c:when>
            <c:otherwise>
        <li class="page-item">
            </c:otherwise>
            </c:choose>
            <a class="page-link" href="#" tabindex="-1">Next</a>
        </li>
    </ul>
</nav>

<style>
  itemtitle {
    font-weight: bold;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  description {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  price {
    font-weight: bold;
  }

  a {
    text-decoration: none;
    color: black
  }

  .card:hover {
    background-color: rgb(224, 235, 235);
  }

</style>