package pl.coderslab.dao;

import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class SolutionDAO {

    private static final String ADD_SOLUTION_QUERY =
            "INSERT INTO solution (created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";

    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";

    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";

    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";

    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";

    public void create(Solution solution) throws SQLException {

        try(Connection conn = DbUtil.getConnection()){

            PreparedStatement statement = conn.prepareStatement(ADD_SOLUTION_QUERY,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExerciseId());
            statement.setInt(5, solution.getUserId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys(); //zapisuje wartość id z auto incrementu

            if(!rs.next()) {

                throw new SQLException("Solution primary key was not generated");

            }

            int id = rs.getInt(1);

            solution.setId(id);

        }

    }

    public Solution read(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getUpdated());
            statement.setString(2, solution.getDescription());
            statement.setInt(3, solution.getExerciseId());
            statement.setInt(4, solution.getUserId());
            statement.setInt(5, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solution[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("users_id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }}

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = s;
        return tmpSolutions;
    }



}
