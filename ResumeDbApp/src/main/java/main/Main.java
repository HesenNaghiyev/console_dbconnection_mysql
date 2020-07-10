package main;

import bean.User;
import dao.inter.UserDaoInter;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao=Context.instanceOfDaoImpl();
//

//        userDao.removeUSer(2);

//        List<User> list2= userDao.getAll();
//        System.out.println("List"+list);
//        System.out.println("List2"+list2);

        User u =userDao.getById(1);
        u.setName("name");
        userDao.updateUser(u);

        List<User> list= userDao.getAll();
        System.out.println(list);

    }


}
