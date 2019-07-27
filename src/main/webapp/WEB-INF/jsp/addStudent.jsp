<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container myBorder">
    <form action='/student/add' method='post' modelAttribute="addStudent">
        <h4> Add New Student Record:</h4>
        <table class='table table-hover table-responsive table-bordered rounded-lg'>
            <tr>
                <td><b>School Year</b></td>
                <td><input type='number' name='schoolYear' class='form-control' min="1950" max="2020" required
                           value="${student.schoolYear}"/></td>
            </tr>
            <tr>
                <td><b>Campus</b></td>
                <td><input type='number' name='campus' class='form-control' required value="${student.campus}"/></td>
            </tr>
            <tr>
                <td><b>EntryDate</b></td>
                <td><input type='date' name='entryDate' class='form-control' required size="20"
                           value="${student.entryDate}"/></td>

            </tr>
            <tr>
                <td><b>gradeLevel</b></td>
                <td><input type='number' name='gradeLevel' class='form-control' required min="1" max="20" size="20"
                           value="${student.gradeLevel}"/></td>

            </tr>
            <tr>
                <td><b>Name</b></td>
                <td><input type='text' name='name' class='form-control' size="50" required value="${student.name}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" class="btn btn-primary">Save User Information</button>
                </td>
            </tr>

        </table>
    </form>
</div>

<jsp:include page="footer.jsp"></jsp:include>