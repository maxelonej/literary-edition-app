package _3ButtonsForSelectedRole.Watch.LiteraryEditor;
import _1Authorization.DatabaseHandler;
import _3ButtonsForSelectedRole.skeleton.Authors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class 
Watch_Controller_Authors{
    @FXML  Button Back;
    @FXML  TableView<Authors> tvBooks;
    @FXML  TableColumn<Authors, Integer> col_id;
    @FXML  TableColumn<Authors, String> col_surname, col_name, col_patronymic;
    @FXML
    void initialize(){
        Back.setOnAction(event -> {
            Back.getScene().getWindow().hide();
            new DatabaseHandler.openNewScene("/_2SelectedRole/LiteraryEditor/MainForLiteraryEditor.fxml", "Авторизация/Литературный редактор", "/assets/employee.png");
        });
        showBooks();
    }
    public ObservableList<Authors> getBooksList(){
        ObservableList<Authors> bookList = FXCollections.observableArrayList();
        Connection conn = DatabaseHandler.getConnection();
        String query = "SELECT * FROM `literary_publishing_house`.authors;";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Authors Authors;
            while (rs.next()){
                Authors = new Authors(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic")
                );
                bookList.add(Authors);
            }
        }catch (Exception e){
            System.out.println("getBooksList " + e);
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Authors> list = getBooksList();
        col_id.setCellValueFactory(new PropertyValueFactory<Authors, Integer>("id"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Authors, String>("surname"));
        col_name.setCellValueFactory(new PropertyValueFactory<Authors, String>("name"));
        col_patronymic.setCellValueFactory(new PropertyValueFactory<Authors, String>("patronymic"));
        tvBooks.setItems(list);
    }
}
