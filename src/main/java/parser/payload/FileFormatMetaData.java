package parser.payload;

import parser.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFormatMetaData {
    private List<HeaderFieldParser> headerFileParserList = new ArrayList<>();
    private List<ItemLinePattern> itemLinePatternList = new ArrayList<>();
    private List<ItemLinePosition> itemLinePositionList = new ArrayList<>();
    private List<FileFormatParser> fileFormatParserList = new ArrayList<>();
    public FileFormatMetaData(Integer fileFormatId, ParserMetaData parserMetaData) {
        this.getFileFormatMetaData(fileFormatId, parserMetaData);
    }
    private void getFileFormatMetaData(final Integer fileFormatId, ParserMetaData parserMetaData) {
        FileFormat currentFileFormat = parserMetaData.getFileFormatList().stream().
                filter(fileFormat -> Objects.equals(fileFormatId, fileFormat.getFileFormatId())).collect(Collectors.toList()).get(0);
        this.headerFileParserList = new ArrayList<>(currentFileFormat.getHeaderFieldParserSet());
        this.itemLinePatternList = new ArrayList<>(currentFileFormat.getItemLinePatternSet());
        ItemLinePattern itemLinePattern = itemLinePatternList.stream().filter(itemLinePattern1 -> itemLinePattern1.getSequence() == 2).collect(Collectors.toList()).get(0);
        this.itemLinePositionList = new ArrayList<>(itemLinePattern.getItemLinePositionSet());
        itemLinePatternList.forEach(itemLinePattern1 -> itemLinePattern1.getItemLinePositionSet().clear());
        this.fileFormatParserList = parserMetaData.getFileFormatParserList();

    }


    public List<HeaderFieldParser> getHeaderFileParserList() {
        return headerFileParserList;
    }
    public List<ItemLinePattern> getItemLinePatternList() {
        return itemLinePatternList;
    }
    public List<ItemLinePosition> getItemLinePositionList() {
        return itemLinePositionList;
    }
    public List<FileFormatParser> getFileFormatParserList() {
        return fileFormatParserList;
    }

}
