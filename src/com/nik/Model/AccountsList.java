package com.nik.Model;

import java.util.ArrayList;

public class AccountsList {
    private ArrayList<Account> accounts;

    public AccountsList(){
        accounts = new ArrayList<>();
    }

    // getter
    public ArrayList<Account> getAccounts() {return accounts;}

    /** Creates a new account */
    public void addAccount(String username, String password, String firstName, String lastName, int age, int id, boolean isAdmin){
        if(accounts.isEmpty() || findByID(id) == null){ // if there are no accounts, or no accounts with the same id, add a new account with the info provided
            accounts.add(new Account(username, password, firstName, lastName, age, id, isAdmin));
        }else // give error message
            System.out.println("Copy of ID on existing account. Can't create new account under the username: " + username);
    }

    /** Helps other methods of this class when they need to search an Account by id */
    private Account findByID(int id){
        if(accounts.isEmpty())
            return null; // no objects in accounts
        for(Account account : accounts){
            if(account.getId() == id)
                return account; // found the account, return it
        }
        return null; // nothing found
    }

    public Account findByName(String username){
        if(accounts.isEmpty()) // no accounts to search through
            return null;
        for(Account account : accounts){
            if(account.getUsername().equals(username))
                return account; // found the account with the username
        }
        return null; // nothing found
    }
}
