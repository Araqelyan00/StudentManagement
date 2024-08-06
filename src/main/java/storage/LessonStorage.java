package storage;

import exception.LessonNotFoundException;
import lombok.Getter;
import model.Lesson;

public class LessonStorage {
    private Lesson[] array = new Lesson[10];
    @Getter
    private int size = 0;

    public void add(Lesson lesson){
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = lesson;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public void increaseArray(){
        Lesson[] temp = new Lesson[array.length + 10];
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

    public Lesson getLessonByIndex(int index) throws LessonNotFoundException {
        if (index < 0 || index >= size) {
            throw new LessonNotFoundException("Lesson with " + index + "doesn't exist.");
        }
        return array[index];
    }

}

