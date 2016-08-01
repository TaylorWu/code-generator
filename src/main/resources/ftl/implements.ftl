package ${packageName}.remote.server;

import com.cea.core.mapper.IgnoreHibernateLazyMapper;
import com.cea.core.modules.entity.dto.PageResult;
import com.cea.core.modules.entity.dto.SearchFilter;
import com.cea.core.modules.persistence.query.JpaQuery;
import com.cea.core.modules.persistence.query.SearchUtil;
import com.cea.core.modules.persistence.tenant.TenantLocalValue;
import com.cea.core.spring.ApplicationContextSpring;
import com.zf.qqcy.dataService.common.dto.WSResult;
import ${packageName}.entity.${entityName};
import ${packageName}.remote.dto.${entityName}Dto;
import ${packageName}.remote.server.interfaces.${entityName}Interface;
import ${packageName}.service.${entityName}Manager;
import org.springframework.data.domain.Page;

import java.rmi.RemoteException;

/**
* Created by Taylor
*/
public class ${entityName}Imp implements ${entityName}Interface {

    private ${entityName}Manager ${entityName?uncap_first}Manager = ApplicationContextSpring.getBean("${entityName?uncap_first}Manager");

    @Override
    public WSResult<${entityName}Dto> save(${entityName}Dto dto) {
        ${entityName} ${entityName?uncap_first} = IgnoreHibernateLazyMapper.map(dto, ${entityName}.class);
        ${entityName?uncap_first} = ${entityName?uncap_first}Manager.save(${entityName?uncap_first});
        return new WSResult<>(IgnoreHibernateLazyMapper.map(${entityName?uncap_first}, ${entityName}Dto.class));
    }

    @Override
    public PageResult<${entityName}Dto> findByFilter(SearchFilter searchFilter) {
        JpaQuery<${entityName}> query = new JpaQuery<>(searchFilter);
        Page<${entityName}> page = ${entityName?uncap_first}Manager.findByFilter(query.getSpec(), query.getPageRequest());
        return SearchUtil.pageToPageResult(page, searchFilter, ${entityName}Dto.class);
    }

    @Override
    public WSResult<${entityName}Dto> findOne(String id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Manager.findOne(id);
        return new WSResult<>(IgnoreHibernateLazyMapper.map(${entityName?uncap_first}, ${entityName}Dto.class));
    }

}