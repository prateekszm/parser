package parser.dao;

import java.util.List;

import parser.entity.FileFormat;

public interface FileFormatRepo {
	public List<FileFormat> getFileFormatRepoByAccountId(Integer accountId);
	public List<FileFormat> getFileFormatRepoByAccountIdV2(Integer accountId);
	public List<FileFormat> getFileFormatRepoByAccountIdV3(Integer accountId);
}
