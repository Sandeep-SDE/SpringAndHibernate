

import com.zemoso.springdemo.testdb.TestDbServlet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestDbServletTest {

    @Test
    public void testSuccessfulDatabaseConnection() throws IOException, ServletException {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create a StringWriter to capture the response output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Create an instance of TestDbServlet
        TestDbServlet servlet = new TestDbServlet();

        // Call the doGet() method
        servlet.doGet(request, response);

        // Verify the output
        writer.flush();
        String responseOutput = stringWriter.toString();

        // Check if the response contains the success message
        Assertions.assertTrue(responseOutput.contains("SUCCESS!!!"));
    }

    @Test
    public void testExceptionHandling() throws IOException, ServletException {
        // Mock HttpServletRequest and HttpServletResponse
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Create a StringWriter to capture the response output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Create an instance of TestDbServlet
        TestDbServlet servlet = new TestDbServlet();

        // Modify the database connection details to cause an exception
        servlet.setJdbcUrl("invalid-url");

        // Call the doGet() method
        servlet.doGet(request, response);

        // Verify the output
        writer.flush();
        String responseOutput = stringWriter.toString();

        // Check if the response does not contain the success message
        Assertions.assertFalse(responseOutput.contains("SUCCESSFULL!!!"));
    }
}
