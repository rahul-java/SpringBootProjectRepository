package com.app.util;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.Uom;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class UomPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Uom> uoms = (List<Uom>) model.get("list");
		
		response.addHeader("Content-Disposition", "attachment; filename=Uom_Data.pdf");
		
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD,26,Font.UNDERLINE,new Color(183, 28, 28, 100));
		Font thFont = FontFactory.getFont(FontFactory.COURIER,18,Font.BOLD,new Color(8, 53, 183, 100));
		Font tdFont = FontFactory.getFont(FontFactory.COURIER,12,Font.SYMBOL,Color.black);
		Font dateFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD,new Color(8, 53, 183, 100));
		
		Paragraph paragraph = new Paragraph("UOM DATA",font);
		Paragraph dateParagraph = new Paragraph(new Date().toString(),dateFont);
		
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingAfter(30.0f);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidths(new float[]{10f, 25f, 25f, 30f});
		table.addCell(new Phrase("ID",thFont));
		table.addCell(new Phrase("UOM_Type",thFont));
		table.addCell(new Phrase("UOM_Model",thFont));
		table.addCell(new Phrase("Description",thFont));
		
		for(Uom uom:uoms)
		{
			table.addCell(new Phrase(String.valueOf(uom.getId()),tdFont));
			table.addCell(new Phrase(uom.getUomType(),tdFont));
			table.addCell(new Phrase(uom.getUomModel(),tdFont));
			table.addCell(new Phrase(uom.getUomDiscription(),tdFont));
		}
		
		document.add(table);
		document.add(dateParagraph);
		
		// adding border
		Rectangle rect= new Rectangle(577,825,18,15); 
	     rect.enableBorderSide(1);
	     rect.enableBorderSide(2);
	     rect.enableBorderSide(4);
	     rect.enableBorderSide(8);
	     rect.setBorderColor(Color.BLACK);
	     rect.setBorderWidth(2);
	     document.add(rect);
		
	}

}
