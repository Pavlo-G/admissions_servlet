package DAO.mysql;

import static org.junit.Assert.*;

import java.sql.*;
import javax.sql.DataSource;

import DAO.DAOFactory;
import DAO.FacultyDAO;
import entity.Faculty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MySqlFacultyDAOTest {

    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockPreparedStmnt;
    @Mock
    ResultSet mockResultSet;
    int userId = 100;

    public MySqlFacultyDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
//        when(mockResultSet.getInt(Fields.GENERATED_KEYS)).thenReturn(userId);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateWithNoExceptions() throws SQLException {
    }

    @Test(expected = SQLException.class)
    public void testCreateWithPreparedStmntException() throws SQLException {

        //mock
        when(mockConn.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());



            FacultyDAO instance = new MySqlFacultyDAO(mockConn);
            instance.createFaculty(new Faculty());

            //verify and assert
            verify(mockConn, times(1)).prepareStatement(anyString());
            verify(mockPreparedStmnt, times(0)).setString(anyInt(), anyString());
            verify(mockPreparedStmnt, times(0)).execute();
            verify(mockConn, times(0)).commit();
            verify(mockResultSet, times(0)).next();



    }
}