package storage;


import lombok.Getter;

import model.Lesson;
import model.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class StudentStorage {

    private Student[] array  = new Student[10];

    @Getter
    private int size = 0;

    public void add(Student student){
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = student;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println("Index " + i + ". " + array[i]);
        }
    }

    public void increaseArray(){
        Student[] temp  = new Student[array.length +10];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }

    public void deleteByIndex(int index){
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
        } else {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }

    public Student getStudentByIndex(int index){
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
        }
        return array[index];
    }

    public void printStudentsByLesson(Lesson lessonName){
        for (int i = 0 ; i < size; i++) {
            if (array[i].getLesson().equals(lessonName)) {
                System.out.println(array[i]);
            }
        }
    }

    public void writeStudentsToExcel(String fileDir) throws IOException {
        File directory = new File(fileDir);
        if (directory.isFile()) {
            throw new RuntimeException("File directory must be a directory");
        }
        File excelFile = new File(directory, "Students_" + System.currentTimeMillis() + ".xlsx");
        excelFile.createNewFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Surname");
        row.createCell(2).setCellValue("Age");
        row.createCell(3).setCellValue("City");
        row.createCell(4).setCellValue("Phone Number");
        row.createCell(5).setCellValue("Lesson");
        row.createCell(6).setCellValue("Registered user");
        row.createCell(7).setCellValue("Register Date");

        for (int i = 0; i < size; i++) {
            Student student = array[i];
            Row studentRow = sheet.createRow(i + 1);
            studentRow.createCell(0).setCellValue(student.getName());
            studentRow.createCell(1).setCellValue(student.getSurname());
            studentRow.createCell(2).setCellValue(student.getAge());
            studentRow.createCell(3).setCellValue(student.getCity());
            studentRow.createCell(4).setCellValue(student.getPhoneNumber());
            studentRow.createCell(5).setCellValue(student.getLesson().getName());

        }
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("Excel was created successfully.");
    }
}