package quiztastic.core;

/** A Jeopardy Board
 *
 */
public class Board {

    private  String header = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+%n";
    private  String categoryLeftAlignFormat = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String separator = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+%n";
    private  String scoreLeftAlignFormatRow1 = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String scoreLeftAlignFormatRow2 = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String scoreLeftAlignFormatRow3 = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String scoreLeftAlignFormatRow4 = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String scoreLeftAlignFormatRow5 = "| %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private  String footer = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+%n";

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
