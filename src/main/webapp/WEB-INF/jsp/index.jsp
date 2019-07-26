<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid bg-light" style="border-radius: 25px;
                                               border: 2px solid #67AD24;
                                               padding: 20px;
                                               ">
    <form action="/student/search" method="post">
	<div class="row align-items-center justify-content-center">
            	        <div class="col-md-2 pt-3">
                           <div class="form-group ">
                              <input type="text" placeholder="Search by year" name="schoolYear" class="form-control">
                           </div>
                        </div>
                		<div class="col-md-2 pt-3">
                           <div class="form-group">
                              <input type="text" placeholder="Search by campus" name="campus" class="form-control">
                           </div>
                        </div>
                        <div class="col-md-2 pt-3">
                            <div class="form-group">
                            <input type="text" placeholder="Search by grade level" name="gradeLevel" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-2 pt-3">
                            <div class="form-group">
                            <input type="text" placeholder="Search by name" name="name" class="form-control">
                         </div>
                        </div>
                        <div class="col-md-2">
            	           <button type="submit" class="btn btn-primary btn-block" target=" ">Search</button>
            	        </div>
                	</div>
     </form>
</div>

<h4>Students Records:</h4>
<table class="table table-hover table-bordered" style="border-radius: 25px;border: 2px solid #67AD24;padding: 20px;">
    <thead>
      <tr>
        <th><b>Student Id</b></th>
        <th><b>School Year</b></th>
        <th><b>Campus</b></th>
        <th><b>Entry Date</b></th>
        <th><b>Grade Level</b></th>
        <th><b>Name</b></th>
        <th colspan='2'><b> Operation</b></th>
      </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="student">
      <tr>
        <td><c:out value="${student.studentId}"></c:out></td>
        <td><c:out value="${student.schoolYear}"></c:out></td>
        <td><c:out value="${student.campus}"></c:out></td>
        <td><c:out value="${student.entryDate}"></c:out></td>
        <td><c:out value="${student.gradeLevel}"></c:out></td>
        <td><c:out value="${student.name}"></c:out></td>
		<td>
        <a href="/student/edit?studentId=${student.studentId}">
        <button type="submit" class="btn btn-primary">Edit Student</button>
        </a>
        </td>
        <td>
           <a href="/student/delete?studentId=${student.studentId}">
           <button type="submit" class="btn btn-primary">Delete Student</button>
           </a>
      </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</div>

<jsp:include page="footer.jsp"></jsp:include>