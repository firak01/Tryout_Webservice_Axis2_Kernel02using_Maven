package tryout.web.webservice.axis2.kernel02usingzzz.maven;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import basic.persistence.util.HibernateUtil;
import basic.zBasic.ExceptionZZZ;
import basic.zBasic.KernelSingletonTHM;
import basic.zBasic.persistence.interfaces.IHibernateContextProviderZZZ;
import basic.zKernel.KernelSingletonZZZ;
import basic.zKernel.KernelZZZ;
import use.thm.dummy.WebDeploymentTest;
import use.thm.persistence.dao.TileDefaulttextDao;
import use.thm.persistence.dao.TroopArmyDao;
import use.thm.persistence.hibernate.HibernateContextProviderSingletonTHM;
import use.thm.persistence.model.Key;
import use.thm.persistence.model.TileDefaulttext;
import use.thm.persistence.model.TroopArmy;
import use.thm.persistence.util.HibernateUtilTHM;

public class HibernateContextProviderServiceZZZ {
	public String sayHello(String name){
		return "Hello " + name;
	}
	public String getNow(){
		Calendar cal = Calendar.getInstance();
		//Date date = new Date();
		Date date = cal.getTime();
		String sReturn = new Integer(date.getYear()).toString() + new Integer(date.getMonth()).toString() + new Integer(date.getDay()).toString();
		return sReturn;
	}
	
	/** Das ist ein Test auf Werte zuzugreifen, die KernelZZZ liefert. 
	 * TODO GOON 20171123: Das Dto muss noch angeboten werden. Zuerst mal mit einfachem String. */
	public String getKernelDto(){
		String sReturn = null;
		main:{
			try {
				KernelZZZ objKernel = new KernelZZZ();
				sReturn = objKernel.getKernelKey();
			} catch (ExceptionZZZ e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end main:
		return sReturn;			
	}
	
	public String getHibernateDto(){
		String sReturn = null;
		main:{
			try{
				
				//Mal mit einer einfachen Klasse beginnen, die nichts weiter beinhaltet, vor allem keine anderen Importe.
				WebDeploymentTest objDeployed = new WebDeploymentTest();
				objDeployed.doIt();
				sReturn = " WebDeploymentTest Klasse wohl erfolgreich deployed.";
				System.out.println(sReturn);
				
				KernelSingletonZZZ objKernelSingleton = KernelSingletonZZZ.getInstance();	
				IHibernateContextProviderZZZ objHibernateContext = HibernateUtilTHM.getHibernateContextProviderUsed(objKernelSingleton);
				
				sReturn = sReturn + " HibernateContextProviderSingletonTHM Klasse wohl erfolgreich deployed.";
				System.out.println(sReturn);
				
				Configuration objConfig = objHibernateContext.getConfiguration();
				objConfig.setProperty("hibernate.hbm2ddl.auto", "update");  //! Jetzt erst wird jede Tabelle über den Anwendungsstart hinaus gespeichert UND auch wiedergeholt.				
				sReturn = sReturn + " Configuration wohl erfolgreich deployed";
				System.out.println(sReturn);
				
//				//Hier die Session Factory NICHT über JNDI holen.
				Session session = objHibernateContext.getSessionOpen();
				session.beginTransaction();
				session.clear();//Die Reihenfolge ist wichtig: Erst clear(), dann close()
				session.close();
				sReturn = " Session erfolgreich geöffnet und wieder geschlossen";
			} catch (ExceptionZZZ e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sReturn = sReturn + " Leider Fehler am Ende.";
			}
		}//end main:
		return sReturn;
	}
}
