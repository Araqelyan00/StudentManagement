import command.Commands;
import exception.LessonNotFoundException;
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

    private static final Scanner sc = new Scanner(System.in);
    private static final LessonStorage lessonStorage = new LessonStorage();
    private static final StudentStorage studentStorage = new StudentStorage();
    private static final UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();
        boolean run = true;
        while (run) {
            System.out.println("\n  Main menu");
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
        User admin = new User("Admin", "Adminyan", "Admin@gmail.com", "admin", UserType.ADMIN);
        User user = new User("User", "Useryan", "User@gmail.com", "user", UserType.USER);
        userStorage.add(admin);
        userStorage.add(user);
        Lesson java = new Lesson("Java", "Asatur", 90, 50.0, stringToDate("07/08/2024"));
        lessonStorage.add(java);
        studentStorage.add(new Student("Aram", "Araqelyan", 23, "095500003", "Vanadzor", java, admin, new Date()));
        studentStorage.add(new Student("Saqo", "Sargsyan", 27, "099887766", "Erevan", java, admin, new Date()));
    }

    private static void login() {
        System.out.println("\nLogin form :");
        System.out.print("Please enter your email :");
        String email = sc.nextLine().trim();
        User user = userStorage.getUserByEmail(email);
        if (user == null) {
            System.out.println("Invalid email !");
        } else {
            System.out.print("Please enter password :");
            String password = sc.nextLine().trim();
            if (user.getPassword().equals(password)) {
                currentUser = user;
                if (user.getUserType() == UserType.ADMIN) {
                    loginAdmin();
                } else if (user.getUserType() == UserType.USER) {
                    loginUser();
                }
            } else {
                System.out.println("Wrong password !");
            }
        }
    }

    private static void register() {
        System.out.println("\nRegistration form :");
        System.out.print("Please enter your name :");
        String name = sc.nextLine().trim();
        System.out.print("Please enter your surname :");
        String surname = sc.nextLine().trim();
        System.out.print("Please enter your email :");
        String email = sc.nextLine().trim();
        if (userStorage.getUserByEmail(email) != null) {
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
            if (userType == 0) {
                user.setUserType(UserType.USER);
            } else if (userType == 1) {
                user.setUserType(UserType.ADMIN);
            } else {
                System.out.println("Invalid user type !");
            }
            userStorage.add(user);
            System.out.println("\nUser successfully registered.");
        }
    }

    private static void loginAdmin() {
        boolean run = true;
        while (run) {
            System.out.println("\nYou Are Welcome " + currentUser.getName());
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
                    deleteStudentByIndex();
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

    private static void loginUser() {
        boolean run = true;
        while (run) {
            System.out.println("\nYou Are Welcome " + currentUser.getName());
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

    private static void createAndAddNewStudent() {
        System.out.println("\nLessons list which you can choose for student :");
        printAllLessons();
        System.out.println("\nCreate a new student :");
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
        String lessonName = sc.nextLine().trim();
        if (lessonStorage.getLessonByName(lessonName) == null) {
            System.out.println("\nLesson with " + lessonName + " doesn't exists !");
        } else {
            Lesson lesson = lessonStorage.getLessonByName(lessonName);

            Student student = new Student();

            student.setName(name);
            student.setSurname(surname);
            student.setAge(age);
            student.setPhoneNumber(phone);
            student.setCity(city);
            student.setLesson(lesson);
            student.setRegisteredUser(currentUser);
            student.setRegisterDate(new Date());

            studentStorage.add(student);

            System.out.println("\nStudent successfully created!");
        }
    }

    private static void printAllStudents() {
        System.out.println("\nStudents list :");
        studentStorage.print();
    }

    private static void printStudentsByLesson() {
        System.out.println("\nFilter students by lesson name :");
        System.out.print("Please enter lesson name :");
        String lessonName = sc.nextLine().trim();
        if (lessonStorage.getLessonByName(lessonName) == null) {
            System.out.println("\nLesson with " + lessonName + " doesn't exists !");
        } else {

            studentStorage.printStudentsByLesson(lessonStorage.getLessonByName(lessonName));
        }
    }

    private static void printStudentsCount() {
        System.out.println("\nWe have " + studentStorage.getSize() + " students in the database.");
    }

    private static void printAllLessons() {
        lessonStorage.print();
    }

    private static void deleteStudentByIndex() {
        System.out.println("\nStudents list :");
        printAllStudents();
        System.out.print("Please enter student's index :");
        int studentIndex;
        try {
            studentIndex = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            studentIndex = -1;
        }
        if (studentStorage.getStudentByIndex(studentIndex) == null) {
            System.out.println("\nStudent doesn't exists !");
        } else {
            studentStorage.deleteByIndex(studentIndex);
            System.out.println("\nStudent successfully deleted!");
        }
    }

    private static void changeStudentsLesson() {
        System.out.println("\nStudents list :");
        printAllStudents();
        System.out.print("Please enter student's index :");
        int studentIndex;
        try {
            studentIndex = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            studentIndex = -1;
        }
        if (studentStorage.getStudentByIndex(studentIndex) == null) {
            System.out.println("\nStudent doesn't exists !");
        } else {
            System.out.println("\nLessons list :");
            lessonStorage.print();
            System.out.println("Please enter lesson index :");
            int lessonIndex;
            try {
                lessonIndex = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                lessonIndex = -1;
            }
            try {
                if (lessonStorage.getLessonByIndex(lessonIndex) == null) {
                    System.out.println("\nLesson doesn't exists !");
                } else {
                    studentStorage.getStudentByIndex(studentIndex).setLesson(lessonStorage.getLessonByIndex(lessonIndex));
                    System.out.println("\nLesson successfully changed!");
                }
            } catch (LessonNotFoundException e) {
                System.out.println("\nLesson does not exists !");
            }
        }
    }

    private static void addLesson(){
        System.out.println("\nAdding new lesson:");
        System.out.print("Please enter lesson's name :");
        String lessonName = sc.nextLine().trim();
        if (lessonStorage.getLessonByName(lessonName) != null) {
            System.out.println("\nLesson already exists !");
        } else {
            System.out.print("Please enter teacher's name :");
            String teacherName = sc.nextLine().trim();
            System.out.print("Please enter duration in minutes :");
            int durationInMinutes = Integer.parseInt(sc.nextLine().trim());
            System.out.println("Please enter lesson's price :");
            double lessonPrice = Double.parseDouble(sc.nextLine().trim());
            System.out.println("Please enter lesson's start date(dd/MM/yyyy) :");
            String startDateStr = sc.nextLine().trim();
            lessonStorage.add(new Lesson(lessonName, teacherName, durationInMinutes, lessonPrice, stringToDate(startDateStr)));
            System.out.println("\nLesson successfully added!");
        }
    }


}