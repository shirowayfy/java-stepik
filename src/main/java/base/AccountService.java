package base;

import dbService.dataSets.UsersProfileDataSet;

public interface AccountService {
    void addNewUser(String login, String pass, String email);
    UsersProfileDataSet getUserByLogin(String login);
}
