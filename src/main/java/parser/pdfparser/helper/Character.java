package parser.pdfparser.helper;

class Character {

    private final char characterValue;
    private int index;
    private final boolean isCharacterPartOfPreviousWord;
    private final boolean isFirstCharacterOfAWord;
    private final boolean isCharacterAtTheBeginningOfNewLine;
    private final boolean isCharacterCloseToPreviousWord;

    public Character(char characterValue, int index, boolean isCharacterPartOfPreviousWord, boolean isFirstCharacterOfAWord, boolean isCharacterAtTheBeginningOfNewLine, boolean isCharacterPartOfASentence) {
        this.characterValue = characterValue;
        this.index = index;
        this.isCharacterPartOfPreviousWord = isCharacterPartOfPreviousWord;
        this.isFirstCharacterOfAWord = isFirstCharacterOfAWord;
        this.isCharacterAtTheBeginningOfNewLine = isCharacterAtTheBeginningOfNewLine;
        this.isCharacterCloseToPreviousWord = isCharacterPartOfASentence;
        if (LayoutTextStripper.DEBUG) System.out.println(this.toString());
    }

    public char getCharacterValue() {
        return this.characterValue;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isCharacterPartOfPreviousWord() {
        return this.isCharacterPartOfPreviousWord;
    }

    public boolean isFirstCharacterOfAWord() {
        return this.isFirstCharacterOfAWord;
    }

    public boolean isCharacterAtTheBeginningOfNewLine() {
        return this.isCharacterAtTheBeginningOfNewLine;
    }

    public boolean isCharacterCloseToPreviousWord() {
        return this.isCharacterCloseToPreviousWord;
    }
    
    @Override
    public String toString() {
        String toString = "";
        toString += index;
        toString += " ";
        toString += characterValue;
        toString += " isCharacterPartOfPreviousWord=" + isCharacterPartOfPreviousWord;
        toString += " isFirstCharacterOfAWord=" + isFirstCharacterOfAWord;
        toString += " isCharacterAtTheBeginningOfNewLine=" + isCharacterAtTheBeginningOfNewLine;
        toString += " isCharacterPartOfASentence=" + isCharacterCloseToPreviousWord;
        toString += " isCharacterCloseToPreviousWord=" + isCharacterCloseToPreviousWord;
        return toString;
    }

}