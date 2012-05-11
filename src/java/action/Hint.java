package action;

import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.jdbc.core.JdbcTemplate;

public class Hint extends ActionSupport {

    private InputStream xmlStream;
    private JdbcTemplate jdbcTemplate;

    public InputStream getXmlStream() {
        return xmlStream;
    }

    public String execute() throws Exception {
        Date date = new Date();
        xmlStream = convert(getHint(randomNum(countHints())));
        return SUCCESS;
    }

    private static InputStream convert(String str) {
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return is;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getHint(int id) {
        Integer[] arg = new Integer[]{id};
        String sql = "select hint from hints where id=?";
        return (String) jdbcTemplate.queryForObject(sql, arg, java.lang.String.class);
    }
    
    public int countHints(){
        String sql = "select count(*) from hints";
        return jdbcTemplate.queryForInt(sql);
    }
    public int randomNum(int max){
       Random random = new Random();
       return random.nextInt(max+1);
    }
}