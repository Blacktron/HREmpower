package db;

import com.fasterxml.jackson.databind.JsonNode;
import models.Entity;
import models.EntityDbHandler;
import models.implementation.Technology;
import utils.DBUtils;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Created by Terrax on 28-Feb-2016.
 */
public class DBTechnologyQueryHandler extends DBUtils implements EntityDbHandler {
    private static DBTechnologyQueryHandler instance;

    private DBTechnologyQueryHandler() { }

    public static DBTechnologyQueryHandler getInstance() {
        if (instance == null) {
            instance = new DBTechnologyQueryHandler();
        }

        return instance;
    }

    /**
     * Returns a list of all Technologies currently entered in the database.
     * @return the list of all Technologies.
     * @throws SQLException
     */
    public List<Entity> getAllEntities() throws SQLException {
        String query = "SELECT * FROM technology";
        return DBUtils.execQueryAndBuildResult(query, null, this);
    }

    /**
     * Adds a new Technology to the database.
     * @param node the JSON object holding the data of the Technology.
     * @return true if the Technology was added, false otherwise.
     * @throws SQLException
     */
    public boolean addEntity(JsonNode node) throws SQLException {
        boolean check = false;
        Technology technology = new Technology(node);

        // Check if such Technology already exists in the database.
        String technologyName = technology.getTechnologyName();
        String technologyExistsQuery = "SELECT technology.technologyId FROM technology WHERE technologyName = ?";
        boolean technologyExists = DBUtils.isParamExists(technologyName, technologyExistsQuery);

        // If there is no such Technology in the database, prepare the query and execute it.
        if (!technologyExists) {
            String query = "INSERT INTO technology(technologyName) VALUES(?)";
            Object[] params = {technologyName};
            DBUtils.execQuery(query, params);
            check = true;
        }

        return check;
    }

    /**
     * Deletes a selected Technology from the database.
     * @param technologyId the ID of the Technology to be removed.
     * @throws SQLException
     */
    public void deleteEntity(int technologyId) throws SQLException {
        Object[] params = {technologyId};
        String query = "DELETE FROM technology WHERE technologyId = ?";
        DBUtils.execQuery(query, params);
    }

    /**
     * Method which decides which exact search method to use based on the given parameters.
     * @param data map which holds the parameters.
     * @return a list with found results.
     * @throws SQLException
     */
    public List<Entity> searchEntity(MultivaluedMap<String, String> data) throws SQLException {
        int searchParametersCount = data.size();
        String technologyName;
        List<Entity> result;

        // Check which parameters exist in the query.
        if (searchParametersCount == 0) {
            // If there are no parameters provided - show all entries in the database.
            result = this.getAllEntities();
        } else if (searchParametersCount == 1 && data.containsKey("technologyName")) {
            technologyName = data.getFirst("technologyName");
            result = searchTechnologyByName(technologyName);
        } else {
            /*
                Make a Map holding the keys and their corresponding values.
                Pass the Map as parameter to a method which dynamically builds a query based on the
                existing parameters.
             */
            Map<String, String> parameters = new HashMap<String, String>();

            for (String key : data.keySet()) {
                String value = data.getFirst(key);
                parameters.put(key, value);
            }

            result = this.searchTechnologyByParams(parameters);
        }

        return result;
    }

    /**
     * Search if Technology with the specified ID exists in the database.
     * @param technologyId the ID of the Technology to be searched.
     * @return the Technology as object.
     * @throws SQLException
     */
    public Entity searchEntityById(int technologyId) throws SQLException {
        Object[] params = {technologyId};
        String query = "SELECT * FROM technology WHERE technologyId = ?";
        List<Entity> result = DBUtils.execQueryAndBuildResult(query, params, this);

        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        else {
            throw new SQLException();
        }
    }

    /**
     * Search Technology by name if exists in the database.
     * @param technologyName the name of the Technology to search for.
     * @return the Technology as object.
     * @throws SQLException
     */
    public List<Entity> searchTechnologyByName(String technologyName) throws SQLException {
        Object[] params = {technologyName};
        String query = "SELECT * FROM technology WHERE technologyName LIKE ?";

        return DBUtils.execQueryAndBuildResult(query, params, this);
    }

    /**
     * Method which searches for entries based on the provided parameters.
     * @param parameters the parameters to search for.
     * @return a List holding the result of the search.
     * @throws SQLException
     */
    public List<Entity> searchTechnologyByParams(Map<String, String> parameters) throws SQLException {
        // The SELECT and FROM part of the query.
        String query = "SELECT * FROM technology";
        int count = 0;                                                  // Used to build the array holding the parameters for query execution.
        Object[] params = new Object[parameters.size()];                // Array of objects holding the parameters for the query execution.
        Set<String> keys = parameters.keySet();                         // Set holding the keys of the Map used to iterate through it and build the array.
        String fullQuery = DBUtils.buildQuery(parameters, query);       // The query which to be used when execute request to the database.

        System.out.println("FULL QUERY: " + fullQuery);

        for (String key : keys) {
            params[count] = parameters.get(key);
            count++;
        }

        return DBUtils.execQueryAndBuildResult(fullQuery, params, this);
    }

    /**
     * Update a Technology entry in the database with provided details.
     * @param technologyId the ID of the Technology to be edited.
     * @param node the details of the Technology which to be used for the modification.
     * @return true if the modification is successful, false otherwise.
     * @throws SQLException
     */
    public boolean updateEntity(int technologyId, JsonNode node) throws SQLException {
        boolean check = false;

        String operation = node.get("operations").textValue();
        String query;
        Object[] params;

        // If we want to edit the name of a Technology.
        if (operation.equalsIgnoreCase("modifyTechnology")) {
            // Check if Technology exists in the database.
            String technologyExistsQuery = "SELECT technologyId FROM technology WHERE technologyId = ?";
            boolean technologyExists = DBUtils.isParamExists(technologyId, technologyExistsQuery);

            // Update the Technology.
            if (technologyExists) {
                String technologyName = node.get("technologyName").textValue();
                query = "UPDATE technology SET technologyName = ? WHERE technologyId = ?";
                params = new Object[]{technologyName, technologyId};
                DBUtils.execQuery(query, params);
            }

            check = true;
        }

        return check;
    }

    /**
     * Method which builds the result returned from the database.
     * @param resultSet the result given by the database.
     * @return list holding the result.
     */
    public List<Entity> buildResult(ResultSet resultSet) {
        List<Entity> data = new ArrayList<Entity>();

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    int id = resultSet.getInt("technologyId");
                    String technologyName = resultSet.getString("technologyName");

                    Technology technology = new Technology(id, technologyName);
                    data.add(technology);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
}