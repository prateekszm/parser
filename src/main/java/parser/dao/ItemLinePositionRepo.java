package parser.dao;

import java.util.List;

import parser.entity.ItemLinePattern;
import parser.entity.ItemLinePosition;

public interface ItemLinePositionRepo {
	List<ItemLinePosition> getItemLinePostionByitemLinePatternId(Integer itemLinePatternId);
	List<ItemLinePosition> getItemLinePostionByitemLinePatternList(List<ItemLinePattern> itemLinePatternList);
}
