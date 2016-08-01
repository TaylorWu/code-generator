package ${packageName}.entity;

import com.cea.core.modules.entity.tenant.hibernate.<#if isTenant == "true">TenantEntity<#else>NoTenantEntity</#if>;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* Created by Taylor
*/
@Entity
@Table(name = "${tableName}")
public class ${entityName} extends <#if isTenant == "true">TenantEntity<#else>NoTenantEntity</#if> {

<#list fields as field>
    private ${field.type} ${field.name};
</#list>

<#list fields as field>
    @Column(name="${field.columnName}")
    public ${field.type} get${field.name?cap_first}() {
        return ${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>
}