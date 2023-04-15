package parser.service.impl.custom;

import parser.payload.FileFormatMetaData;
import parser.service.impl.AttachmentParser;

public class AmazonPdfParserCustom extends AttachmentParser {
    @Override
    public Boolean parseFile(String filePath, FileFormatMetaData fileFormatMetaData, Integer accountId, Integer fileFormatId) {
        return super.parseFile(filePath, fileFormatMetaData, accountId, fileFormatId);
    }
}
