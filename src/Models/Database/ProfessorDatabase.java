package Models.Database;

import Models.Professor;
import Models.ProfessorRepository;
import Models.Subject;
import Utilities.AppConstrants;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static Models.Subject.Java;
import static Models.Subject.Web2;

public class ProfessorDatabase implements ProfessorRepository {

    static Logger log = Logger.getLogger(AppConstrants.LOGGER_STRING);

    private List<Professor> database = new ArrayList<Professor>();
    private String databaseFilePath = "ProfessorDatabase.ser";

    public ProfessorDatabase() {
        boolean testForDatabase = checkForDatabase();
        if(testForDatabase) {
            readDataFromDatabase();
        } else {
            generateDataForDatabase();
        }
    }
    private boolean checkForDatabase(){
        File databaseFile = new File(databaseFilePath);
        return databaseFile.exists() && databaseFile.isFile();
    }
    private void generateDataForDatabase() {
        File databaseFile = new File(databaseFilePath);
        Random random = new Random();
        Subject subject;
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                subject = Java;
            } else {
                subject = Web2;
            }
            database.add(new Professor(UUID.randomUUID(), "Professor" + Integer.toString(i),
                    "Professors" + Integer.toString(i),
                    1990+i, subject));
        }

        try(FileOutputStream fo = new FileOutputStream(databaseFile, false);
            ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Professor database successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Problem with professor database creation!");
        }
    }

    private void readDataFromDatabase() {
        File databaseFile = new File(databaseFilePath);
        try(FileInputStream fi = new FileInputStream(databaseFile);
            ObjectInputStream oi = new ObjectInputStream(fi);){
            database = (ArrayList<Professor>) oi.readObject();
            log.info("Professor database successfully loaded!");

        }catch (Exception e){
            e.printStackTrace();
            log.warning("There is problem with loading professor database!" + e. getMessage());
        }
    }

    private void updateDataInDatabase() {
        File databaseFile = new File(databaseFilePath);
        try (FileOutputStream fo = new FileOutputStream(databaseFile,false);
             ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Professor database successfully updated!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.warning("Problem with professor database update!" + e.getMessage());
        }
    }

    @Override
    public Professor get(UUID id) {
        log.info("Method call!" + id.toString());
        for (Professor c: database) {
            if(c.getUuid().compareTo(id) == 0){
                return c;
            }
        }
        return null;
    }

    @Override
    public void add(Professor professor) {
        log.info("Method call!" + professor.toString());
        database.add(professor);
        updateDataInDatabase();
    }

    @Override
    public boolean update(Professor professor) {
        log.info("Method call!" + professor.toString());
        for (Professor c: database) {
            if(c.getUuid() == professor.getUuid()){
                c.setFirstName(professor.getFirstName());
                c.setLastName(professor.getLastName());
                c.setSubject(professor.getSubject());
                c.setYearOfBirth(professor.getYearOfBirth());
                updateDataInDatabase();
                return true;

            }
        }
        return false;
    }

    @Override
    public void remove(Professor professor) {
        log.info("Method call!" + professor.toString());
        database.remove(professor);
        updateDataInDatabase();
    }

    @Override
    public List<Professor> getAllProfessors() {
        log.info("Method call!");
        return database;
    }
}
