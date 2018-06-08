package com.jxdedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jxdedu.dao.KidsDao;
import com.jxdedu.entity.Kid;
import com.jxdedu.util.DBUtil;

public class KidsDaoImpl implements KidsDao{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	static String tName = "kids";
	
	public boolean addKid(Kid kid){
		boolean isAdd = false;
		String sql = "insert into kids(kno,kname,sex,birthday,hobby) " +
				"values(kids_seq.nextval,?,?,?,?)";
		try{
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, kid.getKname());
			ps.setString(2, kid.getSex());
			ps.setDate(3, kid.getBirthday());
			ps.setString(4, kid.getHobby());
			int row = ps.executeUpdate();
			if(row ==1){
				isAdd = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return isAdd;
	}

	@Override
	public List<Kid> getAllKids() {
		List<Kid> list = null;
		String sql = "select kno,kname,sex,birthday,hobby from kids order by kno desc";
		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			//执行sql得到查询结果
			rs = ps.executeQuery();
			//遍历结果集，让每一条记录生成一个kids对象，存到集合中
			list = new ArrayList();//用lsit的实现类，实例化它的对象
			while(rs.next()){
				Kid k = new Kid();
				k.setKname(rs.getString("kname"));
				k.setKno(rs.getInt("kno"));
				k.setSex(rs.getString("sex"));
				k.setBirthday(rs.getDate("birthday"));
				k.setHobby(rs.getString("hobby"));
				list.add(k);//添加到集合中
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return list;
	}
	@Override
	public List<Kid> fuzzySearch(String name) {
		List<Kid> list = null;
		String sql = "select kno,kname,sex,birthday,hobby from kids "
				+ "where kname like '%"+ name +"%' "
				+ "order by kno desc";
		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			//ps.setString(1, name);
			//执行sql得到查询结果
			rs = ps.executeQuery();
			//遍历结果集，让每一条记录生成一个kids对象，存到集合中
			list = new ArrayList();//用lsit的实现类，实例化它的对象
			while(rs.next()){
				Kid k = new Kid();
				k.setKname(rs.getString("kname"));
				k.setKno(rs.getInt("kno"));
				k.setSex(rs.getString("sex"));
				k.setBirthday(rs.getDate("birthday"));
				k.setHobby(rs.getString("hobby"));
				list.add(k);//添加到集合中
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean deleteKids(int[] ids) {
		boolean flag = false;
//		System.out.println(Arrays.toString(ids));
		String sql = "delete from kids where kno in";
		sql += Arrays.toString(ids).replace("[","(").replace("]", ")");
//		System.out.println("sql: " + sql);
		try{
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			int row = ps.executeUpdate();
			if(row == ids.length){
				flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if (!flag && conn != null){
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBUtil.closeAll(conn, ps, rs);
		}
		return flag;
	}

	@Override
	public boolean updateKid(Kid kid) {
		boolean flag = false;
		String sql = "update kids " +
				"set kname=?, sex=?,birthday=?,hobby=? where kno=?";
		try{
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, kid.getKname());
			ps.setString(2, kid.getSex());
			ps.setDate(3, kid.getBirthday());
			ps.setString(4, kid.getHobby());
			ps.setInt(5, kid.getKno());
			int row = ps.executeUpdate();
			if(row ==1){
				flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return flag;
	}

	@Override
	public Kid getKidById(int kno) {
		Kid k = null;
		String sql = "select kno,kname,sex,birthday,hobby from kids where kno=?";
		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, kno);
			
			//执行sql得到查询结果
			rs = ps.executeQuery();
			if(rs.next()){
				k = new Kid();
				k.setKname(rs.getString("kname"));
				k.setKno(rs.getInt("kno"));
				k.setSex(rs.getString("sex"));
				k.setBirthday(rs.getDate("birthday"));
				k.setHobby(rs.getString("hobby"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		
		return k;
	}

	@Override
	public int totalNum() {
		int totalNum = -1;
		String sql = "select count(*)  total from " + tName;
		try{
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()){
				totalNum = rs.getInt("total"); // 如果使用列索引,注意,索引是从 1 开始的;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return totalNum;
	}

	@Override
	public List<Kid> getPage(int startIndex, int endIndex) {
		List<Kid> list = null;
		
		String sql = String.format(
				"with t1 as (select * from %s order by kno asc)," +
				"	  t2 as (select rownum r,t1.* from t1 where rownum < ?)" +
				"select kno,kname,sex,birthday,hobby " +
				"from t2 " +
				"where r>= ?", tName);

		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, endIndex);
			ps.setInt(2, startIndex);
			
			//执行sql得到查询结果
			rs = ps.executeQuery();
			//遍历结果集，让每一条记录生成一个kids对象，存到集合中
			list = new ArrayList();//用lsit的实现类，实例化它的对象
			while(rs.next()){
				Kid k = new Kid();
				k.setKname(rs.getString("kname"));
				k.setKno(rs.getInt("kno"));
				k.setSex(rs.getString("sex"));
				k.setBirthday(rs.getDate("birthday"));
				k.setHobby(rs.getString("hobby"));
				list.add(k);//添加到集合中
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<String> getHint(String namePrefix) {
		List<String> list = null;
		String sql = "select kname from kids "
				+ "where kname like '"+ namePrefix + "%'order by kno desc";
		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			//ps.setString(1, name);
			//执行sql得到查询结果
			rs = ps.executeQuery();
			//遍历结果集，让每一条记录生成一个kids对象，存到集合中
			list = new ArrayList<String>();//用lsit的实现类，实例化它的对象
			while(rs.next()){
				list.add(rs.getString("kname"));//添加到集合中
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return list;
	}

	@Override
	public int getNameCount(String name) {
		List<String> list = null;
		String sql = "select count(kname) total from kids "
				+ "where kname = ? order by kno desc";
		try{//获取数据库链接
			conn = DBUtil.getConnection();
			//创建执行sql的对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			//执行sql得到查询结果
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("total");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, ps, rs);
		}
		return -1;	// 出错了
	}


	

}
