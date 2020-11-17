package com.fet.alex;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;

public class OpenCsvTest {

	public static void main(String[] args) {
		try {

			List<List<String>> alex = new ArrayList<List<String>>();
			List<String> d = new ArrayList<String>();
			d.add("a01");
			d.add("a02");
			d.add("a03");
			d.add("王大天");

			List<String> e = new ArrayList<String>();
			e.add("b01");
			e.add("b02");
			e.add("b03");
			e.add("王小天");

			alex.add(d);
			alex.add(e);

			String csv = "/home/data.csv";
			
            FileOutputStream fos = new FileOutputStream(csv);
            fos.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF});
            CsvWriter csvWriter = new CsvWriter(fos, ',', Charset.forName("utf-8"));
            String[] headers = {"姓名","年龄","编号","性别","sex"};
            csvWriter.writeRecord(headers);
			 
			for (List<String> list : alex) {
				String[] rowData = new String[list.size()];
				rowData = list.toArray(rowData);
				csvWriter.writeRecord(rowData);
			}
			
			
			String[] rowData2 = {"AAA","555"};
			csvWriter.writeRecord(rowData2);
			
			
			csvWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
