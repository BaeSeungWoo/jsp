package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConnection;

public class SBoardDAOImpl implements SBoardDAO {
	private static SBoardDAO instance = new SBoardDAOImpl();
	public static SBoardDAO getInstance() {
		return instance;
	}

	@Override
	public void boardInsert(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =DBConnection.getConnection();
			String sql = "insert into simpleboard "
					+ " values(simpleboard_seq.nextval,?,?,?,0,?, sysdate)";
			ps =con.prepareStatement(sql);
			ps.setString(1, board.getUserid());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getEmail());
			ps.setString(4, board.getContent());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 closeConnection(con, ps, null, null);
		}
		
	}

	@Override
	public void boardUpdate(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		int num = board.getNum();
		
		try {
			con = DBConnection.getConnection();
			String sql = "update simpleboard set subject = ?, content = ?, regdate = sysdate where num =" +num;
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getSubject());
			ps.setString(2, board.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
		
	}

	@Override
	public ArrayList<BoardDTO> boardList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		ArrayList<BoardDTO> arr= new ArrayList<BoardDTO>();
		
		try {
			con = DBConnection.getConnection();
		    st = con.createStatement();
		    rs = st.executeQuery("select * from simpleboard");
		    while(rs.next()) {
		    	BoardDTO board= new BoardDTO();
		    	board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		    	arr.add(board);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public ArrayList<BoardDTO> boardList(String field, String word) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> arr = new ArrayList<BoardDTO>();
		
		try {
			con = DBConnection.getConnection();
			String sql = "";
			if(word.equals("")) {
               sql = "select * from simpleboard";
			} else {
				sql = "select * from simpleboard where "+field+" like '%"+word+"%'";
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		    	arr.add(board);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, rs);
		}
		return arr;
	}

	@Override
	public void boardDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "delete from simpleboard where num = "+num;
			st = con.createStatement();
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con,null,st,null);
		}
		
	}

	@Override
	public int boardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql ="";
		
		try {
			con = DBConnection.getConnection();
			if(word.equals("")) {
				sql = "select count(*) from simpleboard";
			} else {
				sql = "select count(*) from simpleboard where " +field+ " like '%" +word+"%'";
			}		
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}

	@Override
	public BoardDTO findByNum(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		BoardDTO board=null;
		
		try {
			con = DBConnection.getConnection();
		    st = con.createStatement();
		    rs = st.executeQuery("select * from simpleboard where num="+num);
		    if(rs.next()) {
		    	 board= new BoardDTO();
		    	board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		   
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return board;
	}

	@Override
	public void commentInsert(CommentDTO comment) {
		 Connection con = null;
		  PreparedStatement ps = null;
		  
		  try {
			con =DBConnection.getConnection();
			String sql = "insert into comboard values(comboard_seq.nextval,?,?,sysdate,?)";
			//System.out.println("msg : " +comment.getMsg());
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getMsg());
			ps.setInt(3, comment.getBnum());
			ps.execute();
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, null);
		}
		
	}

	@Override
	public ArrayList<CommentDTO> findAllComment(int bnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<CommentDTO> carr = new ArrayList<CommentDTO>();
		
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from comboard where bnum="+bnum);
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setBnum(rs.getInt("bnum"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setRegdate(rs.getString("regdate"));
				comment.setUserid(rs.getString("userid"));
				carr.add(comment);
			}
		} catch (Exception e) {
					e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return carr;
	}

	@Override
	public int commentCount(int bnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
				
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select count(*) from comboard where bnum="+bnum);
		    if(rs.next()) {
		    	count = rs.getInt(1);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}
	//�ݱ�
	private void closeConnection(Connection con, 
			PreparedStatement ps, 	Statement st, ResultSet rs) {
		try {
			 if(rs!=null) rs.close();
			 if(st!=null) st.close();
			 if(ps!=null) ps.close();
			 if(con!=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
