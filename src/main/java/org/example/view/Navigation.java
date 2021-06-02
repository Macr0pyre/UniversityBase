package org.example.view;

import org.example.DAL.models.*;
import org.example.controllers.*;
import org.example.util.JsonConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Navigation implements View{
    private Scanner input = new Scanner(System.in);
    private final CreditController creditController = new CreditController();
    private final DBUserController userController = new DBUserController();
    private final DepartmentController departmentController = new DepartmentController();
    private final ExamController examController = new ExamController();
    private final FacultyController facultyController = new FacultyController();
    private final ScheduleController scheduleController = new ScheduleController();
    private final StudentController studentController = new StudentController();
    private final StudentGroupController studentGroupController = new StudentGroupController();
    private final SubjectController subjectController = new SubjectController();
    private final TeacherController teacherController = new TeacherController();
    private final UniversityController universityController = new UniversityController();

    @Override
    public void displayLoginPage() {
        boolean flag = true;

        while(true){
            System.out.println("Enter email:");
            String email = input.nextLine();
            System.out.println("Enter password:");
            String password = input.nextLine();

            boolean result = userController.checkLogin(email, password);

            if (result){
                System.out.println("Login is successful!");
                System.out.println();
                break;
            }
            else
                System.out.println("Error: wrong login or password");
        }

        displayMenuPage();
    }

    @Override
    public void displayMenuPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a MAIN MENU!");
            System.out.println("Select the table you want to work with:");
            System.out.println("1. Universities");
            System.out.println("2. Faculties");
            System.out.println("3. Departments");
            System.out.println("4. Teachers");
            System.out.println("5. Students");
            System.out.println("6. Groups");
            System.out.println("7. Schedule");
            System.out.println("8. Subjects");
            System.out.println("9. Exams");
            System.out.println("10. Credits");
            System.out.println("0. Exit");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayUniversityPage();
                    break;
                case (2):
                    displayFacultyPage();
                    break;
                case (3):
                    displayDepartmentPage();
                    break;
                case (4):
                    displayTeacherPage();
                    break;
                case (5):
                    displayStudentPage();
                    break;
                case (6):
                    displayStudentGroupPage();
                    break;
                case (7):
                    displaySchedulePage();
                    break;
                case (8):
                    displaySubjectPage();
                    break;
                case (9):
                    displayExamPage();
                    break;
                case (10):
                    displayCreditPage();
                    break;
                case (0):
                    System.out.println("Goodbye!");
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    @Override
    public void displayUniversityPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for University!");
            System.out.println("Select the option:");
            System.out.println("1. Add a university");
            System.out.println("2. Show all universities");
            System.out.println("3. Show university by id");
            System.out.println("4. Show university by name");
            System.out.println("5. Change university name");
            System.out.println("6. Delete university by id");
            System.out.println("7. Delete university by name");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddUniversityPage();
                    break;
                case (2):
                    displayAllUniversitiesPage();
                    break;
                case (3):
                    displayUniversityByIdPage();
                    break;
                case (4):
                    displayUniversityByNamePage();
                    break;
                case (5):
                    displayChangeUniversityNamePage();
                    break;
                case (6):
                    displayDeleteUniversityByIDPage();
                    break;
                case (7):
                    displayDeleteUniversityByNamePage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayDeleteUniversityByNamePage() {
        System.out.println("Enter name of an university to delete:");

        String name = input.nextLine();

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the university? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = universityController.deleteUniversity(name);
                    if (result) {
                        System.out.println("University successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid name.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayDeleteUniversityByIDPage() {
        System.out.println("Enter ID of an university to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the university? Print y or n:");

            String option = input.nextLine();
            
            switch (option){
                case("y"):
                    result = universityController.deleteUniversity(id);
                    if (result) {
                        System.out.println("University successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayChangeUniversityNamePage() {
        System.out.println("Enter ID of an university to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (universityController.getUniversityById(id) == null){
            System.out.println("Error: no university with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter a new name of the university:");

        String newName = input.nextLine();
        boolean result = universityController.updateUniversityName(id, newName);

        if (result) {
            System.out.println("University updated successfully.");
            System.out.println();
        }
        else {
            System.out.println("Error: university with such name already exists.");
            System.out.println();
        }
    }

    private void displayUniversityByNamePage() {
        System.out.println("Enter name:");

        String name = input.nextLine();
        University university = universityController.getUniversityByName(name);

        if (university != null) {
            System.out.println(JsonConverter.getConvertedToJson(university));
            System.out.println();
        }
        else {
            System.out.println("There is no university with this name.");
            System.out.println();
        }
    }

    private void displayUniversityByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        University university = universityController.getUniversityById(id);

        if (university != null) {
            System.out.println(JsonConverter.getConvertedToJson(university));
            System.out.println();
        }
        else {
            System.out.println("There is no university with this ID.");
            System.out.println();
        }
    }

    private void displayAllUniversitiesPage() {
        System.out.println("All universities:");
        List<University> universities = universityController.getAllUniversities();
        System.out.println(JsonConverter.getConvertedToJson(universities));
        System.out.println();
    }

    private void displayAddUniversityPage() {
        String name;

        System.out.println("Enter university name:");
        name = input.nextLine();

        boolean result = universityController.addUniversity(name);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("University successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayCreditPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Credit!");
            System.out.println("Select the option:");
            System.out.println("1. Add a credit");
            System.out.println("2. Show all credits");
            System.out.println("3. Show credit by id");
            System.out.println("4. Show credit by name");
            System.out.println("5. Edit credit");
            System.out.println("6. Delete credit by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddCreditPage();
                    break;
                case (2):
                    displayAllCreditsPage();
                    break;
                case (3):
                    displayCreditByIdPage();
                    break;
                case (4):
                    displayCreditByNamePage();
                    break;
                case (5):
                    displayEditCreditPage();
                    break;
                case (6):
                    displayDeleteCreditByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayDeleteCreditByIDPage() {
        System.out.println("Enter ID of the credit to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the credit? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = creditController.deleteCredit(id);
                    if (result) {
                        System.out.println("Credit successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayEditCreditPage() {
        System.out.println("Enter ID of a credit to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (creditController.getCreditById(id) == null){
            System.out.println("Error: no credit with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change credit name");
            System.out.println("2. Change credit teacher");
            System.out.println("3. Change credit subject");
            System.out.println("4. Change credit date");
            System.out.println("5. Change credit time");
            System.out.println("0. Back to the credits menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeCreditNamePage(id);
                    break;
                case(2):
                    displayChangeCreditTeacherPage(id);
                    break;
                case(3):
                    displayChangeCreditSubjectPage(id);
                    break;
                case(4):
                    displayChangeCreditDatePage(id);
                    break;
                case(5):
                    displayChangeCreditTimePage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeCreditTimePage(Long id) {
        System.out.println("Enter new time for the credit (hh:mm:ss):");

        String time = input.nextLine();
        creditController.updateCreditTime(id, time);
        System.out.println("Credit updated successfully");
        System.out.println();
    }

    private void displayChangeCreditDatePage(Long id) {
        System.out.println("Enter new date for the credit (YYYY-M-D):");

        String date = input.nextLine();
        creditController.updateCreditDate(id, date);
        System.out.println("Credit updated successfully");
        System.out.println();
    }

    private void displayChangeCreditSubjectPage(Long id) {
        System.out.println("Enter new subject ID for the credit:");

        Long subjectID = Long.parseLong(input.nextLine());
        creditController.updateCreditSubject(id, subjectID);
        System.out.println("Credit updated successfully");
        System.out.println();
    }

    private void displayChangeCreditTeacherPage(Long id) {
        System.out.println("Enter new teacher ID for the credit:");

        Long teacherID = Long.parseLong(input.nextLine());
        creditController.updateCreditTeacher(id, teacherID);
        System.out.println("Credit updated successfully");
        System.out.println();
    }

    private void displayChangeCreditNamePage(Long id) {
        System.out.println("Enter new credit name:");

        String name = input.nextLine();
        creditController.updateCreditName(id, name);
        System.out.println("Credit updated successfully");
        System.out.println();
    }

    private void displayCreditByNamePage() {
        System.out.println("Enter name:");

        String name = input.nextLine();
        Credit credit = creditController.getCreditByName(name);

        if (credit != null) {
            System.out.println(JsonConverter.getConvertedToJson(credit));
            System.out.println();
        }
        else {
            System.out.println("There is no credit with this name.");
            System.out.println();
        }
    }

    private void displayCreditByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Credit credit = creditController.getCreditById(id);

        if (credit != null) {
            System.out.println(JsonConverter.getConvertedToJson(credit));
            System.out.println();
        }
        else {
            System.out.println("There is no credit with this ID.");
            System.out.println();
        }
    }

    private void displayAllCreditsPage() {
        System.out.println("All credits:");
        List<Credit> credits = creditController.getAllCredits();
        System.out.println(JsonConverter.getConvertedToJson(credits));
        System.out.println();
    }

    private void displayAddCreditPage() {
        String name;
        Long teacherID;
        Long subjectID;
        String date;
        String time;

        System.out.println("Enter credit name:");
        name = input.nextLine();

        System.out.println("Enter teacher ID:");
        teacherID = Long.parseLong(input.nextLine());

        System.out.println("Enter subject ID:");
        subjectID = Long.parseLong(input.nextLine());

        System.out.println("Enter date of credit (YYYY-M-D):");
        date = input.nextLine();

        System.out.println("Enter time of credit (hh:mm:ss):");
        time = input.nextLine();

        boolean result = creditController.addCredit(name, teacherID, subjectID, date, time);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Credit successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayDepartmentPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Department!");
            System.out.println("Select the option:");
            System.out.println("1. Add a department");
            System.out.println("2. Show all departments");
            System.out.println("3. Show department by id");
            System.out.println("4. Show department by name");
            System.out.println("5. Change department name");
            System.out.println("6. Delete department by id");
            System.out.println("7. Add teacher to department");
            System.out.println("8. Remove teacher from department");
            System.out.println("9. Get list of teachers of the department");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddDepartmentPage();
                    break;
                case (2):
                    displayAllDepartmentsPage();
                    break;
                case (3):
                    displayDepartmentByIdPage();
                    break;
                case (4):
                    displayDepartmentByNamePage();
                    break;
                case (5):
                    displayChangeDepartmentNamePage();
                    break;
                case (6):
                    displayDeleteDepartmentByIDPage();
                    break;
                case (7):
                    displayAddTeacherToDepartmentPage();
                    break;
                case (8):
                    displayRemoveTeacherFromDepartmentPage();
                    break;
                case (9):
                    displayAllTeacherDepartmentsPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayRemoveTeacherFromDepartmentPage() {
        System.out.println("Enter ID of the department to delete a teacher:");

        Long id = Long.parseLong(input.nextLine());

        if (departmentController.getDepartmentById(id) == null){
            System.out.println("Error: no department with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter id of a teacher to delete:");

        Long teacherId = Long.parseLong(input.nextLine());
        boolean result = departmentController.removeTeacherFromDepartment(id, teacherId);

        if (result) {
            System.out.println("Teacher was deleted successfully.");
            System.out.println();
        }
        else {
            System.out.println("Error: invalid teacher id.");
            System.out.println();
        }
    }

    private void displayAllTeacherDepartmentsPage() {
        System.out.println("Enter department ID: ");

        Long departmentID = Long.parseLong(input.nextLine());

        System.out.println("All teachers in this department:");

        List<Teacher> teachers = new ArrayList<Teacher>(departmentController.getDepartmentTeachers(departmentID));
        System.out.println(JsonConverter.getConvertedToJson(teachers));
        System.out.println();
    }

    private void displayAddTeacherToDepartmentPage() {
        System.out.println("Enter ID of the department to add a teacher:");

        Long id = Long.parseLong(input.nextLine());

        if (departmentController.getDepartmentById(id) == null){
            System.out.println("Error: no department with such ID.");
            System.out.println();
            return;
        }
        
        System.out.println("Enter id of a teacher to add:");

        Long teacherId = Long.parseLong(input.nextLine());
        boolean result = departmentController.addTeacherToDepartment(id, teacherId);

        if (result) {
            System.out.println("Teacher was successfully added.");
            System.out.println();
        }
        else {
            System.out.println("Error: invalid teacher id.");
            System.out.println();
        }
    }

    private void displayDeleteDepartmentByIDPage() {
        System.out.println("Enter ID of the department to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the department? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = departmentController.deleteDepartment(id);
                    if (result) {
                        System.out.println("Department successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayChangeDepartmentNamePage() {
        System.out.println("Enter ID of a department to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (departmentController.getDepartmentById(id) == null){
            System.out.println("Error: no department with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter new department name:");

        String name = input.nextLine();
        departmentController.updateDepartmentName(id, name);
        System.out.println("Department updated successfully");
        System.out.println();
    }

    private void displayDepartmentByNamePage() {
        System.out.println("Enter name:");
        String name = input.nextLine();

        Department department = departmentController.getDepartmentByName(name);

        if (department != null) {
            System.out.println(JsonConverter.getConvertedToJson(department));
            System.out.println();
        }
        else {
            System.out.println("There is no department with this name.");
            System.out.println();
        }
    }

    private void displayDepartmentByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Department department = departmentController.getDepartmentById(id);

        if (department != null) {
            System.out.println(JsonConverter.getConvertedToJson(department));
            System.out.println();
        }
        else {
            System.out.println("There is no department with this ID.");
            System.out.println();
        }
    }

    private void displayAllDepartmentsPage() {
        System.out.println("All departments:");
        List<Department> departments = departmentController.getAllDepartments();
        System.out.println(JsonConverter.getConvertedToJson(departments));
        System.out.println();
    }

    private void displayAddDepartmentPage() {
        String name;
        Long facultyID;
        Long headTeacherID;

        System.out.println("Enter department name:");
        name = input.nextLine();

        System.out.println("Enter faculty ID:");
        facultyID = Long.parseLong(input.nextLine());

        System.out.println("Enter head ID:");
        headTeacherID = Long.parseLong(input.nextLine());

        boolean result = departmentController.addDepartment(name, facultyID, headTeacherID);

        if (!result) {
            System.out.println("Error. Invalid data.");
        }
        else {
            System.out.println("Department successfully added.");
        }
        System.out.println();
    }

    @Override
    public void displayExamPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Exam!");
            System.out.println("Select the option:");
            System.out.println("1. Add an exam");
            System.out.println("2. Show all exams");
            System.out.println("3. Show exam by id");
            System.out.println("4. Show exam by name");
            System.out.println("5. Edit exam");
            System.out.println("6. Delete exam by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddExamPage();
                    break;
                case (2):
                    displayAllExamsPage();
                    break;
                case (3):
                    displayExamByIdPage();
                    break;
                case (4):
                    displayExamByNamePage();
                    break;
                case (5):
                    displayEditExamPage();
                    break;
                case (6):
                    displayDeleteExamByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayDeleteExamByIDPage() {
        System.out.println("Enter ID of the exam to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the exam? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = examController.deleteExam(id);
                    if (result) {
                        System.out.println("Exam successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayEditExamPage() {
        System.out.println("Enter ID of an exam to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (examController.getExamById(id) == null){
            System.out.println("Error: no exam with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change exam name");
            System.out.println("2. Change exam teacher");
            System.out.println("3. Change exam subject");
            System.out.println("4. Change exam date");
            System.out.println("5. Change exam time");
            System.out.println("0. Back to the Exam menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeExamNamePage(id);
                    break;
                case(2):
                    displayChangeExamTeacherPage(id);
                    break;
                case(3):
                    displayChangeExamSubjectPage(id);
                    break;
                case(4):
                    displayChangeExamDatePage(id);
                    break;
                case(5):
                    displayChangeExamTimePage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeExamTimePage(Long id) {
        System.out.println("Enter new time for the exam (hh:mm:ss):");

        String time = input.nextLine();
        examController.updateExamTime(id, time);
        System.out.println("Exam updated successfully");
        System.out.println();
    }

    private void displayChangeExamDatePage(Long id) {
        System.out.println("Enter new date for the exam (YYYY-M-D):");

        String date = input.nextLine();
        examController.updateExamDate(id, date);
        System.out.println("Exam updated successfully");
        System.out.println();
    }

    private void displayChangeExamSubjectPage(Long id) {
        System.out.println("Enter new subject ID for the exam:");

        Long subjectID = Long.parseLong(input.nextLine());
        examController.updateExamSubject(id, subjectID);
        System.out.println("Exam updated successfully");
        System.out.println();
    }

    private void displayChangeExamTeacherPage(Long id) {
        System.out.println("Enter new teacher ID for the exam:");

        Long teacherID = Long.parseLong(input.nextLine());
        examController.updateExamTeacher(id, teacherID);
        System.out.println("Exam updated successfully");
        System.out.println();
    }

    private void displayChangeExamNamePage(Long id) {
        System.out.println("Enter new exam name:");

        String name = input.nextLine();
        examController.updateExamName(id, name);
        System.out.println("Exam updated successfully");
        System.out.println();
    }

    private void displayExamByNamePage() {
        System.out.println("Enter name:");
        String name = input.nextLine();

        Exam exam = examController.getExamByName(name);

        if (exam != null) {
            System.out.println(JsonConverter.getConvertedToJson(exam));
            System.out.println();
        }
        else {
            System.out.println("There is no exam with this name.");
            System.out.println();
        }
    }

    private void displayExamByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Exam exam = examController.getExamById(id);

        if (exam != null) {
            System.out.println(JsonConverter.getConvertedToJson(exam));
            System.out.println();
        }
        else {
            System.out.println("There is no exam with this ID.");
            System.out.println();
        }
    }

    private void displayAllExamsPage() {
        System.out.println("All exams:");
        List<Exam> exams = examController.getAllExams();
        System.out.println(JsonConverter.getConvertedToJson(exams));
        System.out.println();
    }

    private void displayAddExamPage() {
        String name;
        Long teacherID;
        Long subjectID;
        String date;
        String time;

        System.out.println("Enter exam name:");
        name = input.nextLine();

        System.out.println("Enter teacher ID:");
        teacherID = Long.parseLong(input.nextLine());

        System.out.println("Enter subject ID:");
        subjectID = Long.parseLong(input.nextLine());

        System.out.println("Enter date of exam (YYYY-M-D):");
        date = input.nextLine();

        System.out.println("Enter time of exam (hh:mm:ss):");
        time = input.nextLine();

        boolean result = examController.addExam(name, teacherID, subjectID, date, time);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Exam successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayFacultyPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Faculty!");
            System.out.println("Select the option:");
            System.out.println("1. Add a faculty");
            System.out.println("2. Show all faculties");
            System.out.println("3. Show faculty by id");
            System.out.println("4. Show faculty by name");
            System.out.println("5. Edit faculty");
            System.out.println("6. Delete faculty by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddFacultyPage();
                    break;
                case (2):
                    displayAllFacultiesPage();
                    break;
                case (3):
                    displayFacultyByIdPage();
                    break;
                case (4):
                    displayFacultyByNamePage();
                    break;
                case (5):
                    displayEditFacultyPage();
                    break;
                case (6):
                    displayDeleteFacultyByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayDeleteFacultyByIDPage() {
        System.out.println("Enter ID of the faculty to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the faculty? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = facultyController.deleteFaculty(id);
                    if (result) {
                        System.out.println("Faculty successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayEditFacultyPage() {
        System.out.println("Enter ID of a faculty to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (facultyController.getFacultyById(id) == null){
            System.out.println("Error: no faculty with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change faculty name");
            System.out.println("2. Change faculty university");
            System.out.println("3. Change dean of a faculty");
            System.out.println("0. Back to the Exam menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeFacultyNamePage(id);
                    break;
                case(2):
                    displayChangeFacultyUniversityPage(id);
                    break;
                case(3):
                    displayChangeFacultyDeanPage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeFacultyDeanPage(Long id) {
        System.out.println("Enter new dean (name) of the faculty:");

        String dean = input.nextLine();
        facultyController.updateFacultyDean(id, dean);
        System.out.println("Faculty updated successfully");
        System.out.println();
    }

    private void displayChangeFacultyUniversityPage(Long id) {
        System.out.println("Enter new university ID for the faculty:");

        Long universityID = Long.parseLong(input.nextLine());
        facultyController.updateFacultyUniversity(id, universityID);
        System.out.println("Faculty updated successfully");
        System.out.println();
    }

    private void displayChangeFacultyNamePage(Long id) {
        System.out.println("Enter new faculty name:");

        String name = input.nextLine();
        facultyController.updateFacultyName(id, name);
        System.out.println("Faculty updated successfully");
        System.out.println();
    }

    private void displayFacultyByNamePage() {
        System.out.println("Enter name:");
        String name = input.nextLine();

        Faculty faculty = facultyController.getFacultyByName(name);

        if (faculty != null) {
            System.out.println(JsonConverter.getConvertedToJson(faculty));
            System.out.println();
        }
        else {
            System.out.println("There is no faculty with this name.");
            System.out.println();
        }
    }

    private void displayFacultyByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Faculty faculty = facultyController.getFacultyById(id);

        if (faculty != null) {
            System.out.println(JsonConverter.getConvertedToJson(faculty));
            System.out.println();
        }
        else {
            System.out.println("There is no faculty with this ID.");
            System.out.println();
        }
    }

    private void displayAllFacultiesPage() {
        System.out.println("All faculties:");
        List<Faculty> faculties = facultyController.getAllFaculties();
        System.out.println(JsonConverter.getConvertedToJson(faculties));
        System.out.println();
    }

    private void displayAddFacultyPage() {
        String name;
        String dean;
        Long universityId;

        System.out.println("Enter faculty name:");
        name = input.nextLine();

        System.out.println("Enter name of the dean of faculty:");
        dean = input.nextLine();

        System.out.println("Enter university ID:");
        universityId = Long.parseLong(input.nextLine());

        boolean result = facultyController.addFaculty(name, dean, universityId);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Faculty successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displaySchedulePage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Schedule!");
            System.out.println("Select the option:");
            System.out.println("1. Add a schedule");
            System.out.println("2. Show all schedules");
            System.out.println("3. Show schedule by id");
            System.out.println("4. Edit schedule");
            System.out.println("5. Delete schedule by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddSchedulePage();
                    break;
                case (2):
                    displayAllSchedulesPage();
                    break;
                case (3):
                    displayScheduleByIdPage();
                    break;
                case (4):
                    displayEditSchedulePage();
                    break;
                case (5):
                    displayDeleteScheduleByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayDeleteScheduleByIDPage() {
        System.out.println("Enter ID of the schedule to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the schedule? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = scheduleController.deleteSchedule(id);
                    if (result) {
                        System.out.println("Schedule successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayEditSchedulePage() {
        System.out.println("Enter ID of a schedule to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (scheduleController.getScheduleById(id) == null){
            System.out.println("Error: no schedule with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change student group of the schedule");
            System.out.println("2. Change subject of the schedule");
            System.out.println("3. Change teacher of the schedule");
            System.out.println("4. Change start time of the class");
            System.out.println("5. Change end time of the class");
            System.out.println("6. Change semester of the class");
            System.out.println("7. Change day of week of the class");
            System.out.println("0. Back to the Schedule menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeScheduleGroupPage(id);
                    break;
                case(2):
                    displayChangeScheduleSubjectPage(id);
                    break;
                case(3):
                    displayChangeScheduleTeacherPage(id);
                    break;
                case(4):
                    displayChangeScheduleStartTimePage(id);
                    break;
                case(5):
                    displayChangeScheduleEndTimePage(id);
                    break;
                case(6):
                    displayChangeScheduleSemesterPage(id);
                    break;
                case(7):
                    displayChangeScheduleDayOfWeekPage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeScheduleDayOfWeekPage(Long id) {
        System.out.println("Enter new day of week of the schedule:");

        String day = input.nextLine();
        scheduleController.updateScheduleDayOfWeek(id, day);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleSemesterPage(Long id) {
        System.out.println("Enter new number of the new semester of the schedule:");

        Integer semester = Integer.parseInt(input.nextLine());
        scheduleController.updateScheduleSemester(id, semester);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleEndTimePage(Long id) {
        System.out.println("Enter new end time of the schedule:");

        String end = input.nextLine();
        scheduleController.updateScheduleEndTime(id, end);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleStartTimePage(Long id) {
        System.out.println("Enter new start time of the schedule:");

        String start = input.nextLine();
        scheduleController.updateScheduleStartTime(id, start);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleTeacherPage(Long id) {
        System.out.println("Enter new teacher ID for the schedule:");

        Long teacherID = Long.parseLong(input.nextLine());
        scheduleController.updateScheduleTeacher(id, teacherID);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleSubjectPage(Long id) {
        System.out.println("Enter new subject ID for the schedule:");

        Long subjectID = Long.parseLong(input.nextLine());
        scheduleController.updateScheduleSubject(id, subjectID);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayChangeScheduleGroupPage(Long id) {
        System.out.println("Enter new student group ID for the schedule:");

        Long groupID = Long.parseLong(input.nextLine());
        scheduleController.updateScheduleStudentGroup(id, groupID);
        System.out.println("Schedule updated successfully");
        System.out.println();
    }

    private void displayScheduleByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Schedule schedule = scheduleController.getScheduleById(id);

        if (schedule != null) {
            System.out.println(JsonConverter.getConvertedToJson(schedule));
            System.out.println();
        }
        else {
            System.out.println("There is no schedule with this ID.");
            System.out.println();
        }
    }

    private void displayAllSchedulesPage() {
        System.out.println("All schedule:");
        List<Schedule> schedules = scheduleController.getAllSchedules();
        System.out.println(JsonConverter.getConvertedToJson(schedules));
        System.out.println();
    }

    private void displayAddSchedulePage() {
        Long studentGroupID;
        Long subjectID;
        Long teacherID;
        int semester;
        String startTime;
        String endTime;
        String dayOfWeek;

        System.out.println("Enter student group ID:");
        studentGroupID = Long.parseLong(input.nextLine());

        System.out.println("Enter subject ID:");
        subjectID = Long.parseLong(input.nextLine());

        System.out.println("Enter teacher ID:");
        teacherID = Long.parseLong(input.nextLine());

        System.out.println("Enter number of the semester:");
        semester = Integer.parseInt(input.nextLine());

        System.out.println("Enter start time of the class:");
        startTime = input.nextLine();

        System.out.println("Enter end time of the class:");
        endTime = input.nextLine();

        System.out.println("Enter day of week of the class:");
        dayOfWeek = input.nextLine();

        boolean result = scheduleController.addSchedule(studentGroupID, subjectID, teacherID, semester, startTime, endTime, dayOfWeek);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Schedule successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayStudentPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Student!");
            System.out.println("Select the option:");
            System.out.println("1. Add a student");
            System.out.println("2. Show all students");
            System.out.println("3. Show student by id");
            System.out.println("4. Show student by surname");
            System.out.println("5. Edit student");
            System.out.println("6. Delete student by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddStudentPage();
                    break;
                case (2):
                    displayAllStudentsPage();
                    break;
                case (3):
                    displayStudentByIdPage();
                    break;
                case (4):
                    displayStudentBySurnamePage();
                    break;
                case (5):
                    displayEditStudentPage();
                    break;
                case (6):
                    displayDeleteStudentByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayEditStudentPage() {
        System.out.println("Enter ID of a student to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (studentController.getStudentById(id) == null){
            System.out.println("Error: no student with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change name of the student");
            System.out.println("2. Change surname of the student");
            System.out.println("3. Change patronymic of the student");
            System.out.println("4. Change email of the student");
            System.out.println("5. Change birth date of the student");
            System.out.println("6. Change group of the student");
            System.out.println("0. Back to the Student menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeStudentNamePage(id);
                    break;
                case(2):
                    displayChangeStudentSurnamePage(id);
                    break;
                case(3):
                    displayChangeStudentPatronymicPage(id);
                    break;
                case(4):
                    displayChangeStudentEmailPage(id);
                    break;
                case(5):
                    displayChangeStudentBirthDatePage(id);
                    break;
                case(6):
                    displayChangeStudentGroupPage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeStudentGroupPage(Long id) {
        System.out.println("Enter new student group ID:");

        Long GroupId = Long.parseLong(input.nextLine());

        studentController.updateStudentGroup(id, GroupId);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayChangeStudentBirthDatePage(Long id) {
        System.out.println("Enter new student birth date (YYYY-M-D):");

        String birthdate = input.nextLine();
        studentController.updateStudentBirthDate(id, birthdate);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayChangeStudentEmailPage(Long id) {
        System.out.println("Enter new student email:");

        String email = input.nextLine();
        studentController.updateStudentEmail(id, email);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayChangeStudentPatronymicPage(Long id) {
        System.out.println("Enter new student patronimyc:");

        String patronimyc = input.nextLine();
        studentController.updateStudentPatronymic(id, patronimyc);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayChangeStudentSurnamePage(Long id) {
        System.out.println("Enter new student surname:");

        String surname = input.nextLine();
        studentController.updateStudentSurname(id, surname);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayChangeStudentNamePage(Long id) {
        System.out.println("Enter new student name:");

        String name = input.nextLine();
        studentController.updateStudentName(id, name);
        System.out.println("Student updated successfully");
        System.out.println();
    }

    private void displayDeleteStudentByIDPage() {
        System.out.println("Enter ID of the student to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the student? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = studentController.deleteStudent(id);
                    if (result) {
                        System.out.println("Student successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayStudentBySurnamePage() {
        System.out.println("Enter surname:");
        String surname = input.nextLine();

        Student student = studentController.getStudentBySurname(surname);

        if (student != null) {
            System.out.println(JsonConverter.getConvertedToJson(student));
            System.out.println();
        }
        else {
            System.out.println("There is no student with this surname.");
            System.out.println();
        }
    }

    private void displayStudentByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Student student = studentController.getStudentById(id);

        if (student != null) {
            System.out.println(JsonConverter.getConvertedToJson(student));
            System.out.println();
        }
        else {
            System.out.println("There is no student with this ID.");
            System.out.println();
        }
    }

    private void displayAllStudentsPage() {
        System.out.println("All students:");
        List<Student> students = studentController.getAllStudents();
        System.out.println(JsonConverter.getConvertedToJson(students));
        System.out.println();
    }

    private void displayAddStudentPage() {
        String surname;
        String name;
        String patronymic;
        String email;
        Long studentGroupID;
        String birthdate;

        System.out.println("Enter surname:");
        surname = input.nextLine();

        System.out.println("Enter name");
        name = input.nextLine();

        System.out.println("Enter patronymic:");
        patronymic = input.nextLine();

        System.out.println("Enter email:");
        email = input.nextLine();

        System.out.println("Enter group ID:");
        studentGroupID = Long.parseLong(input.nextLine());

        System.out.println("Enter birth date (YYYY-M-D):");
        birthdate = input.nextLine();

        boolean result = studentController.addStudent(surname, name, patronymic, email, studentGroupID, birthdate);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Student successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayStudentGroupPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Group!");
            System.out.println("Select the option:");
            System.out.println("1. Add a group");
            System.out.println("2. Show all groups");
            System.out.println("3. Show group by id");
            System.out.println("4. Show group by name");
            System.out.println("5. Change group name");
            System.out.println("6. Change group department");
            System.out.println("7. Delete group by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddGroupPage();
                    break;
                case (2):
                    displayAllGroupsPage();
                    break;
                case (3):
                    displayGroupByIdPage();
                    break;
                case (4):
                    displayGroupByNamePage();
                    break;
                case (5):
                    displayChangeGroupNamePage();
                    break;
                case (6):
                    displayChangeGroupDepartmentPage();
                    break;
                case (7):
                    displayDeleteGroupByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayChangeGroupDepartmentPage() {
        System.out.println("Enter ID of a group to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (studentGroupController.getStudentGroupById(id) == null){
            System.out.println("Error: no group with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter new department ID:");

        Long DepartmentId = Long.parseLong(input.nextLine());

        studentGroupController.updateStudentGroupDepartment(id, DepartmentId);
        System.out.println("Group updated successfully");
        System.out.println();
    }

    private void displayChangeGroupNamePage() {
        System.out.println("Enter ID of a group to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (studentGroupController.getStudentGroupById(id) == null){
            System.out.println("Error: no group with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter new group name:");

        String name = input.nextLine();

        studentGroupController.updateStudentGroupName(id, name);
        System.out.println("Group updated successfully");
        System.out.println();
    }

    private void displayDeleteGroupByIDPage() {
        System.out.println("Enter ID of the group to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the group? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = studentGroupController.deleteStudentGroup(id);
                    if (result) {
                        System.out.println("Group successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayGroupByNamePage() {
        System.out.println("Enter id:");
        String name = input.nextLine();

        StudentGroup group = studentGroupController.getStudentGroupByName(name);

        if (group != null) {
            System.out.println(JsonConverter.getConvertedToJson(group));
            System.out.println();
        }
        else {
            System.out.println("There is no group with this name.");
            System.out.println();
        }
    }

    private void displayGroupByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        StudentGroup group = studentGroupController.getStudentGroupById(id);

        if (group != null) {
            System.out.println(JsonConverter.getConvertedToJson(group));
            System.out.println();
        }
        else {
            System.out.println("There is no group with this ID.");
            System.out.println();
        }
    }

    private void displayAllGroupsPage() {
        System.out.println("All groups:");
        List<StudentGroup> groups = studentGroupController.getAllStudentGroups();
        System.out.println(JsonConverter.getConvertedToJson(groups));
        System.out.println();
    }

    private void displayAddGroupPage() {
        String name;
        Long departmentID;

        System.out.println("Enter group name:");
        name = input.nextLine();

        System.out.println("Enter department ID:");
        departmentID = Long.parseLong(input.nextLine());

        boolean result = studentGroupController.addStudentGroup(name, departmentID);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Group successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displaySubjectPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Subject!");
            System.out.println("Select the option:");
            System.out.println("1. Add a subject");
            System.out.println("2. Show all subjects");
            System.out.println("3. Show subject by id");
            System.out.println("4. Show subject by name");
            System.out.println("5. Change subject name");
            System.out.println("6. Delete subject by id");
            System.out.println("7. Delete subject by name");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddSubjectPage();
                    break;
                case (2):
                    displayAllSubjectsPage();
                    break;
                case (3):
                    displaySubjectByIdPage();
                    break;
                case (4):
                    displaySubjectByNamePage();
                    break;
                case (5):
                    displayChangeSubjectNamePage();
                    break;
                case (6):
                    displayDeleteSubjectByIDPage();
                    break;
                case (7):
                    displayDeleteSubjectByNamePage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayChangeSubjectNamePage() {
        System.out.println("Enter ID of a subject to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (subjectController.getSubjectById(id) == null){
            System.out.println("Error: no subject with such ID.");
            System.out.println();
            return;
        }

        System.out.println("Enter new subject name:");

        String name = input.nextLine();

        subjectController.updateSubjectName(id, name);
        System.out.println("Subject updated successfully");
        System.out.println();
    }

    private void displayDeleteSubjectByNamePage() {
        System.out.println("Enter name of the subject to delete:");

        String name = input.nextLine();

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the subject? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = subjectController.deleteSubject(name);
                    if (result) {
                        System.out.println("Subject successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid name.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayDeleteSubjectByIDPage() {
        System.out.println("Enter ID of the subject to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the subject? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = subjectController.deleteSubject(id);
                    if (result) {
                        System.out.println("Subject successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displaySubjectByNamePage() {
        System.out.println("Enter name:");
        String name = input.nextLine();

        Subject subject = subjectController.getSubjectByName(name);

        if (subject != null) {
            System.out.println(JsonConverter.getConvertedToJson(subject));
            System.out.println();
        }
        else {
            System.out.println("There is no subject with this name.");
            System.out.println();
        }
    }

    private void displaySubjectByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Subject subject = subjectController.getSubjectById(id);

        if (subject != null) {
            System.out.println(JsonConverter.getConvertedToJson(subject));
            System.out.println();
        }
        else {
            System.out.println("There is no subject with this ID.");
            System.out.println();
        }
    }

    private void displayAllSubjectsPage() {
        System.out.println("All subject:");
        List<Subject> subjects = subjectController.getAllSubjects();
        System.out.println(JsonConverter.getConvertedToJson(subjects));
        System.out.println();
    }

    private void displayAddSubjectPage() {
        String name;

        System.out.println("Enter subject name:");
        name = input.nextLine();

        boolean result = subjectController.addSubject(name);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Subject successfully added.");
            System.out.println();
        }
    }

    @Override
    public void displayTeacherPage() {
        boolean flag = true;

        while (flag) {
            System.out.println("This is a page for Teacher!");
            System.out.println("Select the option:");
            System.out.println("1. Add a teacher");
            System.out.println("2. Show all teachers");
            System.out.println("3. Show teacher by id");
            System.out.println("4. Show teacher by surname");
            System.out.println("5. Edit teacher");
            System.out.println("6. Delete teacher by id");
            System.out.println("0. Back to main menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option) {
                case (1):
                    displayAddTeacherPage();
                    break;
                case (2):
                    displayAllTeachersPage();
                    break;
                case (3):
                    displayTeacherByIdPage();
                    break;
                case (4):
                    displayTeacherBySurnamePage();
                    break;
                case (5):
                    displayEditTeacherPage();
                    break;
                case (6):
                    displayDeleteTeacherByIDPage();
                    break;
                case (0):
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose the correct option!");
                    break;
            }
        }
    }

    private void displayEditTeacherPage() {
        System.out.println("Enter ID of a teacher to edit:");

        Long id = Long.parseLong(input.nextLine());

        if (teacherController.getTeacherById(id) == null){
            System.out.println("Error: no teacher with such ID.");
            return;
        }

        boolean flag = true;
        while(flag){
            System.out.println("Choose what you want to edit:");
            System.out.println("1. Change name of the teacher");
            System.out.println("2. Change surname of the teacher");
            System.out.println("3. Change patronymic of the teacher");
            System.out.println("4. Change email of the teacher");
            System.out.println("5. Change degree of the teacher");
            System.out.println("6. Change position of the teacher");
            System.out.println("0. Back to the Teacher menu");

            int option;
            try{
                option = Integer.parseInt(input.nextLine());
            }
            catch(Exception e){
                option = 100;
            }

            switch (option){
                case(1):
                    displayChangeTeacherNamePage(id);
                    break;
                case(2):
                    displayChangeTeacherSurnamePage(id);
                    break;
                case(3):
                    displayChangeTeacherPatronymicPage(id);
                    break;
                case(4):
                    displayChangeTeacherEmailPage(id);
                    break;
                case(5):
                    displayChangeTeacherDegreePage(id);
                    break;
                case(6):
                    displayChangeTeacherPositionPage(id);
                    break;
                case(0):
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayChangeTeacherPositionPage(Long id) {
        System.out.println("Enter new teacher position:");

        String position = input.nextLine();
        teacherController.updateTeacherPosition(id, position);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayChangeTeacherDegreePage(Long id) {
        System.out.println("Enter new teacher degree:");

        String degree = input.nextLine();
        teacherController.updateTeacherDegree(id, degree);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayChangeTeacherEmailPage(Long id) {
        System.out.println("Enter new teacher email:");

        String email = input.nextLine();
        teacherController.updateTeacherEmail(id, email);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayChangeTeacherPatronymicPage(Long id) {
        System.out.println("Enter new teacher patronymic:");

        String patronymic = input.nextLine();
        teacherController.updateTeacherPatronymic(id, patronymic);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayChangeTeacherSurnamePage(Long id) {
        System.out.println("Enter new teacher surname:");

        String surname = input.nextLine();
        teacherController.updateTeacherSurname(id, surname);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayChangeTeacherNamePage(Long id) {
        System.out.println("Enter new teacher name:");

        String name = input.nextLine();
        teacherController.updateTeacherName(id, name);
        System.out.println("Teacher updated successfully");
        System.out.println();
    }

    private void displayDeleteTeacherByIDPage() {
        System.out.println("Enter ID of the teacher to delete:");

        Long id = Long.parseLong(input.nextLine());

        boolean flag = true;
        boolean result;

        while(flag){
            System.out.println("Are you sure you want to delete the teacher? Print y or n:");

            String option = input.nextLine();

            switch (option){
                case("y"):
                    result = teacherController.deleteTeacher(id);
                    if (result) {
                        System.out.println("Teacher successfully deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: invalid ID.");
                        System.out.println();
                    }
                    flag = false;
                    break;
                case("n"):
                    flag = false;
                    break;
            }
        }
    }

    private void displayTeacherBySurnamePage() {
        System.out.println("Enter surname:");
        String surname = input.nextLine();

        Teacher teacher = teacherController.getTeacherBySurname(surname);

        if (teacher != null) {
            System.out.println(JsonConverter.getConvertedToJson(teacher));
            System.out.println();
        }
        else {
            System.out.println("There is no teacher with this surname.");
            System.out.println();
        }
    }

    private void displayTeacherByIdPage() {
        System.out.println("Enter id:");
        Long id = Long.parseLong(input.nextLine());

        Teacher teacher = teacherController.getTeacherById(id);

        if (teacher != null) {
            System.out.println(JsonConverter.getConvertedToJson(teacher));
            System.out.println();
        }
        else {
            System.out.println("There is no teacher with this ID.");
            System.out.println();
        }
    }

    private void displayAllTeachersPage() {
        System.out.println("All teachers:");
        List<Teacher> teachers = teacherController.getAllTeachers();
        System.out.println(JsonConverter.getConvertedToJson(teachers));
        System.out.println();
    }

    private void displayAddTeacherPage() {
        String surname;
        String name;
        String patronymic;
        String email;
        String degree;
        String position;

        System.out.println("Enter surname:");
        surname = input.nextLine();

        System.out.println("Enter name");
        name = input.nextLine();

        System.out.println("Enter patronymic:");
        patronymic = input.nextLine();

        System.out.println("Enter email:");
        email = input.nextLine();

        System.out.println("Enter degree:");
        degree = input.nextLine();

        System.out.println("Enter position");
        position = input.nextLine();

        boolean result = teacherController.addTeacher(surname, name, patronymic, email, degree, position);

        if (!result) {
            System.out.println("Error. Invalid data.");
            System.out.println();
        }
        else {
            System.out.println("Teacher successfully added.");
            System.out.println();
        }
    }
}
