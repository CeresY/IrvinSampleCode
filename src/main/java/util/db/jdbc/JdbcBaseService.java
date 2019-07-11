package util.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-9
 * @Vesion 1.0
 **/
public abstract class JdbcBaseService {
    private Logger log = LoggerFactory.getLogger(JdbcBaseService.class);
    private DatabaseMetaData databaseMetaData;

    //public abstract JdbcTemplate getJdbcTemplate();

    public abstract Connection getConnection();

    public DatabaseMetaData getDatabaseMetaData() throws SQLException {
        //this.databaseMetaData = this.getJdbcTemplate().getDataSource().getConnection().getMetaData();
        if(databaseMetaData == null) {
            databaseMetaData = getConnection().getMetaData();
        }
        return databaseMetaData;
    }

    public String getCatalog() throws SQLException {
        //return this.getJdbcTemplate().getDataSource().getConnection().getCatalog(); // meta_data
        return this.getConnection().getCatalog(); // meta_data
    }

    public String getSchema() throws SQLException {
        //return this.getJdbcTemplate().getDataSource().getConnection().getSchema(); // dbo
        return this.getConnection().getSchema(); // dbo
    }

    @SuppressWarnings("Duplicates")
    public List<Map<String,String >> getTables() throws SQLException {
        List<Map<String,String>> list = new ArrayList<>();
        ResultSet rs = this.getDatabaseMetaData().getTables(this.getCatalog(), this.getSchema(), "%", null);
        while(rs.next()) {
            //TABLE_CAT
            //String tableCatalog = rs.getString(1);
            String tableCatalog = rs.getString("TABLE_CAT");

            //TABLE_SCHEM
            //String tableSchema = rs.getString(2);
            String tableSchema = rs.getString("TABLE_SCHEM");

            //table name
            //String tableName = rs.getString(3);
            String tableName = rs.getString("TABLE_NAME");

            //table type
            //String tableType = rs.getString(4);
            String tableType = rs.getString("TABLE_TYPE");

            // table remarks
            String remarks = rs.getString("REMARKS");

            // 官方文档说：JDBC 驱动程序不支持此类型。
                /*//TYPE_CAT
                String typeCatalog = rs.getString(5);

                // TYPE_SCHEM
                //String typeSchema = rs.getString("TYPE_SCHEM");

                //TYPE_NAME
                String typeName = rs.getString(5);
                //String typeName = rs.getString("TYPE_NAME");*/

            /*System.out.println("tableCatalog:" + tableCatalog
                            + ", tableSchema:" + tableSchema
                            + ", tableName:" + tableName
                            + ", tableType:" + tableType
                            + ", remarks:" + remarks
                    //+ ", typeCatalog:" + typeCatalog
                    //+ ", typeSchema:" + typeSchema
                    //+ ", typeName:" + typeName
            );*/
            Map<String,String> map = new HashMap<>();
            map.put("tableCatalog", tableCatalog);
            map.put("tableSchema", tableSchema);
            map.put("tableName", tableName);
            map.put("tableType", tableType);
            map.put("remarks", remarks);
            list.add(map);
        }
        return list;
    }

