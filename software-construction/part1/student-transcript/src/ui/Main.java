package ui;

import model.Transcript;

public class Main
{
    public static void main(String[] args)
    {
        Transcript t1 = new Transcript("Jane Doe", 7830);
        Transcript t2 = new Transcript("Ada Lovelace", 8853);
        Transcript t3 = new Transcript("Ronnie Chen", 1907);

        t1.add_grade("CS-201", 3.5);
        t1.add_grade("ENG-201", 4.1);
        t1.add_grade("CS-110", 3.1);

        t2.add_grade("MATH-105", 4.0);
        t2.add_grade("CS-330", 3.3);

        t3.add_grade("STAT-101", 4.0);
        t3.add_grade("CS-510", 4.3);

        t1.print_transcript();
        System.out.println(t1.get_gpa());
    }
}
