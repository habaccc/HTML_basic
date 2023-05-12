package com.itwill.post.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.datasource.HikariDataSourceUtil;
import com.itwill.post.model.Post;
import com.zaxxer.hikari.HikariDataSource;

// Repository(Persistence) Layer(저장소/영속성 계층)
// DB CRUD(Create, Read, Update, Delete) 작업을 수행하는 계층.
public class PostDao {
    // Slf4j 로깅 기능 사용:
    private static final Logger log = LoggerFactory.getLogger(PostDao.class);

    // singleton
    private static PostDao instance = null;

    private HikariDataSource ds; // sql 실행 코드

    private PostDao() {
        ds = HikariDataSourceUtil.getInstance().getDataSource();
    }

    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }

        return instance;
    }

    // POSTS 테이블에서 전체 레코드를 id 내림차순으로 정렬해서 검색.
    private static final String SQL_SELECT_ALL = "select * from POSTS order by ID desc"; // 가장 최근에 작성된 포스트 먼저 검색.

    public List<Post> select() {
        List<Post> list = new ArrayList<>(); // List의 하위타입 arrayList니까 다형성

        log.info(SQL_SELECT_ALL);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection(); // Pool에서 Connection 객체를 빌려옴.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                // 테이블 컬럼 내용을 Post 타입 객체로 변환하고 리스트에 추가:
                Post post = recordToPost(rs);
                list.add(post);
            }
            log.info("# of rows = {}", list.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close(); // 물리적으로 connection을 끊는게 아니라, Pool에 반환하는 것임.
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return list;
    }

    private Post recordToPost(ResultSet rs) throws SQLException { // recordToPost가 Post 타입에 저장하니까 리턴타입은 Post
        long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        String content = rs.getString("CONTENT");
        String author = rs.getString("AUTHOR");
        LocalDateTime created = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
        LocalDateTime modified = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();

        Post post = new Post(id, title, content, author, created, modified);

        return post;
    }

    // 포스트 번호로 검색
    private static final String SQL_SELECT_BY_ID = "select * from POSTS where id = ?";

    public Post selectById(long id) {
        log.info("selectById({})", id);
        log.info(SQL_SELECT_BY_ID);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null; // 여기서 post 객체를 먼저 생성합니다.

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                post = recordToPost(rs); // 여기서 post 객체에 값을 대입.
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return post; // post 객체를 반환합니다.
    }

    // 새 포스트 작성
    private static final String SQL_INSERT = "insert into POSTS (TITLE, CONTENT, AUTHOR) values (?, ?, ?)";

    public int insert(Post post) {
        log.info("insert({})", post);
        log.info(SQL_INSERT);

        int result = 0; // db에서 executeUpdate() 결과를 저장할 변수
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());

            result = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    private static final String SQL_DELETE_BY_ID =
            "delete from POSTS where id = ?";
    
    public int delete(long id) {
        log.info("delete(id={})", id);
        log.info(SQL_DELETE_BY_ID);
        
        int result = 0; // SQL 실행 결과를 저장할 변수
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID);
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        
        return result;
    }

    // 해당 아이디의 포스트의 제목과 내용, 수정 시간을 업데이트
    private static final String SQL_UPDATE =
            "update POSTS set TITLE = ?, CONTENT = ?, MODIFIED_TIME = sysdate where ID = ?";
    
    public int update(Post post) {
        log.info("update({})", post);
        log.info(SQL_UPDATE);
        
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            result = stmt.executeUpdate();        
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return result;
    }
    
    private static final String SQL_SELECT_BY_TITLE = "select * from POSTS where title LIKE ?";
    private static final String SQL_SELECT_BY_CONTENT = "select * from POSTS where content LIKE ?";
    private static final String SQL_SELECT_BY_TITLE_AND_CONTENT = "select * from POSTS where title LIKE ? or content LIKE ?";
    private static final String SQL_SELECT_BY_AUTHOR = "select * from POSTS where author LIKE ?";
    public List<Post> search(String category, String keyword) {
        List<Post> posts = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = null;

        try {
            conn = ds.getConnection();
            
            if (category.equals("t")) {
                sql = SQL_SELECT_BY_TITLE;
            } else if (category.equals("c")) {
                sql = SQL_SELECT_BY_CONTENT;
            } else if (category.equals("tc")) {
                sql = SQL_SELECT_BY_TITLE_AND_CONTENT;
            } else if (category.equals("a")) {
                sql = SQL_SELECT_BY_AUTHOR;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            
            if(category.equals("tc")) {
                stmt.setString(2, "%" + keyword + "%");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Post post = recordToPost(rs);
                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return posts;
    }

}
