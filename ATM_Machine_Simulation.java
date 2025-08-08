import java.util.*;

public class ATM_Machine_Simulation {

    // function for the user to login.
    public static boolean login(HashMap<String, Integer> initialDataMap, Scanner sc) {

        int i = 0;
        String cardnumber = "";
        // Giving 3 chances to the user in case they type incorrect card number.
        for (i = 0; i < 3; i++) {
            System.out.print("Enter your 16 digit Debit / ATM Card number: ");

            cardnumber = sc.nextLine().trim();

            if (!initialDataMap.containsKey(cardnumber)) {
                if (i == 2) {
                    i++;
                    break;
                }
                System.out.println("Invalid Card number. Try again.");
            } else
                break;
        }
        if (i == 3) {
            System.out.println("Access Denied since you have Entered wrong Card Number 3 times.");
            return false;
        }

        int j = 0;
        // Giving 3 chances to the user in case they type incorrect PIN.
        for (j = 0; j < 3; j++) {
            System.out.print("Enter your PIN: ");
            int PIN = sc.nextInt();
            if (initialDataMap.get(cardnumber).equals(PIN)) {
                return true;
            } else {
                if (j == 2) {
                    j++;
                    break;
                }
                System.out.println("Invalid PIN entered. Try again.");
            }
        }
        if (j == 3) {
            System.out.println("Access Denied since you have Entered wrong PIN 3 times.");
            return false;
        }
        return true;
    }

    // function to deposit money into their bank account.
    public static int depositMoney(int balance, List<String> transaction_history, Scanner sc) {
        System.out.print("Enter the amount of money you want to deposit into your account: ");

        int deposit_money = sc.nextInt();

        balance += deposit_money;
        String str = "Deposited INR ";
        String str2 = Integer.toString(deposit_money);
        str += str2;
        transaction_history.add(str);

        System.out.println("INR " + deposit_money + " has been Successfully deposited into your account.");

        return balance;
    }

    // function to withdraw money from their bank account.
    public static int withdrawMoney(int balance, List<String> transaction_history, Scanner sc) {
        System.out.print("Enter the amount of money you want to withdraw from your account: ");
        int withdraw_money = sc.nextInt();

        if (withdraw_money > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= withdraw_money;
            String str = "Withdrew INR ";
            String str2 = Integer.toString(withdraw_money);
            str += str2;
            transaction_history.add(str);

            System.out.println("You withdrew INR " + withdraw_money + " from your account.");
        }

        return balance;
    }

    // function to view their transaction history.
    public static void viewTransactionHistory(List<String> transaction_history) {
        if (transaction_history.isEmpty()) {
            System.out.println("You have not done any transactions yet.");
            return;
        }
        System.out.println("The Transaction History ordered by recent transactions on top: \n");

        int count = 1;
        for (int i = transaction_history.size() - 1; i >= 0; i--) {
            System.out.println(count + ". " + transaction_history.get(i));
            count++;
        }
    }

    public static void main(String[] args) {
        // using a hashmap initially to store data of 3 users.
        // storing card number in form of a string.
        HashMap<String, Integer> initialDataMap = new HashMap<>();
        initialDataMap.put("1234567895619785", 1596);
        initialDataMap.put("4589632574198627", 4427);
        initialDataMap.put("9715236842559663", 9636);

        System.out.println("-------------Welcome to ATM Machine.---------------\n\n");
        System.out.println("----------------LOGIN-----------------\n");

        Scanner sc = new Scanner(System.in);
        boolean ans = login(initialDataMap, sc);

        if (ans == false)
            return;

        int balance = 0;
        List<String> transaction_history = new ArrayList<>();
        System.out.println("----------------------------------------------------------");

        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("The following options are available to choose: \n");
            System.out.println("1. Check Account Balance.");
            System.out.println("2. Deposit Money.");
            System.out.println("3. Withdraw Money.");
            System.out.println("4. View Transaction History.");
            System.out.println("5. EXIT.\n");
            System.out.print("Enter your choice number: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("The Balance amount in your account is INR " + balance);
                    break;
                case 2:
                    balance = depositMoney(balance, transaction_history, sc);
                    break;
                case 3:
                    balance = withdrawMoney(balance, transaction_history, sc);
                    break;
                case 4:
                    viewTransactionHistory(transaction_history);
                    break;
                case 5:
                    System.out.println("You have exited. Thank You.");
                    break;
                default:
                    System.out.println("Invalid choice number.");
                    break;
            }

            if (choice == 5)
                break;

        }
    }
}
