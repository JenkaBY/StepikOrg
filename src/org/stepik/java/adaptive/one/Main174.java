package org.stepik.java.adaptive.one;

import java.util.Scanner;

public class Main174 {

/*
    You have login and password as integer numbers stored in the code as login and password variables.
    The user inputs two integers (the login and the password). If they match to one in the code - output "Login success",
    if the password doesn't match, but logins match, output "Wrong password", if login is wrong,
    output "No user with login XXXX found", where XXXX is the login, the user's just input.
    INPUT
        Two integers, the first - login, the second - password.
    OUTPUT
        "Login success" if both match, "Wrong password" if passwords do not match, but logins match and
        "No user with login XXXX found" if logins do not match (XXXX is the login, the user has input).
*/

    public static void main(String[] args) {
        int login = 100500;
        int password = 424242;
        String[] loginAndPassword = new Scanner(System.in).nextLine().split("\\s+");
        print(new DoLogin(login, password).perform(numberFromStr(loginAndPassword[0]), numberFromStr(loginAndPassword[1])).getResult());
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public static Integer numberFromStr(String readLine) {
        return Integer.valueOf(readLine);
    }

    public static abstract class LoginResponse {
        protected Response response;
        protected int login;
        protected int password;

        public LoginResponse(int login, int password) {
            this.login = login;
            this.password = password;
        }

        public Response getResponse() {
            return response;
        }

        public abstract String getResult();
    }

    public static class SuccessResponse extends LoginResponse {
        public SuccessResponse(int login, int password) {
            super(login, password);
            response = Response.SUCCESS;
        }

        @Override
        public String getResult() {
            return "Login success";
        }
    }

    public static abstract class FailureResponse extends LoginResponse {
        public FailureResponse(int login, int password) {
            super(login, password);
            response = Response.FAILURE;
        }
    }

    public static class WrongPassword extends FailureResponse {
        public WrongPassword(int login, int password) {
            super(login, password);
        }

        @Override
        public String getResult() {
            return "Wrong password";
        }
    }

    public static class WrongLogin extends FailureResponse {
        public WrongLogin(int login, int password) {
            super(login, password);
        }

        @Override
        public String getResult() {
            return "No user with login " + login + " found";
        }
    }

    public static class DoLogin {
        protected int loginDB;
        protected int passDB;

        public DoLogin(int loginDB, int passDB) {
            this.loginDB = loginDB;
            this.passDB = passDB;
        }

        public LoginResponse perform(int login, int password) {
            if (login == loginDB && password == passDB) {
                return new SuccessResponse(login, password);
            }
            if (login == loginDB && password != passDB) {
                return new WrongPassword(login, password);
            }
            return new WrongLogin(login, password);
        }
    }

    enum Response {
        SUCCESS, FAILURE;
    }

}