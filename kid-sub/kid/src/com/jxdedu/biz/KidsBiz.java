package com.jxdedu.biz;

import java.util.List;

import com.jxdedu.entity.Kid;

public interface KidsBiz {
	/**
	 * 添加幼儿功能
	 * @Description:@param kid 要添加的幼儿
	 * @Description:@return 是否添加成功
	 */
	boolean addKid(Kid kid);
	/**
	 * 查询所有幼儿信息
	 * @Description:@return 全部幼儿信息
	 */
	List<Kid> getAllKids();
	/**
	 * 根据名字,模糊搜索
	 * @param name
	 * @return
	 */
	List<Kid> fuzzySearch(String name);
	/**
	 * 根据编号查询幼儿信息
	 * @param kno
	 * @return
	 */
	Kid getKidById(int kno);
	
	/**
	 * 批量删除;
	 * @param ids
	 * @return
	 */
	boolean deleteKids(int[] ids);
	
	/**
	 * 更新幼儿信息
	 * @param kid
	 * @return
	 */
	boolean updateKid(Kid kid);

	/**
	 * 在给定页面大小下,一共有多少页
	 * @param pageSize
	 * @return
	 */
	int getPageCount(int pageSize);
	/**
	 * 获取一页数据, 设定一页长度为和页码;
	 * 第一页的编号为 1, 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Kid> getPage(int pageNo, int pageSize);
	/**
	 * 获取所有以 namePrefix 开头的幼儿姓名列表
	 * @param namePrefix
	 * @return
	 */
	List<String> getHint(String namePrefix);
	/**
	 * 查询指定姓名的幼儿人数
	 * @param name
	 * @return
	 */
	int getNameCount(String name);
}
