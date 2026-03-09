package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Greetings;
import service.AdminService;
import service.LoggedInUserService;
import service.Menu;

public class LoggedInUserController {
    Menu menu = new Menu();
    Scanner sc = new Scanner(System.in);
    Greetings greetings = new Greetings();
    AdminService adminService = new AdminService();
    LoggedInUserService loggedInUserService = new LoggedInUserService();

    public void loggedInUser() throws InterruptedException, FileNotFoundException {
        while (true) {
            menu.LoggedInUser();
            greetings.enter();
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    loggedInUserService.playSongs();
                    break;

                case 2:
                    adminService.searchSong();
                    break;
                case 3:
                    System.out.println("WORKING");
                    break;

                case 4:
                    adminService.showSongs();
                    break;

                default:
                    return;
            }
        }
    }
}