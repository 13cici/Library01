package Models.Database;

import Models.Buyer;
import Models.BuyerRepository;
import Models.Subscription;
import Utilities.AppConstrants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

public class BuyerDatabase implements BuyerRepository {

    static Logger log = Logger.getLogger(AppConstrants.LOGGER_STRING);

    private List<Buyer> database = new ArrayList<Buyer>();
    private String databaseFilePath = "BuyerDatabase.ser";

    public BuyerDatabase() {
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
        for(int i = 0; i < 10; i++) {
            database.add(new Buyer(UUID.randomUUID(), "Buyer" + Integer.toString(i),
                    "Buyers" + Integer.toString(i), 1990+i, true));
        }

        try(FileOutputStream fo = new FileOutputStream(databaseFile, false);
            ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Buyer database successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("Problem with buyer database creation!");
        }
    }

    private void readDataFromDatabase() {
        File databaseFile = new File(databaseFilePath);
        try(FileInputStream fi = new FileInputStream(databaseFile);
            ObjectInputStream oi = new ObjectInputStream(fi);){
            database = (ArrayList<Buyer>) oi.readObject();
            log.info("Buyer database successfully loaded!");

        }catch (Exception e){
            e.printStackTrace();
            log.warning("There is problem with loading buyer database!" + e. getMessage());
        }
    }

    private void updateDataInDatabase() {
        File databaseFile = new File(databaseFilePath);
        try (FileOutputStream fo = new FileOutputStream(databaseFile,false);
             ObjectOutputStream ou = new ObjectOutputStream(fo)) {
            ou.writeObject(database);
            log.info("Buyer database successfully updated!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.warning("Problem with buyer database update!" + e.getMessage());
        }
    }

    @Override
    public Buyer get(UUID id) {
        log.info("Method call!" + id.toString());
        for (Buyer c: database) {
            if(c.getUuid().compareTo(id) == 0){
                return c;
            }
        }
        return null;
    }

    @Override
    public void add(Buyer buyer) {
        log.info("Method call!" + buyer.toString());
        database.add(buyer);
        updateDataInDatabase();
    }

    @Override
    public boolean update(Buyer buyer) {
        log.info("Method call!" + buyer.toString());
        for (Buyer c: database) {
            if(c.getUuid() == buyer.getUuid()){
                c.setFirstName(buyer.getFirstName());
                c.setLastName(buyer.getLastName());
                c.setYearOfBirth(buyer.getYearOfBirth());
                c.setHasBought(buyer.getHasBought());
                updateDataInDatabase();
                return true;

            }
        }
        return false;
    }

    @Override
    public void remove(Buyer buyer) {
        log.info("Method call!" + buyer.toString());
        database.remove(buyer);
        updateDataInDatabase();
    }

    @Override
    public List<Buyer> getAllBuyers() {
        log.info("Method call!");
        return database;
    }
}

