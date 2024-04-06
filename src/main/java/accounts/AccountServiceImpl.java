package accounts;

import base.AccountService;
import base.DBService;
import dbService.DBException;
import dbService.dataSets.UsersProfileDataSet;



public class AccountServiceImpl implements AccountService {
    private final DBService dbService;



    public AccountServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }

    public void addNewUser(String login, String pass, String email) {
        try {

            dbService.addUser(login, pass, email);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UsersProfileDataSet getUserByLogin(String login) {
        try {
            return dbService.getUser(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return  null;
    }


}
