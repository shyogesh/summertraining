package com.bit.hostel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bit.hostel.common.CommonSql;
import com.bit.hostel.common.DBUtils;
import com.bit.hostel.model.Leave;
import com.bit.hostel.model.Staff;

public abstract class CommonDao<T> {
	public Connection getConnection(){
		return DBUtils.getConnection();
		
	}
	public abstract List<T> get();
	public abstract T get(int id);
	public abstract void saveOrUpdate(T obj);
	public abstract int delete(int id);
	public abstract int delete(T obj);
	
	
	
	public List<Leave> gets() {
		List<Leave> list = new ArrayList<Leave>();
		Connection con = getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(CommonSql.LEAVE_SQL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setPurpose(rs.getString("purpose"));
				leave.setRemark(rs.getString("remark"));
				leave.setIntime(rs.getDate("intime"));
				leave.setOutTime(rs.getDate("outtime"));
				list.add(leave);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	
	public Leave gets(int id) {
		Leave leave = null;
		Connection con = getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(CommonSql.LEAVE_ID_SQL);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setPurpose(rs.getString("purpose"));
				leave.setRemark(rs.getString("remark"));
				leave.setIntime(rs.getDate("intime"));
				leave.setOutTime(rs.getDate("outtime"));
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return leave;
	}


	
	
	public void saveLeave(Leave leave) {		
		Connection con = getConnection();
		try{
			PreparedStatement stmt = null;
			if(leave.getId() == null || leave.getId() == 0){
				stmt = con.prepareStatement(CommonSql. LEAVE_SAVE_SQL);
				stmt.setInt(1,leave.getUser().getUserId());
				stmt.setString(2, leave.getPurpose());
				stmt.setString(3, leave.getRemark());
				stmt.setTimestamp(4, new Timestamp(leave.getIntime().getTime()));
				stmt.setTimestamp(5, new Timestamp(leave.getOutTime().getTime()));
				stmt.setString(6, "pending");
				stmt.setString(7, leave.getUser().getUsername());
				stmt.setInt(8, 0);
			} else {
				stmt = con.prepareStatement(CommonSql. LEAVE_UPDATE_SQL);
				stmt.setInt(1,leave.getApprovedBy().getUserId());
				stmt.setString(2, leave.getStatus());
				stmt.setString(3, leave.getApprovedBy().getUsername());
				stmt.setInt(4, leave.getId());
				
			}			
			stmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();	
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	
	
	public void GeneratePass(Leave leave) {
		
		Connection con = getConnection();
		try{
			PreparedStatement stmt = null;
			if(leave.getIsGatePassGenerate()==1){
				stmt = con.prepareStatement(CommonSql. LEAVE_GATE_PASS_SQL);
				stmt.setString(1,leave.getUpdatedBy());
				stmt.setInt(2, 1);
				stmt.setInt(3, leave.getId());
			} else {
				stmt = con.prepareStatement(CommonSql. LEAVE_GATE_PASS_SQL);
				stmt.setString(1,leave.getUpdatedBy());
				stmt.setInt(2, 0);
				stmt.setInt(3, leave.getId());
				
			}			
			stmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();	
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	
		
	}
	public int deletes(int id) {

		Connection con = getConnection();
		try{
			PreparedStatement stmt = con.prepareStatement(CommonSql. LEAVE_DELETE_SQL);
			stmt.setInt(1, id);
			return stmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();	
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return 0;

}
}
