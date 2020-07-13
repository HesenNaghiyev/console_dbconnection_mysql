package main;

import dao.inter.UserDaoInter;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter userDao=Context.instanceOfDaoImpl();
        System.out.println(userDao.getAll());


    }


}
