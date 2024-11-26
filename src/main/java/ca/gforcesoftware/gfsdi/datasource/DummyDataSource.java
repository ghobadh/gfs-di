package ca.gforcesoftware.gfsdi.datasource;

/**
 * @author gavinhashemi on 2024-10-08
 */
public class DummyDataSource {

    private String username;
    private String password;
    private String jdbcURL;


    public DummyDataSource(String username, String password, String jdbcURL) {
        this.username = username;
        this.password = password;
        this.jdbcURL = jdbcURL;
    }

    public DummyDataSource() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
