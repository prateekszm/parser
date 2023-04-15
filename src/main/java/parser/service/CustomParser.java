package parser.service;

import parser.payload.FileFormatMetaData;

public interface CustomParser {
	public Boolean parseFile(final String filePath, FileFormatMetaData fileFormatMetaData, Integer accountId, Integer fileFormatId);
}
