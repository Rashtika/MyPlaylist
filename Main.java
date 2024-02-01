package Arrays.partOne.LinkedList.Playlist;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a program that implements a playlist for songs
        // Create a Song class having Title and Duration for song.
        // The program will have an Album class containing a list of songs.
        // The albums will be sorted in an ArrayList
        // Songs from different albums can be added to playlist and will appear in the list in the order
        // they are added.
        // Once the songs have been added to the playlist, create a menu of options to:-
        // Quit, Skip forward to the next song, skip backwards to the previous song. Replay the current song.
        // List the songs in the playlist
        // A song must exist in an album before it can be added to the playlist (so you can only play songs that
        // you own).
        // Hint: To replay a song, consider what happened when we went back and forth from a citiy before we
        // started tracking the direction we were going.
        // As an optional extra, provide an option to remove the current song from the playlist
        // (hint: listiterator.remove())


        Album album = new Album("Blackwater park", "Opeth");
        albums.add(album);
        album.addSong("The Drapery Falls", 11.7);
        album.addSong("Harvest", 6.2);
        album.addSong("Leper Affinity", 10);
        album.addSong("Blackwater Park", 12.1);
        album.addSong("Patterns in the Ivy", 2);


        album = new Album("Sounds of Playground Fading", "In flames");
        album.addSong("Enter tragedy", 4);
        album.addSong("The Puzzle", 5.66);
        album.addSong("A New Down", 6);
        album.addSong("Deliver Us", 3.11);
        albums.add(album);

        album = new Album("1989", "Taylor Swift");
        album.addSong("Blank Space", 2.5);
        album.addSong("This Love", 4.3);
        album.addSong("Wildest Dreams", 4);
        album.addSong("Wonderland", 5.09);
        albums.add(album);

        LinkedList<Song> myFavorites = new LinkedList<Song>();
        albums.get(2).addToPlayList("Blank Space", myFavorites);
        albums.get(0).addToPlayList("Mirror", myFavorites); // does not exist
        albums.get(0).addToPlayList("Harvest", myFavorites);
        albums.get(2).addToPlayList("Wonderland", myFavorites);
        albums.get(1).addToPlayList("The Puzzle", myFavorites);
        albums.get(0).addToPlayList(4, myFavorites);
        albums.get(1).addToPlayList(3, myFavorites);
        albums.get(2).addToPlayList(2, myFavorites);
        albums.get(0).addToPlayList(16, myFavorites);

        play(myFavorites);

    }

    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;


        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }
        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Replaying the " + listIterator.previous().toString() + " song");
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else  {
                        if (listIterator.hasNext()) {
                            System.out.println("Replaying the " + listIterator.next() + " song");
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printPlaylist(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if (playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }
    private static void printPlaylist(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("======================");
        while (iterator.hasNext()) {
                System.out.println(iterator.next());
        }
        System.out.println("======================");

    }
    private static void printMenu() {
        System.out.println("Menu options:\npress ");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions\n" +
                "6 - delete current song from playlist");
    }
}
