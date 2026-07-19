package views;

import models.Status;
import models.TVSeries;
import models.Episodes;

import java.util.Scanner;

public class TVSeriesView {
    private Scanner scan = new Scanner(System.in);

    public void displayTVMenu(){
        System.out.println("===== TV SERIES =====");
        System.out.println("[1] View TV Series");
        System.out.println("[2] Add TV Series");
        System.out.println("[3] Edit TV Series");
        System.out.println("[4] Remove TV Series");
        System.out.println("[0] Return to Main Menu");
        System.out.print("Input above option: ");
    }

    public void displayTVSeries(TVSeries tvseries){
        for (int i = 0; i < tvseries.getEpisodes().size(); i++) {
            System.out.printf("[%d] Season %d\n", i + 1, i + 1);
        }
        System.out.println("Input above option: ");
    }

    public void displayEpisodes(TVSeries tvseries, int SeasonNum){
        System.out.printf("[%d] Season %d\n", SeasonNum, SeasonNum);
        System.out.println("[%d] Season ");
    }

    public void displayEp(Episodes episode){
        String status = switch (episode.getStatus()){
            case Status.PLANNED -> "Planning to watch.";
            case Status.IN_PROGRESS -> "Watching...";
            case Status.COMPLETED -> "Have watched!";
        };


        System.out.println("===== EPISODE =====");
        System.out.println("Title: " + episode.getTitle());
        System.out.println("Run Time: " + episode.getRunTime() + "mins.");
        System.out.println("Status: " + status);

        if (episode.getRating() == -1)
            System.out.println("No rating.");
        else {
            System.out.println("Rating: " + episode.getRating() + "/10");
        }

        if (episode.getReview() == null)
            System.out.println("No review.");
        else if (episode.getReview().isEmpty())
            System.out.println("No review.");
        else
            System.out.println("Review: " + episode.getReview());
    }

    public int getChoice(){
        return Integer.parseInt(scan.nextLine());
    }
}
