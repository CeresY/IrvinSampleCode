package grammar;

import java.sql.SQLException;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-11
 * @Vesion 1.0
 **/
public class JdbcExtendsExtractServiceImpl extends JdbcExtractBaseServiceImpl{

    @Override
    public void getCatalog() throws SQLException {

    }

    @Override
    public void setMetaData() {

    }

    @Override
    public boolean isMatchExtractSchema(String paramString) {
        return false;
    }
}
