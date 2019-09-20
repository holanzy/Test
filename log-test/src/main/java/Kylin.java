import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jinbiao.yao on 2019/1/11.
 */
public class Kylin {
    public static  void main(String[] args) {
        try {
            Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
            Properties info = new Properties();
            info.put("user", "admin");
            info.put("password", "KYLIN");
            Connection conn = driver.connect("jdbc:kylin://10.255.73.118:7070/yjb_test", info);
            String sqlStr1 = "select * from city where YEAR=1994 limit 100";
            String sqlStr2 = "select * from porduct WHERE id=5 limit 100";
            PreparedStatement ps = conn.prepareStatement(sqlStr2);
            //ps.setString(1, "2018-02-01");
            ResultSet resultSet = ps.executeQuery();
            List<String> uidList = new ArrayList();
            while (resultSet.next()) {
                uidList.add(resultSet.getString(1));
                //System.out.println(resultSet.getString(1)+"-"+resultSet.getString(2)+"-"+resultSet.getString(3)+"-"+resultSet.getString(4));
                System.out.println(resultSet.getString(1)+"-"+resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
