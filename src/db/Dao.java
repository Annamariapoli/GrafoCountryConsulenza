package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Country;


public class Dao {
	
	public List<Country> getAllCity(){
		List<Country> city = new LinkedList<Country>();
		Connection conn =DBConnect.getConnection();
		String query ="select * from country ;";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Country c = new Country(res.getString("stateAbb"), res.getInt("cCode"), res.getString("stateNme"));
				city.add(c);
			}
			conn.close();
			return city;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Country> getBorderingCountries(Country c, int contType, int year) {  //mi da le nazioni confinanti  //anno è sempre 2006
		String sql = 
				"SELECT CCode, StateAbb, StateNme FROM country, contiguity " +
				"WHERE country.CCode = contiguity2006.state2no " +
				"AND contiguity2006.state1no = ? " +
				"AND contiguity2006.conttype <= ? " +
				"AND contiguity2006.year = ? ";
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, c.getcCode()) ;                          //primo para
			st.setInt(2, contType) ;                             //secondo para
			st.setInt(3, year);                                 //terzo para
			ResultSet rs = st.executeQuery() ;
			List<Country> list = new LinkedList<Country>() ;
			while( rs.next() ) {
				Country c2 = new Country(rs.getString("StateAbb"),rs.getInt("ccode"), rs.getString("StateNme")) ;	
				list.add(c2) ;
			}
			conn.close() ;
			return list ;                      
    	} catch (SQLException e){
			e.printStackTrace();
		}
		return null ;
	}
}
