package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import data.SongList;

class Node {
    Song song;
    Node next;
}

public class TrendingSongPriorityQ {
    private Node head = null;
    private SongList songList = new SongList();

    private Node insert(Song newSong, Node head) {
        Node newNode = new Node();
        newNode.song = newSong;

        if (head == null || newSong.views >= head.song.views) {
            newNode.next = head;
            return newNode;
        }

        Node current = head;
        while (current.next != null && current.next.song.views > newSong.views) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        return head;
    }

    public void initQ() throws FileNotFoundException {
        songList.loadsongList();
        ArrayList<Song> songs = songList.getSongList();

        for (Song s : songs) {
            head = insert(s, head);
        }
    }

    public void displayQueue() {
        Node ptr = head;
        System.out.println("Trending Songs (High to Low Views):");
        while (ptr != null) {
            System.out.print(ptr.song.id + " (" + ptr.song.views + " views) -> ");
            ptr = ptr.next;
        }
        System.out.println("NULL");
    }

    public ArrayList<Song> getTop3TrendingSongs() {
        ArrayList<Song> top3 = new ArrayList<>();
        if (head == null) {
            return null;
        }

        Node ptr = head;
        int ctr = 0;
        while (ctr < 3) {
            ctr++;
            top3.add(ptr.song);
            ptr = ptr.next;
        }

        return top3;
    }

    public ArrayList<Song> getTop10TrendingSongs() {
        ArrayList<Song> top10 = new ArrayList<>();
        if (head == null) {
            return null;
        }

        Node ptr = head;
        int ctr = 0;
        while (ctr < 10) {
            ctr++;
            top10.add(ptr.song);
            ptr = ptr.next;
        }

        return top10;
    }
}