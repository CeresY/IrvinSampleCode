package grammar;

import java.sql.SQLException;

public interface IJdbcExtractService {
    void getCatalog() throws SQLException;

    void setMetaData();

    boolean isMatchExtractSchema(String paramString);
}
