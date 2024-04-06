package base;

import dbService.DBException;
import dbService.dataSets.UsersProfileDataSet;

public interface DBService {

    void printConnectInfo();
    UsersProfileDataSet getUser(String login) throws DBException;
    long addUser(String login, String pass, String email) throws DBException;
}
