package cn.dk.shiro.realm.dao.impl;

import cn.dk.shiro.domain.Permission;
import cn.dk.shiro.realm.dao.IPermissionDAO;
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
public class PermissionDAOImpl implements IPermissionDAO {

    private JdbcTemplate template;

    @Autowired
    private void setDataSource(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Permission permission) {
        template.update("insert into permission(name,resource) values(?,?)",
                    permission.getName(), permission.getResource());
    }

    //
    @Override
    public List<String> getPermissionResourceByUserId(long userId) {
        String sql = "select resource from permission where id in(" +
                "       select permission_id from role_permission where role_id in(" +
                "           select role_id from admin_role where ad_type = ?)" +
                "      );";
        try {
            return template.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("resource");
                }
            }, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();



    }

    @Override
    public List<String> getAllResources() {
        String  sql = "select resource from permission ";
        return template.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("resource");
            }
        });
    }
}
