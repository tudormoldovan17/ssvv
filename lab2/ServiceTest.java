import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.StudentValidator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceTest {
    private Service studentService;
    private StudentXMLRepository studentXmlRepo;
    private StudentValidator studentValidator;
    private TemaXMLRepository temaXMLRepository;
    private NotaXMLRepository notaXMLRepository;

    @Before
    public void setUp() {
        studentXmlRepo = mock(StudentXMLRepository.class); // Creating a mock repository
        temaXMLRepository = mock(TemaXMLRepository.class); // Creating a mock repository
        notaXMLRepository = mock(NotaXMLRepository.class); // Creating a mock repository
        studentService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository); // Injecting the mock repositories
    }

    @Test
    public void testSaveStudent_Success() {
        // Mocking the save method of studentXmlRepo to return a non-null result
        when(studentXmlRepo.save(any(Student.class))).thenReturn(new Student("1", "John Doe", 123));

        int result = studentService.saveStudent("1", "John Doe", 123);
        assertEquals(0, result);
    }

    @Test
    public void testSaveStudent_Failure() {
        // Mocking the save method of studentXmlRepo to return null
        when(studentXmlRepo.save(any(Student.class))).thenReturn(null);

        int result = studentService.saveStudent("1", "Jane Smith", 456);
        assertEquals(1, result);
    }
}
