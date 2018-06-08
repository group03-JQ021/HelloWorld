package com.jxdedu.biz.impl;

import java.util.List;

import com.jxdedu.biz.KidsBiz;
import com.jxdedu.dao.KidsDao;
import com.jxdedu.dao.impl.KidsDaoImpl;
import com.jxdedu.entity.Kid;

public class KidsBizImpl implements KidsBiz {
	private KidsDao dao = new KidsDaoImpl();
	@Override
	public boolean addKid(Kid kid) {
		return dao.addKid(kid);
	}

	@Override
	public List<Kid> getAllKids() {
		return dao.getAllKids();
	}

	@Override
	public List<Kid> fuzzySearch(String name) {
		return dao.fuzzySearch(name);
	}

	@Override
	public boolean deleteKids(int[] ids) {
		return new KidsDaoImpl().deleteKids(ids);
	}

	@Override
	public boolean updateKid(Kid kid) {
		return dao.updateKid(kid);

	}

	@Override
	public Kid getKidById(int kno) {
		return dao.getKidById(kno);
	}


	@Override
	public List<Kid> getPage(int pageNo, int pageSize) {
		int totalNum = dao.totalNum();
		int startIndex = pageSize*(pageNo - 1)+1;
		
		int endIndex = pageSize*pageNo;
		if (endIndex > totalNum) endIndex = totalNum;
		endIndex++;	// 超尾区间
		
		return dao.getPage(startIndex, endIndex);
	}

	@Override
	public int getPageCount(int pageSize) {
		int total = dao.totalNum();
		// pageSize == 0 时,需要保护;
		return (total%pageSize==0? total/pageSize: total/pageSize+1);
		
	}

	@Override
	public List<String> getHint(String namePrefix) {
		return dao.getHint(namePrefix);
	}

	@Override
	public int getNameCount(String name) {
		return dao.getNameCount(name);
	}

}
