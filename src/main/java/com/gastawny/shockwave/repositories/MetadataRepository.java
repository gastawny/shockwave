package com.gastawny.shockwave.repositories;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MetadataRepository {

    private final DataSource dataSource;

    public MetadataRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String findPrimaryKeyColumn(String table) {
        if (table == null || table.isBlank()) {
            throw new IllegalArgumentException("table must not be null or blank");
        }
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();

            String catalog = safeCatalog(conn);
            String schema = safeSchema(conn);

            try (ResultSet rs = meta.getPrimaryKeys(catalog, schema, table)) {
                if (rs.next()) {
                    return rs.getString("COLUMN_NAME");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving primary key for table: " + table, e);
        }
        return null;
    }

    public boolean tableExists(String table) {
        if (table == null || table.isBlank()) {
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            String catalog = safeCatalog(conn);
            String schema = safeSchema(conn);

            try (ResultSet rs = meta.getTables(catalog, schema, table, null)) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking table existence: " + table, e);
        }
    }

    public boolean columnExists(String table, String column) {
        if (table == null || table.isBlank() || column == null || column.isBlank()) {
            return false;
        }
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            String catalog = safeCatalog(conn);
            String schema = safeSchema(conn);

            try (ResultSet rs = meta.getColumns(catalog, schema, table, column)) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking column existence: " + table + "." + column, e);
        }
    }

    public Set<String> getColumnNames(String table) {
        Set<String> cols = new HashSet<>();
        if (table == null || table.isBlank()) {
            return cols;
        }
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            String catalog = safeCatalog(conn);
            String schema = safeSchema(conn);

            try (ResultSet rs = meta.getColumns(catalog, schema, table, null)) {
                while (rs.next()) {
                    String col = rs.getString("COLUMN_NAME");
                    if (col != null) {
                        cols.add(col);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving columns for table: " + table, e);
        }
        return cols;
    }

    private String safeCatalog(Connection conn) {
        try {
            String c = conn.getCatalog();
            return (c == null || c.isBlank()) ? null : c;
        } catch (SQLException e) {
            return null;
        }
    }

    private String safeSchema(Connection conn) {
        try {
            String s = conn.getSchema();
            return (s == null || s.isBlank()) ? null : s;
        } catch (SQLException e) {
            return null;
        }
    }

    public Double resolveParameterDependencySymbol(String referenceTable, String referenceColumn, Long dataId) {
        var referenceColumnId = findPrimaryKeyColumn(referenceTable);
        if (referenceTable == null || referenceTable.isBlank()
                || referenceColumn == null || referenceColumn.isBlank()
                || referenceColumnId == null || referenceColumnId.isBlank()
                || dataId == null) {
            throw new IllegalArgumentException("Invalid arguments provided");
        }

        if (!tableExists(referenceTable)
                || !columnExists(referenceTable, referenceColumn)
                || !columnExists(referenceTable, referenceColumnId)) {
            throw new IllegalArgumentException("Table or columns do not exist");
        }

        String tableQuoted = quoteIdentifier(referenceTable);
        String colQuoted = quoteIdentifier(referenceColumn);
        String idQuoted = quoteIdentifier(referenceColumnId);

        String sql = "SELECT " + colQuoted + " FROM " + tableQuoted + " WHERE " + idQuoted + " = ? LIMIT 1";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, dataId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Object val = rs.getObject(1);
                if (val == null) return null;
                if (val instanceof Number) {
                    return ((Number) val).doubleValue();
                }
                String s = val.toString();
                if (s.isBlank()) return null;
                try {
                    return Double.valueOf(s);
                } catch (NumberFormatException ex) {
                    throw new IllegalStateException("Value is not numeric: " + s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error resolving parameter dependency symbol", e);
        }
    }

    private String quoteIdentifier(String ident) {
        String clean = ident.replace("`", "");
        return "`" + clean + "`";
    }
}
