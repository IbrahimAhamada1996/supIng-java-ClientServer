package jpa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JpaConversation {


    public static List<String> allConversation(){
        List<String> conversations = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/socket_supingenieur","root","");
            PreparedStatement preparedStatement  =  connection.prepareStatement("SELECT nom, message FROM conversation ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                conversations.add(resultSet.getString("nom"));
                conversations.add(resultSet.getString("message"));
                // conversation.setUser((User) resultSet.getObject("user_id"));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conversations;
    }

    public static void addConversation(String nom,String message) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/socket_supingenieur","root","");
            PreparedStatement preparedStatement  = (PreparedStatement) connection.prepareStatement("insert into conversation (nom,message) values (?,?)");
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,message);
            int nbreresulta =  preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("pas de pilote driver");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(allConversation());
        addConversation("ibrahim","ok");
    }

}
