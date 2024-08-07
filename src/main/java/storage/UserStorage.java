package storage;

import lombok.Getter;
import model.User;

public class UserStorage {
    private User[] array  = new User[10];

    @Getter
    private int size = 0;

    public void add(User user){
        if (size == array.length) {
            increaseArray();
        }
        array[size++] = user;
    }

    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public void increaseArray(){
        User[] temp  = new User[array.length +10];
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

    public User getUserByIndex(int index){
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
        }
        return array[index];
    }

    public User getUserByEmail(String email){
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }


}
