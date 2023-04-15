package parser;


import java.io.IOException;

import parser.payload.FileFormatMetaData;
import parser.payload.ParserMetaData;

public class AgentTester {
    public static void main(String[] args) throws IOException {
       // ParserMetaData parserMetaData = new ParserMetaData(1);

        /*File file = new File("C:/Users/KIIT/Downloads/Ajio_FN6903302200_1680714517727.pdf");
	        PDFParser pdfParser = new PDFParser(new RandomAccessFile( file,"r"));
	        pdfParser.parse();
	        PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
	        PDFTextStripper pdfTextStripper = new LayoutTextStripper();
	        String string = pdfTextStripper.getText(pdDocument);
        //System.out.println(string);
//        PdfToTextConverter pdfToTextConverter = new PdfToTextConverter();
//        String dtr = pdfToTextConverter.pdfToTextConverter("C:/Users/KIIT/Downloads/Ajio_FN6903302200_1680714517727.pdf");
        System.out.println(string);*/

		ParserMetaData metaData = new ParserMetaData(1);
        FileFormatMetaData parserMetaData = new FileFormatMetaData(1,metaData);
    }
}






















