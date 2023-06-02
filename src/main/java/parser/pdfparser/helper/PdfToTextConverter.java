package parser.pdfparser.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
//Have a custom pdf parser that have tags for built item tags set values in xml and json
public class PdfToTextConverter extends LayoutTextStripper {

	public PdfToTextConverter() throws IOException {}

	public static String pdfToTextConverter(String path) throws IOException {
		File file = new File(path);
		PDFParser pdfParser = new PDFParser(new RandomAccessFile( file,"r"));
		pdfParser.parse();
		PDDocument pdfDocument = null;
		String text = "";
		try  {
			pdfDocument = new PDDocument(pdfParser.getDocument());
			final PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(pdfDocument);
			return text;
		} catch (Exception e) {
			System.out.println("Error in Converting pdf to text");
			e.printStackTrace();
		}finally {
			if(pdfDocument!=null) {
				pdfDocument.close();
			}
		}
		return text;
	}

	 public static String layoutPreserveTextConverter(String path){
		String text = "";
		try {
			PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File(path), "r"));
			pdfParser.parse();
			PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
			LayoutTextStripper layoutTextStripper = new LayoutTextStripper();
			text = layoutTextStripper.getText(pdDocument);
			return text;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return text;
	}
}
