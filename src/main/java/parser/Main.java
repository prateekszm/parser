package parser;



import java.util.HashMap;

import parser.service.impl.FileManager;

public class Main {
	private static FileManager fileManager = new FileManager();

	private static void launcher(HashMap<String, String> config) {
		String bot  = config.get("bot");
		if(bot.equals("fileManager")) {
			fileManager.run(config);
		}

	}
	public static void main(String[] args) {
		HashMap<String,String> config = new HashMap<>();
		config.put("accountId", "1");
		config.put("deletePdfAfterParsed", "false");
		config.put("folderPath", "C:\\Users\\KIIT\\Desktop\\entity");
		config.put("goDeep", "true");
		config.put("bot", "fileManager");
		
		launcher(config);
	}

}
