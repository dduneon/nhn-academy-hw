<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <title>NHN Academy Shopping Mall</title>

</head>
<body>

<div class="mainContainer">

    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

                <a href="/"
                   class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
                </ul>

            </div>
        </div>
    </header>
</div>

<div class="album py-5 bg-light">
    <div class="container">
        <form class="row-12 row-lg-auto mb-3 mb-lg-0 me-lg-3" action="/category">
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">카테고리별 검색으로 이동</button>
        </form>
        <br>
        <br>
        <form class="row-12 row-lg-auto mb-3 mb-lg-0 me-lg-3" action="/search?${productName}">
            <input type="search" class="form-control form-control-dark" id="productName" name="name"
                   placeholder="검색할 상품을 입력하세요.." aria-label="Search">
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">검색</button>
        </form>
        <br>
        <div class="row row-cols-1 row-cols-sm-3 g-3">
            <c:forEach var="item" items="${PAGE_CONTENT.getContent()}">
                <div class="col">
                    <a href="<c:url value="/detail/${item.getId()}"/>">
                        <div class="card shadow-sm">
                            <div class="text-center">
                                <img src="${item.getImage()}" alt="${item.getId()}"
                                     width="100%"
                                     height="300" role="img">
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <itemtitle class="card-text">${item.getName()}</itemtitle>
                                    <description>${item.getDescription()}</description>
                                    <price><fmt:formatNumber value="${item.getUnitCost()}" type="number"
                                                             maxFractionDigits="3"/>원
                                    </price>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>

        <c:set var="current_page" value="${PAGE_CONTENT.getPageable().getPageNumber()}"/>

        <nav aria-label="Page navigation" style="padding-top: 20px">
            <ul class="pagination justify-content-center">
                <c:choose>
                <c:when test="${PAGE_CONTENT.isFirst()}">
                <li class="page-item disabled">
                    </c:when>
                    <c:otherwise>
                <li class="page-item">
                    </c:otherwise>
                    </c:choose>
                    <a class="page-link" href="?page=${current_page > 0 ? current_page - 1 : 0}" tabindex="-1">Previous
                    </a>
                </li>
                <c:forEach var="item" begin="${NAVIGATION_START_POS}" end="${NAVIGATION_END_POS}">
                    <c:choose>
                        <c:when test="${item eq current_page}">
                            <li class="page-item active" aria-current="page">
                                <span class="page-link">${item}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="?name=${NAME}&page=${item}">${item}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                <c:when test="${PAGE_CONTENT.isLast()}">
                    <li class="page-item disabled">
                        </c:when>
                    <c:otherwise>
                <li class="page-item">
                    </c:otherwise>
                    </c:choose>
                    <a class="page-link" href="?page=${current_page + 1}" tabindex="-1">Next</a>
                </li>
            </ul>
        </nav>

    </div>
</div>
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