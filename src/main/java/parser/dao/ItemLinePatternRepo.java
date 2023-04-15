package parser.dao;

import java.util.List;

import parser.entity.ItemLinePattern;

public interface ItemLinePatternRepo {
	List<ItemLinePattern> findItemLinePatternByFileFormatId(Integer fileFormatId);
	
}
