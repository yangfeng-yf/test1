package cn.dk.shiro.realm.dao.impl;

import cn.dk.shiro.realm.dao.IUserDAO;
import cn.dk.shiro.domain.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements IUserDAO {

    private JdbcTemplate template;

    @Autowired
    private void setDataSource(DataSource dataSource){
        //System.out.println("999999");
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public AdminInfo getUserByAdId(int ad_id) {
        //System.out.println("8888888");
        try {
            AdminInfo adminInfo = template.queryForObject("select * from admin_info where ad_id = ? ", new RowMapper<AdminInfo>() {
                @Override
                public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminInfo user = new AdminInfo();
                    user.setAdId(rs.getInt("ad_id"));
                    user.setAdName(rs.getString("ad_name"));
                    user.setAdLastlogintime(rs.getDate("ad_lastlogintime"));
                    user.setAdPhone(rs.getString("ad_phone"));
                    user.setAdPassword(rs.getString("ad_password"));
                    user.setAdType(rs.getLong("ad_type"));
                    return user;
                }
            }, ad_id);

            return adminInfo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }
}
