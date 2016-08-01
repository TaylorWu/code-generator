package ${packageName}.remote.client;

import com.cea.core.modules.entity.dto.PageResult;
import com.cea.core.modules.entity.dto.SearchFilter;
import com.zf.qqcy.dataService.common.dto.WSResult;
import ${packageName}.remote.dto.${entityName}Dto;
import ${packageName}.remote.server.interfaces.${entityName}Interface;
import org.oasisopen.sca.annotation.Reference;

import java.rmi.RemoteException;

/**
* Created by Taylor
*/
public class ${entityName}Client {

    private ${entityName}Interface ${entityName?uncap_first}Interface;

    @Reference
    public void set${entityName}Interface(${entityName}Interface ${entityName?uncap_first}Interface) {
        this.${entityName?uncap_first}Interface = ${entityName?uncap_first}Interface;
    }

    public WSResult<${entityName}Dto> save(${entityName}Dto dto) throws RemoteException {
        return ${entityName?uncap_first}Interface.save(dto);
    }

    public PageResult<${entityName}Dto> findByFilter(SearchFilter searchFilter) throws RemoteException {
        return ${entityName?uncap_first}Interface.findByFilter(searchFilter);
    }

    public WSResult<${entityName}Dto> findOne(String id) throws RemoteException {
        return ${entityName?uncap_first}Interface.findOne(id);
    }

}