package quiztastic.core;

/** A Jeopardy Board
 *
 */
public class Board {

    private  String header = "+-----------------+------+%n";
    private  String categoryLeftAlignFormat = "| %-15s | %-4d |%n";
    private  String separator = "+-----------------+------+%n";
    private  String scoreLeftAlignFormatRow1 = "| %-15s | %-4d |%n";
    private  String scoreLeftAlignFormatRow2 = "| %-15s | %-4d |%n";
    private  String scoreLeftAlignFormatRow3 = "| %-15s | %-4d |%n";
    private  String scoreLeftAlignFormatRow4 = "| %-15s | %-4d |%n";
    private  String scoreLeftAlignFormatRow5 = "| %-15s | %-4d |%n";
    private  String footer = "+-----------------+------+%n";

    public Board(String header, String categoryLeftAlignFormat, String separator, String scoreLeftAlignFormatRow1, String scoreLeftAlignFormatRow2, String scoreLeftAlignFormatRow3, String scoreLeftAlignFormatRow4, String scoreLeftAlignFormatRow5, String footer) {
        this.header = header;
        this.categoryLeftAlignFormat = categoryLeftAlignFormat;
        this.separator = separator;
        this.scoreLeftAlignFormatRow1 = scoreLeftAlignFormatRow1;
        this.scoreLeftAlignFormatRow2 = scoreLeftAlignFormatRow2;
        this.scoreLeftAlignFormatRow3 = scoreLeftAlignFormatRow3;
        this.scoreLeftAlignFormatRow4 = scoreLeftAlignFormatRow4;
        this.scoreLeftAlignFormatRow5 = scoreLeftAlignFormatRow5;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public String getCategoryLeftAlignFormat() {
        return categoryLeftAlignFormat;
    }

    public String getSeparator() {
        return separator;
    }

    public String getScoreLeftAlignFormatRow1() {
        return scoreLeftAlignFormatRow1;
    }

    public String getScoreLeftAlignFormatRow2() {
        return scoreLeftAlignFormatRow2;
    }

    public String getScoreLeftAlignFormatRow3() {
        return scoreLeftAlignFormatRow3;
    }

    public String getScoreLeftAlignFormatRow4() {
        return scoreLeftAlignFormatRow4;
    }

    public String getScoreLeftAlignFormatRow5() {
        return scoreLeftAlignFormatRow5;
    }

    public String getFooter() {
        return footer;
    }
}
