import java.io.*;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Reader {

	public static void main(String[] args) throws InvalidPasswordException, IOException {
		
		ArrayList<PDDocument> documents = new ArrayList<>();
		documents.add(PDDocument.load(new File("2016 Exam.pdf")));
		documents.add(PDDocument.load(new File("2015 Exam.pdf")));
		documents.add(PDDocument.load(new File("2014 Exam.pdf")));
		documents.add(PDDocument.load(new File("2013 Exam.pdf")));
		documents.add(PDDocument.load(new File("2012 Exam.pdf")));
		
		try{
			for(int i = 0; i < documents.size(); i++){
				PDDocument doc = documents.get(i);
				doc.getClass();
				if(!doc.isEncrypted()){
					PDFTextStripperByArea st = new PDFTextStripperByArea();
					st.setSortByPosition(true);
					PDFTextStripper Tstripper = new PDFTextStripper();
					String str = Tstripper.getText(doc);
					splitWords(str);
					//countWords();
				}
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void splitWords(String input) throws FileNotFoundException{
		String[] words = input.split("\\s+");
		printToTextFile(words);
	}
	
	public static void printToTextFile(String[] words){
		try(FileWriter fw = new FileWriter("test.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			   for(int i = 0; i < words.length; i++){
				   out.println(words[i]);
			}
		} catch (IOException e) {
			    System.out.print(e);
		}
	}
	
}