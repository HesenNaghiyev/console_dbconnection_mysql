package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {

    public List<User> getAll() throws Exception;
    public User getById(int id);

    public boolean updateUser(User u);

    public boolean insertUser(User u);

    public boolean removeUSer(int id);

}
