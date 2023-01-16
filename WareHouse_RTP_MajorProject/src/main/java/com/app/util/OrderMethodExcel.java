package com.app.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.model.OrderMethod;
import com.app.model.ShipmentType;

public class OrderMethodExcel extends AbstractXlsView implements View {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// set fileName
		response.addHeader("Content-Disposition", "attachment; filename=OrderMethod_Data.xls");
		// create sheet
		Sheet sheet = workbook.createSheet("OrderMethod");
		shipmentType(sheet);

		@SuppressWarnings("unchecked")
		List<OrderMethod> orderMethodList = (List<OrderMethod>) model.get("list");

		shipmentTypeRecordsBody(sheet, orderMethodList);
	}

	private void shipmentTypeRecordsBody(Sheet sheet, List<OrderMethod> orderMethodList) {

		int r_no = 1;
		for (OrderMethod orderMethod : orderMethodList) {
			Row row = sheet.createRow(r_no++);
			row.createCell(0).setCellValue(orderMethod.getId());
			row.createCell(1).setCellValue(orderMethod.getOrderMode());
			row.createCell(2).setCellValue(orderMethod.getOrderCode());
			row.createCell(3).setCellValue(orderMethod.getOrderType());
			row.createCell(4).setCellValue(orderMethod.getOrderDescription());
			row.createCell(5).setCellValue(orderMethod.getOrderAcceptList().toString());

		}

	}

	private void shipmentType(Sheet sheet) {
        //create row
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("TYPE");
		row.createCell(4).setCellValue("DESCRIPTION");
		row.createCell(5).setCellValue("ACCEPT_LIST");

	}

}
