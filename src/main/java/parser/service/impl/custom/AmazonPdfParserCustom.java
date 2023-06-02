package parser.service.impl.custom;

import parser.payload.FileFormatMetaData;
import parser.service.impl.AttachmentParser;

public class AmazonPdfParserCustom extends AttachmentParser {
    @Override
    public Boolean parseFile(String filePath, FileFormatMetaData fileFormatMetaData, Integer accountId, String fileFormatName) {
        return super.parseFile(filePath, fileFormatMetaData, accountId, fileFormatName);
    }
}
