package service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import data.SongList;
import model.Greetings;
import model.Song;

class Node {
    Node next, prev;
    Song song;

    Node(Song song) {
        this.song = song;
    }

    public static Node initializeSongDLL(ArrayList<Song> list) {
        if (list == null || list.isEmpty())
            return null;

        Node head = new Node(list.get(0));
        Node tail = head;

        for (int i = 1; i < list.size(); i++) {
            Node newNode = new Node(list.get(i));
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = head;
        head.prev = tail;

        return head;
    }
}

public class LoggedInUserService {
    SongList songList = new SongList();
    Scanner sc = new Scanner(System.in);
    Greetings greetings = new Greetings();
    Node head = new Node(null);

    public void playSongs() throws FileNotFoundException {
        songList.loadsongList();
        ArrayList<Song> list = songList.getSongList();

        Node current = Node.initializeSongDLL(list);
        if (current == null)
            return;

        String padding = " ".repeat(67);
        while (true) {
            Song s = current.song;

            System.out.println(padding + "------------------------------------");
            System.out.printf(padding + "| Name:- %-27s |%n", s.getTitle());
            System.out.printf(padding + "| Duration:- %-21s  |%n",
                    String.valueOf(s.getDuration()).substring(0, 3) + "s");
            System.out.printf(padding + "| Views:- %-26s |%n", s.getViews());
            System.out.printf(padding + "| Views:- %-26s |%n", "(X)BACK");
            System.out.println(padding + "------------------------------------");
            System.out.printf(padding + "(-)PREV %-20s (+)NEXT%n", "");

            char ch = sc.next().charAt(0);
            if (ch == '+') {
                current = current.next;
            } else if (ch == '-') {
                current = current.prev;
            } else {
                break;
            }
        }
    }
}