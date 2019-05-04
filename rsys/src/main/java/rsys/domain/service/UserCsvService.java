package rsys.domain.service;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import rsys.domain.model.csv.UserCSV;

@Service
public class UserCsvService {
	/*
	 * UserCSVクラスのリストをファイルに書き込む
	 */
	public void write(Writer writer, List<UserCSV> beans) throws CsvException {
        StatefulBeanToCsv<UserCSV> beanToCsv = new StatefulBeanToCsvBuilder<UserCSV>(writer).build();
        beanToCsv.write(beans);
    }

	/*
	 * CSVファイルからUserCSVクラスのリストを作成する
	 */
    public List<UserCSV> read(Reader reader) throws CsvException {
        CsvToBean<UserCSV> csvToBean = new CsvToBeanBuilder<UserCSV>(reader).withType(UserCSV.class).build();
        return csvToBean.parse();
    }
}
