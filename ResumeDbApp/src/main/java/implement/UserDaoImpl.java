package implement;

import entity.Country;
import entity.User;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class UserDaoImpl  extends AbstractDAO implements UserDaoInter {


    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        Date birthDate = rs.getDate("birthdate");
        String desc =rs.getString("profileInfo");
        int nationality_id =rs.getInt("nationality_id");
        int birthplace_id =rs.getInt("birthplace_id");
        String nationality = rs.getString("nationality");
        String birthplace = rs.getString("birthplace");

        Country nation = new Country(nationality_id,null,nationality);
        Country birth = new Country(birthplace_id,birthplace,null);

       return new User(id,name,surname,email,phone,birthDate,desc,nation,birth);

    }


    public List<User> getAll()  {

        List<User> userList =new ArrayList<>();
        try(Connection connection = connect()){
            Statement statement = connection.createStatement();
            statement.execute("SELECT "
                    + "u.*,"
                    + " n.nationality as nationality, "
                    + " c.name as birthplace  "
                    + "from user u "
                    + "LEFT JOIN country n on u.nationality_id=n.id "
                    + "LEFT JOIN country c on u.birthplace_id=c.id  ");
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
              User u = getUser(rs);
              userList.add(u);
            }
        }

        catch (Exception  ex){
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int userId) {

        User specificUser =null;
        try(Connection connection = connect()){
            Statement statement = connection.createStatement();
            statement.execute("SELECT "
                    + "u.*,"
                    + " n.nationality as nationality, "
                    + " c.name as birthplace  "
                    + "from user u "
                    + "LEFT JOIN country n on u.nationality_id=n.id "
                    + "LEFT JOIN country c on u.birthplace_id=c.id where u.id= " +userId);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                specificUser=getUser(rs);

            }
        }

        catch (Exception  ex){
            ex.printStackTrace();
        }
        return specificUser;
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
    public boolean insertUser(User u2) {
        try(Connection connection =connect()){
            PreparedStatement statement = connection.prepareStatement("insert into user(name, surname, email, phone) values (?, ?, ?,?)");
            statement.setString(1,u2.getName());
            statement.setString(2,u2.getSurname());
            statement.setString(3,u2.getEmail());
            statement.setString(4,u2.getPhone());
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
