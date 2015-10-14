package mx.amib.sistemas.membership.controller.rest.wrapper;

import java.util.List;

import mx.amib.sistemas.external.membership.UserTO;

public class FindAllResponseWrapper{
	private List<UserTO> list;
	private long count;
	public List<UserTO> getList() {
		return list;
	}
	public void setList(List<UserTO> list) {
		this.list = list;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
