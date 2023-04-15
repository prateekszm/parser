package parser.pdfparser.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.text.TextPositionComparator;

public class LayoutTextStripper extends PDFTextStripper {

    public static final boolean DEBUG = false;
    public static final int OUTPUT_SPACE_CHARACTER_WIDTH_IN_PT = 4;

    private double currentPageWidth;
    private TextPosition previousTextPosition;
    private List<TextLine> textLineList;

    /**
     * Constructor
     */
    public LayoutTextStripper() throws IOException {
        super();
        this.previousTextPosition = null;
        this.textLineList = new ArrayList<TextLine>();
    }


    @Override
    public void processPage(PDPage page) throws IOException {
        PDRectangle pageRectangle = page.getMediaBox();
        if (pageRectangle!= null) {
            this.setCurrentPageWidth(pageRectangle.getWidth());
            super.processPage(page);
            this.previousTextPosition = null;
            this.textLineList = new ArrayList<TextLine>();
        }
    }

    @Override
    protected void writePage() throws IOException {
        List<List<TextPosition>> charactersByArticle = super.getCharactersByArticle();
        for( int i = 0; i < charactersByArticle.size(); i++) {
            List<TextPosition> textList = charactersByArticle.get(i);
            try {
                this.sortTextPositionList(textList);
            } catch ( java.lang.IllegalArgumentException e) {
                System.err.println(e);
            }
            this.iterateThroughTextList(textList.iterator()) ;
        }
        this.writeToOutputStream(this.getTextLineList());
    }

    private void writeToOutputStream(final List<TextLine> textLineList) throws IOException {
        for (TextLine textLine : textLineList) {
            char[] line = textLine.getLine().toCharArray();
            super.getOutput().write(line);
            super.getOutput().write('\n');
            super.getOutput().flush();
        }
    }

   
    private void sortTextPositionList(final List<TextPosition> textList) {
        TextPositionComparator comparator = new TextPositionComparator();
        textList.sort(comparator);
    }

    private void writeLine(final List<TextPosition> textPositionList) {
        if ( textPositionList.size() > 0 ) {
            TextLine textLine = this.addNewLine();
            boolean firstCharacterOfLineFound = false;
            for (TextPosition textPosition : textPositionList ) {
                CharacterFactory characterFactory = new CharacterFactory(firstCharacterOfLineFound);
                Character character = characterFactory.createCharacterFromTextPosition(textPosition, this.getPreviousTextPosition());
                textLine.writeCharacterAtIndex(character);
                this.setPreviousTextPosition(textPosition);
                firstCharacterOfLineFound = true;
            }
        } else {
            this.addNewLine(); // white line new line at first 
        }
    }

    private void iterateThroughTextList(Iterator<TextPosition> textIterator) {
        List<TextPosition> textPositionList = new ArrayList<TextPosition>();

        while ( textIterator.hasNext() ) {
            TextPosition textPosition = (TextPosition)textIterator.next();
            int numberOfNewLines = this.getNumberOfNewLinesFromPreviousTextPosition(textPosition);
            if ( numberOfNewLines == 0 ) {
                textPositionList.add(textPosition);
            } else {
                this.writeTextPositionList(textPositionList);
                this.createNewEmptyNewLines(numberOfNewLines);
                textPositionList.add(textPosition);
            }
            this.setPreviousTextPosition(textPosition);
        }
        if (!textPositionList.isEmpty()) {
            this.writeTextPositionList(textPositionList);
        }
    }

    private void writeTextPositionList(final List<TextPosition> textPositionList) {
        this.writeLine(textPositionList);
        textPositionList.clear();
    }

    private void createNewEmptyNewLines(int numberOfNewLines) {
        for (int i = 0; i < numberOfNewLines - 1; ++i) {
            this.addNewLine();
        }
    }

    private int getNumberOfNewLinesFromPreviousTextPosition(final TextPosition textPosition) {
        TextPosition previousTextPosition = this.getPreviousTextPosition();
        if ( previousTextPosition == null ) {
            return 1;
        }

        float textYPosition = Math.round( textPosition.getY() );
        float previousTextYPosition = Math.round( previousTextPosition.getY() );

        if ( textYPosition > previousTextYPosition && (textYPosition - previousTextYPosition > 5.5) ) {
            double height = textPosition.getHeight();
            int numberOfLines = (int) (Math.floor( textYPosition - previousTextYPosition) / height );
            numberOfLines = Math.max(1, numberOfLines - 1); // exclude current new line
            if (DEBUG) System.out.println(height + " " + numberOfLines);
            return numberOfLines ;
        } else {
            return 0;
        }
    }

    private TextLine addNewLine() {
        TextLine textLine = new TextLine(this.getCurrentPageWidth());
        textLineList.add(textLine);
        return textLine;
    }

    private TextPosition getPreviousTextPosition() {
        return this.previousTextPosition;
    }

    private void setPreviousTextPosition(final TextPosition setPreviousTextPosition) {
        this.previousTextPosition = setPreviousTextPosition;
    }

    private int getCurrentPageWidth() {
        return (int) Math.round(this.currentPageWidth);
    }

    private void setCurrentPageWidth(double currentPageWidth) {
        this.currentPageWidth = currentPageWidth;
    }

    private List<TextLine> getTextLineList() {
        return this.textLineList;
    }

}




