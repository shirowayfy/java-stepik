package accounts.controller;

import base.AccountService;

public class AccountServerController implements AccountServerControllerMBean {

    private final AccountService accountService;

    public AccountServerController(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public int getUsersLimit() {
        return accountService.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit){
        accountService.setUsersLimit(usersLimit);
    }
}
