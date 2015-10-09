package mx.amib.sistemas.membership.model.convert;

import java.util.ArrayList;
import java.util.List;

import mx.amib.sistemas.external.membership.ApplicationTO;
import mx.amib.sistemas.membership.model.Application;

public class ApplicationTransportConverter {
	public static List<ApplicationTO> convertToTransport(List<Application> objList){
		List<ApplicationTO> tobjList = new ArrayList<ApplicationTO>();
		for( Application x : objList ){
			tobjList.add( ApplicationTransportConverter.convertToTransport(x) );
		}
		return tobjList;
	}
	public static ApplicationTO convertToTransport(Application obj){
		ApplicationTO tobj = new ApplicationTO();
		tobj.setId( obj.getId() );
		tobj.setUuid( obj.getUuid() );
		tobj.setName( obj.getName() );
		tobj.setNameLowercase( obj.getNameLowercase() );
		return tobj;
	}
	public static Application setValuesOnEntity(Application obj, ApplicationTO tobj){
		//obj.setId( tobj.getId() );
		obj.setUuid( tobj.getUuid() );
		obj.setName( tobj.getName() );
		obj.setNameLowercase( tobj.getNameLowercase() );
		return obj;
	}
}
