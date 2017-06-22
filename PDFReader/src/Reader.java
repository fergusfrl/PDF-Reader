import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Reader {

	public static HashMap<String, Integer> wordCount = new HashMap<>();
	
	public static void main(String[] args) throws InvalidPasswordException, IOException {
		
		ArrayList<PDDocument> documents = new ArrayList<>();
		documents.add(PDDocument.load(new File("test1.pdf")));
		documents.add(PDDocument.load(new File("test2.pdf")));
		documents.add(PDDocument.load(new File("test3.pdf")));
		
		try{
			for(int i = 0; i < documents.size(); i++){
				PDDocument doc = documents.get(i);
				doc.getClass();
				if(!doc.isEncrypted()){
					PDFTextStripperByArea st = new PDFTextStripperByArea();
					st.setSortByPosition(true);
					PDFTextStripper Tstripper = new PDFTextStripper();
					String str = Tstripper.getText(doc);
					mapWords(str);
				}
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println(wordCount);
		printWords(wordCount);
	}
	
	public static void mapWords(String input) throws FileNotFoundException{
		String[] words = input.split("\\s+");
		for(int i = 0; i < words.length; i++){
			if(wordCount.containsKey(words[i])){
				wordCount.put(words[i], wordCount.get(words[i])+1);
			} else {
				wordCount.put(words[i], 1);
			}
		}
	}
	
	public static void printWords(HashMap<String, Integer> wordCount){
		for(int i = Collections.max(wordCount.values()); i > 0; i--){
			if(wordCount.containsValue(i)){
				System.out.println(i);
			}
		}
	}
	
}