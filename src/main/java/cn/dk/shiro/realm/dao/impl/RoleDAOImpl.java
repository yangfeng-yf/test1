package cn.dk.shiro.realm.dao.impl;

import cn.dk.shiro.realm.dao.IRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAOImpl implements IRoleDAO {
    private JdbcTemplate template;

    @Autowired
    private void setDataSource(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> getAllRoleSn() {
        try{
            return template.query("select * from role", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("sn");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleSnByUserId(Long userId) {
        String sql = "select sn from role where id in (select role_id from admin_role where ad_type = ?)";
        try{
            return template.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("sn");
                }
            }, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
            return new ArrayList<>();
        }
}
