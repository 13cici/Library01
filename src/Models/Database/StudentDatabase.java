package Models.Database;

import Models.Student;
import Models.StudentRepository;
import Models.Subscription;
import Models.Subject;
import Utilities.AppConstrants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static Models.Subscription.Yes;
import static Models.Subscription.No;

public class StudentDatabase implements StudentRepository {

    static Logger log = Logger.getLogger(AppConstrants.LOGGER_STRING);

    private List<Student> database = new ArrayList<Student>();
    private String databaseFilePath = "StudentDatabase.ser";
    public StudentDatabase() {
        boolean testForDatabase = checkForDatabase();
        if(testForDatabase == true) {
            readDataFromDatabase();
        } else {
            generateDataForDatabase();
        }
    }
    private boolean checkForDatabase(){
        File databaseFile = new File(databaseFilePath);
        if(databaseFile.exists() && databaseFile.isFile()){
            return true;
        }
        return false;
    }
    private void generateDataForDatabase() {
        File databaseFile = new File(databaseFilePath);
        Random random = new Random();
        Subscription subscription;
        Subject subject;
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                subscription = Yes;
            } else {
                subscription = No;
            }
            database.add(new Student(UUID.randomUUID(), "Student" + Integer.toString(i),
                    "Student" + Integer.toString(i),
                    1990+i, subscription));
        }

        try(FileOutputStream fo = new FileOutputStream(databaseFile, false);
            ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Student database successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Problem with student database creation!");
        }
    }

    private void readDataFromDatabase() {
        File databaseFile = new File(databaseFilePath);
        try(FileInputStream fi = new FileInputStream(databaseFile);
            ObjectInputStream oi = new ObjectInputStream(fi);){
            database = (ArrayList<Student>) oi.readObject();
            log.info("Student database successfully loaded!");

        }catch (Exception e){
            e.printStackTrace();
            log.warning("There is problem with loading student database!" + e. getMessage());
        }
    }

    private void updateDataInDatabase() {
        File databaseFile = new File(databaseFilePath);
        try (FileOutputStream fo = new FileOutputStream(databaseFile,false);
             ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Student database successfully updated!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.warning("Problem with student database update!" + e.getMessage());
        }
    }

    @Override
    public Student get(UUID id) {
        log.info("Method call!" + id.toString());
        for (Student e: database) {
            if(e.getUuid().compareTo(id) == 0){
                return e;
            }
        }
        return null;
    }

    @Override
    public void add(Student student) {
        log.info("Method call!" + student.toString());
        database.add(student);
        updateDataInDatabase();
    }

    @Override
    public boolean update(Student student) {
        log.info("Method call!" + student.toString());
        for (Student e: database) {
            if(e.getUuid() == student.getUuid()){
                e.setFirstName(student.getFirstName());
                e.setLastName(student.getLastName());
                e.setSubscription(student.getSubscription());
                e.setYearOfBirth(student.getYearOfBirth());
                updateDataInDatabase();
                return true;

            }
        }
        return false;
    }

    @Override
    public void remove(Student student) {
        log.info("Method call!" + student.toString());
        database.remove(student);
        updateDataInDatabase();
    }

    @Override
    public List<Student> getAllStudents() {
        log.info("Method call!");
        return database;
    }
}

