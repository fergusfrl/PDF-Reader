import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Reader {

	public static void main(String[] args) {
		String fileName = "2016 Exam.pdf";
		try{
			PDDocument doc = PDDocument.load(new File(fileName));
			doc.getClass();
			if(!doc.isEncrypted()){
				PDFTextStripperByArea st = new PDFTextStripperByArea();
				st.setSortByPosition(true);
				PDFTextStripper Tstripper = new PDFTextStripper();
				String str = Tstripper.getText(doc);
				System.out.println(str);
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
}