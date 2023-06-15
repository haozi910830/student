package entity;

public class student {
	private Integer stuId;

	private String stuName;

	private String stuSex;

	private Integer stuAge;

	private String stuClass;

	public student(Integer stuId, String stuName, String stuSex, Integer stuAge, String stuClass) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuAge = stuAge;
		this.setStuClass(stuClass);
	}

	public student() {
		super();
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(Integer stuAge) {
		this.stuAge = stuAge;
	}

	public String getStuClass() {
		return stuClass;
	}

	public void setStuClss(String stuClass) {
		this.setStuClass(stuClass);
	}

	@Override
	public String toString() {
		return "student [stuId=" + stuId + ", stuName=" + stuName + ", stuSex=" + stuSex + ", stuAge=" + stuAge
				+ ", stuClass=" + getStuClass() + "]";
	}

	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	//根据班级查询全部学生信息
	  @Override
	    public boolean equals(Object obj) {
	        // 如果obj是null或者不是student类型，返回false
	        if (obj == null || !(obj instanceof student)) {
	            return false;
	        }
	        // 如果obj和this是同一个对象，返回true
	        if (obj == this) {
	            return true;
	        }
	        // 将obj转换为student类型
	        student other = (student) obj;
	        // 比较两个对象的班级是否相同
	        return this.getStuClass().equals(other.getStuClass());
	    }

	    @Override
	    public int hashCode() {
	        // 使用31作为乘数，因为它是一个奇素数，可以减少哈希冲突的概率
	        int result = 31;
	        // 使用班级作为哈希码的基础，如果班级是null，则使用0
	        result = result + (this.getStuClass() == null ? 0 : this.getStuClass().hashCode());
	        // 返回哈希码
	        return result;
	    }


}
