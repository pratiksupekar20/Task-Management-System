package com.jap.task;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Authenticator userAuthenticator=new Authenticator();
        TaskManager taskManagerNew = new TaskManager();
        User authenticatedUser=null;
        boolean flag=false;
        while (authenticatedUser==null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Username");
            String username = scanner.next();
            System.out.println("Enter your Password");
            String password = scanner.next();
            authenticatedUser=userAuthenticator.authenticateNewUser(username,password);
            //System.out.println("testing "+authenticatedUser);
          flag=   userAuthenticator.authenticateUser(username,password);
        }
        if(flag) {
            taskManagerNew.takeChoices(authenticatedUser);
        }else{
            System.out.println("please try agian...");
        }






    }
}
