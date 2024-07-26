package com.jap.task;

import java.util.*;

public class Authenticator {

    // Declare attributes: users - a list of users, authenticatedUser - the currently authenticated user
  private List<User> list;
  private User user;
  private User authenticatedUser = null;

    // Generate getter and setter methods for both attributes



    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authenticator() {
        // Initialize the users list and add a few users
        list=new ArrayList<User>();
        list.add(new User("pratik","pass123"));
        list.add(new User("sumit","admin123"));
        list.add(new User("admin","setup123"));
        list.add(new User("John","pass1"));
    }

    public User getAuthenticatedUser() {
        // Return the currently authenticated user
        user=new User("sham","pass123");

        return user;
    }

    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username to authenticate.
     * @param password The password associated with the username.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String username, String password) {
        // Iterate through the users list
        try {
            Iterator<User> it = list.iterator();
            // Check if the given username and password match any user's credentials
            boolean flag = false;
            while (it.hasNext()) {
                User user = it.next();

                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    flag = true;
                }
            }
            // If a match is found, assign the user to the authenticatedUser and return true
            if (flag) {
              list.add(new User(username,password));
                return true;
            }

        }catch (Exception e){
            System.out.println("something happen wrong in Authenticate user");
        }
            return false;

    }

    /**
     * Authenticates a new user with the given username and password.
     *
     * @param username The username of the new user.
     * @param password The password associated with the new user.
     * @return The authenticated user if authentication is successful, null otherwise.
     */
    public User authenticateNewUser(String username, String password) {

        // Call the authenticateUser method to check if the new user's credentials are valid
        try {
            authenticatedUser=new User();
            Authenticator authenticator = new Authenticator();
            boolean check = authenticator.authenticateUser(username, password);
          //  System.out.println("check " + check);
            // If the authenticateUser method returns true, set authenticatedUser to the authenticated user
            if (check) {
                authenticatedUser.setUserName(username);
                authenticatedUser.setPassword(password);
                System.out.println("Authentication Successful: Welcome " + username);
            } else {
                System.out.println("invalid username or password");
            }
            // Display an appropriate message
        }catch (Exception e){
            System.out.println("something happen wrong in authenticated ");
        }

        return authenticatedUser;
    }
}
