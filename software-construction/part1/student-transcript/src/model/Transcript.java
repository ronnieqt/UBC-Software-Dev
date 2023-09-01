package model;

import java.util.List;
import java.util.ArrayList;

public class Transcript
{
    /**
     * INVARIANT: course list and grade list are the same size
     *            each course has a grade associated, and vice versa, at matching indices
     */
    String m_student_name;
    int m_student_id;
    List<String> m_courses;
    List<Double> m_grades;

    public Transcript(String student_name, int student_id)
    {
        m_student_name = student_name;
        m_student_id = student_id;
        m_courses = new ArrayList<String>();
        m_grades = new ArrayList<Double>();
    }

    // getters
    public String get_student_name()
    {
        return m_student_name;
    }

    // setters
    public void set_student_name(String student_name)
    {
        this.m_student_name = student_name;
    }
    public void set_student_id(int student_id)
    {
        this.m_student_id = student_id;
    }

    // REQUIRES : `grade` should be in [0.0, 4.0] and `course` should not be null
    // MODIFIES : this
    // EFFECTS  : add a course to the transcript
    public void add_grade(String course, double grade)
    {
        m_courses.add(course);
        m_grades.add(grade);
    }

    // REQUIRES: a course the student has already taken
    // EFFECTS : returns course name and grade in format CourseName: Grade
    public String get_course_and_grade(String course)
    {
        // This method should return course name and grade in some consistent String format
        int idx = m_courses.indexOf(course);
        if (idx < 0) {
            return null;
        }
        else {
            return course + ": " + m_grades.get(idx);
        }
    }

    // EFFECTS: print transcript to the screen
    public void print_transcript()
    {
        System.out.println("=== " + m_student_name + " (" + m_student_id + ") ===");
        for (String c : m_courses) {
            System.out.println(get_course_and_grade(c));
        }
    }

    // EFFECTS: get student's average GPA
    public double get_gpa()
    {
        double sum = 0.0;
        for (double g : m_grades) {
            sum += g;
        }
        if (!m_grades.isEmpty()) {
            return sum / m_grades.size();
        }
        else {
            return 0.0;
        }
    }
}
