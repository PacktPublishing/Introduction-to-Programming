package com.packt.javapath.ch16demo;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.PGConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class JdbcDemo {
   /**
        Uncomment and run the following methods
        only after database server is installed
        and the database "javaintro" is created
    **/
    public static void main(String... args){
/*
        connect();
        getConnection();
        executeStatement1("insert into person (first_name, last_name, dob) values ('Bill', 'Grey', '1980-01-27')");
        executeStatement2("select first_name from person");
        executeStatement3("select first_name from person");
        executeStatement4("update person set first_name = 'Jim' where last_name = 'Adams'");
        executeStatement5("select first_name from person");
        preparedStatement1("select first_name from person");
        preparedStatement1("select first_name from person");
        selectPersonsByFirstName();
*/
    }

    private static void selectPersonsByFirstName() {
        List<Person> list = selectPersonsByFirstName("select * from person where first_name = ?", "Jim");
        for(Person person: list){
            System.out.println(person);
        }
    }

    private static List<Person> selectPersonsByFirstName(String sql, String searchValue){
        List<Person> list = new ArrayList<>();
        Connection conn = getConnection();
        try (conn; PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, searchValue);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                list.add(new Person(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("dob").toLocalDate()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static void preparedStatement1(String sql){
        Connection conn = getConnection();
        try (conn; PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                System.out.print(rs.getString(1) + " "); //Jim Bill
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeStatement5(String sql){
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                System.out.print(rs.getString(1) + " "); //Jim Bill
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeStatement4(String sql){
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.executeUpdate(sql));//prints: 1
            System.out.println(st.getResultSet());    //prints: null
            System.out.println(st.getUpdateCount());  //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeStatement3(String sql){
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.executeQuery(sql)); //prints: ResultSet
            System.out.println(st.getResultSet());    //prints: ResultSet
            System.out.println(st.getUpdateCount());  //prints: -1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeStatement2(String sql){
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));      //prints: false
            System.out.println(st.getResultSet());    //prints: null
            System.out.println(st.getUpdateCount());  //prints: 1
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void executeStatement1(String sql){
        Connection conn = getConnection();
        try (conn; Statement st = conn.createStatement()) {
            st.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static Connection getConnection(){
        String dbName = "javaintro";
        PGConnectionPoolDataSource source = new PGConnectionPoolDataSource();
        source.setServerName("localhost");
        source.setDatabaseName(dbName);
        source.setLoginTimeout(10);
        try {
            return source.getConnection();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to create DB " + dbName + " connection");
        }
    }

    private static Connection getConnection2(){
        String dbName = "javaintro";
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setDatabaseName(dbName);
        source.setLoginTimeout(10);
        try {
            return source.getConnection();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to create DB " + dbName + " connection");
        }
    }

    private static Connection getConnection1(){
        String url = "jdbc:postgresql://localhost/javaintro";
        try {
            return DriverManager.getConnection(url);
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to create DB connection using url=" + url);
        }
    }

    private static void connect(){
        String URL = "jdbc:postgresql://localhost/javaintro";
        Properties prop = new Properties( );
        //prop.put( "user", "java" );
        //prop.put( "password", "secretPass123" );
        try {
            Connection conn = DriverManager.getConnection(URL, prop);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private static class Person {
        private String firstName, lastName;
        private LocalDate dob;
        private int id;

        public Person(int id, String firstName, String lastName, LocalDate dob) {
            this(firstName, lastName, dob);
            this.id = id;
        }

        public Person(String firstName, String lastName, LocalDate dob) {
            this.firstName = firstName == null ? "" : firstName;
            this.lastName = lastName == null ? "" : lastName;
            if(dob == null){
                throw new RuntimeException("Date of birth is null");
            }
            this.dob = dob;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public LocalDate getDob() {
            return dob;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (this == other) return true;
            if (!(other instanceof Person)) return false;
            final Person that = (Person) other;
            return Objects.equals(this.firstName, that.firstName)
                    && Objects.equals(this.lastName, that.lastName)
                    && Objects.equals(this.dob, that.dob);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", dob=" + dob +
                    ", id=" + id +
                    '}';
        }
    }

}
