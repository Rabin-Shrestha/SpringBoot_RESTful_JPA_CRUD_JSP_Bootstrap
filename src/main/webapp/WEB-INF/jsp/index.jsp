<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid bg-light myBorder">
    <form action="/student/search" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-2 pt-3">
                <div class="form-group ">
                    <input type="number" placeholder="By year" name="schoolYear" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="By campus" name="campus" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="By grade level" name="gradeLevel" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="text" placeholder="By name" name="name" class="form-control">
                </div>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary btn-block" target=" ">Search</button>
            </div>
        </div>
    </form>
</div>
<div><br></div>

<div class="container-fluid bg-light myBorder">
    <h4>Students Records:</h4>
    <table class="table table-bordered table-striped text-center">
        <thead>
        <tr class="text-center">
            <%--<th><b>Student Id</b></th>--%>
            <th class="text-center"><b>School Year</b></th>
            <th class="text-center"><b>Campus</b></th>
            <th class="text-center"><b>Entry Date</b></th>
            <th class="text-center"><b>Grade Level</b></th>
            <th class="text-center"><b>Name</b></th>
            <th class="text-center" colspan='2'><b> Operation</b></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="student">
            <tr>
                <%--<td><c:out value="${student.studentId}"></c:out></td>--%>
                <td><c:out value="${student.schoolYear}"></c:out></td>
                <td><c:out value="${student.campus}"></c:out></td>
                <td><c:out value="${student.entryDate}"></c:out></td>
                <td><c:out value="${student.gradeLevel}"></c:out></td>
                <td><c:out value="${student.name}"></c:out></td>
                <td>
                    <a href="/student/edit?studentId=${student.studentId}">
                        <button type="submit" class="btn btn-primary">Edit Student</button>
                    </a>

                    <a href="/student/delete?studentId=${student.studentId}">
                        <button type="submit" class="btn btn-danger"
                                onclick="alert(' !! Warning !! Student record will be deleted.')">Delete Student
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</div>
<div><br></div>
<jsp:include page="footer.jsp"></jsp:include>