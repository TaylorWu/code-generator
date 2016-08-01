package ${packageName}.remote.server.interfaces;

import com.cea.core.modules.entity.dto.PageResult;
import com.cea.core.modules.entity.dto.SearchFilter;
import com.cea.identity.remote.dto.WsConstants;
import com.zf.qqcy.dataService.common.dto.WSResult;
import com.zf.qqcy.dataService.oa.flow.remote.dto.FlowStepDto;
import org.oasisopen.sca.annotation.Remotable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.rmi.RemoteException;

/**
* Created by Taylor
*/
@Remotable
public interface ${entityName}Interface {

    @POST
    @Path("save")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET})
    WSResult<${entityName}Dto> save(${entityName}Dto dto) throws RemoteException;

    @POST
    @Path("findByFilter")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET})
    PageResult<${entityName}Dto> findByFilter(SearchFilter searchFilter) throws RemoteException;

    @GET
    @Path("findOne")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + WsConstants.CHARSET})
    WSResult<${entityName}Dto> findOne(@QueryParam("id") String id) throws RemoteException;

}