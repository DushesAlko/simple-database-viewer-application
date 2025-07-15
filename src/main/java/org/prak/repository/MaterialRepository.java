package org.prak.repository;

import org.prak.database.DatabaseConnector;
import org.prak.model.Material;
import org.prak.repository.base.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialRepository extends BaseRepository<Material> {

    @Override
    protected String getQuery() {
        return "SELECT id, name, discription, group_id FROM materials ORDER BY name";
    }

    @Override
    protected Material mapResultSet(ResultSet rs) throws SQLException {
        return new Material(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("discription"),
                rs.getInt("group_id")
        );
    }

    public List<Material> getByGroupId(int groupId) {
        List<Material> materials = new ArrayList<>();
        String sql = "SELECT id, name, discription, group_id FROM materials WHERE group_id = ? ORDER BY name";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, groupId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    materials.add(mapResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }
}
