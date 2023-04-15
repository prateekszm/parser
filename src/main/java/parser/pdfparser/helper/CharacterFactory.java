package parser.pdfparser.helper;


import org.apache.pdfbox.text.TextPosition;

class CharacterFactory {

    private TextPosition previousTextPosition;
    private boolean firstCharacterOfLineFound;
    private boolean isCharacterPartOfPreviousWord;
    private boolean isFirstCharacterOfAWord;
    private boolean isCharacterAtTheBeginningOfNewLine;
    private boolean isCharacterCloseToPreviousWord;

    public CharacterFactory(boolean firstCharacterOfLineFound) {
        this.firstCharacterOfLineFound = firstCharacterOfLineFound;
    }

    public Character createCharacterFromTextPosition(final TextPosition textPosition, final TextPosition previousTextPosition) {
        this.setPreviousTextPosition(previousTextPosition);
        this.isCharacterPartOfPreviousWord = this.isCharacterPartOfPreviousWord(textPosition);
        this.isFirstCharacterOfAWord = this.isFirstCharacterOfAWord(textPosition);
        this.isCharacterAtTheBeginningOfNewLine = this.isCharacterAtTheBeginningOfNewLine(textPosition);
        this.isCharacterCloseToPreviousWord = this.isCharacterCloseToPreviousWord(textPosition);
        char character = this.getCharacterFromTextPosition(textPosition);
        int index = (int) textPosition.getX() / LayoutTextStripper.OUTPUT_SPACE_CHARACTER_WIDTH_IN_PT;
        return new Character(character,
                index,
                isCharacterPartOfPreviousWord,
                isFirstCharacterOfAWord,
                isCharacterAtTheBeginningOfNewLine,
                isCharacterCloseToPreviousWord);
    }

    private boolean isCharacterAtTheBeginningOfNewLine(final TextPosition textPosition) {
        if (!firstCharacterOfLineFound) {
            return true;
        }
        TextPosition previousTextPosition = this.getPreviousTextPosition();
        float previousTextYPosition = previousTextPosition.getY();
        return (Math.round(textPosition.getY()) < Math.round(previousTextYPosition));
    }

    private boolean isFirstCharacterOfAWord(final TextPosition textPosition) {
        if (!firstCharacterOfLineFound) {
            return true;
        }
        double numberOfSpaces = this.numberOfSpacesBetweenTwoCharacters(previousTextPosition, textPosition);
        return (numberOfSpaces > 1) || this.isCharacterAtTheBeginningOfNewLine(textPosition);
    }

    private boolean isCharacterCloseToPreviousWord(final TextPosition textPosition) {
        if (!firstCharacterOfLineFound) {
            return false;
        }
        double numberOfSpaces = this.numberOfSpacesBetweenTwoCharacters(previousTextPosition, textPosition);
        return (numberOfSpaces > 1 && numberOfSpaces <= LayoutTextStripper.OUTPUT_SPACE_CHARACTER_WIDTH_IN_PT);
    }

    private boolean isCharacterPartOfPreviousWord(final TextPosition textPosition) {
        TextPosition previousTextPosition = this.getPreviousTextPosition();
        if (previousTextPosition.getUnicode().equals(" ")) {
            return false;
        }
        double numberOfSpaces = this.numberOfSpacesBetweenTwoCharacters(previousTextPosition, textPosition);
        return (numberOfSpaces <= 1);
    }

    private double numberOfSpacesBetweenTwoCharacters(final TextPosition textPosition1, final TextPosition textPosition2) {
        double previousTextXPosition = textPosition1.getX();
        double previousTextWidth = textPosition1.getWidth();
        double previousTextEndXPosition = (previousTextXPosition + previousTextWidth);
        return (double) Math.abs(Math.round(textPosition2.getX() - previousTextEndXPosition));
    }


    private char getCharacterFromTextPosition(final TextPosition textPosition) {
        String string = textPosition.getUnicode();
        return string.charAt(0);
    }

    private TextPosition getPreviousTextPosition() {
        return this.previousTextPosition;
    }

    private void setPreviousTextPosition(final TextPosition previousTextPosition) {
        this.previousTextPosition = previousTextPosition;
    }


}

