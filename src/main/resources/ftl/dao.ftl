package ${packageName}.dao;

import ${packageName}.entity.${entityName};
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
* Created by Taylor
*/
public interface ${entityName}Dao extends PagingAndSortingRepository<${entityName}, String>, JpaSpecificationExecutor<${entityName}> {

}