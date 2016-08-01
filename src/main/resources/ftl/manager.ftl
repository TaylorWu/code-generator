package ${packageName}.service;

import ${packageName}.dao.${entityName}Dao;
import ${packageName}.entity.${entityName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
* Created by Taylor
*/
@Component
@Transactional(readOnly = true)
public class ${entityName}Manager {

    @Autowired
    private ${entityName}Dao ${entityName?uncap_first}Dao;

    @Transactional
    public ${entityName} save(${entityName} ${entityName?uncap_first}) {
        return ${entityName?uncap_first}Dao.save(${entityName?uncap_first});
    }

    public ${entityName} findOne(String id) {
        return ${entityName?uncap_first}Dao.findOne(id);
    }

    public Page<${entityName}> findByFilter(Specification<${entityName}> spec, PageRequest pageRequest) {
        return ${entityName?uncap_first}Dao.findAll(spec, pageRequest);
    }

    @Transactional
    public void delete(String id) {
        ${entityName?uncap_first}Dao.delete(id);
    }

}