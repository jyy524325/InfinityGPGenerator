package org.javaforever.infinity.s2sh.verb;

import java.util.ArrayList;
import java.util.List;

import org.javaforever.infinity.core.Verb;
import org.javaforever.infinity.core.Writeable;
import org.javaforever.infinity.domain.Domain;
import org.javaforever.infinity.domain.Method;
import org.javaforever.infinity.domain.Signature;
import org.javaforever.infinity.domain.Statement;
import org.javaforever.infinity.domain.Type;
import org.javaforever.infinity.domain.Var;
import org.javaforever.infinity.generator.NamedStatementGenerator;
import org.javaforever.infinity.s2sh.core.NamedS2SHStatementGenerator;
import org.javaforever.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import org.javaforever.infinity.utils.InterVarUtil;
import org.javaforever.infinity.utils.StringUtil;
import org.javaforever.infinity.utils.WriteableUtil;

public class FindById extends Verb{

	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
			method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
			method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
			method.setThrowException(true);
			method.addMetaData("Override");
			
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(NamedS2SHStatementGenerator.getFindByIdUsingHqlStatement(1000L,2,domain,InterVarUtil.DB.query));
			if (this.domain.getDomainId().getRawType().isLong()){
				list.add(new Statement(2000L,2,this.domain.getCapFirstDomainName() + " " + this.domain.getLowerFirstDomainName() + " = ("+this.domain.getCapFirstDomainName()+") this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+InterVarUtil.DB.query.getVarName()+").setLong(\""+this.domain.getDomainId().getLowerFirstFieldName()+"\", "+this.domain.getDomainId().getLowerFirstFieldName()+").list().get(0);"));
			}else if (this.domain.getDomainId().getRawType().isInt()){
				list.add(new Statement(2000L,2,this.domain.getCapFirstDomainName() + " " + this.domain.getLowerFirstDomainName() + " = ("+this.domain.getCapFirstDomainName()+") this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("+InterVarUtil.DB.query.getVarName()+").setInteger(\""+this.domain.getDomainId().getLowerFirstFieldName()+"\", "+this.domain.getDomainId().getLowerFirstFieldName()+").list().get(0);"));
			}
			list.add(new Statement(2000L,2,"return "+this.domain.getLowerFirstDomainName()+";"));
			method.setMethodStatementList(WriteableUtil.merge(list));
			return method;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String generateDaoImplMethodString(){
		return generateDaoImplMethod().generateMethodString();
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		method.setThrowException(true);
		return method;
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodDefinition();
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() {
		Method m = this.generateDaoImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		method.setThrowException(true);
		
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(new Statement(1000,2, this.domain.getType().getTypeName() + " "+ this.domain.getLowerFirstDomainName() + "2 = " +serviceMethod.generateStandardServiceImplUsingDomainPrefixCallString(InterVarUtil.DB.service.getVarName(),this.domain)+";"));
		wlist.add(new Statement(2000,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+StringUtil.lowerFirst(this.domain.getStandardName())+"\","+this.domain.getLowerFirstDomainName()+"2);"));		
		wlist.add(NamedS2SHStatementGenerator.getActionForward(3000L,2, serviceMethod.getStandardName()));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateControllerMethodString() {
		return generateControllerMethod().generateMethodString();
	}

	@Override
	public Method generateServiceImplMethod() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		method.setReturnType(new Type(this.domain.getStandardName(),this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.addSignature(new Signature(1,this.domain.getDomainId().getFieldName(),this.domain.getDomainId().getRawType()));
		method.setThrowException(true);
		method.addMetaData("Override");
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(new Statement(1000L,2, "return " + daomethod.generateStandardServiceImplCallString(InterVarUtil.DB.dao.getVarName())+";"));
		method.setMethodStatementList(WriteableUtil.merge(list));

		return method;
	}

	@Override
	public String generateServiceImplMethodString() {
		return generateServiceImplMethod().generateMethodString();
	}

	@Override
	public String generateServiceImplMethodStringWithSerial() {
		Method m = this.generateServiceImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
	
	public FindById(){
		super();
		if (this.domain != null) this.setVerbName("Find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		else this.setVerbName("FindById");
	}
	
	public FindById(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("Find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
	}

	@Override
	public String generateControllerMethodStringWithSerial() {
		Method m = this.generateControllerMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateFacadeMethod() {
		Method method = new Method();
		method.setStandardName("find"+this.domain.getStandardName()+"By"+this.domain.getDomainId().getCapFirstFieldName());
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Method serviceMethod = this.generateServiceMethodDefinition();
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		Var domainVar = new Var(this.domain.getLowerFirstDomainName()+"2", new Type(this.domain.getCapFirstDomainName(),this.domain.getPackageToken()+".domain."+this.domain.getCapFirstDomainName()));
		wlist.add(NamedS2SHStatementGenerator.getJsonResultMap(1000L, 2, resultMap));
		wlist.add(new Statement(2000,2, this.domain.getType().getTypeName() + " "+ this.domain.getLowerFirstDomainName() + "2 = " +serviceMethod.generateStandardServiceImplUsingDomainPrefixCallString(InterVarUtil.DB.service.getVarName(),this.domain)+";"));
		wlist.add(NamedS2SHStatementListGenerator.getPutJsonResultMapWithSuccessAndDomainVar(3000L, 2, resultMap,domainVar));
		wlist.add(NamedS2SHStatementGenerator.getEncodeMapToJsonResultMap(4000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getJumpToActionSuccess(5000L, 2));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateFacadeMethodString() {
		Method m = this.generateFacadeMethod();
		return m.generateMethodString();
	}

	@Override
	public String generateFacadeMethodStringWithSerial() {
		Method m = this.generateFacadeMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
}
