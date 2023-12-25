package com.driver;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRooo;

    public void addSTUDEnt(Student g) {
        studentRooo.addSTUDEnt(g);
    }
    public void addTEACHer(Teacher w) {
        studentRooo.addTEACHer(w);
    }
    public void add_S_T_Pair(String g,String w) {
        studentRooo.add_S_T_Pair(g, w);
    }

    public Student getstudenTbynamE(String Nem) {
        return studentRooo.getstudenTbynamE(Nem);
    }

    public Teacher getteacheRbynamE(String Nem) {
        return studentRooo.getteacheRbynamE(Nem);
    }
    public List<String>getStudentsByTeacherName(String Nem){
        return studentRooo.getStudentsByTeacherNAME(Nem);
    }
    public List<String>getallSTUDEnt(){
        return studentRooo.getallSTUDEnt();
    }
    public void deleteTEACHERbyName(String Nem) {
        studentRooo.deleteTEACHERbyName(Nem);
    }
    public void deleteallTEACHER() {
        studentRooo.deleteallTEACHER();
    }

}