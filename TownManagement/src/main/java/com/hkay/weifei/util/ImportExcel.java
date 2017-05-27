//package com.hkay.weifei.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFDrawing;
//import org.apache.poi.xssf.usermodel.XSSFPictureData;
//import org.apache.poi.xssf.usermodel.XSSFShape;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.hkay.weifei.pojo.Tb_qiyedanwei;
//import com.hkay.weifei.service.CompanyService;
//
//public class ImportExcel {
//	public static List<Tb_qiyedanwei> getImportStream(HttpServletRequest request, MultipartFile[] files, CompanyService companyService) throws IOException {
//		Workbook workbook = null;
//		int flag = 0;
//		boolean isE2007 = false; // 判断是否是excel2007格式
//		String fileName = files[1].getOriginalFilename();
//		if (fileName.endsWith("xlsx")) {
//			isE2007 = true;
//		}
//		InputStream fInputStream = files[1].getInputStream();
//		try {
//			if (isE2007) {
//				workbook = new XSSFWorkbook(fInputStream);
//			} else{
////				tb_operation.setErrorMsg("请另存为.xlsx格式");
////				tb_operations.add(tb_operation);
////				return tb_operations;
//			}
//		} catch (Exception e) {
//
//		}
//		// 得到一个工作表
//		Sheet sheet = workbook.getSheetAt(0);
//		// 获得表头
//		Row rowHead = sheet.getRow(2);
//		// 根据不同的data放置不同的表头
//		Map<Object, Integer> headMap = new HashMap<Object, Integer>();
//		// 判断表头是否合格 ------------------------这里看你有多少列
//		if (rowHead.getPhysicalNumberOfCells() != 47) {
//			System.out.println("表头列数与要导入的数据不对应");
////			tb_operation.setErrorMsg("表头列数与要导入的数据不对应");
////			tb_operations.add(tb_operation);
////			return tb_operations;
//		}
//		try {
//			// ----------------这里根据你的表格有多少列
//			while (flag < 47) {
//				Cell cell = rowHead.getCell(flag);
//				if (getRightTypeCell(cell).toString().equals("编号")) {
//					headMap.put("rn", flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位名称")) {
//					headMap.put("comname", flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位类别")) {
//					headMap.put("comcategory", flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位类型")) {
//					headMap.put("comtype", flag);
//				}else if (getRightTypeCell(cell).toString().equals("上市情况")) {
//					headMap.put("comlisted", flag);
//				}else if (getRightTypeCell(cell).toString().equals("股票代码")) {
//					headMap.put("comstockcode", flag);
//				}else if (getRightTypeCell(cell).toString().equals("世界五百强")) {
//					headMap.put("comworldfive", flag);
//				}else if (getRightTypeCell(cell).toString().equals("中国五百强")) {
//					headMap.put("comcountryfive", flag);
//				}else if (getRightTypeCell(cell).toString().equals("民企五百强")) {
//					headMap.put("comprivatefive", flag);
//				}else if (getRightTypeCell(cell).toString().equals("法人代表")) {
//					headMap.put("comrepresent", flag);
//				}else if (getRightTypeCell(cell).toString().equals("注册资本")){
//					 headMap.put("comcapital",flag);
//				}else if (getRightTypeCell(cell).toString().equals("资本单位")){
//					headMap.put("comcapitalunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("成立时间")){
//					headMap.put("comestablish",flag);
//				}else if (getRightTypeCell(cell).toString().equals("股东情况")){
//					headMap.put("comshareholder",flag);
//				}else if (getRightTypeCell(cell).toString().equals("办公地点")){
//					headMap.put("comoffice",flag);
//				}else if (getRightTypeCell(cell).toString().equals("省份")){
//					headMap.put("comprovince",flag);
//				}else if (getRightTypeCell(cell).toString().equals("地市")){
//					headMap.put("comcity",flag);
//				}else if (getRightTypeCell(cell).toString().equals("区县")){
//					headMap.put("comtown",flag);
//				}else if (getRightTypeCell(cell).toString().equals("具体地址")){
//					headMap.put("comaddress",flag);
//				}else if (getRightTypeCell(cell).toString().equals("产业类别")){
//					headMap.put("comindustrytype",flag);
//				}else if (getRightTypeCell(cell).toString().equals("主要产业")){
//					headMap.put("commajorindustry",flag);
//				}else if (getRightTypeCell(cell).toString().equals("信用代码")){
//					headMap.put("comcreditcode",flag);
//				}else if (getRightTypeCell(cell).toString().equals("经营范围")){
//					headMap.put("comscope",flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位简介")){
//					headMap.put("comabstract",flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位荣誉")){
//					headMap.put("comhonor",flag);
//				}else if (getRightTypeCell(cell).toString().equals("数据年度")){
//					headMap.put("comdatayear",flag);
//				}else if (getRightTypeCell(cell).toString().equals("资产总额")){
//					headMap.put("comlassets",flag);
//				}else if (getRightTypeCell(cell).toString().equals("资产单位")){
//					headMap.put("comlassetsunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("负债总额")){
//					headMap.put("comliabilities",flag);
//				}else if (getRightTypeCell(cell).toString().equals("负债单位")){
//					headMap.put("comliabunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("年度营业收入")){
//					headMap.put("comincomeyear",flag);
//				}else if (getRightTypeCell(cell).toString().equals("营业单位")){
//					headMap.put("cominyearunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("年度净利润")){
//					headMap.put("comnetprofiyear",flag);
//				}else if (getRightTypeCell(cell).toString().equals("利润单位")){
//					headMap.put("comnetyearunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("年度纳税额")){
//					headMap.put("comtaxesyear",flag);
//				}else if (getRightTypeCell(cell).toString().equals("纳税单位")){
//					headMap.put("comtaxyearunit",flag);
//				}else if (getRightTypeCell(cell).toString().equals("发展方向")){
//					headMap.put("comdevelop",flag);
//				}else if (getRightTypeCell(cell).toString().equals("行业方向")){
//					headMap.put("comindustry",flag);
//				}else if (getRightTypeCell(cell).toString().equals("联系人1")){
//					headMap.put("comcontact1",flag);
//				}else if (getRightTypeCell(cell).toString().equals("职务1")){
//					headMap.put("compost1",flag);
//				}else if (getRightTypeCell(cell).toString().equals("联系电话1")){
//					headMap.put("comcontacttel1",flag);
//				}else if (getRightTypeCell(cell).toString().equals("联系人2")){
//					headMap.put("comcontact2",flag);
//				}else if (getRightTypeCell(cell).toString().equals("职务2")){
//					headMap.put("compost2",flag);
//				}else if (getRightTypeCell(cell).toString().equals("联系电话2")){
//					headMap.put("comcontacttel2",flag);
//				}else if (getRightTypeCell(cell).toString().equals("单位官网")){
//					headMap.put("comofficeweb",flag);
//				}else if (getRightTypeCell(cell).toString().equals("业务类型")){
//					headMap.put("combustype",flag);
//				}else if (getRightTypeCell(cell).toString().equals("关系情况")){
//					headMap.put("comrelation",flag);
//				}else {
////					tb_operation.setErrorMsg("第"+(flag+1)+"列的表头错误！");
////					tb_operations.add(tb_operation);
////					return tb_operations;
//					System.out.println("表头错误");
//				}
//				flag++;
//			}
//		} catch (Exception e) {
//			System.out.println("表头不合规范，请修改后重新导入");
////			tb_operation.setErrorMsg("表头不合规范，请修改后重新导入");
////			tb_operations.add(tb_operation);
////			return tb_operations;
//		}
//		// 获得数据的总行数
//		int totalRowNum = sheet.getLastRowNum();
//		if (totalRowNum - 2 <= 0) {
//			System.out.println("Excel内没有数据！");
////			tb_operation.setErrorMsg("Excel内没有数据！");
////			tb_operations.add(tb_operation);
////			return tb_operations;
//		}
//		// 查询归属地及下一个问题编号
//		String number = querySeqOfNextValue(user, tb_operation, businessservice);
////		String area = (String) value.subSequence(0, 1);
////		int number = Integer.valueOf(value.substring(1)) + 1;
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		//获得所有图片
//		XSSFSheet xSheet = (XSSFSheet) sheet;
//		XSSFDrawing drawing = xSheet.getDrawingPatriarch();
//		List<XSSFShape> xShapeList = drawing.getShapes();
//		int i = 3;
//		try {
//			for (i=3; i <= totalRowNum; i++) {
//				// 获得第i行对象
//				Row row = sheet.getRow(i);
//				Tb_operation tbOperation = new Tb_operation();
//				// 判断归属地
//				String problemno = formatNo(area, number);
//				tbOperation.setProblemno(problemno);
//				String prodescription = (String) getRightTypeCell(row.getCell(headMap.get("prodescription")));
//				String mileagepile = (String) getRightTypeCell(row.getCell(headMap.get("mileagepile")));
//				String problemtype = (String) getRightTypeCell(row.getCell(headMap.get("problemtype")));
//				String operation = (String) getRightTypeCell(row.getCell(headMap.get("operation")));
//				String areaname = (String) getRightTypeCell(row.getCell(headMap.get("areacode")));
//				String manager = (String) getRightTypeCell(row.getCell(headMap.get("manager")));
//				String timelimit = (String) getRightTypeCell(row.getCell(headMap.get("timelimit")));
//				tbOperation.setProdescription(prodescription);
//				tbOperation.setMileagepile(mileagepile);
//				problemtype=problemtype.trim();
//				if (problemtype.equals("废品垃圾（乱堆放）")) {
//					problemtype = "废品垃圾";
//				} else if (problemtype.equals("赤膊墙破旧建（构）筑物")) {
//					problemtype = "赤膊房";
//				} else if (problemtype.equals("黑臭河，垃圾河")) {
//					problemtype = "乱采乱挖";
//				}else if (problemtype.equals("乱采乱挖")||problemtype.equals("乱搭乱建")||problemtype.equals("废品垃圾")||problemtype.equals("广告牌残留")||problemtype.equals("青山白化")||problemtype.equals("绿化缺失")||problemtype.equals("赤膊房")||problemtype.equals("蓝色屋面")||problemtype.equals("矿山复绿")||problemtype.equals("乱堆乱放")){
//					
//				}else{
//					tbOperation.setErrorMsg("第" + (i +1)+ "行的问题类型不正确");
//					tb_operations.add(tbOperation);
//					return tb_operations;
//				}
//				tbOperation.setProblemtype(problemtype);
//				String picbeforename = "";
//				XSSFPictureData picbeforedata=getPictureNameOfCell(sheet,row.getCell(headMap.get("picbefore")),xShapeList);
//				if (picbeforedata!=null) {
//					if (isE2007) {
//						picbeforename = ReadPicFromExcel07(picbeforedata, realPath);
//					}
//				}else{
//					tb_operation.setErrorMsg("第" + (i +1)+ "行问题照片不存在");
//					tb_operations.add(tb_operation);
//					return tb_operations;
//				}
//				tbOperation.setPicbefore(picbeforename);
//				String picaftername="";
//				XSSFPictureData picafterdata=getPictureNameOfCell(sheet,row.getCell(headMap.get("picafter")),xShapeList);
//				if (picafterdata!=null) {
//					if (isE2007) {
//						picaftername = ReadPicFromExcel07(picafterdata, realPath);
//						tbOperation.setPicafter(picaftername);
//						tbOperation.setState("5");
//					}
//				}else{
//					tbOperation.setState("3");
//				}
//				tbOperation.setOperation(operation);
//				tbOperation.setAreaname(areaname.trim());
//				List<Tb_operation> areacode = businessservice.queryStreetInfo(tbOperation);
//				if (areacode.size() == 1) {
//					if(areacode.get(0).getParentcode().equals(user.getArea())){
//						tbOperation.setAreacode(areacode.get(0).getAreacode());
//					}else{
//						tbOperation.setErrorMsg("第" + (i +1)+ "行的责任单位与用户不符");
//						tb_operations.add(tbOperation);
//						return tb_operations;
//					}
//				} else {
//					tbOperation.setErrorMsg("第" + (i +1)+ "行的乡镇街道存在错误");
//					tb_operations.add(tbOperation);
//					return tb_operations;
//				}
//				tbOperation.setManager(manager.trim());
//				Date time = sdf.parse(timelimit + " 00:00:00");
//				tbOperation.setTimelimit(time);
//				tbOperation.setType(tb_operation.getType());
//				tbOperation.setUserid(user.getId());
//				tbOperation.setSource(tb_operation.getSource());
//				tbOperation.setRouteid(tb_operation.getRouteid());
//				tb_operations.add(tbOperation);
//				number++;
//			}
//		} catch (Exception e) {
//			System.out.println("<-------------------->内容错误<-------------------->");
//			tb_operation.setErrorMsg("内容错误：第"+(i+1)+"行"+e.getMessage()+"，请检查数据是否缺失或格式错误");
//			tb_operations.add(tb_operation);
//			return tb_operations;
//		}
//		File file = new File(path);
//		// 文件刪除
//		if (!file.isDirectory()) {
//			file.delete();
//		}
//		return tb_operations;
//	}
//	/**
//	 * 
//	 * @param cell
//	 *            一个单元格的对象
//	 * @return 返回该单元格相应的类型的值
//	 */
//	public static Object getRightTypeCell(Cell cell) {
//		Object object = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_STRING: {
//			object = cell.getStringCellValue();
//			break;
//		}
//		case Cell.CELL_TYPE_NUMERIC: {
//			short format = cell.getCellStyle().getDataFormat();
//			if (format == 14 || format == 31 || format == 57 || format == 58 || format==176) {
//				// 日期
//				double value = cell.getNumericCellValue();
//				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
//				object = sdf.format(date);
//			} else {
//				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//				object = cell.getNumericCellValue();
//			}
//			break;
//		}
//		case Cell.CELL_TYPE_FORMULA: {
//			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//			object = cell.getNumericCellValue();
//			break;
//		}
//		case Cell.CELL_TYPE_BLANK: {
//			cell.setCellType(Cell.CELL_TYPE_BLANK);
//			object = cell.getStringCellValue();
//			break;
//		}
//		}
//		return object;
//	}
//}
