package parser.service.impl;

import parser.dao.ClaimRepo;
import parser.dao.impl.ClaimRepoImpl;
import parser.entity.*;
import parser.payload.FileFormatMetaData;
import parser.payload.ParserMetaData;
import parser.pdfparser.helper.PdfToTextConverter;
import parser.service.CustomParser;
import parser.utils.ReflectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

//have parser method and check if this account pdf format version have custom class then call custom
//class method 
public class AttachmentParser implements CustomParser {
	Boolean run(List<String> filePathList, ParserMetaData parserMetaData, Integer accountId) {
		String pdfText = "";
		String className = parserMetaData.getFileFormatList().get(0).getTextExtractorClass();
		String methodName = parserMetaData.getFileFormatList().get(0).getTextExtractormethod();
		FileFormatMetaData fileFormatMetaData = null;
		Integer fileFormatId = null;
		try {
			for (String filePath : filePathList) {
				pdfText = ReflectionUtils.getTextFromPdf(className, methodName, filePath);
				if (pdfText == null || pdfText.isEmpty())
					return false;
				BaseParser.print(pdfText);
				for (FileFormat fileFormat : parserMetaData.getFileFormatList()) {
					pdfText = BaseParser.getMatch(pdfText, fileFormat.getTempeletRegex(), 0);
					if (pdfText != null) {
						fileFormatMetaData = new FileFormatMetaData(fileFormat.getFileFormatId(), parserMetaData);
						fileFormatId = fileFormat.getFileFormatId();
						parseFile(filePath, fileFormatMetaData, accountId, fileFormatId);
						fileFormatMetaData = null;
						break;
					}
				}
				
			}
			return true;
			// TODO: if true change the status
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean parseFile(String filePath, FileFormatMetaData fileFormatMetaData, Integer accountId,
			Integer fileFormatId) {
		boolean status = false;
		Account account = new Account();
		account.setAccountId(accountId);
		FileFormat fileFormat = new FileFormat();
		fileFormat.setFileFormatId(fileFormatId);
		Claim claim = new Claim();
		claim.setAccount(account);
		claim.setFileFormat(fileFormat);
		Map<String, Claim> claimMap = new HashMap<>();
		try {
			String pdfText = PdfToTextConverter.pdfToTextConverter(filePath);
			boolean keyIdentifierFound = fileFormatMetaData.getHeaderFileParserList().stream()
					.anyMatch(HeaderFieldParser::getKeyIdentifier);
			if (!keyIdentifierFound) {
				return status;
			} else {
				claimMap = parseClaim(pdfText, fileFormatMetaData, claimMap, claim);
				saveIntoDatabase(claimMap);
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	private Map<String, Claim> parseClaim(String pdfText, FileFormatMetaData fileFormatMetaData,
			Map<String, Claim> claimMap, Claim claim) {
		try {
			String claimId = parseHeaderLevelData(pdfText, fileFormatMetaData.getHeaderFileParserList(), claim);
			parseTableLevelData(pdfText, fileFormatMetaData, claim);
			claimMap.put(claimId, claim);
		} catch (Exception e) {
			System.out.println("Error in parsing claim");
			throw new RuntimeException(e);
		}
		return claimMap;
	}

	private void parseTableLevelData(String pdfText, FileFormatMetaData fileFormatMetaData, Claim claim)
			throws Exception {
		if (fileFormatMetaData.getItemLinePatternList().size() == 1) {
			throw new RuntimeException("Please add both child and parent matcher");
		} else if (fileFormatMetaData.getItemLinePatternList().size() == 2) {
			String pdfTableText = "";
			ItemLinePattern parentItemLinePattern = fileFormatMetaData.getItemLinePatternList().stream()
					.filter(itemLinePattern -> itemLinePattern.getSequence() == 1).collect(Collectors.toList()).get(0);
			pdfTableText = BaseParser.getMatch(pdfText, parentItemLinePattern.getRegexPattern(), 0);
			ItemLinePattern tableItemLinePattern = fileFormatMetaData.getItemLinePatternList().stream()
					.filter(itemLinePattern -> itemLinePattern.getSequence() == 2).collect(Collectors.toList()).get(0);
			Matcher matcher = BaseParser.getMatches(pdfTableText, tableItemLinePattern.getRegexPattern());
			parseTableLevelDataHelper(pdfTableText, matcher, fileFormatMetaData.getItemLinePositionList(), claim);
		}
	}

	private void parseTableLevelDataHelper(String pdfText, Matcher matcher, List<ItemLinePosition> itemLinePositionList,
			Claim claim) {
		try {
			createOpeningTagForTable(claim);
			while (matcher.find()) {
				for (ItemLinePosition itemLinePosition : itemLinePositionList) {
					String value = matcher.group(itemLinePosition.getItemLinePosition());
					buildClaimForTable(itemLinePosition.getFieldName(), value, claim);
				}
			}
			createClosingTagForTable(claim);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in parsing Header Level Data");
		}
	}

	private void createOpeningTagForTable(Claim claim) {
		claim.setItemLevelData("<Table>");
	}

	private void createClosingTagForTable(Claim claim) {
		StringBuilder sb = new StringBuilder(claim.getItemLevelData());
		sb.append("</Table>");
		claim.setItemLevelData(sb.toString());
	}

	private void buildClaimForTable(String fieldName, String value, Claim claim) {
		if (!value.isEmpty()) {
			StringBuilder sb = new StringBuilder(claim.getHeaderLevelData());
			sb.append("<");
			sb.append(fieldName);
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(fieldName);
			sb.append(">");
			claim.setItemLevelData(sb.toString());
		}
	}

	private String parseHeaderLevelData(String pdfText, List<HeaderFieldParser> headerFileParserList, Claim claim) {
		String claimId = "";
		try {
			createOpeningTagForHeader(claim);
			for (HeaderFieldParser headerFieldParser : headerFileParserList) {
				if (headerFieldParser.getKeyIdentifier()) {
					claimId = BaseParser.getMatch(pdfText, headerFieldParser.getRegexPattern(), 0);
					claim.setDataClaimKey(claimId);
				}
				buildClaimForHeader(headerFieldParser.getFieldName(),
						BaseParser.getMatch(pdfText, headerFieldParser.getRegexPattern(), 0), claim);
			}
			createClosingTagForHeader(claim);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in parsing Header Level Data");
		}
		return claimId;
	}

	private void createClosingTagForHeader(Claim claim) {
		StringBuilder sb = new StringBuilder(claim.getHeaderLevelData());
		sb.append("</Header>");
		claim.setHeaderLevelData(sb.toString());
	}

	private void createOpeningTagForHeader(Claim claim) {
		claim.setHeaderLevelData("<Header>");
	}

	private void buildClaimForHeader(String fieldName, String value, Claim claim) {
		if (!value.isEmpty()) {
			StringBuilder sb = new StringBuilder(claim.getHeaderLevelData());
			sb.append("<");
			sb.append(fieldName);
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(fieldName);
			sb.append(">");
			claim.setHeaderLevelData(sb.toString());
		}
	}

	private void saveIntoDatabase(Map<String, Claim> claimMap) {
		ClaimRepo claimRepo = new ClaimRepoImpl();
		try {
			for (Map.Entry<String, Claim> entry : claimMap.entrySet()) {
				claimRepo.save(entry.getValue());
			}
		} catch (RuntimeException e) {
			System.out.println("Error while saving in database" + e);
		}
	}
}
