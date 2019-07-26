package com.example.SpringBootDemo.util;

import com.example.SpringBootDemo.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {
    public List<Student> loadStudentRecords(String fileName) throws IOException {
        List<Student> result = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:" + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            // Read first line
            String line = br.readLine();
            // Make sure file has correct headers
            if (line == null) throw new IllegalArgumentException("File is empty");

            if (!line.equals("SchoolYr,Campus,StudentID,EntryDate,GradeLevel,Name"))
                throw new IllegalArgumentException("File has wrong columns: " + line);

            // Run through following lines
            while ((line = br.readLine()) != null) {
                // Break line into entries using comma
                String[] items = line.split(",");
                try {
                    // If there are too many entries, throw a dummy exception, if
                    // there are too few, the same exception will be thrown later
                    if (items.length > 6) throw new ArrayIndexOutOfBoundsException();
                    // Convert data to Student record
                    Student student = new Student();

                    //SchoolYr,Campus,StudentID,EntryDate,GradeLevel,Name
                    student.setSchoolYear(Integer.valueOf(items[0]));
                    student.setCampus(Integer.valueOf(items[1]));
                    student.setStudentId(Long.valueOf(items[2]));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    Date entryDate = Date.valueOf(LocalDate.parse(items[3], formatter));

                    student.setEntryDate(entryDate);
                    student.setGradeLevel(Integer.valueOf(items[4]));
                    student.setName(items[5]);
                    result.add(student);
                } catch (Exception e) {
                    // Caught errors indicate a problem with data format -> Print warning and continue
                    System.out.println("Invalid line: " + line);
                }
            }
            System.out.println("Student List" + result);
            return result;
        } finally {
            br.close();
        }
    }

}