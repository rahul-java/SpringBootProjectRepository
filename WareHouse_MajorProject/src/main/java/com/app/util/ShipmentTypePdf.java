package com.app.util;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.ShipmentType;
import com.app.model.Uom;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ShipmentTypePdf extends AbstractPdfView implements View {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<ShipmentType> shipmentTypeList = (List<ShipmentType>) model.get("list");
		
		response.addHeader("Content-Disposition", "attachment; filename=ShipmentType_Data.pdf");
		
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD,26,Font.UNDERLINE,new Color(183, 28, 28, 100));
		Font thFont = FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD,new Color(8, 53, 183, 100));
		Font tdFont = FontFactory.getFont(FontFactory.COURIER,12,Font.SYMBOL,Color.black);
		Font dateFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD,new Color(8, 53, 183, 100));
		
		Paragraph paragraph = new Paragraph("ShipmentType DATA",font);  
		Paragraph dateParagraph = new Paragraph(new Date().toString(),dateFont);
		
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingAfter(30.0f);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidths(new float[]{10f, 20f, 20f, 20f, 20f, 32f});
		table.addCell(new Phrase("ID",thFont));
		table.addCell(new Phrase("MODE",thFont));
		table.addCell(new Phrase("CODE",thFont));
		table.addCell(new Phrase("ENABLE",thFont));
		table.addCell(new Phrase("GRADE",thFont));
		table.addCell(new Phrase("DESCRIPTION",thFont));
		
		for(ShipmentType shipmentType:shipmentTypeList)
		{
			table.addCell(new Phrase(String.valueOf(shipmentType.getId()),tdFont));
			table.addCell(new Phrase(shipmentType.getShipmentMode(),tdFont));
			table.addCell(new Phrase(shipmentType.getShipmentCode(),tdFont));
			table.addCell(new Phrase(shipmentType.getEnableShipment(),tdFont));
			table.addCell(new Phrase(shipmentType.getGrade(),tdFont));
			table.addCell(new Phrase(shipmentType.getShipmentDescription(),tdFont));
		}
		
		document.add(table);
		document.add(dateParagraph);
		
		//Border
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


