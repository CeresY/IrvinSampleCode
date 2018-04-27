package util.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

import corejava.date.DateFormatterUtils;
import corejava.enumration.DataTypeEnum;

/**
 * 数据类型转换 文件名 DataTypeConvertUtil.java
 */
public class DataTypeConvertUtils {

    public static String getString(Object obj) {
	return obj != null ? obj.toString() : (String) obj;
    }

    public static Float getFloat(Object obj) {
	return Float.parseFloat(obj.toString());
    }

    public static Long getInteger(Object obj) {
	return Long.valueOf(obj.toString());
    }

    public static Boolean getBoolean(Object obj) {
	return (Boolean) obj;
    }

    public static long getDateToLong(Object obj) {
	return obj == null ? -1L : getDate(obj).getTime();
    }

    public static java.util.Date getDate(Object obj) {
	Date date = null;
	if (obj instanceof String) {
	    try {
		date = DateFormatterUtils.getDateFromString(String.valueOf(obj), "yyyy-MM-dd HH:mm:ss");
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	} else if (obj instanceof java.sql.Date) {
	    java.sql.Date sqlDate = (java.sql.Date) obj;
	    date = new java.util.Date(sqlDate.getTime());
	} else if (obj instanceof java.util.Date) {
	    date = (Date) obj;
	}
	return date;
    }

    public static Double getDouble(Object obj) {
	return new Double(obj.toString());
    }

    public static BigDecimal getBigDecimal(Object object) {

	if (object == null) // NULL
	{
	    return null;
	}
	if (object instanceof String) {
	    return new BigDecimal((String) object);
	} else if (object instanceof Integer) {
	    return BigDecimal.valueOf(((Integer) object).intValue());
	} else if (object instanceof Double) {
	    return BigDecimal.valueOf(((Double) object).doubleValue());
	} else if (object instanceof Long) {
	    return BigDecimal.valueOf(((Long) object).longValue());
	} else if (object instanceof BigInteger) {
	    return new BigDecimal((BigInteger) object);
	} else {
	    return (BigDecimal) object;
	}
    }

    public static String getDecimalFormat(int format) {
	String applyPattern = "#.###############################################;-#.###############################################";
	if (format == 1) {
	    applyPattern = "#.#;-#.#";
	} else if (format == 2) {
	    applyPattern = "#.##;-#.##";
	} else if (format == 3) {
	    applyPattern = "#.###;-#.###";
	} else if (format == 4) {
	    applyPattern = "#.####;-#.####";
	} else if (format == 5) {
	    applyPattern = "#.#####;-#.#####";
	} else if (format == 6) {
	    applyPattern = "#.######;-#.######";
	} else if (format == 7) {
	    applyPattern = "#.#######;-#.#######";
	} else if (format == 8) {
	    applyPattern = "#.########;-#.########";
	}
	return applyPattern;
    }

    /**
     * 将不同数据库字段的字段类型统一转成一个中间类型
     * @param type
     * @return
     */
    public static DataTypeEnum switch2MiddleDataType(DataTypeEnum type){
		if(type == null){
			return null;
		}
		DataTypeEnum result = null;
		String value = type.value();
		switch(value){
		case "Date":
			result = DataTypeEnum.Date;
			break;
		case "DateTime":
			result = DataTypeEnum.DateTime;
		case "Timestamp":
			result = DataTypeEnum.Timestamp;
		case "Numeric":
			result = DataTypeEnum.Numeric;
		case "Integer":
			result = DataTypeEnum.Numeric;
		case "Double":
			result = DataTypeEnum.Numeric;
		case "BigDecimal":
			result = DataTypeEnum.Numeric;
		case "String":
			result = DataTypeEnum.String;
		case "Boolean":
			result = DataTypeEnum.Boolean;
		default:
			break;
		}
		return result;
	}
}
