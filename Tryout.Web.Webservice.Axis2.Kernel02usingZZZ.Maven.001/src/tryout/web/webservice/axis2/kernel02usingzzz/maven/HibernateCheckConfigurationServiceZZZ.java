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

import basic.zBasic.ExceptionZZZ;
import basic.zKernel.KernelZZZ;
import tryout.zBasic.persistence.webservice.TryoutSessionFactoryCreation;
import use.thm.dummy.WebDeploymentTest;
import use.thm.persistence.dao.TileDefaulttextDao;
import use.thm.persistence.dao.TroopArmyDao;
import use.thm.persistence.hibernate.HibernateContextProviderSingletonTHM;
import use.thm.persistence.model.Key;
import use.thm.persistence.model.TileDefaulttext;
import use.thm.persistence.model.TroopArmy;

public class HibernateCheckConfigurationServiceZZZ {
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
	
	/**Merke: Das ist der flexiblere Einsatz der MEthode.
	 *        In den WebServices kann durchaus eine Methode existieren, in der der Überagebestring fest ist,
	 *        da von einer festen Datasource (deshalb ...used...) ausgegangen wird.
	 *        Z.B.  TileService.getProofJndiResourceUsedAvailable();
	 * @param sJndiContext
	 * @return
	 */
	public String getProofJndiResourceAvailable(String sJndiContext){
		String sReturn=null;
		
		//Missbrauch dieser Methode:
		//Tryout eine SessionFactory per JNDI zu erzeugen
		TryoutSessionFactoryCreation objTryout = new TryoutSessionFactoryCreation();
		//Das funktioniert. boolean bReturn = objTryout.tryoutGetSessionFactoryByJndi();
		
		//DEBUG: 20171206 NEUE ALTERNATIVE ÜBER CONTEXTPROVIDERJNDI
		boolean bReturn = objTryout.tryoutGetSessionFactoryByJndiContextProvider(sJndiContext);
		if(bReturn){
			sReturn = "vorhanden";
		}else{
			sReturn = "nicht vorhanden";
		}
		return sReturn;
	}
}
