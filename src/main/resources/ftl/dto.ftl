package ${packageName}.remote.dto;

import com.cea.core.modules.entity.dto.WsConstants;
import com.zf.qqcy.dataService.common.dto.<#if isTenant == "true">TenantEntityDto<#else>NoTenantEntityDto</#if>;

import javax.xml.bind.annotation.XmlRootElement;

/**
* Created by Taylor
*/
@XmlRootElement(name = "${entityName}Dto", namespace = WsConstants.NS)
public class ${entityName}Dto extends <#if isTenant == "true">TenantEntityDto<#else>NoTenantEntityDto</#if> {

<#list fields as field>
    private ${field.type} ${field.name};
</#list>

<#list fields as field>
    public ${field.type} get${field.name?cap_first}() {
        return ${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }

</#list>
}