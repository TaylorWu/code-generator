package com.taylor.util;

import sun.applet.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseEntityParser {

    private String tableName;//表名

    //数据库连接
    private static final String URL = "jdbc:oracle:thin:@192.168.1.13:1521:orcl";
    private static final String NAME = "utility";
    private static final String PASS = "utility";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";


    public DatabaseEntityParser() {
    }

    public DatabaseEntityParser(String tableName) {
        this.tableName = tableName;
    }

    public Entity parse() {
        Entity entity = new Entity();
        entity.setTableName(tableName);
        //创建连接
        Connection con = null;
        //查要生成实体类的表
        String sql = "select * from " + tableName;
        Statement statement;
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
            Class.forName(properties.getProperty("driver"));
            con = DriverManager.getConnection(properties.getProperty("jdbcUrl"), properties.getProperty("user"), properties.getProperty("password"));
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            List<Field> fields = new ArrayList<>();
            List<String> noNeedColumns = new ArrayList<>();
            noNeedColumns.add("id");
            noNeedColumns.add("optime");
            noNeedColumns.add("tenant_id");
            for (int i = 0; i < size; i++) {
                String columnName = rsmd.getColumnName(i + 1);
                if (noNeedColumns.contains(columnName.toLowerCase())) {
                    continue;
                }
                Field field = new Field();
                field.setColumnName(columnName);
                field.setName(underlineToCamel(columnName.toLowerCase()));
                field.setType(sqlType2JavaType(rsmd.getColumnTypeName(i + 1)));
                fields.add(field);
            }
            entity.setFields(fields);
            return entity;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("binary_double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("binary_float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2")
                || sqlType.equalsIgnoreCase("varchar2")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")
                || sqlType.equalsIgnoreCase("timestamp with local time zone")
                || sqlType.equalsIgnoreCase("timestamp with time zone")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("number")) {
            return "Long";
        }

        return "String";
    }

    private static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}