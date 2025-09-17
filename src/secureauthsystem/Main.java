/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package secureauthsystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SecureAuthSystem Demo ===");
        System.out.println("Type 'r' to register, 'l' to login, 'q' to quit.");

        while (true) {
            System.out.print("Option (r/l/q): ");
            String opt = sc.nextLine().trim().toLowerCase();
            if (opt.equals("q")) {
                System.out.println("Goodbye.");
                break;
            } else if (opt.equals("r")) {
                System.out.print("Username: "); String username = sc.nextLine();
                System.out.print("Password: "); String password = sc.nextLine();
                System.out.print("First name: "); String fn = sc.nextLine();
                System.out.print("Last name: "); String ln = sc.nextLine();
                System.out.print("Cellphone (SA): "); String cell = sc.nextLine();

                AuthService.LoginStatus status = auth.registerUser(username, password, fn, ln, cell);
                System.out.println(auth.returnLoginStatus(status));

            } else if (opt.equals("l")) {
                System.out.print("Username: "); String username = sc.nextLine();
                System.out.print("Password: "); String password = sc.nextLine();

                AuthService.LoginStatus status = auth.loginUser(username, password);
                System.out.println(auth.returnLoginStatus(status));
            } else {
                System.out.println("Unknown option.");
            }
        }

        sc.close();
    }
}
