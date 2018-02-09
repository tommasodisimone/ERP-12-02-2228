package cn.itcast.invoice.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import cn.itcast.invoice.invoice.storedetail.vo.StoreDetailModel;

public class GeneratorUtil {
	
	
	
	private Class clazz;
	private String pkg;
	private String rootDir;
	private String big;
	private String small;
	private String little;
	/*
	pkg		cn.itcast.invoice.auth.emp
	rootDir	src/cn/itcast/invoice/auth/emp
	big		Emp		EmpModel	EmpQueryModel
	small	emp		empEbi	empDao
	little	e		em		eqm
	*/
	public GeneratorUtil(Class clazz)throws Exception{
		this.clazz = clazz;
		//-1.Ã¦â€¢Â°Ã¦ï¿½Â®Ã¥Ë†ï¿½Ã¥Â§â€¹Ã¥Å’â€“
		dataInit();
		//0.Ã§â€�Å¸Ã¦Ë†ï¿½Ã¤Â¿ï¿½Ã¥Â­ËœÃ¦â€“â€¡Ã¤Â»Â¶Ã§Å¡â€žÃ§â€ºÂ®Ã¥Â½â€¢
		genderatorDirectories();
		//1.Ã§â€�Å¸Ã¦Ë†ï¿½QueryModel
		generatorQueryModel();
		//2.Ã§â€�Å¸Ã¦Ë†ï¿½Model.hbm.xml
		generatorHbmXml();
		//3.Ã§â€�Å¸Ã¦Ë†ï¿½Dao
		generatorDao();
		//4.Ã§â€�Å¸Ã¦Ë†ï¿½DaoImpl
		generatorDaoImpl();
		//5.Ã§â€�Å¸Ã¦Ë†ï¿½Ebi
		generatorEbi();
		//6.Ã§â€�Å¸Ã¦Ë†ï¿½Ebo
		generatorEbo();
		//7.Ã§â€�Å¸Ã¦Ë†ï¿½Action
		generatorAction();
		//8.Ã§â€�Å¸Ã¦Ë†ï¿½applicationContext.xml
		generatorApplicationContextXml();
		//9.Ã§â€�Å¸Ã¦Ë†ï¿½struts.xml
	}
	
