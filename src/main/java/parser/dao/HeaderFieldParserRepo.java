package parser.dao;

import java.util.List;

import parser.entity.HeaderFieldParser;

public interface HeaderFieldParserRepo {
	public List<HeaderFieldParser> getHeaderFieldParserByFileFormatId(Integer id);

}
