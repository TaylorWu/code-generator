package com.taylor.util;

import java.util.List;

/**
 * Created by Taylor on 2016/7/30.
 * Entity
 */
public class Entity {

    private String tableName;
    private String packageName;
    private String entityName;
    private List<Field> fields;
    private String isTenant;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getIsTenant() {
        return isTenant;
    }

    public void setIsTenant(String isTenant) {
        this.isTenant = isTenant;
    }
}
