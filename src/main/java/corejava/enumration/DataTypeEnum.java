/*
 * Copyright 2009-2018 the original author or authors.
 *
 * http://www.techstar.com/licenses/LICENSE-3.2
 *
 */
package corejava.enumration;

import util.file.DataTypeConvertUtils;

/** 
 * <p>数据类枚举</p>
 * @author wuzy
 * @since 4.0
 * @date 2016-4-20
 *  */
public enum DataTypeEnum {

    Date("Date") {
        @Override
        public boolean isNumeric() {
            return false;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getDate(value);
        }
    },
    DateTime("DateTime") {
        @Override
        public boolean isNumeric() {
            return false;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getDate(value);
        }
    },
    Timestamp("Timestamp") {
        @Override
        public boolean isNumeric() {
            return false;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getDate(value);
        }
    },
    Numeric("Numeric") {
        @Override
        public boolean isNumeric() {
            return true;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getFloat(value);
        }
    },
    Integer("Integer") {
        @Override
        public boolean isNumeric() {
            return true;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getInteger(value);
        }
    },
    Double("Double") {
        @Override
        public boolean isNumeric() {
            return true;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getDouble(value);
        }
    },
    String("String") {
        @Override
        public boolean isNumeric() {
            return false;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getString(value);
        }
    },
    BigDecimal("BigDecimal") {
        @Override
        public boolean isNumeric() {
            return true;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getBigDecimal(value);
        }
    },
    Boolean("Boolean") {
        @Override
        public boolean isNumeric() {
            return false;
        }

        @Override
        public Object convertValue(Object value) {
            return DataTypeConvertUtils.getBoolean(value);
        }
    };

    private String value;

    private DataTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static DataTypeEnum fromValue(String v) {
        for (DataTypeEnum c : DataTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public Object convertValue(Object value) {
        throw new UnsupportedOperationException();
    }

    public boolean isNumeric() {
        throw new UnsupportedOperationException();
    }
}
