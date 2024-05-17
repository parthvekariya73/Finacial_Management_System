import java.sql.*;
import java.util.Scanner;

public class User {
    private int op;
    public static final Scanner sc = new Scanner(System.in);

    public User() {
        System.out.println("|************************************************|");
        System.out.println("|     WELCOME To FINANCIAL MANAGEMENT SYSTEM     |");
        System.out.println("|************************************************|\n");
        op = 0;
    }

    public void UserRun() {
        var flag = true;
        while(flag)
        {
            try {
                System.out.print("Choose a Option : \n\t1. Login\n\t2. Registration\n\t3. Exit\nEnter Number : ");
                op = sc.nextInt();
                switch (op) {   // option here ->
                    case 1 -> {    login l = new login();   }
                    case 2 -> {     Registration r = new Registration();  }
                    case 3 -> {     flag = false;   }
                    default -> {     System.out.println("Invalid input...\nTry Again");     }
                }
            } catch (Exception e) {
                System.out.println("Invalid input...\n" +
                        "Try Again");
                flag = false;
            }
        }
    }

    private static class login {
        private String uname, pass;
        private login() {
            System.out.println("---> Login ");
            System.out.print("Enter UserName : ");
            uname = sc.next();
            System.out.print("Enter Password : ");
            pass = sc.next();

            if(checkLogin(uname, pass)) {   // check username and password exist or not
                System.out.println("\tLogin Successfully...");
                // connection on User Database
                try{
                    Connection con = Connections.CreateUserConnection.CreateUserConn(uname);
                    System.out.println("\tUser Connection Successfully...");

                    // DeshBoard Start
                    DeshBoard d = new DeshBoard(uname);
                    d.Run();

                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else {
                System.out.println("\nInvaild username or Password...");
                System.out.println("Try Again...");
            }
        }
    }

    private static class Registration {
        private String uname, pass, work;
        private int sal = 0, tax = 0, cash = 0;

        private Registration() {
            System.out.println("---> Registraction");
            System.out.print("Enter UserName : ");
            uname = sc.next();

            if(checkLogin(uname)) {
                System.out.println("This UserName is Already Exist...");
                System.out.println("Try Again...\n");
            } else {
                try {
                    System.out.print("Enter Password : ");
                    pass = sc.next();
                    System.out.print("Enter Your Name of job/Business work : ");
                    work = sc.next();
                    System.out.print("Enter Your Salary : ");
                    sal = sc.nextInt();
                    System.out.print("Enter Income Tax : ");
                    tax = sc.nextInt();
                    System.out.print("Enter Current Cash amount(wallet) : ");
                    cash = sc.nextInt();

                    if (addUser(uname, pass, work, sal, tax, cash)) {
                        System.out.print("\tUser Successfully Added...");
                        System.out.println("(can be log in)\n");
                    } else {
                        System.out.println("Something want Wrong...");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            }
        }

        private static boolean addUser(String uname, String pass, String work, int sal, int tax, int cash) {
            boolean flag = false;
            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = Connections.ConnectionProvider.CreateConnection();
                String q = "insert into userinfo(username, password, work, sal, tax, cash) Values(?, ?, ?, ?, ?, ?)";
                // prepared Statement
                ps = con.prepareStatement(q);
                // set values of parameter
                ps.setString(1, uname);
                ps.setString(2, pass);
                ps.setString(3, work);
                ps.setInt(4, sal);
                ps.setInt(5, tax);
                ps.setInt(6, cash);

                // execute
                ps.executeUpdate();

                // create user database for store user data
                flag = createUserDB(uname);

            }catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        }

        private static boolean createUserDB(String uname) {
            boolean flag = false;
            Connection con = null;
            Statement statement = null;

            try {
                uname = uname + "db";

                // create connection
                String user = "root";
                String pass = "";
                String url = "jdbc:mysql://localhost:3306/";
                con = DriverManager.getConnection(url, user, pass);
                statement = con.createStatement();

                String q = "create database " + uname;

                statement.executeUpdate(q);
                System.out.println("\tProcessing...\n\tDatabase created Successfully...");
                statement.close();
                con.close();

                // create table for store different category of user data
                if(CreatePredefinedTable(uname)) {
                    System.out.println("\tTables created Successfully");
                } else {
                    System.out.println("\tSomething want Wrong...");
                }

                flag = true;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        }

        private static boolean checkLogin(String Uname) {
            boolean flag = false;
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = Connections.ConnectionProvider.CreateConnection();

                String q = "select count(*) as count from userinfo where username = ?";

                // prepared Statement
                ps = ((Connection) con).prepareStatement(q);

                ps.setString(1, Uname);

                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    int count = rs.getInt("count");
                    if(count > 0) {
                        flag = true;
                    }
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        }

        private static boolean CreatePredefinedTable(String uname) {
            boolean flag = false;
            Connection con = null;
            Statement statement = null;

            try {
                // create connection
                String user = "root";
                String pass = "";
                String url = "jdbc:mysql://localhost:3306/" + uname;
                con = DriverManager.getConnection(url, user, pass);
                statement = con.createStatement();

                String q1 = "create table realestatetbl(" +
                        "rname varchar(20)," +
                        "rcashflow int(10)," +
                        "rcost int(10)" +
                        ")";

                String q2 = "create table expensetbl(" +
                        "ename varchar(20)," +
                        "ecost int(10)" +
                        ")";

                String q3 = "create table loantbl(" +
                        "lname varchar(20)," +
                        "lmoney int(10)," +
                        "lemi int(7)" +
                        ")";

                String q4 = "create table investstocktbl(" +
                        "isname varchar(20)," +
                        "isdividend int(10)," +
                        "iscost int(10)," +
                        "istotal int(10)" +
                        ")";

                String q5 = "create table tradingstocktbl(" +
                        "tsname varchar(20)," +
                        "tscost int(10)," +
                        "tstotal int(10)" +
                        ")";

                statement.executeUpdate(q1);
                statement.executeUpdate(q2);
                statement.executeUpdate(q3);
                statement.executeUpdate(q4);
                statement.executeUpdate(q5);

                flag = true;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        }
    }

    private static boolean checkLogin(String Uname, String pass) {
        // check username and password exist or not
        boolean flag = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Connections.ConnectionProvider.CreateConnection();
            String q = "select count(*) as count from userinfo where username = ? and password = ? ";
            ps = ((Connection) con).prepareStatement(q);

            ps.setString(1, Uname);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int count = rs.getInt("count");
                if(count > 0) {
                    flag = true;    // user exist
                }
            }
            ps.close();
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;    // user doesn't exist
    }
}
