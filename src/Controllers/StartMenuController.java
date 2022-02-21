package Controllers;

import Models.*;
import Models.Database.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

import static Models.Subject.Java;
import static Models.Subject.Web2;
import static Models.Subscription.No;
import static Models.Subscription.Yes;

public class StartMenuController {

    @FXML private TableView<Person> table;
    @FXML private TabPane tabPane;
    @FXML private TableColumn<Person, UUID> id;
    @FXML private TableColumn<Person, String> firstName;
    @FXML private TableColumn<Person, String> lastName;
    @FXML private TableColumn<Person, Integer> yearOfBirth;
    @FXML private TableColumn<Professor, Subject> subject;
    @FXML private TableColumn<Buyer, Boolean> hasBought;
    @FXML private TableColumn<Student, Subscription> subscription;
    // tabProfessor
    @FXML private TextField uuidTextFieldProfessor;
    @FXML private TextField firstNameTextFieldProfessor;
    @FXML private TextField lastNameTextFieldProfessor;
    @FXML private DatePicker dateOfBirthProfessor;
    @FXML private RadioButton rbProfessorJava;
    @FXML private RadioButton rbProfessorWeb2;
    // tabBuyer
    @FXML private TextField uuidTextFieldBuyer;
    @FXML private TextField firstNameTextFieldBuyer;
    @FXML private TextField lastNameTextFieldBuyer;
    @FXML private DatePicker dateOfBirthBuyer;
    @FXML private CheckBox cbHasBought;
    // tabStudent
    @FXML private TextField uuidTextFieldStudent;
    @FXML private TextField firstNameTextFieldStudent;
    @FXML private TextField lastNameTextFieldStudent;
    @FXML private DatePicker dateOfBirthStudent;
    @FXML private RadioButton rbSubscriptionYesStudent;
    @FXML private RadioButton rbSubscriptionNoStudent;

    @FXML private Label wrongInformationLabelSubj;
    @FXML private Label wrongInformationLabelSubs;

