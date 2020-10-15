package dto;

import entity.Faculty;

import java.util.List;

public class FacultyDTO {

    int count;
    List<Faculty> facultyList;


    public FacultyDTO(int count, List<Faculty> facultyList) {
        this.count = count;
        this.facultyList = facultyList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }
}
