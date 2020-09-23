
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class Pdfbox 
{
	public static PDDocument CreateDocument(String path,int nbPage) throws IOException
	{
		PDDocument document = new PDDocument();
		
		for (int i = 0; i < nbPage;i++)
		{
			PDPage page = new PDPage();
			document.addPage(page);
		}
		document.save(path);
		
		document.close();
		
		return document;
	}
	public static void DocumentToImage(PDDocument document,int pageIndex,PDRectangle region,String imagePath) throws IOException
	{
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		PDPage page = document.getPage(pageIndex);
		page.setCropBox(region);
		
		
		BufferedImage bim = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);

		ImageIOUtil.writeImage(bim, imagePath + ".png", 300);
		
		bim.flush();
		
	}
	public static void AddText(PDDocument document,int pageIndex,int posX,int posY,
			String text,PDType1Font font) throws IOException
	{
		PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(pageIndex));
	      
		  
	    contentStream.beginText(); 
	       
	    
	    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

	
	    contentStream.newLineAtOffset(posX, posY);
	    
 
	    contentStream.showText(text);      
	   
	    
	    contentStream.endText();

	   
	    contentStream.close();
	}
	public static void DrawImage(PDDocument document,int pageIndex,String imagePath,int x,int y) throws IOException
	{
		PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(pageIndex));
	      
	    PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
	    
	  
	    contentStream.drawImage(pdImage, x, y);

	    contentStream.close();
	}
}
