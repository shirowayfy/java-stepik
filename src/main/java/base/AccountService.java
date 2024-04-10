package base;

import dbService.dataSets.UsersProfileDataSet;

public interface AccountService {

    void setUsersLimit(int usersLimit);
    int getUsersLimit();

    void addNewUser(String login, String pass, String email);
    UsersProfileDataSet getUserByLogin(String login);
}
