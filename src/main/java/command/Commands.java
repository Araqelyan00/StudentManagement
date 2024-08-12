package command;

public interface Commands {

    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENTS = 2;
    int PRINT_STUDENTS_BY_LESSON = 3;
    int PRINT_STUDENTS_COUNT = 4;
    int PRINT_ALL_LESSONS = 5;
    int CHANGE_STUDENT_LESSON = 6;
    int DELETE_STUDENT_BY_INDEX = 7;
    int ADD_LESSON = 8;
    int DOWNLOAD_STUDENT_EXCEL = 9;


    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printAdminCommands(){
        System.out.println("Please input " + LOGOUT + " to log out.");
        System.out.println("Please input " + ADD_STUDENT + " to add a Student.");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " to print all Students.");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " to print students by lesson.");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " to print students count.");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " to print all lessons.");
        System.out.println("Please input " + CHANGE_STUDENT_LESSON + " to change student's lesson.");
        System.out.println("Please input " + DELETE_STUDENT_BY_INDEX + " to delete a Student by index.");
        System.out.println("Please input " + ADD_LESSON + " to add a lesson.");
        System.out.println("Please input " + DOWNLOAD_STUDENT_EXCEL + " to download student excel.");
        System.out.print("Please input your choice here : ");
    }

    static void printUserCommands(){
        System.out.println("Please input " + LOGOUT + " to log out.");
        System.out.println("Please input " + ADD_STUDENT + " to add a Student.");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " to print all Students.");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " to print students by lesson.");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " to print students count.");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " to print all lessons.");
        System.out.println("Please input " + DOWNLOAD_STUDENT_EXCEL + " to download student excel.");
        System.out.print("Please input your choice here : ");
    }

    static void printLoginCommands(){
        System.out.println("Please input " + EXIT + " to exit.");
        System.out.println("Please input " + LOGIN + " to login.");
        System.out.println("Please input " + REGISTER + " to register.");
        System.out.print("Please input your choice here : ");
    }

}
