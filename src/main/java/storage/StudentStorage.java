package storage;


import lombok.Getter;

import model.Student;

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
            System.out.println(i + ". " + array[i]);
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

    public void printStudentsByLesson(String lessonName){
        for (int i = 0 ; i < size; i++) {
            if (array[i].getLesson().equals(lessonName)) {
                System.out.println(array[i]);
            }
        }
    }
}
