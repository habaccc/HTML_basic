package com.itwill.post.datasource;

import com.zaxxer.hikari.HikariConfig;

import com.zaxxer.hikari.HikariDataSource;
// 서버가 시작되기전에 미리 겟 커넥션과 클로즈커넥션의 시간을 줄여주는 데이터 풀 기능
public class HikariDataSourceUtil {
    // singleton 디자인 패턴 적용:
    private static HikariDataSourceUtil instance = null;
    
    private HikariDataSource ds;
    
    private HikariDataSourceUtil() {
        // HikariCP 를 사용하기 위한 환경 설정 객체
        HikariConfig config = new HikariConfig();
        
        // CP(Data Source)를 생성하기 위한 설정들:
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");

        // CP(Data Source) 객체 생성:
        ds = new HikariDataSource(config);
    }
    
    public static HikariDataSourceUtil getInstance() {
        if (instance == null) {
            instance = new HikariDataSourceUtil();
        }
        
        return instance;
    }
    
    public HikariDataSource getDataSource() {
        return ds;
    }
}
