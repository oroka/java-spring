package rsys.domain.model.csv;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.Data;
import rsys.domain.model.converter.LocalDateConverterForCSV;

/*
 * ユーザークラス　CSV形式
 * 名前,ふりがな,アドレス,性別,年齢,誕生日,婚姻,都道府県,都道府県コード,電話番号,携帯
 */
@Data
public class UserCSV {
	@CsvBindByName(column = "名前", required = true)
	private String name;

	@CsvBindByName(column = "ふりがな", required = true)
	private String furigana;

	@CsvBindByName(column = "アドレス", required = true)
	private String address;

	@CsvBindByName(column = "性別", required = true)
	private String gendar;

	@CsvBindByName(column = "年齢", required = true)
	private Integer age;

	@CsvCustomBindByName(column = "誕生日", required = true, converter = LocalDateConverterForCSV.class)
	private LocalDate birthday;

	@CsvBindByName(column = "婚姻", required = true)
	private String marrige;

	@CsvBindByName(column = "都道府県", required = true)
	private String region;

	@CsvBindByName(column = "都道府県コード", required = true)
	private Integer regionCode;

	@CsvBindByName(column = "電話番号", required = true)
	private String phoneNumber;

	@CsvBindByName(column = "携帯", required = true)
	private String portablePhoneNumber;
}
