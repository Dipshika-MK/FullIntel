package com.fullintel.user.dao;

import com.fullintel.datasource.DataSource;
import com.fullintel.user.dto.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    static  String SELECT_USER = "select * from user where Email=? and Password =?";
    private static final String INSERT_USER = "insert into user" + "(Email, Password, FirstName, LastName ) VALUES " + "(?, ?, ?, ?);";
    private static final String UPDATE_USER = "update user set FirstName=?,LastName=? where Id=?";
    private static final String DELETE_USER ="DELETE FROM user WHERE id = ?";

    public List<User> getUser(String email, String password) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DataSource.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("email");
                String passwrd = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                userList.add(new User(mail, password, firstName, lastName));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return userList;
    }

    public void insertUser(User user)  {
        try (Connection connection = DataSource.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateUser(int id, String firstName, String lastName){
        try (Connection connection = DataSource.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();

        }catch (Exception e) {
            System.out.println(e);
        }

    }
    public void deleteUser(Integer id) throws Exception {
        try (Connection connection = DataSource.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
    }
}

