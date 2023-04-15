package parser.dao;

import parser.entity.FileFormatParser;

import java.util.List;

public interface FileFormatParserRepo {
	public FileFormatParser getFileFormatParserByFileFormatId(Integer fileFormatId);
	public List<FileFormatParser> getFileFormatParserByAccountId(Integer accountId);


}
