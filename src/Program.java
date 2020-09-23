import java.io.IOException;

import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class Program 
{
	public static void main(String[] args) throws IOException 
	{
		PDDocument document = new PDDocument();
		
		PDPage page = new PDPage();
		
		
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      
	  
	    contentStream.beginText(); 
	       
	    
	    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

	    // set position for line of text
	    contentStream.newLineAtOffset(25, 500);
	    
	    PDImageXObject pdImage = PDImageXObject.createFromFile("image.png", document);
	    
	    String text = "This is the sample document and we are adding content to it.";

	      //Adding text in the form of string 
	    contentStream.showText(text);      
	    
	      //Ending the content stream
	    contentStream.endText();
	    
	    // drawing image
	    contentStream.drawImage(pdImage, 70, 250);
	    
	    System.out.println("Content added");

	   
	    contentStream.close();
	      
	      
		document.save("test.pdf");
			
		document.close();
		
		System.out.println("Document saved");
		
		
		
	
		
	}
}
