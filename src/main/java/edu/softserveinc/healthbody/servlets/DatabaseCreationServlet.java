package edu.softserveinc.healthbody.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.db.DataSourceRepository;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

/**
 * Servlet implementation class DatabaseCreationServlet.
 */
@WebServlet("/PleaseCreateDatabase")
public class DatabaseCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DatabaseCreationServlet() {
	}

	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		try {
			conn = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresDatabase())
					.getConnection();
		} catch (JDBCDriverException e) {
			Log4jWrapper.get().error("JDBC Driver Exception ", e);
			return;
		}
		try {
			DBCreationManager.getInstance().dropAllDatabaseTables(conn);
			DBCreationManager.getInstance().createDatabaseTables(conn);
			DBPopulateManager.getInstance().populateDatabaseTables(conn);
		} catch (SQLException e) {
			Log4jWrapper.get().error("SQL Exception ", e);
			return;
		}
		
		Log4jWrapper.get().info("Database successfully created and populated at: " + request.getContextPath());
	
	}

}
