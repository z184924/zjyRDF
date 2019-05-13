package cn.zhangjingyao.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class ParseExcelUtil {
	
	 private OPCPackage xlsxPackage;
	  private int minColumns;
	  private PrintStream output;
	  private String sheetName;

	  public ParseExcelUtil(OPCPackage pkg, PrintStream output, String sheetName, int minColumns)
	  {
	    this.xlsxPackage = pkg;
	    this.output = output;
	    this.minColumns = minColumns;
	    this.sheetName = sheetName;
	  }

	  public List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream)
	    throws IOException, ParserConfigurationException, SAXException
	  {
	    InputSource sheetSource = new InputSource(sheetInputStream);
	    SAXParserFactory saxFactory = SAXParserFactory.newInstance();
	    SAXParser saxParser = saxFactory.newSAXParser();
	    XMLReader sheetParser = saxParser.getXMLReader();
	    MyXssfSheetHandler handler = new MyXssfSheetHandler(styles, strings, this.minColumns, this.output);
	    sheetParser.setContentHandler(handler);
	    sheetParser.parse(sheetSource);
	    return handler.getRows();
	  }

	  public List<String[]> process()
	    throws IOException, OpenXML4JException, ParserConfigurationException, SAXException
	  {
	    ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
	    XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
	    List list = null;
	    StylesTable styles = xssfReader.getStylesTable();
	    XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator)xssfReader.getSheetsData();
	    int index = 0;
	    while (iter.hasNext()) {
	      InputStream stream = iter.next();
	      String sheetNameTemp = iter.getSheetName();
	      if (this.sheetName.equals(sheetNameTemp)) {
	        list = processSheet(styles, strings, stream);
	        stream.close();
	        index++;
	      }
	    }
	    return list;
	  }

	  public static List<String[]> readerExcel(MultipartFile uploadFile, String sheetName, int minColumns)
	    throws IOException, OpenXML4JException, ParserConfigurationException, SAXException
	  {
	    OPCPackage p = OPCPackage.open(uploadFile.getInputStream());
	    ParseExcelUtil xlsx2csv = new ParseExcelUtil(p, System.out, sheetName, minColumns);
	    List list = xlsx2csv.process();
	    p.close();
	    return list;
	  }

	  class MyXssfSheetHandler extends DefaultHandler
	  {
	    private StylesTable stylesTable;
	    private ReadOnlySharedStringsTable sharedStringsTable;
	    private final PrintStream output;
	    private final int minColumnCount;
	    private boolean vIsOpen;
	    private ParseExcelUtil.xssfDataType nextDataType;
	    private short formatIndex;
	    private String formatString;
	    private final DataFormatter formatter;
	    private int thisColumn = -1;

	    private int lastColumnNumber = -1;
	    private StringBuffer value;
	    private String[] record;
	    private List<String[]> rows = new ArrayList();
	    private boolean isCellNull = false;

	    public MyXssfSheetHandler(StylesTable styles, ReadOnlySharedStringsTable strings, int cols, PrintStream target)
	    {
	      this.stylesTable = styles;
	      this.sharedStringsTable = strings;
	      this.minColumnCount = cols;
	      this.output = target;
	      this.value = new StringBuffer();
	      this.nextDataType = ParseExcelUtil.xssfDataType.NUMBER;
	      this.formatter = new DataFormatter();
	      this.record = new String[this.minColumnCount];
	      this.rows.clear();
	    }

	    @Override
		public void startElement(String uri, String localName, String name, Attributes attributes)
	      throws SAXException
	    {
	      if (("inlineStr".equals(name)) || ("v".equals(name))) {
	        this.vIsOpen = true;

	        this.value.setLength(0);
	      }
	      else if ("c".equals(name))
	      {
	        String r = attributes.getValue("r");
	        int firstDigit = -1;
	        for (int c = 0; c < r.length(); c++) {
	          if (Character.isDigit(r.charAt(c))) {
	            firstDigit = c;
	            break;
	          }
	        }
	        this.thisColumn = nameToColumn(r.substring(0, firstDigit));

	        this.nextDataType = ParseExcelUtil.xssfDataType.NUMBER;
	        this.formatIndex = -1;
	        this.formatString = null;
	        String cellType = attributes.getValue("t");
	        String cellStyleStr = attributes.getValue("s");
	        if ("b".equals(cellType)) {
	          this.nextDataType = ParseExcelUtil.xssfDataType.BOOL;
	        } else if ("e".equals(cellType)) {
	          this.nextDataType = ParseExcelUtil.xssfDataType.ERROR;
	        } else if ("inlineStr".equals(cellType)) {
	          this.nextDataType = ParseExcelUtil.xssfDataType.INLINESTR;
	        } else if ("s".equals(cellType)) {
	          this.nextDataType = ParseExcelUtil.xssfDataType.SSTINDEX;
	        } else if ("str".equals(cellType)) {
	          this.nextDataType = ParseExcelUtil.xssfDataType.FORMULA;
	        } else if (cellStyleStr != null)
	        {
	          int styleIndex = Integer.parseInt(cellStyleStr);
	          XSSFCellStyle style = this.stylesTable.getStyleAt(styleIndex);
	          this.formatIndex = style.getDataFormat();
	          this.formatString = style.getDataFormatString();
	          if (this.formatString == null) {
                  this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
              }
	        }
	      }
	    }

	    @Override
		public void endElement(String uri, String localName, String name)
	      throws SAXException
	    {
	      String thisStr = null;

	      if ("v".equals(name))
	      {
	        switch (this.nextDataType.ordinal()) {
	        case 1:
	          char first = this.value.charAt(0);
	          thisStr = first == '0' ? "FALSE" : "TRUE";
	          break;
	        case 2:
	          thisStr = "\"ERROR:" + this.value.toString() + '"';
	          break;
	        case 3:
	          thisStr = '"' + this.value.toString() + '"';
	          break;
	        case 4:
	          XSSFRichTextString rtsi = new XSSFRichTextString(this.value.toString());
	          thisStr = '"' + rtsi.toString() + '"';
	          break;
	        case 5:
	          String sstIndex = this.value.toString();
	          try {
	            int idx = Integer.parseInt(sstIndex);
	            XSSFRichTextString rtss = new XSSFRichTextString(this.sharedStringsTable.getEntryAt(idx));
	            thisStr = '"' + rtss.toString() + '"';
	          } catch (NumberFormatException ex) {
	            this.output.println("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
	          }

	        case 6:
	          String n = this.value.toString();

	          if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
	            Double d = Double.valueOf(Double.parseDouble(n));
	            Date date = HSSFDateUtil.getJavaDate(d.doubleValue());
	            thisStr = formateDateToString(date);
	          } else if (this.formatString != null) {
	            thisStr = this.formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
	          } else {
	            thisStr = n;
	          }
	          break;
	        default:
	          thisStr = "(TODO: Unexpected type: " + this.nextDataType + ")";
	        }

	        if (this.lastColumnNumber == -1) {
	          this.lastColumnNumber = 0;
	        }

	        if ((thisStr == null) || ("".equals(Boolean.valueOf(this.isCellNull)))) {
	          this.isCellNull = true;
	        }
	        this.record[this.thisColumn] = thisStr;

	        if (this.thisColumn > -1) {
                this.lastColumnNumber = this.thisColumn;
            }
	      }
	      else if ("row".equals(name))
	      {
	        if (ParseExcelUtil.this.minColumns > 0)
	        {
	          if (this.lastColumnNumber == -1) {
	            this.lastColumnNumber = 0;
	          }

	          if (!this.isCellNull) {
	            this.rows.add((String[])this.record.clone());
	            this.isCellNull = false;
	            for (int i = 0; i < this.record.length; i++) {
	              this.record[i] = null;
	            }
	          }
	        }
	        this.lastColumnNumber = -1;
	      }
	    }

	    public List<String[]> getRows() {
	      return this.rows;
	    }

	    public void setRows(List<String[]> rows) {
	      this.rows = rows;
	    }

	    @Override
		public void characters(char[] ch, int start, int length)
	      throws SAXException
	    {
	      if (this.vIsOpen) {
              this.value.append(ch, start, length);
          }
	    }

	    private int nameToColumn(String name)
	    {
	      int column = -1;
	      for (int i = 0; i < name.length(); i++) {
	        int c = name.charAt(i);
	        column = (column + 1) * 26 + c - 65;
	      }
	      return column;
	    }

	    private String formateDateToString(Date date) {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return sdf.format(date);
	    }
	  }

	  static enum xssfDataType
	  {
	    BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER;
	  }

}
