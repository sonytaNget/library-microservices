<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${msg} a Book</title>
</head>

<body>
    <c:if test="${msg == 'Update'}">

        <form:form modelAttribute="book" action="../books/${book.id}" method="post">
            <form:errors path="*"  element="div" />
            <table>
                <tr>
                    <td>Title:</td>
                    <td><input  type="text" name="title" value="${book.title}" /> </td>
                </tr>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" value="${book.author}" /> </td>
                </tr>
                <tr>
                    <td>Isbn:</td>
                    <td><input type="text" name="ISBN" value="${book.ISBN}" /> </td>
                </tr>
                <tr>
                    <td>price:</td>
                    <td><input type="text" name="price" value="${book.price}" /> </td>
                </tr>
            </table>
            <input type="submit" value="${msg}"/>
        </form:form>

    </c:if>
    <c:if test="${msg == 'Add'}">
        <form:form modelAttribute="book" action="../books" method="post" >
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table>
                <tr>
                    <td>Title:</td>
                    <td><input  type="text" name="title" value="${book.title}" /> </td>
                </tr>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" value="${book.author}" /> </td>
                </tr>
                <tr>
                    <td>Isbn:</td>
                    <td><input type="text" name="ISBN" value="${book.ISBN}" /> </td>
                </tr>
                <tr>
                    <td>price:</td>
                    <td><input type="text" name="price" value="${book.price}" /> </td>
                </tr>
            </table>
            <input type="submit" value="${msg}"/>
        </form:form>
    </c:if>

    <c:if test="${msg == 'Update'}">
        <form:form action="delete?bookId=${book.id}" method="post">
            <button type="submit">Delete</button>
        </form:form>
    </c:if>

</body>
</html>