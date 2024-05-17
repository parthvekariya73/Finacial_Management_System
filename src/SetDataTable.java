import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class SetDataTable {

    public static final Scanner sc = new Scanner(System.in);
    public static Connection con = null;
    public static PreparedStatement ps = null;

    public static class tbl_realestate {
        public tbl_realestate(String UserDB) {
            try {
                int t = 1;
                System.out.println("Choose Tool : \n\t1. Add New Real_Estate\n\t2. Delete Old Real_Estate\n\t3. Back");
                System.out.print("Enter : ");
                t = sc.nextInt();

                con = Connections.CreateUserConnection.CreateUserConn(UserDB);
                if (t == 1) {
                    addRS();
                } else if (t == 2) {
                    deleteRS();
                } else if (t == 3) {
                }else {
                    System.out.println("Invalid input...");
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input...");
                exit(0);
            }
        }

        public void addRS() {
            try {
                System.out.print("\tEnter Real-Estate Name : ");
                String rname = sc.next();
                System.out.print("\tEnter Real-Estate Income/Cashflow/Rent : ");
                int rcashflow = sc.nextInt();
                System.out.print("\tEnter Real-Estate Cost : ");
                int rcost = sc.nextInt();
                String q = "insert into " +
                        "realestatetbl" +
                        "(rname, rcashflow, rcost) Values(?, ?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, rname);
                ps.setInt(2, rcashflow);
                ps.setInt(3, rcost);
                ps.executeUpdate();
                System.out.println("Data inserted Successfully...");
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public boolean exist(String name) {
            final String q = "select count(*) as count from realestatetbl where rname=?";
            try{
                ps = con.prepareStatement(q);
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()) {
                    return ( rs.next() && rs.getInt("count") > 0 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void deleteRS() {
            System.out.print("\tEnter Real-Estate Name which you want to Delete: ");
            String rname = sc.next();
            if ( exist(rname) ) {
                try {
                    String q = "delete from " +
                            "realestatetbl" +
                            " where rname=? ";
                    ps = con.prepareStatement(q);
                    ps.setString(1, rname);
                    ps.executeUpdate();
                    System.out.println("Data deleted Successfully...");
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\tInvaild input / Data doesn't exist...");
            }
        }
    }

    public static class tbl_exp {
        public tbl_exp(String UserDB) {
            try {
                int t = 1;
                System.out.println("Choose Tool : \n\t1. Add Expenses\n\t2. Delete Expenses\n\t3. Back");
                System.out.print("Enter : ");
                t = sc.nextInt();

                con = Connections.CreateUserConnection.CreateUserConn(UserDB);
                if (t == 1) {
                    addEx();
                } else if (t == 2) {
                    deleteEx();
                } else if(t == 3) {}else {
                    System.out.println("Invaild input...");
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public void addEx() {
            try {
                System.out.print("\tEnter Expense Name : ");
                String ename = sc.next();
                System.out.print("\tEnter Expense Cost : ");
                int ecost = sc.nextInt();

                String q = "insert into " +
                        "expensetbl" +
                        "(ename, ecost) Values(?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, ename);
                ps.setInt(2, ecost);
                ps.executeUpdate();
                System.out.println("Data inserted Successfully...");
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public boolean exist(String name) {
            final String q = "select count(*) as count from expensetbl where ename=?";
            try{
                ps = con.prepareStatement(q);
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()) {
                    return ( rs.next() && rs.getInt("count") > 0 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void deleteEx() {
            System.out.print("\tEnter Expense Name which you want to Delete: ");
            String ename = sc.next();
            if (exist(ename)) {
                try {
                    String q = "delete from " +
                            "expensetbl" +
                            " where ename=? ";
                    ps = con.prepareStatement(q);
                    ps.setString(1, ename);
                    ps.executeUpdate();
                    System.out.println("Data deleted Successfully...");
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\tInvaild Input / Data doesn't Exist...");
            }
        }
    }

    public static class tbl_loan {
        public tbl_loan(String UserDB) {
            try {
                int t = 1;
                System.out.println("Choose Tool : \n\t1. Add Loan\n\t2. Delete Loan\n\t3. Back");
                System.out.print("Enter : ");
                t = sc.nextInt();

                con = Connections.CreateUserConnection.CreateUserConn(UserDB);
                if (t == 1) {
                    addloan();
                } else if (t == 2) {
                    deleteloan();
                } else if(t==3){}else {
                    System.out.println("Invaild input...");
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public void addloan() {
            try {
                System.out.print("\tEnter Loan Name : ");
                String lname = sc.next();
                System.out.print("\tEnter Loan Money : ");
                int lmoney = sc.nextInt();
                System.out.print("\tEnter Loan EMI : ");
                int lemi = sc.nextInt();

                String q = "insert into " +
                        "loantbl" +
                        "(lname, lmoney, lemi) Values(?, ?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, lname);
                ps.setInt(2, lmoney);
                ps.setInt(3, lemi);
                ps.executeUpdate();
                System.out.println("Data inserted Successfully...");
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public boolean exist(String name) {
            final String q = "select count(*) as count from loantbl where lname=?";
            try{
                ps = con.prepareStatement(q);
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()) {
                    return ( rs.next() && rs.getInt("count") > 0 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void deleteloan() {
            System.out.print("\tEnter Loan Name which you want to Delete: ");
            String lname = sc.next();
            if (exist(lname)) {
                try {
                    String q = "delete from " +
                            "loantbl" +
                            " where lname=? ";
                    ps = con.prepareStatement(q);
                    ps.setString(1, lname);
                    ps.executeUpdate();
                    System.out.println("Data deleted Successfully...");
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\tInvaild Input / Data doesn't Exist...");
            }
        }
    }

    public static class tbl_investmentstock {
        public tbl_investmentstock(String UserDB) {
            try {
                int t = 1;
                System.out.println("Choose Tool : \n\t1. Add Investment Stock\n\t2. Delete Investment Stock\n\t3. Back");
                System.out.print("Enter : ");
                t = sc.nextInt();

                con = Connections.CreateUserConnection.CreateUserConn(UserDB);
                if (t == 1) {
                    addIS();
                } else if (t == 2) {
                    deleteIS();
                } else if(t==3){}else {
                    System.out.println("Invaild input...");
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public void addIS() {
            try {
                System.out.print("\tEnter Stock Name : ");
                String isname = sc.next();
                System.out.print("\tEnter Stock Dividend : ");
                int isdividend = sc.nextInt();
                System.out.print("\tEnter Purchase Stock Cost : ");
                int iscost = sc.nextInt();
                System.out.print("\tEnter Purchase Total Stock : ");
                int istotal = sc.nextInt();

                String q = "insert into " +
                        "investstocktbl" +
                        "(isname, isdividend, iscost, istotal) Values(?, ?, ?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, isname);
                ps.setInt(2, isdividend);
                ps.setInt(3, iscost);
                ps.setInt(4, istotal);
                ps.executeUpdate();
                System.out.println("Data inserted Successfully...");
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public boolean exist(String name) {
            final String q = "select count(*) as count from investstocktbl where isname=?";
            try{
                ps = con.prepareStatement(q);
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()) {
                    return ( rs.next() && rs.getInt("count") > 0 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void deleteIS() {
            System.out.print("\tEnter Investment Stock Name which you want to Delete: ");
            String isname = sc.next();
            if (exist(isname)) {
                try {
                    String q = "delete from " +
                            "investstocktbl" +
                            " where isname=? ";
                    ps = con.prepareStatement(q);
                    ps.setString(1, isname);
                    ps.executeUpdate();
                    System.out.println("Data deleted Successfully...");
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\tInvaild Input / Data doesn't Exist...");
            }
        }
    }

    public static class tbl_tradingshare {
        public tbl_tradingshare(String UserDB) {
            try {
                int t = 1;
                System.out.println("Choose Tool : \n\t1. Add Trading Stock\n\t2. Delete Trading Stock\n\t3. Back");
                System.out.print("Enter : ");
                t = sc.nextInt();

                con = Connections.CreateUserConnection.CreateUserConn(UserDB);
                if (t == 1) {
                    addTS();
                } else if (t == 2) {
                    deleteTS();
                } else if(t==3){}else {
                    System.out.println("Invaild input...");
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public void addTS() {
            try {
                System.out.print("\tEnter Share Name : ");
                String tsname = sc.next();
                System.out.print("\tEnter Purchase Share Cost : ");
                int tscost = sc.nextInt();
                System.out.print("\tEnter Purchase Total SHare : ");
                int tstotal = sc.nextInt();

                String q = "insert into " +
                        "tradingstocktbl" +
                        "(tsname, tscost, tstotal) Values(?, ?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, tsname);
                ps.setInt(2, tscost);
                ps.setInt(3, tstotal);
                ps.executeUpdate();
                System.out.println("Data inserted Successfully...");
            }catch (SQLException e) {
                e.printStackTrace();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                exit(0);
            }
        }

        public boolean exist(String name) {
            final String q = "select count(*) as count from tradingstocktbl where tsname=?";
            try{
                ps = con.prepareStatement(q);
                ps.setString(1, name);
                try(ResultSet rs = ps.executeQuery()) {
                    return ( rs.next() && rs.getInt("count") > 0 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void deleteTS() {
            System.out.print("\tEnter Trading Stock Name which you want to Delete: ");
            String tsname = sc.next();
            if (exist(tsname)) {
                try {
                    String q = "delete from " +
                            "tradingstocktbl" +
                            " where tsname=? ";
                    ps = con.prepareStatement(q);
                    ps.setString(1, tsname);
                    ps.executeUpdate();
                    System.out.println("Data deleted Successfully...");
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\tInvaild Input / Data doesn't Exist...");
            }
        }
    }

}