    @SuppressWarnings("Duplicates")
    public void getDbInfo() {
        try {
            //Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            Connection conn = this.getConnection();
            DatabaseMetaData meta =  conn.getMetaData();
            String dbVendorName = meta.getDatabaseProductName();
            String dbVersion = meta.getDatabaseProductVersion();
            System.out.println("dbVendorName:" + dbVendorName + ", dbVersion:" +dbVersion);

            ResultSet rsSchema = meta.getSchemas();
            System.out.println("Schema ...");
            while(rsSchema.next()) {
                //TABLE_SCHEM
                //String tableSchema = rsSchema.getString(1);
                String tableSchema = rsSchema.getString("TABLE_SCHEM");

                //TABLE_CATALOG
                //String tableCatalog = rsSchema.getString(2);
                String tableCatalog = rsSchema.getString("TABLE_CATALOG");
                System.out.println("tableSchema:" + tableSchema + ", tableCatalog:" + tableCatalog );
            }

            log.info("rs : catalog=[meta_data], dbschema=[dbo]");
            System.out.println("Tables ...");
            String catalog_ = "meta_data", schema_ = "dbo";

            // ----------------------------tables-------------------------------------
            // 参数文档: https://docs.microsoft.com/zh-cn/sql/connect/jdbc/reference/gettables-method-sqlserverdatabasemetadata?view=sql-server-2017
            //ResultSet rs = meta.getTables(null, dbSchema, "%", null); // origin
            ResultSet rs = meta.getTables(catalog_, schema_, "%", null);
            while(rs.next()) {
                //TABLE_CAT
                //String tableCatalog = rs.getString(1);
                String tableCatalog = rs.getString("TABLE_CAT");

                //TABLE_SCHEM
                //String tableSchema = rs.getString(2);
                String tableSchema = rs.getString("TABLE_SCHEM");

                //table name
                //String tableName = rs.getString(3);
                String tableName = rs.getString("TABLE_NAME");

                //table type
                //String tableType = rs.getString(4);
                String tableType = rs.getString("TABLE_TYPE");

                // table remarks
                String remarks = rs.getString("REMARKS");

                // 官方文档说JDBC 驱动程序不支持此类型。
                /*//TYPE_CAT
                String typeCatalog = rs.getString(5);

                // TYPE_SCHEM
                //String typeSchema = rs.getString("TYPE_SCHEM");

                //TYPE_NAME
                String typeName = rs.getString(5);
                //String typeName = rs.getString("TYPE_NAME");*/

                System.out.println("tableCatalog:" + tableCatalog
                        + ", tableSchema:" + tableSchema
                        + ", tableName:" + tableName
                        + ", tableType:" +tableType
                        + ", remarks:" +remarks
                        //+ ", typeCatalog:" + typeCatalog
                        //+ ", typeSchema:" + typeSchema
                        //+ ", typeName:" + typeName
                );

                // ---------------------------- primary key -------------------------------------
                //ResultSet rsPrimaryKeys = meta.getPrimaryKeys(null, "", tableName);
                ResultSet rsPrimaryKeys = meta.getPrimaryKeys(catalog_, schema_, tableName);
                while(rsPrimaryKeys.next()) {
                    // COLUMN_NAME
                    String keyColName = rsPrimaryKeys.getString(4);
                    //PK_NAME String
                    String pkName = rsPrimaryKeys.getString(6);
                    System.out.println("\tPrimaryKeyColumn:" + keyColName + ", primaryKeyName:" + pkName );
                }

                // ---------------------------- columns -------------------------------------
                //ResultSet rsColumns = meta.getColumns(null, "", tableName, null);
                ResultSet rsColumns = meta.getColumns(catalog_, schema_, tableName, null);
                while(rsColumns.next()) {
                    // COLUMN_NAME
                    String columnName = rsColumns.getString(4);
                    // DATA_TYPE
                    int colType = rsColumns.getInt(5);
                    //TYPE_NAME
                    String colTypeName = rsColumns.getString(6);
                    // COLUMN_SIZE int => column size.
                    int colSize = rsColumns.getInt(7);
                    // IS_NULLABLE
                    String isNullAble = rsColumns.getString(18);
                    // COLUMN_DEF
                    String columnDef = rsColumns.getString(13);

                    // IS_AUTOINCREMENT
                    String isAutoIncrement = rsColumns.getString(22);

                    System.out.println("\tcolumnName:" + columnName + ", colTypeName:" + colTypeName +", colSize:" + colSize
                            + ", isNullAble:" +isNullAble
                            + ", columnDef:" + columnDef
                            + ", isAutoIncrement:" + isAutoIncrement);
                }

                // ---------------------------- foreign key -------------------------------------
                //ResultSet rsImportedKeys = meta.getImportedKeys(null, dbSchema, tableName); // 检索表中外键列引用的主键列的说明
                //ResultSet rsExportedKeys = meta.getExportedKeys(null, dbSchema, tableName);
                ResultSet rsExportedKeys = meta.getExportedKeys(catalog_, schema_, tableName);
                System.out.println("ExportedKeys for table '" + tableName + "'");
                while(rsExportedKeys.next()) {
                    // COLUMN_NAME
                    String pkTableName = rsExportedKeys.getString("PKTABLE_NAME");
                    String pkColumnName = rsExportedKeys.getString("PKCOLUMN_NAME");
                    String fkTableName = rsExportedKeys.getString("FKTABLE_NAME");
                    String fkColumnName = rsExportedKeys.getString("FKCOLUMN_NAME");
                    String fkName = rsExportedKeys.getString("FK_NAME");
                    System.out.println("\tpkTableName:" + pkTableName + ", pkColumnName:" + pkColumnName +", fkTableName:" + fkTableName
                            + ", fkColumnName:" +fkColumnName
                            + ", fkName:" + fkName);
                }
                System.out.println("------");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
