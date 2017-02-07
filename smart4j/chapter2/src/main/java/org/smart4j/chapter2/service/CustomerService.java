package org.smart4j.chapter2.service;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by osx on 17/2/7.
 */
// 提供客户数据服务
public class CustomerService {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
            createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            String createSql = "create table customer(" +
                    "id bigint(20) not null," +
                    "name varchar(255) default null," +
                    "contact varchar(255) default null," +
                    "telephone varchar(255) default null," +
                    "email varchar(255) default null," +
                    "remark text," +
                    "primary key (id))";
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();
            stmt.executeUpdate(createSql);

            stmt.executeUpdate("insert into customer values ('1', 'customer1', 'Jack', '13512345678', 'jack@gmail.com', null)");
            stmt.executeUpdate("insert into customer values ('2', 'customer2', 'Rose', '13612345678', 'rose@gmail.com', null)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 获取客户列表
    public List<Customer> getCustomerList(String keyword) {
        Connection conn = null;
        try {
            List<Customer> customerList = new ArrayList<>();
            String sql = "select * from customer";
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
            stmt.close();
            rs.close();

            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    // 获取客户
    public Customer getCustomer(long id) {
        // TODO
        return null;
    }

    // 创建客户
    public boolean createCustomer(Map<String, Object> fieldMap) {
        // TODO
        return false;
    }

    // 更新客户
    public boolean updateCustomer(long id, Map<String, Object> filedMap) {
        // TODO
        return false;
    }

    // 删除客户
    public boolean deleteCustomer(long id) {
        // TODO
        return false;
    }
}
