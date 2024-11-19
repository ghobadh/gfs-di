package ca.gforcesoftware.gfsdi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gavinhashemi on 2024-11-14
 */
public class Meeting {


    class Student {
        Integer id;
        String name;
        List<Integer> deptIds;
    }

    class Department {
        Integer departmentId;
        String deptName;
    }

    public void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        List<Department> departmentList = new ArrayList<Department>();

        List<Student> listSTudent = studentList.stream()

                .filter(student -> {
                    return departmentList.stream().anyMatch(x -> x.departmentId.equals(student.deptIds));

                })

                .collect(Collectors.toList());


    }
// All the departments with atleast two students
}
