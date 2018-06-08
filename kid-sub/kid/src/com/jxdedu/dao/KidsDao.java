package com.jxdedu.dao;

import java.util.List;

import com.jxdedu.entity.Kid;

public interface KidsDao {
	/**
	 * 添加幼儿数据到数据库
	 * @Description:@param k 要添加的幼儿对象
	 * @Description:@return 是否添加成功
	 */
	boolean addKid(Kid k);
	/**
	 * 查询所有的幼儿信息
	 * @Description:@return 全部幼儿信息记录
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
	
	// 分页实现
	/**
	 * 查询记录总条数
	 * @return
	 */
	int totalNum();
	/**
	 * 获取一页数据, 区间是: [startIndec, endIndex),所以总条数是  endIdnex-startIIndex
	 * 约定查询结果中, 第一条记录编号为 1;
	 * @param startIndex	起始编号(包含)
	 * @param endIndex		结束编号(不包含)
	 * @return
	 */
	List<Kid> getPage(int startIndex, int endIndex);
	
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
