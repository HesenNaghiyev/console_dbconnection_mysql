package implement;

import bean.User;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl  extends AbstractDAO implements UserDaoInter {
    @Override
    public List<User> getAll()  {

        List<User> userList =new ArrayList<>();
        try(Connection connection = connect()){
            Statement statement = connection.createStatement();
            statement.execute("select * from user ");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                userList.add(new User(id,name,surname,email,phone));

            }
        }

        catch (Exception  ex){
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int userId) {

        User userList =null;
        try(Connection connection = connect()){
            Statement statement = connection.createStatement();
            statement.execute("select * from user where id="+userId);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                userList=(new User(id,name,surname,email,phone));

            }
        }

        catch (Exception  ex){
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean updateUser(User u) {

       try(Connection connection =connect()){
           PreparedStatement statement = connection.prepareStatement("update user set name =?, surname =?, email=?,phone=? where id =?");
           statement.setString(1,u.getName());
           statement.setString(2,u.getSurname());
           statement.setString(3,u.getEmail());
           statement.setString(4,u.getPhone());
           statement.setInt(5,u.getId());
         return statement.execute();

       }
       catch (Exception ex){
           ex.printStackTrace();
           return false;
       }

    }

    @Override
    public boolean removeUSer(int id) {
        try(Connection connection =connect()){

            Statement statement = connection.createStatement();
            return   statement.execute("delete from user  where id="+ id);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }




}


