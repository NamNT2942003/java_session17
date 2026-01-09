package Kha1.dao;

import Kha1.Entity.Movie;
import Kha1.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoiveDAO {
    public List<Movie> getAllMovies(){
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from list_movies()";
        try {
            Connection conn = ConnectionDB.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Movie movie = new Movie();
                movie.setId(rs.getInt("out_id"));
                movie.setTitle(rs.getString("out_title"));
                movie.setDirector(rs.getString("out_director"));
                movie.setYear(rs.getInt("out_year"));
                movies.add(movie);

            }

        }catch (SQLException e){
            System.out.println("Lỗi DAO (List): " + e.getMessage());
        }
        return movies;
    }
    public boolean addMovie(Movie movie){
        String query = "call add_movie(?,?,?)";
        Connection conn = ConnectionDB.openConnection();
        try {
            CallableStatement cstmt = conn.prepareCall(query);
            cstmt.setString(1, movie.getTitle());
            cstmt.setString(2, movie.getDirector());
            cstmt.setInt(3, movie.getYear());
            cstmt.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Lôi DAO (Add): " + e.getMessage());
            return false;
        }
    }
    public boolean updateMovie(Movie movie){
        String query = "call update_movie(?,?,?,?)";
        try {
            Connection conn = ConnectionDB.openConnection();
            CallableStatement cstmt = conn.prepareCall(query);
            cstmt.setInt(1, movie.getId());
            cstmt.setString(2, movie.getTitle());
            cstmt.setString(3, movie.getDirector());
            cstmt.setInt(4, movie.getYear());
            cstmt.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Lỗi DAO (Update): " + e.getMessage());
            return false;
        }
    }
    public boolean deleteMovie(int id){
        String query = "call delete_movie(?)";
        try {
            Connection conn = ConnectionDB.openConnection();
            CallableStatement cstmt = conn.prepareCall(query);
            cstmt.setInt(1, id);
            cstmt.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Lỗi  DAO (Delete): " + e.getMessage());
            return false;
        }
    }
}
