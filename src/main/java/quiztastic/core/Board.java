package quiztastic.core;

/** A Jeopardy Board
 *
 */
public class Board {
    
    private String header = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+";
    private String categoryLeftAlignFormat = "| %-30s | %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private String separator = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+";
    private String scoreLeftAlignFormatRow = "| %-30s | %-30s | %-30s | %-30s | %-30s | %-30s |%n";
    private String footer = "+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+--------------------------------+";
//    private int status;

//    public Board() {
//
//        this.status = 0;
//    }

    public String getHeader() {
        return header;
    }

    public String getCategoryLeftAlignFormat() {
        return categoryLeftAlignFormat;
    }

    public String getSeparator() {
        return separator;
    }

    public String getScoreLeftAlignFormatRow() {
        return scoreLeftAlignFormatRow;
    }

    public String getFooter() {
        return footer;
    }

//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
}
