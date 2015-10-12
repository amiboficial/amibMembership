package mx.amib.sistemas.membership.model.convert;

import java.util.ArrayList;
import java.util.List;

import mx.amib.sistemas.external.membership.PathTO;
import mx.amib.sistemas.membership.model.Path;

public class PathTransportConverter {
	public static List<PathTO> convertToTransport(List<Path> objList){
		List<PathTO> tobjList = new ArrayList<PathTO>();
		for( Path x : objList ){
			tobjList.add( PathTransportConverter.convertToTransport(x) );
		}
		return tobjList;
	}
	public static PathTO convertToTransport(Path obj){
		PathTO tobj = new PathTO();
		tobj.setPath( obj.getPath() );
		tobj.setPathLowercase( obj.getPathLowercase() );
		return tobj;
	}
	public static Path setValuesOnEntity(Path obj, PathTO tobj){
		obj.setPath( tobj.getPath() );
		obj.setPathLowercase( tobj.getPathLowercase() );
		return obj;
	}
}
