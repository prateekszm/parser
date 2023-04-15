package parser.pdfparser.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

@Deprecated
public class GetLinesFromPDF extends PDFTextStripper{
	static List<String> lines = new ArrayList<String>();
	public GetLinesFromPDF() throws IOException {
		super();
	}
	 /**
     * @throws IOException If there is an error parsing the document.
     */

    public static void main( String[] args ) throws IOException {
        PDDocument document = null;
        String fileName = "C:/Users/KIIT/Downloads/Ajio_FN6903302200_1680714517727.pdf";
        try {
        	StringBuffer sb = new StringBuffer();
            document = PDDocument.load( new File(fileName) );
            PDFTextStripper stripper = new GetLinesFromPDF();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);

            // print lines
            for(String line:lines){
            	sb.append(line).append("\n");

            }
            System.out.println(sb.toString());
        }
        finally {
            if( document != null ) {
                document.close();
            }
        }
    }
    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        lines.add(str);

    }

}
