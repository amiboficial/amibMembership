package mx.amib.sistemas.membership.model.convert;

import java.util.ArrayList;
import java.util.List;

import mx.amib.sistemas.external.membership.RoleTO;
import mx.amib.sistemas.membership.model.Role;

public class RoleTransportConverter {
	public static List<RoleTO> convertToTranport(List<Role> objList){
		List<RoleTO> tobjList = new ArrayList<RoleTO>();
		for( Role x : objList ){
			tobjList.add( RoleTransportConverter.convertToTransport(x) );
		}
		return tobjList;
	}
	public static RoleTO convertToTransport(Role obj){
		RoleTO tobj = new RoleTO();
		tobj.setIdApplication( obj.getIdApplication() );
		tobj.setNumberRole( obj.getNumberRole() );
		tobj.setName( obj.getName() );
		tobj.setDescription( obj.getDescription() );
		tobj.setActive( obj.isActive() );
		return tobj;
	}
	public static Role setValuesOnEntity(Role obj, RoleTO tobj){
		obj.setName( tobj.getName() );
		obj.setDescription( tobj.getDescription() );
		obj.setActive( tobj.isActive() );
		return obj;
	}
}
