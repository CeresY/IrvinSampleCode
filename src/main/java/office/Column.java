/*
 * Copyright 2009-2018 the original author or authors.
 *
 * http://www.techstar.com/licenses/LICENSE-3.2
 *
 */
package office;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;

import corejava.enumration.ColumnTypeEnum;
import corejava.enumration.DataTypeEnum;
import corejava.enumration.MetaDataTypeEnum;
import util.SimpleUtils;

/**
 * <p>
 * 列定义
 * </p>
 * 
 * @author wuzy
 * @since 4.0
 * @date 2016-6-20
 */
public class Column implements Serializable {
	private static final long serialVersionUID = 3404297726534775486L;
	// 数据类型
	private DataTypeEnum type;
	// 列类型
	private ColumnTypeEnum columnType;
	// 列名称
	private String name;
	// 列编码
	private String code;
	// 新的列编码
	private String mCode;
	// 查询时的编码
	@SuppressWarnings("unused")
	private String qCode;
	// 长度
	private int length;
	// 精度
	private int precision;
	// 小数点
	private int scale;
	// 数据源ID
	private String dataSourceId;
	// 数据表ID
	private String dataTableId;
	// 别名
	private String alias;
	// 模型ID
	private String dataModelId;
	// 公式
	private String formula;
	// 聚合列
	private String aggregation;
	// 是否启用
	private boolean enable;
	// 是否是索引
	private boolean indexKey;
	// 用于存放结点名称
	private transient String nodeName;
	// 维度、度量标记
	private MetaDataTypeEnum metaDataType;

	private String tag;
	/** 列默认值 */
	private String defaultValue;
	/** ClickHouse中数值为NULL时的默认值 */
	public static final String DEFUALT_VALUE = "-99999999999999";
	
	public String getAggregation() {
		return aggregation;
	}

	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public DataTypeEnum getType() {
		return type;
	}

	public void setType(DataTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getmCode() {
		return mCode;
	}

	public void setmCode(String mCode) {
		this.mCode = mCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getDataTableId() {
		return dataTableId;
	}

	public void setDataTableId(String dataTableId) {
		this.dataTableId = dataTableId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDataModelId() {
		return dataModelId;
	}

	public void setDataModelId(String dataModelId) {
		this.dataModelId = dataModelId;
	}

	public MetaDataTypeEnum getMetaDataType() {
		return metaDataType;
	}

	public void setMetaDataType(MetaDataTypeEnum metaDataType) {
		this.metaDataType = metaDataType;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public ColumnTypeEnum getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnTypeEnum columnType) {
		this.columnType = columnType;
	}

	public boolean isIndexKey() {
		return indexKey;
	}

	public void setIndexKey(boolean indexKey) {
		this.indexKey = indexKey;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getDefaultValue() {
		//TODO 正确设置默认值
//		return defaultValue;
		return (type==DataTypeEnum.String? "":DEFUALT_VALUE);
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@JsonIgnore
	public String getqCode() {
		return mCode;
	}

	public void setqCode(String qCode) {
		this.qCode = qCode;
	}

	@JsonIgnore
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

}