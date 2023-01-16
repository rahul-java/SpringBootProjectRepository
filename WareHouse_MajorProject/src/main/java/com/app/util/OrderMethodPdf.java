package com.app.util;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.OrderMethod;
import com.app.model.ShipmentType;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderMethodPdf extends AbstractPdfView implements View {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<OrderMethod> orderMethodList = (List<OrderMethod>) model.get("list");
		
		response.addHeader("Content-Disposition", "attachment; filename=OrderMethod_Data.pdf");
		
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD,26,Font.UNDERLINE,new Color(183, 28, 28, 100));
		Font thFont = FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD,new Color(8, 53, 183, 100));
		Font tdFont = FontFactory.getFont(FontFactory.COURIER,12,Font.SYMBOL,Color.black);
		Font dateFont = FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.BOLD,new Color(8, 53, 183, 100));
		
		Paragraph paragraph = new Paragraph("OrderMethod DATA",font);
		Paragraph dateParagraph = new Paragraph(new Date().toString(),dateFont);
		
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingAfter(30.0f);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidths(new float[]{10f, 15f, 15f, 15f,32f, 32f});
		table.addCell(new Phrase("ID",thFont));
		table.addCell(new Phrase("MODE",thFont));
		table.addCell(new Phrase("CODE",thFont));
		table.addCell(new Phrase("TYPE",thFont));
		table.addCell(new Phrase("DESCRIPTION",thFont));
		table.addCell(new Phrase("ACCEPT_List",thFont));
		
		for(OrderMethod orderMethod:orderMethodList)
		{
			table.addCell(new Phrase(String.valueOf(orderMethod.getId()),tdFont));
			table.addCell(new Phrase(orderMethod.getOrderMode(),tdFont));
			table.addCell(new Phrase(orderMethod.getOrderCode(),tdFont));
			table.addCell(new Phrase(orderMethod.getOrderType(),tdFont));
			table.addCell(new Phrase(orderMethod.getOrderDescription(),tdFont));
			table.addCell(new Phrase(orderMethod.getOrderAcceptList().toString(),tdFont));
		}
		
		document.add(table);
		document.add(dateParagraph);
		
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

