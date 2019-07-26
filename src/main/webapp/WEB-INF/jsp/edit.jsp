<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
<form action='/student/edit/${student.studentId}' method='post' modelAttribute="editStudent">
 <h3> Edit Student Record</h3>
    <table class='table table-hover table-responsive table-bordered'>
        <tr>
            <td><b>School Year</b></td>
            <td><input type='text' name='schoolYear' class='form-control' value="${student.schoolYear}" /></td>
        </tr>
 
        <tr>
            <td><b>Campus</b></td>
            <td><input type='text' name='campus' class='form-control' value="${student.campus}" /></td>
        </tr>
 
        <tr>
            <td><b>EntryDate</b></td>
            <td><input type='text' name='entryDate' class='form-control' size="20" value="${student.entryDate}" /></td>
            
        </tr>
        <tr>
            <td><b>gradeLevel</b></td>
            <td><input type='text' name='gradeLevel' class='form-control' size="20" value="${student.gradeLevel}" /></td>

        </tr>
        <tr>
             <td><b>Name</b></td>
             <td><input type='text' name='name' class='form-control' size="20" value="${student.name}" /></td>

         </tr>
 			<input type='hidden' id='id' rows='5' class='form-control' name='id' value="${student.studentId}"/>
        <tr>
            <td colspan="2" >
                <button type="submit"class="btn btn-primary">Update User Information</button>
            </td>
        </tr>
 
    </table>
</form>


</div>

<jsp:include page="footer.jsp"></jsp:include>