    // professors
    @FXML void AddProfessor(ActionEvent event) {
        ProfessorDatabase professordb = new ProfessorDatabase();
        String name = firstNameTextFieldProfessor.getText();
        String lastName = lastNameTextFieldProfessor.getText();
        Subject subject;
        int year = dateOfBirthProfessor.getValue().getYear();
        if(rbProfessorJava.isSelected()) {
            subject = Java;
            professordb.add(new Professor(UUID.randomUUID(), name, lastName, year, subject));
        } else if (rbProfessorWeb2.isSelected()){
            subject = Web2;
            professordb.add(new Professor(UUID.randomUUID(), name, lastName, year, subject));
        } else {
            wrongInformationLabelSubj.setText("Označite predmet");
        }
    }
    @FXML void DeleteProfessor(ActionEvent event) {
        ProfessorDatabase professordb = new ProfessorDatabase();
        String inputUUID = uuidTextFieldProfessor.getText();
        Professor c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = professordb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            uuidTextFieldProfessor.setDisable(false);
            uuidTextFieldProfessor.clear();
            firstNameTextFieldProfessor.clear();
            lastNameTextFieldProfessor.clear();
            rbProfessorJava.setSelected(false);
            rbProfessorWeb2.setSelected(false);
            professordb.remove(c);
        }

    }
    @FXML void GetByUUIDProfessor(ActionEvent event) {
        ProfessorDatabase professordb = new ProfessorDatabase();
        String inputUUID = uuidTextFieldProfessor.getText();
        Professor c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = professordb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            firstNameTextFieldProfessor.setText(c.getFirstName());
            lastNameTextFieldProfessor.setText(c.getLastName());
            if(c.getSubject() == Java) {
                rbProfessorJava.setSelected(true);
            } else {
                rbProfessorWeb2.setSelected(true);
            }
            uuidTextFieldProfessor.setDisable(true);
        }

    }
    @FXML void UpdateProfessor(ActionEvent event) {
        ProfessorDatabase professordb = new ProfessorDatabase();
        String inputUUID = uuidTextFieldProfessor.getText();
        Professor c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = professordb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            c.setFirstName(firstNameTextFieldProfessor.getText());
            c.setLastName(lastNameTextFieldProfessor.getText());
            if(rbProfessorWeb2.isSelected()) {
                c.setSubject(Web2);
            } else {
                c.setSubject(Java);
            }
            professordb.update(c);
        }

    }

    // buyers
    @FXML void GetByUUIDBuyer(ActionEvent event) {
        BuyerDatabase buyerdb = new BuyerDatabase();
        String inputUUID = uuidTextFieldBuyer.getText();
        Buyer c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = buyerdb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            firstNameTextFieldBuyer.setText(c.getFirstName());
            lastNameTextFieldBuyer.setText(c.getLastName());
            dateOfBirthBuyer.setUserData(c.getYearOfBirth());
            uuidTextFieldProfessor.setDisable(true);
            if(c.getHasBought() == true) {
                cbHasBought.setSelected(true);
            } else {
                cbHasBought.setSelected(false);
            }
            uuidTextFieldBuyer.setDisable(true);
        }
    }
    @FXML void AddBuyer(ActionEvent event) {
        BuyerDatabase buyerdb = new BuyerDatabase();
        String name = firstNameTextFieldBuyer.getText();
        String lastName = lastNameTextFieldBuyer.getText();
        int year = dateOfBirthBuyer.getValue().getYear();
    }
    @FXML void DeleteBuyer(ActionEvent event) {
        BuyerDatabase buyerdb = new BuyerDatabase();
        String inputUUID = uuidTextFieldProfessor.getText();
        Buyer c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = buyerdb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            uuidTextFieldBuyer.setDisable(false);
            uuidTextFieldBuyer.clear();
            firstNameTextFieldBuyer.clear();
            lastNameTextFieldBuyer.clear();
            cbHasBought.setSelected(false);
            buyerdb.remove(c);
        }
    }
    @FXML void UpdateBuyer(ActionEvent event) {
        BuyerDatabase buyerdb = new BuyerDatabase();
        String inputUUID = uuidTextFieldBuyer.getText();
        Buyer c = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            c = buyerdb.get(UUID.fromString(inputUUID));
        }
        if(c == null){
            return;
        } else {
            c.setFirstName(firstNameTextFieldBuyer.getText());
            c.setLastName(lastNameTextFieldBuyer.getText());

            if(cbHasBought.isSelected()) {
                c.setHasBought(true);
            } else {
                c.setHasBought(false);
            }
            buyerdb.update(c);
        }
    }

    // students
    @FXML public void GetByUUIDStudent(ActionEvent actionEvent) {
        StudentDatabase studentdb = new StudentDatabase();
        String inputUUID = uuidTextFieldStudent.getText();
        Student e = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            e = studentdb.get(UUID.fromString(inputUUID));
        }
        if(e == null){
            return;
        } else {
            firstNameTextFieldStudent.setText(e.getFirstName());
            lastNameTextFieldStudent.setText(e.getLastName());
            if(e.getSubscription() == Yes) {
                rbSubscriptionYesStudent.setSelected(true);
            } else {
                rbSubscriptionNoStudent.setSelected(true);
            }
            uuidTextFieldStudent.setDisable(true);
        }

    }
    @FXML public void AddStudent(ActionEvent actionEvent) {
        StudentDatabase studentdb = new StudentDatabase();
        String name = firstNameTextFieldStudent.getText();
        String lastName = lastNameTextFieldStudent.getText();
        int year = dateOfBirthStudent.getValue().getYear();
        Subscription subscription = null;
        if(rbSubscriptionYesStudent.isSelected()) {
            subscription = Yes;
        } else if (rbSubscriptionNoStudent.isSelected()){
            subscription = No;
        } else {
            wrongInformationLabelSubs.setText("Choose subscription");
        }
        if(subscription != null) {
            studentdb.add(new Student(UUID.randomUUID(), name, lastName,
                    year, subscription));
        } else {
            return;
        }

    }
    @FXML public void DeleteStudent(ActionEvent actionEvent) {
        StudentDatabase studentdb = new StudentDatabase();
        String inputUUID = uuidTextFieldStudent.getText();
        Student e = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            e = studentdb.get(UUID.fromString(inputUUID));
        }
        if(e == null){
            return;
        } else {
            uuidTextFieldStudent.setDisable(false);
            uuidTextFieldStudent.clear();
            firstNameTextFieldStudent.clear();
            lastNameTextFieldStudent.clear();
            rbSubscriptionNoStudent.setSelected(false);
            rbSubscriptionYesStudent.setSelected(false);

            studentdb.remove(e);
        }
    }
    @FXML public void UpdateStudent(ActionEvent actionEvent) {
        StudentDatabase studentdb = new StudentDatabase();
        String inputUUID = uuidTextFieldStudent.getText();
        Student e = null;
        if(inputUUID.isEmpty()){
            return;
        } else {
            e = studentdb.get(UUID.fromString(inputUUID));
        }
        if(e == null){
            return;
        } else {
            e.setFirstName(firstNameTextFieldStudent.getText());
            e.setLastName(lastNameTextFieldStudent.getText());
            if(rbSubscriptionNoStudent.isSelected()) {
                e.setSubscription(No);
            } else {
                e.setSubscription(Yes);
            }
            studentdb.update(e);
        }

    }

    //  show data
    @FXML void getAllDatabaseData(ActionEvent event) {
        hasBought.setVisible(false);
        subject.setVisible(false);
        subscription.setVisible(false);
        id.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        yearOfBirth.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));


        table.getItems().clear();

        ProfessorDatabase professordb = new ProfessorDatabase();
        professordb.getAllProfessors().stream().forEach(professor -> System.out.println(professor.toString()));
        BuyerDatabase buyerdb = new BuyerDatabase();
        buyerdb.getAllBuyers().stream().forEach(buyer -> System.out.println(buyer.toString()));
        StudentDatabase studentdb = new StudentDatabase();
        studentdb.getAllStudents().stream().forEach(student -> System.out.println(student.toString()));


        List<Professor> professors = professordb.getAllProfessors();
        List<Buyer> buyers = buyerdb.getAllBuyers();
        List<Student> students = studentdb.getAllStudents();
        List<Person> persons = new ArrayList<Person>();

        persons.addAll(professors);
        persons.addAll(buyers);
        persons.addAll(students);
        for (Person p: persons) {
            table.getItems().add(p);
        }
        clear();
    }
    @FXML void getAllProfessorData(ActionEvent event) {
        hasBought.setVisible(false);
        subject.setVisible(true);
        subscription.setVisible(false);
        id.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        yearOfBirth.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));

        table.getItems().clear();

        ProfessorDatabase professordb = new ProfessorDatabase();
        professordb.getAllProfessors().stream().forEach(professor -> System.out.println(professor.toString()));
        List<Professor> professors = professordb.getAllProfessors();


        List<Person> persons = new ArrayList<Person>();

        persons.addAll(professors);
        for (Person p: persons) {
            table.getItems().add(p);
        }
        clear();
    }

    @FXML void getAllBuyerData(ActionEvent event) {
        hasBought.setVisible(true);
        subject.setVisible(false);
        subscription.setVisible(false);
        id.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        yearOfBirth.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));

        table.getItems().clear();

        BuyerDatabase buyerdb = new BuyerDatabase();
        buyerdb.getAllBuyers().stream().forEach(buyer -> System.out.println(buyer.toString()));

        List<Buyer> buyers = buyerdb.getAllBuyers();
        List<Person> persons = new ArrayList<Person>();

        persons.addAll(buyers);
        for (Person p: persons) {
            table.getItems().add(p);
        }
        clear();
    }
    @FXML void getAllStudentData(ActionEvent event) {
        hasBought.setVisible(false);
        subject.setVisible(false);
        subscription.setVisible(true);
        id.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        yearOfBirth.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));


        table.getItems().clear();

        StudentDatabase studentdb = new StudentDatabase();
        studentdb.getAllStudents().stream().forEach(student -> System.out.println(student.toString()));

        List<Student> students = studentdb.getAllStudents();
        List<Person> persons = new ArrayList<Person>();

        persons.addAll(students);
        for (Person p: persons) {
            table.getItems().add(p);
        }
        clear();
    }

    @FXML public void handleRowSelect(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println( table.getSelectionModel().getSelectedItem());
        UUID selectedPerson = table.getSelectionModel().getSelectedItem().getUuid();
        if(table.getSelectionModel().getSelectedItem() instanceof Professor ) {
            tabPane.getSelectionModel().select(1);
            uuidTextFieldProfessor.setText(selectedPerson.toString());
        } else if (table.getSelectionModel().getSelectedItem() instanceof Buyer) {
            tabPane.getSelectionModel().select(2);
            uuidTextFieldBuyer.setText(selectedPerson.toString());
        } else {
            tabPane.getSelectionModel().select(3);
            uuidTextFieldStudent.setText(selectedPerson.toString());
        }
    }

    @FXML void logoutAction(ActionEvent event) {
        ScreenController screenController = new ScreenController();
        screenController.switchToSceneLogin(event);
    }

    // clear up all inputs
    @FXML void clear() {
        uuidTextFieldProfessor.clear();
        firstNameTextFieldProfessor.setText("");
        lastNameTextFieldProfessor.setText("");
        rbProfessorJava.setSelected(false);
        rbProfessorWeb2.setSelected(false);
        dateOfBirthProfessor.getEditor().clear();

        uuidTextFieldBuyer.clear();
        firstNameTextFieldBuyer.setText("");
        lastNameTextFieldBuyer.setText("");
        cbHasBought.setSelected(false);
        dateOfBirthBuyer.getEditor().clear();

        uuidTextFieldStudent.clear();
        firstNameTextFieldStudent.setText("");
        lastNameTextFieldStudent.setText("");
        rbSubscriptionYesStudent.setSelected(false);
        rbSubscriptionNoStudent.setSelected(false);
        dateOfBirthStudent.getEditor().clear();

    }

}


