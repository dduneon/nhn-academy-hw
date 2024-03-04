<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>카테고리별 검색</title>
</head>
<body>

<h1>@Query(JPQL)와 Querydsl을 사용한 카테고리별 검색</h1>

<h2>@Query(JPQL)을 사용한 카테고리별 상품 검색기능</h2>
<form action="/category/search" method="post">
    <select id="category" name="category">
        <c:forEach var="category" items="${ALL_CATEGORY}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Search">
</form>

<br>

<h2>Querydsl을 사용한 다중 카테고리 상품 검색기능</h2>

<form action="/category/multisearch" method="post">
    <c:forEach var="category" items="${ALL_CATEGORY}">
        <input type="checkbox" id="category_${category.id}" name="category" value="${category.id}">
        <label for="category_${category.id}">${category.name}</label>
    </c:forEach>

    <br>

    <input type="submit" value="Search">
</form>

<!-- Display search results here, if any -->
<c:if test="${not empty searchResults}">
    <h2>Search Results</h2>
    <ul>
        <c:forEach var="result" items="${searchResults}">
            <li>상품 아이디 : ${result.name}</li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${not empty multiSearchResults}">
<h2>Search Results</h2>
<ul>
    <c:forEach var="result" items="${multiSearchResults}">
        <li>카테고리 아이디 : ${result.pk.categoryId}, 상품 아이디 : ${result.pk.productId}</li>
    </c:forEach>
</ul>
</c:if>

</body>
</html>
