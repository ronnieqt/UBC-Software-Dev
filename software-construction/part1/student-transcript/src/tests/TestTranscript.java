package tests;

import org.junit.Before;
import org.junit.Test;

import model.Transcript;

import static org.junit.Assert.assertEquals;

public class TestTranscript
{
    private Transcript m_transcript;

    @Before
    public void init()
    {
        m_transcript = new Transcript("Ronnie Chen", 1907);
    }

    @Test
    public void test_add_grade()
    {
        m_transcript.add_grade("STA-101", 3.3);
        m_transcript.add_grade("MATH-250", 4.0);
        assertEquals(m_transcript.get_course_and_grade("STA-101"), "STA-101: 3.3");
        assertEquals(m_transcript.get_gpa(), 3.65, 1e-6);
    }
}
