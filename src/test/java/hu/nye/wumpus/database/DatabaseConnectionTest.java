package hu.nye.wumpus.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectionTest {
    @Test
    public void testGetConnectionShouldReturnNonNullConnectionWhenDatabaseIsAvailable() {
        // Given
        DatabaseConnection underTest = new DatabaseConnection();

        // When
        try {
            Connection connection = underTest.getConnection();

            // Then
            assertNotNull(connection);
            // Egyéb ellenőrzések, ha szükséges
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException thrown");
        }
    }
}
