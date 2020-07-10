package main;

import implement.UserDaoImpl;

public class Context {

    public static UserDaoImpl instanceOfDaoImpl(){
        return new UserDaoImpl();
    }

    /**
     * kind of Factory Pattern
     */
}
