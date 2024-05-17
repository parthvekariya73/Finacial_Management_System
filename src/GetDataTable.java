import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataTable {
    private static String UserDB;
    private static int totalIncome = 0;
    private static int totalExp = 0;

    public static class Portfolio {
            private static final String Q_RealEstate = "select * from realestatetbl";
            private static final String Q_Exp = "select * from expensetbl";
            private static final String Q_Loan = "select * from loantbl";
            private static final String Q_IStock = "select * from investstocktbl";
            private static final String Q_TStock = "select * from tradingstocktbl";

            private List<RealEstateClass> R_List = null;
            private List<ExpClass> E_List = null;
            private List<LoanCLass> L_List = null;
            private List<IStockClass> IS_List = null;
            private List<TStockClass> Ts_List = null;

            public Portfolio(String User) {
                UserDB = User;
                R_List = TableData.getRealEstateFromTable(Q_RealEstate, UserDB);
                E_List = TableData.getExpFromTable(Q_Exp, UserDB);
                L_List = TableData.getLoanTable(Q_Loan, UserDB);
                IS_List = TableData.getIStockFromTable(Q_IStock, UserDB);
                Ts_List = TableData.getTSockFromTable(Q_TStock, UserDB);

                System.out.println("\n\t|*************************************|");
                System.out.println("\t\t\t\tYour PortFoilo");
                System.out.println("\t|*************************************|\n");

                Income i = new Income(R_List, IS_List);
                Expenses e = new Expenses(E_List, L_List);
                Assets a = new Assets(R_List, IS_List, Ts_List);
                Liabilities l = new Liabilities(R_List, L_List);
                payday(i.MainIncome);
            }

            private static void payday(int income) {
                System.out.println("\n\t|*********** FINANCIAL STATEMENT **********|");
                System.out.println("\t\t\tTotal Income : " + totalIncome);
                System.out.println("\t\t\tTotal Expenses : -" + totalExp);
                System.out.println("\t\t\tPAYDAY : " + (totalIncome - totalExp));
                int sideincome = totalIncome - income;
                System.out.println("\t\t\tPassive Income(Side Income) : " + sideincome + " \n\t(Increase Passive Income to Escape RatRace)");
                System.out.println("\t|********************************************|");
            }

            private static class Income {
                private int MainIncome;
                private Income(List<RealEstateClass> R_List, List<IStockClass> IS_List) {
                    System.out.println("_______________ INCOME _______________");
                    setMainIncome();
                    totalIncome += getMainIncome();
                    System.out.println("Salary : " + getMainIncome());

                    System.out.println("Interest/Dividends : \t[Cash Flow]");
                    getdividenddata(IS_List);

                    System.out.println("Real-EState/Business : \t[Cash Flow]");
                    getrealesatedata(R_List);
                }
                private void getrealesatedata(List<RealEstateClass> R_List) {
                    for(RealEstateClass r : R_List) {
                        r.toStringIncome();
                    }
                }
                private void getdividenddata(List<IStockClass> IS_List) {
                    for(IStockClass is : IS_List) {
                        System.out.println("\t\t" + is.toStringIncome());
                    }
                }
                private void setMainIncome() {
                    String q = "select sal from userinfo where username=?";
                    try (Connection connection = Connections.ConnectionProvider.CreateConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(q)) {

                        preparedStatement.setString(1, UserDB);
                        try(ResultSet rs = preparedStatement.executeQuery()) {
                            if (rs.next()) {
                                MainIncome = rs.getInt("sal");
                            }
                        }
                        connection.close();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                private int getMainIncome() {
                    return MainIncome;
                }
            }

            private static class Expenses {
                private int MainTax;

                private Expenses(List<ExpClass> E_List, List<LoanCLass> L_List) {
                    System.out.println("_______________ EXPEENSES _______________");
                    setMainTax();
                    totalExp += getMainTax();
                    System.out.println("Taxes : " + getMainTax());

                    System.out.println("Expenses : ");
                    getexpdata(E_List);

                    System.out.println("Loan EMI : ");
                    getloanemidata(L_List);
                }
                private void getexpdata(List<ExpClass> E_List) {
                    for(ExpClass e : E_List) {
                        System.out.println("\t\t" + e.toString());
                    }
                }
                private void getloanemidata(List<LoanCLass> L_List) {
                    for(LoanCLass l : L_List) {
                        System.out.println("\t\t" + l.toStringExp());
                    }
                }
                private void setMainTax() {
                    String q = "select tax from userinfo where username=?";
                    try (Connection connection = Connections.ConnectionProvider.CreateConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(q)) {

                        preparedStatement.setString(1, UserDB);
                        try(ResultSet rs = preparedStatement.executeQuery()) {
                            if (rs.next()) {
                                MainTax = rs.getInt("tax");
                            }
                        }
                        connection.close();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                private int getMainTax() {
                    return MainTax;
                }
            }

            private static class Assets {
                private Assets(List<RealEstateClass> R_List, List<IStockClass> IS_List, List<TStockClass> TS_List) {
                    System.out.println("_______________ ASSETS _______________");

                    System.out.println("Stocks/Funds : \t\t\t [Share]");
                    getstockdata(IS_List, TS_List);

                    System.out.println("Real-EState/Business : \t [Cost]");
                    getrealestateAssetdata(R_List);
                }
                private void getstockdata(List<IStockClass> IS_List, List<TStockClass> TS_List) {
                    for(IStockClass is : IS_List) {
                        System.out.println("\t\t" + is.toStringAsstes());
                    }
                    for(TStockClass ts : TS_List) {
                        System.out.println("\t\t" + ts.toStringAsstes());
                    }
                }
                private void getrealestateAssetdata(List<RealEstateClass> R_List) {
                    for(RealEstateClass r : R_List) {
                        r.toStringAssets();
                    }
                }
            }

            private static class Liabilities {
                private Liabilities(List<RealEstateClass> R_List, List<LoanCLass> L_List) {
                    System.out.println("_______________ LIABILITIES _______________");

                    System.out.println("Loan : \t\t [Amount]");
                    getloandata(L_List);

                    System.out.println("Real-EState/Business : \t [Cost]");
                    getrealestatecostdata(R_List);
                }
                private void getloandata(List<LoanCLass> L_List) {
                    for(LoanCLass l : L_List) {
                        System.out.println("\t\t" + l.toStringLIB());
                    }
                }
                private void getrealestatecostdata(List<RealEstateClass> R_List) {
                    for(RealEstateClass r : R_List) {
                        r.toStringLIB();
                    }
                }
            }

        }

    private static class RealEstateClass {
        private String rname;
        private int rcashflow;
        private int rcost;

        private RealEstateClass(String rname, int rcashflow, int rcost) {
            this.rname = rname;
            this.rcashflow = rcashflow;
            this.rcost = rcost;
        }
        private void toStringIncome() {
            totalIncome += rcashflow;
            System.out.println("\t\t{" + rname +  " => " + rcashflow + "}");
        }
        private void toStringAssets() {
            if(rcashflow > 0)
                System.out.println("\t\t{" + rname +  " => " + rcost + "}");
        }
        private void toStringLIB() {
            if(rcashflow <= 0)
                System.out.println("\t\t{" + rname + " => " + rcost + "}");
        }

        private String getRname() {
            return rname;
        }

        private int getRcashflow() {
            return rcashflow;
        }

        private int getRcost() {
            return rcost;
        }
    }
    private static class ExpClass {
        private String ename;
        private int ecost;

        private ExpClass(String ename, int ecost) {
            this.ename = ename;
            this.ecost = ecost;
        }

        @Override
        public String toString() {
            totalExp += ecost;
            return "{" + ename +
                    ", (Payment) => " + ecost +
                    '}';
        }

        private String getEname() {
            return ename;
        }

        private int getEcost() {
            return ecost;
        }
    }
    private static class LoanCLass {
        private String lname;
        private int lmoney;
        private int lemi;

        private LoanCLass(String lname, int lmoney, int lemi) {
            this.lname = lname;
            this.lmoney = lmoney;
            this.lemi = lemi;
        }

        private String toStringExp() {
            totalExp += lemi;
            return "{" + lname + " Loan " +
                    "EMI => " + lemi +
                    '}';
        }

        private String toStringLIB() {
            return "{" + lname + " => " + lmoney + "}";
        }

        private String getLname() {
            return lname;
        }

        private int getLmoney() {
            return lmoney;
        }

        private int getLemi() {
            return lemi;
        }
    }
    private static class IStockClass {
        private String sname;
        private int sdividend;
        private int scost;
        private int stotal;

        private IStockClass(String sname, int sdividend, int scost, int stotal) {
            this.sname = sname;
            this.sdividend = sdividend;
            this.scost = scost;
            this.stotal = stotal;
        }

        private String toStringIncome() {
            totalIncome += sdividend;
            return "{" + stotal + " Shares of " + sname + " => " + sdividend + "}";
        }

        private String toStringAsstes() {
            return "{" + stotal + " Shares of " + sname + " => " + scost + "}";
        }

        private String getSname() {
            return sname;
        }

        private int getSdividend() {
            return sdividend;
        }

        private int getScost() {
            return scost;
        }

        private int getStotal() {
            return stotal;
        }
    }
    private static class TStockClass {
        private String sname;
        private int stotal;
        private int scost;

        private TStockClass(String sname, int stotal, int scost) {
            this.sname = sname;
            this.stotal = stotal;
            this.scost = scost;
        }

        private String toStringAsstes() {
            return "{" + stotal + " Shares of " + sname + " => " + scost + "}";
        }

        private String getSname() {
            return sname;
        }

        private int getStotal() {
            return stotal;
        }

        private int getScost() {
            return scost;
        }
    }

    private class TableData {

        private static List<RealEstateClass> getRealEstateFromTable(String query, String UserDB) {
            List<RealEstateClass> Robj = new ArrayList<>();

            try (Connection connection = Connections.CreateUserConnection.CreateUserConn(UserDB);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String rname  = resultSet.getString("rname");
                    int rcashflow = resultSet.getInt("rcashflow");
                    int rcost = resultSet.getInt("rcost");
                    Robj.add(new RealEstateClass(rname, rcashflow, rcost));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Robj;
        }
        private static List<ExpClass> getExpFromTable(String query, String UserDB) {
            List<ExpClass> Eobj = new ArrayList<>();

            try (Connection connection = Connections.CreateUserConnection.CreateUserConn(UserDB);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String ename  = resultSet.getString("ename");
                    int ecost = resultSet.getInt("ecost");
                    Eobj.add(new ExpClass(ename, ecost));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Eobj;
        }
        private static List<LoanCLass> getLoanTable(String query, String UserDB) {
            List<LoanCLass> Lobj = new ArrayList<>();

            try (Connection connection = Connections.CreateUserConnection.CreateUserConn(UserDB);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String lname  = resultSet.getString("lname");
                    int lmoney = resultSet.getInt("lmoney");
                    int lemi = resultSet.getInt("lemi");
                    Lobj.add(new LoanCLass(lname, lmoney, lemi));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Lobj;
        }
        private static List<IStockClass> getIStockFromTable(String query, String UserDB) {
            List<IStockClass> ISobj = new ArrayList<>();

            try (Connection connection = Connections.CreateUserConnection.CreateUserConn(UserDB);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String sname  = resultSet.getString("isname");
                    int sdividend = resultSet.getInt("isdividend");
                    int scost = resultSet.getInt("iscost");
                    int stotal = resultSet.getInt("istotal");
                    ISobj.add(new IStockClass(sname, sdividend,scost, stotal));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ISobj;
        }
        private static List<TStockClass> getTSockFromTable(String query, String UserDB) {
            List<TStockClass> TSobj = new ArrayList<>();

            try (Connection connection = Connections.CreateUserConnection.CreateUserConn(UserDB);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String tsname  = resultSet.getString("tsname");
                    int tscost = resultSet.getInt("tscost");
                    int tstotal = resultSet.getInt("tstotal");
                    TSobj.add(new TStockClass(tsname, tscost, tscost));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return TSobj;
        }
    }

}
