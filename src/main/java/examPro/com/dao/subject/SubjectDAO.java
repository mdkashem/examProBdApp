package examPro.com.dao.subject;

import java.util.List;

import examPro.com.model.subject.Subject;

public interface SubjectDAO {
	
		
	    public boolean addSubject(Subject sub);    
	    public List<Subject> getAllSubject();
	    public int findLastSubjectId(Subject sub);
		public Subject getSubjectById(int id);
		public boolean updateSubject(Subject sub);
		public boolean deleteSubjectByID(int id);
		public Subject findSubjectByName(String sub);

}