	//8.Ã§â€�Å¸Ã¦Ë†ï¿½applicationContext.xml
	private void generatorApplicationContextXml() throws Exception{
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File("resources/applicationContext-"+small+".xml");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();

		bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		bw.newLine();

		bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		bw.newLine();
		
		bw.write("	xsi:schemaLocation=\"");
		bw.newLine();
		
		bw.write("		http://www.springframework.org/schema/beans");
		bw.newLine();
		
		bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd");
		bw.newLine();
		
		bw.write("		\">  ");
		bw.newLine();
		
		bw.write("	<!-- Action -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+small+"Action\" class=\""+pkg+".web."+big+"Action\" scope=\"prototype\">");
		bw.newLine();
		
		bw.write("		<property name=\""+small+"Ebi\" ref=\""+small+"Ebi\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("	<!-- Ebi -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+small+"Ebi\" class=\""+pkg+".business.ebo."+big+"Ebo\">");
		bw.newLine();
		
		bw.write("		<property name=\""+small+"Dao\" ref=\""+small+"Dao\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("	<!-- Dao -->");
		bw.newLine();
		
		bw.write("	<bean id=\""+small+"Dao\" class=\""+pkg+".dao.impl."+big+"DaoImpl\">");
		bw.newLine();
		
		bw.write("		<property name=\"sessionFactory\" ref=\"sessionFactory\"/>");
		bw.newLine();
		
		bw.write("	</bean>");
		bw.newLine();
		
		bw.write("</beans>");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
		
		
		
	}
	//7.Ã§â€�Å¸Ã¦Ë†ï¿½Action
	private void generatorAction() throws Exception{
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/web/"+big+"Action.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".web;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import java.util.List;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".business.ebi."+big+"Ebi;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"QueryModel;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseAction;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+big+"Action extends BaseAction{");
		bw.newLine();
		
		bw.write("	public "+big+"Model "+little+"m = new "+big+"Model();");
		bw.newLine();
		
		bw.write("	public "+big+"QueryModel "+little+"qm = new "+big+"QueryModel();");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	private "+big+"Ebi "+small+"Ebi;");
		bw.newLine();
		
		bw.write("	public void set"+big+"Ebi("+big+"Ebi "+small+"Ebi) {");
		bw.newLine();
		
		bw.write("		this."+small+"Ebi = "+small+"Ebi;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¥Ë†â€”Ã¨Â¡Â¨Ã©Â¡ÂµÃ©ï¿½Â¢");
		bw.newLine();
		
		bw.write("	public String list(){");
		bw.newLine();
		
		bw.write("		setDataTotal("+small+"Ebi.getCount("+little+"qm));");
		bw.newLine();
		
		bw.write("		List<"+big+"Model> "+small+"List = "+small+"Ebi.getAll("+little+"qm,pageNum,pageCount);");
		bw.newLine();
		
		bw.write("		put(\""+small+"List\","+small+"List);");
		bw.newLine();
		
		bw.write("		return LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//Ã¤Â¿ï¿½Ã¥Â­Ëœ/Ã¤Â¿Â®Ã¦â€�Â¹");
		bw.newLine();
		
		bw.write("	public String save(){");
		bw.newLine();
		
		bw.write("		if("+little+"m.getUuid()== null){");
		bw.newLine();
		
		bw.write("			"+small+"Ebi.save("+little+"m);");
		bw.newLine();
		
		bw.write("		}else{");
		bw.newLine();
		
		bw.write("			"+small+"Ebi.update("+little+"m);");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//Ã¨Â·Â³Ã¨Â½Â¬Ã¥Ë†Â°Ã¦Â·Â»Ã¥Å Â /Ã¤Â¿Â®Ã¦â€�Â¹Ã©Â¡ÂµÃ©ï¿½Â¢");
		bw.newLine();
		
		bw.write("	public String input(){");
		bw.newLine();
		
		bw.write("		if("+little+"m.getUuid()!=null){");
		bw.newLine();
		
		bw.write("			"+little+"m = "+small+"Ebi.get("+little+"m.getUuid());");
		bw.newLine();
		
		bw.write("		}");
		bw.newLine();
		
		bw.write("		return INPUT;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	//Ã¥Ë†Â Ã©â„¢Â¤");
		bw.newLine();
		
		bw.write("	public String delete(){");
		bw.newLine();
		
		bw.write("		"+small+"Ebi.delete("+little+"m);");
		bw.newLine();
		
		bw.write("		return TO_LIST;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();

		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
	}
	//6.Ã§â€�Å¸Ã¦Ë†ï¿½Ebo
	private void generatorEbo() throws Exception{
		File f = null;
		BufferedWriter bw= null;
		try {
		f =  new File(rootDir+"/business/ebo/"+big+"Ebo.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".business.ebo;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import java.io.Serializable;");
		bw.newLine();
		
		bw.write("import java.util.List;");
		bw.newLine();

		bw.newLine();
		
		bw.write("import "+pkg+".business.ebi."+big+"Ebi;");
		bw.newLine();
		
		bw.write("import "+pkg+".dao.dao."+big+"Dao;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.exception.AppException;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+big+"Ebo implements "+big+"Ebi{");
		bw.newLine();
		
		bw.write("	private "+big+"Dao "+small+"Dao;");
		bw.newLine();
		
		bw.write("	public void set"+big+"Dao("+big+"Dao "+small+"Dao) {");
		bw.newLine();
		
		bw.write("		this."+small+"Dao = "+small+"Dao;");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void save("+big+"Model "+little+"m) {");
		bw.newLine();
		
		bw.write("		"+small+"Dao.save("+little+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void delete("+big+"Model "+little+"m) {");
		bw.newLine();
		
		bw.write("		"+small+"Dao.delete("+little+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public void update("+big+"Model "+little+"m) {");
		bw.newLine();
		
		bw.write("		"+small+"Dao.update("+little+"m);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public "+big+"Model get(Serializable uuid) {");
		bw.newLine();
		
		bw.write("		return "+small+"Dao.get(uuid);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();

		bw.write("	public List<"+big+"Model> getAll() {");
		bw.newLine();
		
		bw.write("		return "+small+"Dao.getAll();");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public List<"+big+"Model> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {");
		bw.newLine();
		
		bw.write("		return "+small+"Dao.getAll(qm,pageNum,pageCount);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("	public Integer getCount(BaseQueryModel qm) {");
		bw.newLine();
		
		bw.write("		return "+small+"Dao.getCount(qm);");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }

	}
	//5.Ã§â€�Å¸Ã¦Ë†ï¿½Ebi
	private void generatorEbi() throws Exception{
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/business/ebi/"+big+"Ebi.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".business.ebi;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import org.springframework.transaction.annotation.Transactional;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseEbi;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("@Transactional");
		bw.newLine();
		
		bw.write("public interface "+big+"Ebi extends BaseEbi<"+big+"Model> {");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
		
	}
	//4.Ã§â€�Å¸Ã¦Ë†ï¿½DaoImpl
	private void generatorDaoImpl() throws Exception{
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/dao/impl/"+big+"DaoImpl.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".dao.impl;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import org.hibernate.criterion.DetachedCriteria;");
		bw.newLine();
		
		bw.write("import org.hibernate.criterion.Restrictions;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".dao.dao."+big+"Dao;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"QueryModel;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseDaoImpl;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+big+"DaoImpl extends BaseDaoImpl<"+big+"Model> implements "+big+"Dao{");
		bw.newLine();
		
		bw.write("	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){");
		bw.newLine();
		
		bw.write("		"+big+"QueryModel "+little+"qm = ("+big+"QueryModel) qm;");
		bw.newLine();
		
		bw.write("		//TODO Ã¦Â·Â»Ã¥Å Â Ã¨â€¡ÂªÃ¥Â®Å¡Ã¤Â¹â€°Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¨Â§â€žÃ¥Ë†â„¢");
		bw.newLine();
		
		bw.write("	}");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
		
	}
	//3.Ã§â€�Å¸Ã¦Ë†ï¿½Dao
	private void generatorDao() throws Exception{
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/dao/dao/"+big+"Dao.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".dao.dao;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import "+pkg+".vo."+big+"Model;");
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseDao;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public interface "+big+"Dao extends BaseDao<"+big+"Model> {");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
            System.out.println("File not found");
          } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
		
		
	}
	//2.Ã§â€�Å¸Ã¦Ë†ï¿½Model.hbm.xml
	private void generatorHbmXml() throws Exception{
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/vo/"+big+"Model.hbm.xml");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		
		bw.write("<!DOCTYPE hibernate-mapping PUBLIC");
		bw.newLine();
		
		bw.write("        '-//Hibernate/Hibernate Mapping DTD 3.0//EN'");
		bw.newLine();
		
		bw.write("        'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("<hibernate-mapping>");
		bw.newLine();
		
		bw.write("    <class name=\""+pkg+".vo."+big+"Model\" table=\"tbl_"+small+"\">");
		bw.newLine();
		
		bw.write("        <id name=\"uuid\">");
		bw.newLine();
		
		bw.write("            <generator class=\"native\" />");
		bw.newLine();
		
		bw.write("        </id>");
		bw.newLine();
		//Ã¥Â¾ÂªÃ§Å½Â¯Ã§â€�Å¸Ã¦Ë†ï¿½
		//Ã©Å“â‚¬Ã¨Â¦ï¿½Ã¨Å½Â·Ã¥ï¿½â€“Ã¦â€°â‚¬Ã¦Å“â€°Ã§Å¡â€žÃ¥Â­â€”Ã¦Â®ÂµÃ¥ï¿½ï¿½
		Field[] fields = clazz.getDeclaredFields();
		for(Field fd:fields){
			//Ã¥Â¦â€šÃ¦Å¾Å“Ã¦ËœÂ¯Ã§Â§ï¿½Ã¦Å“â€°Ã§Å¡â€žÃ§â€�Å¸Ã¦Ë†ï¿½
			if (check(fd)) {
			bw.write("        <property name=\""+fd.getName()+"\"/>");
			bw.newLine();}
		}
		
		bw.write("    </class>");
		bw.newLine();
		
		bw.write("</hibernate-mapping>");
		
		bw.flush();
	}
	catch (java.io.FileNotFoundException e1) {
        System.out.println("File not found");
      } 
	 finally {
           if (bw != null) {
             try {
               bw.close(); // OK
             } catch (java.io.IOException e3) {
               System.out.println("I/O Exception");
             }
           }
         }
	
		
	}
/*
"+big+"
"+small+"
"+pkg+"
*/
	//1.Ã§â€�Å¸Ã¦Ë†ï¿½QueryModel
	private void generatorQueryModel() throws Exception {
		//1.Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€“â€¡Ã¤Â»Â¶
		File f = null;
		BufferedWriter bw= null;
		try {
		f = new File(rootDir+"/vo/"+big+"QueryModel.java");
		if(f.exists()){
			return;
		}
		f.createNewFile();
		
		bw = new BufferedWriter(new FileWriter(f));
		
		bw.write("package "+pkg+".vo;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("import cn.itcast.invoice.util.base.BaseQueryModel;");
		bw.newLine();
		
		bw.newLine();
		
		bw.write("public class "+big+"QueryModel extends "+big+"Model implements BaseQueryModel{");
		bw.newLine();
		
		bw.write("	//TODO Ã¨Â¯Â·Ã¦Â·Â»Ã¥Å Â Ã¨â€¡ÂªÃ¥Â®Å¡Ã¤Â¹â€°Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¦ï¿½Â¡Ã¤Â»Â¶Ã¥Â­â€”Ã¦Â®Âµ");
		bw.newLine();
		
		bw.write("}");
		bw.newLine();
		
		bw.flush();
		}
		catch (java.io.FileNotFoundException e1) {
	        System.out.println("File not found");
	      } 
		 finally {
	           if (bw != null) {
	             try {
	               bw.close(); // OK
	             } catch (java.io.IOException e3) {
	               System.out.println("I/O Exception");
	             }
	           }
	         }
				
	}

	//0.Ã§â€�Å¸Ã¦Ë†ï¿½Ã¤Â¿ï¿½Ã¥Â­ËœÃ¦â€“â€¡Ã¤Â»Â¶Ã§Å¡â€žÃ§â€ºÂ®Ã¥Â½â€¢
	private void genderatorDirectories() {
		//Ã¥Ë†â€ºÃ¥Â»ÂºÃ¦â€°â‚¬Ã¦Å“â€°Ã¦â€“â€¡Ã¤Â»Â¶Ã§Å¡â€žÃ¤Â¿ï¿½Ã¥Â­ËœÃ§â€ºÂ®Ã¥Â½â€¢
		//web
		File f = new File(rootDir+"/web");
		f.mkdirs();
		//business.ebi
		f = new File(rootDir+"/business/ebi");
		f.mkdirs();
		//business.ebo
		f = new File(rootDir+"/business/ebo");
		f.mkdirs();
		//dao.dao
		f = new File(rootDir+"/dao/dao");
		f.mkdirs();
		//dao.impl
		f = new File(rootDir+"/dao/impl");
		f.mkdirs();
	}
	
	//-1.Ã¦â€¢Â°Ã¦ï¿½Â®Ã¥Ë†ï¿½Ã¥Â§â€¹Ã¥Å’â€“
	private void dataInit() {
		//pkg;
		String allPkg = clazz.getPackage().getName();
		pkg = allPkg.substring(0,allPkg.length()-3);
		//rootDir;			src/cn/itcast/invoice/auth/emp
		rootDir = "src/"+pkg.replace(".", "/");
		//big;		Emp
		String allClazzName = clazz.getSimpleName();
		big = allClazzName.substring(0, allClazzName.length()-5);
		//little;
		little = big.substring(0, 1).toLowerCase();
		//small;
		small = little+big.substring(1);
	}
	
	private boolean check(Field fd) {
		boolean checked = false;
		if(fd.getModifiers() == Modifier.PRIVATE){
			if(!fd.getName().endsWith("View") && !fd.getName().equals("uuid")){
				//Ã¦Â»Â¡Ã¨Â¶Â³Long,Double,Integer,StringÃ§Å¡â€žÃ§â€�Å¸Ã¦Ë†ï¿½
				if(fd.getType().equals(Long.class)
						||fd.getType().equals(Double.class)
						||fd.getType().equals(Integer.class)
						||fd.getType().equals(String.class)
						)  { checked= true;
				}
			}
		}
		return checked;
	}
	
	
}
