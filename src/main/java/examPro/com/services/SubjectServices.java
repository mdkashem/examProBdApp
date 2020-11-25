package examPro.com.services;

import java.util.List;

import examPro.com.dao.subject.SubjectDAO;
import examPro.com.dao.subject.SubjectDAOImpl;
import examPro.com.model.subject.Subject;

public class SubjectServices {
	SubjectDAO subjects;
	public SubjectServices() {
		this.subjects = new SubjectDAOImpl();
	}
	
	public List<Subject> getAllSubject(){
		return this.subjects.getAllSubject();
	}
}
