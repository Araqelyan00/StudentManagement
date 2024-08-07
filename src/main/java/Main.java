import command.Commands;
import model.Lesson;
import model.Student;
import model.User;
import model.UserType;
import storage.LessonStorage;
import storage.StudentStorage;
import storage.UserStorage;

import java.util.Date;
import java.util.Scanner;
import static util.DateUtil.stringToDate;

public class Main implements Commands {

    private static Scanner sc = new Scanner(System.in);
    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();
        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command !");
            }
        }
    }

    private static void initData() {
        User admin = new User("Admin", "Adminyan" , "Admin@gmail.com", "admin", UserType.ADMIN);
        User user = new User("User", "Useryan" , "User@gmail.com", "user", UserType.USER);
        userStorage.add(admin);
        userStorage.add(user);
        Lesson java = new Lesson("Java", "Asatur", 90, 50.0, stringToDate("07/08/2024"));
        lessonStorage.add(java);
        studentStorage.add(new Student("Aram", "Araqelyan", 23, "095500003", "Vanadzor", java, admin, new Date()));
        studentStorage.add(new Student("Saqo", "Sargsyan", 27, "099887766", "Erevan", java, admin, new Date()));
    }

    private static void login(){
        System.out.print("Please enter your email :");
        String email = sc.nextLine().trim();
        User user = userStorage.getUserByEmail(email);
        if (user == null) {
            System.out.println("Invalid email !");
        }else {
        System.out.print("Please enter your email :");
        String password = sc.nextLine().trim();
            if (user.getPassword().equals(password)) {
                currentUser = user;
                if (user.getUserType() == UserType.ADMIN){
                    loginAdmin();
                } else if (user.getUserType() == UserType.USER){
                    loginUser();
                }
            } else {
                System.out.println("Wrong password !");
            }
        }
    }

    private static void register(){
        System.out.print("Please enter your name :");
        String name = sc.nextLine().trim();
        System.out.print("Please enter your surname :");
        String surname = sc.nextLine().trim();
        System.out.print("Please enter your email :");
        String email = sc.nextLine().trim();
        if (userStorage.getUserByEmail(email) != null){
            System.out.println("User already exists !");
        } else {
        System.out.print("Please enter your password :");
        String password = sc.nextLine().trim();
        System.out.print("Please enter your user type (0:User or 1:Admin) :");
        int userType;
        try {
            userType = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            userType = -1;
        }
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            if (userType == 0){
                user.setUserType(UserType.USER);
            } else if (userType == 1){
                user.setUserType(UserType.ADMIN);
            } else {
                System.out.println("Invalid user type !");
            }
            userStorage.add(user);
            System.out.println("User successfully registered.");
        }
    }

    private static void loginAdmin(){
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    createAndAddNewStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    printAllStudents();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentsByLesson();
                    break;
                case PRINT_STUDENTS_COUNT:
                    printStudentsCount();
                    break;
                case PRINT_ALL_LESSONS:
                    printAllLessons();
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteByIndex();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeStudentsLesson();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;

            }
        }
    }

    private static void loginUser(){
        boolean run = true;
        while (run) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_STUDENT:
                    createAndAddNewStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    printAllStudents();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentsByLesson();
                    break;
                case PRINT_STUDENTS_COUNT:
                    printStudentsCount();
                    break;
                case PRINT_ALL_LESSONS:
                    printAllLessons();
                    break;
            }
        }
    }

    private static void createAndAddNewStudent(){
        System.out.print("Please enter student's name :");
        String name = sc.nextLine().trim();
        System.out.print("Please enter student's surname :");
        String surname = sc.nextLine().trim();
        System.out.print("Please enter student's age :");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Please enter student's phone number :");
        String phone = sc.nextLine().trim();
        System.out.print("Please enter student's city :");
        String city = sc.nextLine().trim();
        System.out.print("Please enter student's lesson :");
        String lesson = sc.nextLine().trim();
    }


}