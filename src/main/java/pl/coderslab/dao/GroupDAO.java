package pl.coderslab.dao;

import pl.coderslab.entity.Group;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class GroupDAO extends Group{

    private static final String ADD_USER_GROUP_QUERY = "INSERT INTO user_group (name) VALUES (?)";

    private static final String READ_USER_QUERY = "SELECT * FROM user_group WHERE id = ?";

    private static final String UPDATE_USER_GROUP_QUERY = "UPDATE user_group SET name = ? WHERE id = ?";

    private static final String DELETE_USER_GROUP_QUERY = "DELETE FROM user_group WHERE id = ?";

    private static final String FIND_ALL_USERS_GROUP_QUERY = "SELECT * FROM user_group";



    public void create(Group group) throws SQLException {

        try(Connection conn = DbUtil.getConnection()){

            PreparedStatement statement = conn.prepareStatement(ADD_USER_GROUP_QUERY,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, group.getName());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys(); //zapisuje wartość id z auto incrementu

            if(!rs.next()) {

                throw new SQLException("User Group primary key was not generated");

            }

            int id = rs.getInt(1);

            group.setId(id);

        }

    }

    public Group read(int groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));

                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Group[] groups = new Group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));

                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }}

    private Group[] addToArray(Group g, Group[] groups) {
        Group[] tmpGroup = Arrays.copyOf(groups, groups.length + 1);
        tmpGroup[groups.length] = g;
        return tmpGroup;
    }
}
