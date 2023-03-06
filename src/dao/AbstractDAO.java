package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

public class AbstractDAO<T> {

	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Method that gets all entries from a table
	 * @return arrayList of type T
	 */
	public ArrayList<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return (ArrayList<T>) createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return null;
	}

	/**
	 * Method that gets an entry from a table by the corresponding ID
	 * @param id of the entry we want
	 * @return object of type T
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(type.getDeclaredFields()[0].getName());
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Method that gets an entry from a table by the corresponding name
	 * @param name of the entry we want
	 * @return object of type T
	 */
	public T findByName(String name){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(type.getDeclaredFields()[1].getName());
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return null;
	}

	/**
	 * Method that creates at least one object depending on data set as a parameter
	 * @param resultSet of entry/entries represented by the created objects
	 * @return list of objects of type T
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * CREATE QUERIES FOR INSERTING/UPDATING/DELETING OBJECTS FROM DB
	 */


	/**
	 * Method that creates a statement that selects data from a table
	 * @return String as build statement
	 */
	public String createSelectQuery() {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT");
		sb.append(" * ");
		sb.append("FROM ");
		sb.append(type.getSimpleName());

		return sb.toString();
	}

	/**
	 * Method that creates a statement that selects data from a table by a field
	 * @param field that we select
	 * @return String as build statement
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT");
		sb.append(" * ");
		sb.append("FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = ?");

		return sb.toString();
	}

	/**
	 * Method that creates a statement that deletes data from a table
	 * @param id of objects to be deleted
	 * @return String as build statement
	 */
	private String createDeleteQuery(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM "
				+ type.getSimpleName() + " WHERE "
				+ type.getDeclaredFields()[0].getName() + "="
				+ id);
		return sb.toString();
	}

	/**
	 * Method that creates a statement that inserts data in a table
	 * @param t object to be inserted
	 * @return String as build statement
	 */
	private String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO `");
		sb.append(type.getSimpleName() + "` (");
		boolean first = true;
		boolean second = true;
		for (Field field : type.getDeclaredFields()) {
			if (first) {
				first = false;
			} else if(second) {
				sb.append(field.getName());
				second = false;
			} else {
				sb.append(", " + field.getName());
			}
		}
		sb.append(") VALUES (");

		try {
			first = true;
			second = true;
			for (Field field : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(t);

				if (first) {
					first = false;
				} else if(second) {
					sb.append("'" + value.toString() + "'");
					second = false;
				} else {
					sb.append(", '" + value.toString() + "'");
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		sb.append(")");

		return sb.toString();
	}

	/**
	 * Method that creates a statement that updates a field
	 * @param t field we want to update
	 * @param id id of the specific object
	 * @return String as a build statement
	 */
	private String createUpdateQuery(T t, int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " + type.getSimpleName() + " SET ");

		try {
			String idc = "";
			boolean first = true;
			for (Field field : type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(t);

				if (first) {
					sb.append(field.getName() + "='" + value.toString() + "'");
					idc = field.getName() + "='" + id + "'";
					first = false;
				} else {
					sb.append(", " + field.getName() + "='" + value.toString() + "'");
				}
			}

			sb.append(" WHERE " + idc);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}


	/**
	 * INSERT/UPDATE/DELETE DATA FROM DB
	 */


	/**
	 * Method that inserts entry in database
	 * @param t object that contains the data to be inserted
	 * @return ID at which we insert data
	 */
	public int insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return -1;
	}

	/**
	 * Method that updates entry from a table
	 * @param t object that contains the data to be updated
	 * @param id corresponding ID to object t
	 * @return true for update
	 */
	public boolean update(T t, int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery(t, id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
			return false;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return true;
	}

	/**
	 * Method that deletes entry from a table
	 * @param id corresponding to object to be deleted
	 * @return true if deleted
	 */
	public boolean delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
			return false;
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return true;
	}
}
