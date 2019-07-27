package com.example.SpringBootDemo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Column(name = "studentId")
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long studentId;

    @Column(name = "school_year")
    private Integer schoolYear = null;

    @Column(name = "campus")
    private Integer campus = null;

    @Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date entryDate;

    @Column(name = "grade_level")
    private Integer gradeLevel = null;

    @Column(name = "name")
    private String name=null;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", schoolYear=" + schoolYear +
                ", campus=" + campus +
                ", entryDate=" + entryDate +
                ", gradeLevel=" + gradeLevel +
                ", name='" + name + '\'' +
                '}';
    }
    public Student( Integer schoolYear, Integer campus, Date entryDate, Integer gradeLevel, String name) {
        this.schoolYear = schoolYear;
        this.campus = campus;
        this.entryDate = entryDate;
        this.gradeLevel = gradeLevel;
        this.name = name;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getCampus() {
        return campus;
    }

    public void setCampus(Integer campus) {
        this.campus = campus;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
