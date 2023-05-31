import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private final InitializeGameCase useCase =
            new InitializeGameCase();

    @Test
    void testValidateLineParts() {
        // Scenario 1: Verify valid line
        assertDoesNotThrow(() -> useCase.validateLineParts
                (new String[]{"lawsoaj01", "A.J. Lawson", "MIN", "-2.9573255", "-3.9235241", "-6.8808496"}));

        // Scenario 2: Verify empty player ID
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"", "A.J. Lawson", "MIN", "-2.9573255", "-3.9235241", "-6.8808496"}));

        // Scenario 3: Verify empty player name
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"lawsoaj01", "", "MIN", "-2.9573255", "-3.9235241", "-6.8808496"}));

        // Scenario 4: Verify empty team name
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"lawsoaj01", "A.J. Lawson", "", "-2.9573255", "-3.9235241", "-6.8808496"}));

        // Scenario 5: Verify Non decimal offense
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"lawsoaj01", "A.J. Lawson", "MIN", "aaa", "-3.9235241", "-6.8808496"}));

        // Scenario 6: Verify Non decimal defense
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"lawsoaj01", "A.J. Lawson", "MIN", "-2.9573255", "bbb", "-6.8808496"}));

        // Scenario 7: Verify Non decimal total
        assertThrows(InvalidLineStructureException.class,
                () -> useCase.validateLineParts(new String[]{"lawsoaj01", "A.J. Lawson", "MIN", "-2.9573255", "-3.9235241", "ccc"}));
    }
}
