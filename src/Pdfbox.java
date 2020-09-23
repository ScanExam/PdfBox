
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
	public static void DocumentToImage(PDDocument document,int pageIndex,PDRectangle region,String imagePath)
	{
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		PDPage page = document.getPage(pageIndex);
		page.setCropBox(region);
		
		BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

		    // suffix in filename will be used as the file format
		ImageIOUtil.writeImage(bim, imagePath + ".png", 300);
		
		bim.flush();
	}
}
