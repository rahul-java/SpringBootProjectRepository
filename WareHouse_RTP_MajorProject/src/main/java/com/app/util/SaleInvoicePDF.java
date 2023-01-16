package com.app.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleToIntFunction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.app.model.SaleOrder;
import com.app.model.SaleOrderDetails;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class SaleInvoicePDF extends AbstractPdfView implements View {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		    @SuppressWarnings("unchecked")
			List<SaleOrderDetails> poDtlsList = (List<SaleOrderDetails>) model.get("list");
			
			@SuppressWarnings("unchecked")
			SaleOrder po = (SaleOrder) model.get("so");
			
			response.addHeader("Content-Disposition", "attachment; filename=Invoice.pdf");
		
			Double totalAmount=0.0;
			
			Font font = new Font();
			font.setColor(Color.white);
			font.setSize(30f);
			
			Font font1 = new Font();
			font1.setColor(Color.white);
			font1.setSize(10f);
			
			Font font2 = new Font();
			font2.setSize(15f);
			font2.setStyle(Font.BOLD);
			
			Font thFont = new Font();
			thFont.setSize(13f);
			//thFont.setStyle(Font.BOLD);
			thFont.setColor(Color.white);
			
			//Header
			
			PdfPTable table1 = new PdfPTable(new float[]{260f, 300f});
			PdfPCell cell1 = new PdfPCell(new Phrase("S-INVOICE",font));
			cell1.setBackgroundColor(new Color(63,169,219));
			cell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell1.setPaddingTop(30f);
			cell1.setPaddingBottom(30f);
			cell1.setPaddingLeft(30f);
			cell1.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell1);
			
		    
			PdfPCell cell2 = new PdfPCell(new Phrase("Oneline Warehouse Management System\nRajapur, Chitrakoot, UP 210207\nMob : 8299552516",font1));
			cell2.setBackgroundColor(new Color(63,169,219));
			cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			
			cell2.setPaddingTop(30f);
			cell2.setPaddingBottom(30f);
			cell2.setPaddingRight(10f);
			cell2.setBorder(Rectangle.NO_BORDER);
			table1.addCell(cell2);
			
			document.add(table1);
			document.add(new Paragraph("\n"));
			
			//Body
			
			PdfPTable saleOrderTable = new PdfPTable(new float[]{120f, 220f, 120f, 100f});
			//row #1
			PdfPCell poCell11 = new PdfPCell(new Phrase("Sale Order",font2));
			poCell11.setRowspan(1);
			poCell11.setColspan(4);
			poCell11.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell11);
			//row #2
			PdfPCell poCell21 = new PdfPCell(new Phrase("\nCustomer :"));
			poCell21.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell21);
			
			PdfPCell poCell22 = new PdfPCell(new Phrase("\n"+po.getUser().getUserCode()));
			poCell22.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell22);
			
			PdfPCell poCell23 = new PdfPCell(new Phrase("\nShipment :"));
			poCell23.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell23);
			
			PdfPCell poCell24 = new PdfPCell(new Phrase("\n"+po.getShipmentType().getShipmentMode()));
			poCell24.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell24);
			//row #3
			PdfPCell poCell31 = new PdfPCell(new Phrase("Order Code :"));
			poCell31.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell31);
			
			PdfPCell poCell32 = new PdfPCell(new Phrase(po.getOrderCode()));
			poCell32.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell32);
			
			PdfPCell poCell33 = new PdfPCell(new Phrase("Reference No :"));
			poCell33.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell33);
			
			PdfPCell poCell34 = new PdfPCell(new Phrase(po.getReferenceNo()));
			poCell34.setBorder(Rectangle.NO_BORDER);
			saleOrderTable.addCell(poCell34);
			
			document.add(saleOrderTable);
			document.add(new Paragraph("\n\n"));
			
			//Order Details List
			
			PdfPTable orderDetailsTable = new PdfPTable(new float[]{60f, 175f, 75f, 125f, 125f});
			//row #1
			PdfPCell odCell11 = new PdfPCell(new Phrase("S.No",thFont));
			odCell11.setBackgroundColor(new Color(63,169,219));
			orderDetailsTable.addCell(odCell11);
			
			PdfPCell odCell12 = new PdfPCell(new Phrase("Product Name",thFont));
			odCell12.setBackgroundColor(new Color(63,169,219));
			orderDetailsTable.addCell(odCell12);
			
			PdfPCell odCell13 = new PdfPCell(new Phrase("Quantity",thFont));
			odCell13.setBackgroundColor(new Color(63,169,219));
			orderDetailsTable.addCell(odCell13);
			
			PdfPCell odCell14 = new PdfPCell(new Phrase("Unit Price",thFont));
			odCell14.setBackgroundColor(new Color(63,169,219));
			orderDetailsTable.addCell(odCell14);
			
			PdfPCell odCell15 = new PdfPCell(new Phrase("Amount",thFont));
			odCell15.setBackgroundColor(new Color(63,169,219));
			orderDetailsTable.addCell(odCell15);
			
			for(SaleOrderDetails od:poDtlsList)
			{
				orderDetailsTable.addCell(new Phrase((Integer.valueOf(poDtlsList.indexOf(od)+1)).toString()));
				orderDetailsTable.addCell(new Phrase(od.getProduct().getProdName()));
				orderDetailsTable.addCell(new Phrase(od.getQuantity().toString()));
				orderDetailsTable.addCell(new Phrase(od.getProduct().getProdCost().toString()));
				orderDetailsTable.addCell(new Phrase(Double.valueOf(od.getProduct().getProdCost()*od.getQuantity()).toString()));
				totalAmount+=Double.valueOf(od.getProduct().getProdCost()*od.getQuantity());
			}
			
			PdfPCell pdfPCell1 = new PdfPCell(new Phrase(""));
			pdfPCell1.setBackgroundColor(new Color(63,169,219));
			pdfPCell1.setBorder(Rectangle.NO_BORDER);
			orderDetailsTable.addCell(pdfPCell1);
			
			PdfPCell pdfPCell2 = new PdfPCell(new Phrase(""));
			pdfPCell2.setBackgroundColor(new Color(63,169,219));
			pdfPCell2.setBorder(Rectangle.NO_BORDER);
			orderDetailsTable.addCell(pdfPCell2);
			
			PdfPCell pdfPCell3 = new PdfPCell(new Phrase(""));
			pdfPCell3.setBackgroundColor(new Color(63,169,219));
			pdfPCell3.setBorder(Rectangle.NO_BORDER);
			orderDetailsTable.addCell(pdfPCell3);
			
			PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Total Amount :",thFont));
			pdfPCell4.setBackgroundColor(new Color(63,169,219));
			pdfPCell4.setBorder(Rectangle.NO_BORDER);
			orderDetailsTable.addCell(pdfPCell4);
			
			PdfPCell pdfPCell5 = new PdfPCell(new Phrase(totalAmount.toString(),thFont));
			pdfPCell5.setBackgroundColor(new Color(63,169,219));
			pdfPCell5.setBorder(Rectangle.NO_BORDER);
			orderDetailsTable.addCell(pdfPCell5);
			
			document.add(orderDetailsTable);
			document.add(new Paragraph("\n\n"));
			
			//Footer
			PdfPTable footerTable = new PdfPTable(new float[]{300f, 260f});
			//Date Format
			LocalDate today = LocalDate.now( ZoneId.of( "America/Montreal" ) ) ;
			
			PdfPCell f1 = new PdfPCell(new Phrase(today.toString()));
			f1.setBorder(Rectangle.NO_BORDER);
			f1.setVerticalAlignment(PdfPCell.ALIGN_LEFT);
			footerTable.addCell(f1);
			
			PdfPCell f2 = new PdfPCell(new Phrase("Authorized Signatory"));
			f2.setBorder(Rectangle.NO_BORDER);
			f2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			footerTable.addCell(f2);
			
			document.add(footerTable);
			
			//Border
			Rectangle rect= new Rectangle(577,825,18,15); 
		     rect.enableBorderSide(1);
		     rect.enableBorderSide(2);
		     rect.enableBorderSide(4);
		     rect.enableBorderSide(8);
		     rect.setBorderColor(new Color(63,169,219));
		     rect.setBorderWidth(2);
		     document.add(rect);
	}	
}

