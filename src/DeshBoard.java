import java.util.InputMismatchException;
import java.util.Scanner;

public class DeshBoard {
    private int num = 0;
    private boolean flag = true;
    private final Scanner sc = new Scanner(System.in);
    private String UserDB;

    public DeshBoard(String UserDB) {
        this.UserDB = UserDB;
    }

    public void Run() {
        try {
            while (flag) {
                System.out.println("\n\t----------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t\tDeshBoard");
                System.out.println("\t----------------------------------------------------------");

                System.out.println("Choose a Menu ->\n\t1. Real-Estate\n\t2. Expenses\n\t3. Loan\n\t" +
                        "4. Investment Stock\n\t5. Trading Stock\n\t6. View Portfolio\n\t7. Log Out");
                System.out.print("Enter : ");
                num = sc.nextInt();

                switch (num) {
                    case 1 -> { // real-estate : string rname(asstes, GetDataTable.income), rcashflow(GetDataTable.income), rcost(asstes)
                        SetDataTable.tbl_realestate t = new SetDataTable.tbl_realestate(UserDB);
                    }

                    case 2 -> { // Exp : string ename(GetDataTable.Expenses), ecost(GetDataTable.Expenses)
                        SetDataTable.tbl_exp e = new SetDataTable.tbl_exp(UserDB);
                    }

                    case 3 -> { // Loan : lname(libality, GetDataTable.Expenses(emi)), lmoney(libality), lemi(GetDataTable.Expenses)
                        SetDataTable.tbl_loan l = new SetDataTable.tbl_loan(UserDB);
                    }

                    case 4 -> { // Investemnt stock : sname(GetDataTable.income), sdividends(GetDataTable.income), scost(asstes), stotal(asstes)
                        SetDataTable.tbl_investmentstock is = new SetDataTable.tbl_investmentstock(UserDB);
                    }

                    case 5 -> { // Trading stock : sname(asstes), stotal(asstes), scost(asstes)
                        SetDataTable.tbl_tradingshare ts = new SetDataTable.tbl_tradingshare(UserDB);
                    }

                    case 6 -> { // view Portfolio
                        GetDataTable.Portfolio p = new GetDataTable.Portfolio(UserDB);
                    }

                    case 7 -> {
                        System.out.println("LogOut.....\n");
                        flag = false;
                    }

                    default -> System.out.println("Invalid input...");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input...Try Again.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
