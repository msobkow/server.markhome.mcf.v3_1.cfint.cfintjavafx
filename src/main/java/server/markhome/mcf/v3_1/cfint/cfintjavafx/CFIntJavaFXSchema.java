// Description: Java 25 JavaFX Schema for CFInt.

/*
 *	server.markhome.mcf.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Mark's Code Fractal CFInt is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU Library General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFInt is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU Library General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFInt is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU Library General Public License
 *	along with Mark's Code Fractal CFInt.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes in order to
 *	tie it to proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfint.cfintjavafx;

import java.math.*;
import java.time.*;
import java.text.*;
import java.util.*;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecjavafx.*;

public class CFIntJavaFXSchema
implements ICFIntJavaFXSchema
{
	protected ICFIntSchemaObj schema = null;
	protected String clusterName = "system";
	protected ICFSecClusterObj clusterObj = null;
	protected String tenantName = "system";
	protected ICFSecTenantObj tenantObj = null;
	protected String secUserName = "system";
	protected ICFSecSecUserObj secUserObj = null;
	protected ICFSecSecSessionObj secSessionObj = null;
	protected ICFSecJavaFXClusterFactory factoryCluster = null;
	protected ICFSecJavaFXHostNodeFactory factoryHostNode = null;
	protected ICFSecJavaFXISOCcyFactory factoryISOCcy = null;
	protected ICFSecJavaFXISOCtryFactory factoryISOCtry = null;
	protected ICFSecJavaFXISOCtryCcyFactory factoryISOCtryCcy = null;
	protected ICFSecJavaFXISOCtryLangFactory factoryISOCtryLang = null;
	protected ICFSecJavaFXISOLangFactory factoryISOLang = null;
	protected ICFSecJavaFXISOTZoneFactory factoryISOTZone = null;
	protected ICFIntJavaFXLicenseFactory factoryLicense = null;
	protected ICFIntJavaFXMajorVersionFactory factoryMajorVersion = null;
	protected ICFIntJavaFXMimeTypeFactory factoryMimeType = null;
	protected ICFIntJavaFXMinorVersionFactory factoryMinorVersion = null;
	protected ICFSecJavaFXSecDeviceFactory factorySecDevice = null;
	protected ICFSecJavaFXSecGroupFactory factorySecGroup = null;
	protected ICFSecJavaFXSecGrpIncFactory factorySecGrpInc = null;
	protected ICFSecJavaFXSecGrpMembFactory factorySecGrpMemb = null;
	protected ICFSecJavaFXSecSessionFactory factorySecSession = null;
	protected ICFSecJavaFXSecUserFactory factorySecUser = null;
	protected ICFSecJavaFXServiceFactory factoryService = null;
	protected ICFSecJavaFXServiceTypeFactory factoryServiceType = null;
	protected ICFIntJavaFXSubProjectFactory factorySubProject = null;
	protected ICFSecJavaFXSysClusterFactory factorySysCluster = null;
	protected ICFSecJavaFXTSecGroupFactory factoryTSecGroup = null;
	protected ICFSecJavaFXTSecGrpIncFactory factoryTSecGrpInc = null;
	protected ICFSecJavaFXTSecGrpMembFactory factoryTSecGrpMemb = null;
	protected ICFSecJavaFXTenantFactory factoryTenant = null;
	protected ICFIntJavaFXTldFactory factoryTld = null;
	protected ICFIntJavaFXTopDomainFactory factoryTopDomain = null;
	protected ICFIntJavaFXTopProjectFactory factoryTopProject = null;
	protected ICFIntJavaFXURLProtocolFactory factoryURLProtocol = null;

	public CFIntJavaFXSchema() {
		CFIntJavaFX.init();
	}

	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	public void setSchema( ICFSecSchemaObj argSchema ) {
		final String S_ProcName = "setSchema";
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"argSchema" );
		}
		if( ! ( argSchema instanceof ICFIntSchemaObj ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"argSchema",
				argSchema,
				"ICFIntSchemaObj" );
		}
		schema = (ICFIntSchemaObj)argSchema;
	}

	public String getClusterName() {
		return( clusterName );
	}

	public void setClusterName( String argClusterName ) {
		final String S_ProcName = "setClusterName";
		if( ( argClusterName == null ) || ( argClusterName.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"argClusterName" );
		}
		clusterName = argClusterName;
		clusterObj = null;
	}

	public ICFSecClusterObj getClusterObj() {
		if( clusterObj == null ) {
			if( schema != null ) {
				clusterObj = schema.getClusterTableObj().readClusterByUDomNameIdx( clusterName );
			}
			if( clusterObj == null ) {
				throw new CFLibDataNotFoundException( getClass(),
					"getClusterObj",
					"UniqueDomainName",
					"UniqueDomainName",
					clusterName );
			}
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				"getClusterObj",
				String.format(Inz.x("cflib.common.CannotResolveWithoutCnx"), "Cluster"),
				String.format(Inz.s("cflib.common.CannotResolveWithoutCnx"), "Cluster") );
		}
		return( clusterObj );
	}

	public String getTenantName() {
		return( tenantName );
	}

	public void setTenantName( String argTenantName ) {
		final String S_ProcName = "setTenantName";
		if( ( argTenantName == null ) || ( argTenantName.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"argTenantName" );
		}
		tenantName = argTenantName;
		tenantObj = null;
	}

	public ICFSecTenantObj getTenantObj() {
		if( tenantObj == null ) {
			if( schema != null ) {
				tenantObj = schema.getTenantTableObj().readTenantByUNameIdx( getClusterObj().getRequiredId(), tenantName );
			}
			if( tenantObj == null ) {
				throw new CFLibDataNotFoundException( getClass(),
					"getTenantObj",
					"UniqueTenantName",
					"UniqueTenantName",
					new Object() {	protected CFLibDbKeyHash256 clusterId = getClusterObj().getRequiredId();
						protected String name = tenantName; } );
			}
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				"getTenantObj",
				String.format(Inz.x("cflib.common.CannotResolveWithoutCnx"), "Tenant"),
				String.format(Inz.s("cflib.common.CannotResolveWithoutCnx"), "Tenant") );
		}
		return( tenantObj );
	}

	public String getSecUserName() {
		return( secUserName );
	}

	public void setSecUserName( String argSecUserName ) {
		final String S_ProcName = "setSecUserName";
		if( ( argSecUserName == null ) || ( argSecUserName.length() <= 0 ) ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"argSecUserName" );
		}
		secUserName = argSecUserName;
		secUserObj = null;
	}

	public ICFSecSecUserObj getSecUserObj() {
		if( secUserObj == null ) {
			if( schema != null ) {
				secUserObj = schema.getSecUserTableObj().readSecUserByULoginIdx( secUserName );
			}
			if( secUserObj == null ) {
				throw new CFLibDataNotFoundException( getClass(),
					"getSecUserObj",
					"UniqueLogin",
					"UniqueLogin",
					secUserName );

			}
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				"getSecUserObj",
				String.format(Inz.x("cflib.common.CannotResolveWithoutCnx"), "SecUser"),
				String.format(Inz.s("cflib.common.CannotResolveWithoutCnx"), "SecUser") );
		}
		return( secUserObj );
	}

	public ICFSecSecSessionObj getSecSessionObj() {
		if( secSessionObj == null ) {
			if( schema != null ) {
				getClusterObj();
				getTenantObj();
				getSecUserObj();
				secSessionObj = schema.getSecSessionTableObj().newInstance();
				ICFSecSecSessionEditObj sessionEdit = secSessionObj.beginEdit();
				sessionEdit.setRequiredSecUserId( secUserObj.getPKey() );
				sessionEdit.setRequiredStart( LocalDateTime.now() );
				sessionEdit.setOptionalFinish( null );
				secSessionObj = sessionEdit.create();
				sessionEdit = null;
			}
		}
		else {
			throw new CFLibRuntimeException( getClass(),
				"getSecSessionObj",
				String.format(Inz.x("cflib.common.CannotResolveWithoutCnx"), "SecSession"),
				String.format(Inz.s("cflib.common.CannotResolveWithoutCnx"), "SecSession") );
		}
		return( secSessionObj );
	}

	public ICFSecJavaFXClusterFactory getClusterFactory() {
		if( factoryCluster == null ) {
			factoryCluster = new CFSecJavaFXClusterFactory( this );
		}
		return( factoryCluster );
	}

	public ICFSecJavaFXHostNodeFactory getHostNodeFactory() {
		if( factoryHostNode == null ) {
			factoryHostNode = new CFSecJavaFXHostNodeFactory( this );
		}
		return( factoryHostNode );
	}

	public ICFSecJavaFXISOCcyFactory getISOCcyFactory() {
		if( factoryISOCcy == null ) {
			factoryISOCcy = new CFSecJavaFXISOCcyFactory( this );
		}
		return( factoryISOCcy );
	}

	public ICFSecJavaFXISOCtryFactory getISOCtryFactory() {
		if( factoryISOCtry == null ) {
			factoryISOCtry = new CFSecJavaFXISOCtryFactory( this );
		}
		return( factoryISOCtry );
	}

	public ICFSecJavaFXISOCtryCcyFactory getISOCtryCcyFactory() {
		if( factoryISOCtryCcy == null ) {
			factoryISOCtryCcy = new CFSecJavaFXISOCtryCcyFactory( this );
		}
		return( factoryISOCtryCcy );
	}

	public ICFSecJavaFXISOCtryLangFactory getISOCtryLangFactory() {
		if( factoryISOCtryLang == null ) {
			factoryISOCtryLang = new CFSecJavaFXISOCtryLangFactory( this );
		}
		return( factoryISOCtryLang );
	}

	public ICFSecJavaFXISOLangFactory getISOLangFactory() {
		if( factoryISOLang == null ) {
			factoryISOLang = new CFSecJavaFXISOLangFactory( this );
		}
		return( factoryISOLang );
	}

	public ICFSecJavaFXISOTZoneFactory getISOTZoneFactory() {
		if( factoryISOTZone == null ) {
			factoryISOTZone = new CFSecJavaFXISOTZoneFactory( this );
		}
		return( factoryISOTZone );
	}

	public ICFIntJavaFXLicenseFactory getLicenseFactory() {
		if( factoryLicense == null ) {
			factoryLicense = new CFIntJavaFXLicenseFactory( this );
		}
		return( factoryLicense );
	}

	public ICFIntJavaFXMajorVersionFactory getMajorVersionFactory() {
		if( factoryMajorVersion == null ) {
			factoryMajorVersion = new CFIntJavaFXMajorVersionFactory( this );
		}
		return( factoryMajorVersion );
	}

	public ICFIntJavaFXMimeTypeFactory getMimeTypeFactory() {
		if( factoryMimeType == null ) {
			factoryMimeType = new CFIntJavaFXMimeTypeFactory( this );
		}
		return( factoryMimeType );
	}

	public ICFIntJavaFXMinorVersionFactory getMinorVersionFactory() {
		if( factoryMinorVersion == null ) {
			factoryMinorVersion = new CFIntJavaFXMinorVersionFactory( this );
		}
		return( factoryMinorVersion );
	}

	public ICFSecJavaFXSecDeviceFactory getSecDeviceFactory() {
		if( factorySecDevice == null ) {
			factorySecDevice = new CFSecJavaFXSecDeviceFactory( this );
		}
		return( factorySecDevice );
	}

	public ICFSecJavaFXSecGroupFactory getSecGroupFactory() {
		if( factorySecGroup == null ) {
			factorySecGroup = new CFSecJavaFXSecGroupFactory( this );
		}
		return( factorySecGroup );
	}

	public ICFSecJavaFXSecGrpIncFactory getSecGrpIncFactory() {
		if( factorySecGrpInc == null ) {
			factorySecGrpInc = new CFSecJavaFXSecGrpIncFactory( this );
		}
		return( factorySecGrpInc );
	}

	public ICFSecJavaFXSecGrpMembFactory getSecGrpMembFactory() {
		if( factorySecGrpMemb == null ) {
			factorySecGrpMemb = new CFSecJavaFXSecGrpMembFactory( this );
		}
		return( factorySecGrpMemb );
	}

	public ICFSecJavaFXSecSessionFactory getSecSessionFactory() {
		if( factorySecSession == null ) {
			factorySecSession = new CFSecJavaFXSecSessionFactory( this );
		}
		return( factorySecSession );
	}

	public ICFSecJavaFXSecUserFactory getSecUserFactory() {
		if( factorySecUser == null ) {
			factorySecUser = new CFSecJavaFXSecUserFactory( this );
		}
		return( factorySecUser );
	}

	public ICFSecJavaFXServiceFactory getServiceFactory() {
		if( factoryService == null ) {
			factoryService = new CFSecJavaFXServiceFactory( this );
		}
		return( factoryService );
	}

	public ICFSecJavaFXServiceTypeFactory getServiceTypeFactory() {
		if( factoryServiceType == null ) {
			factoryServiceType = new CFSecJavaFXServiceTypeFactory( this );
		}
		return( factoryServiceType );
	}

	public ICFIntJavaFXSubProjectFactory getSubProjectFactory() {
		if( factorySubProject == null ) {
			factorySubProject = new CFIntJavaFXSubProjectFactory( this );
		}
		return( factorySubProject );
	}

	public ICFSecJavaFXSysClusterFactory getSysClusterFactory() {
		if( factorySysCluster == null ) {
			factorySysCluster = new CFSecJavaFXSysClusterFactory( this );
		}
		return( factorySysCluster );
	}

	public ICFSecJavaFXTSecGroupFactory getTSecGroupFactory() {
		if( factoryTSecGroup == null ) {
			factoryTSecGroup = new CFSecJavaFXTSecGroupFactory( this );
		}
		return( factoryTSecGroup );
	}

	public ICFSecJavaFXTSecGrpIncFactory getTSecGrpIncFactory() {
		if( factoryTSecGrpInc == null ) {
			factoryTSecGrpInc = new CFSecJavaFXTSecGrpIncFactory( this );
		}
		return( factoryTSecGrpInc );
	}

	public ICFSecJavaFXTSecGrpMembFactory getTSecGrpMembFactory() {
		if( factoryTSecGrpMemb == null ) {
			factoryTSecGrpMemb = new CFSecJavaFXTSecGrpMembFactory( this );
		}
		return( factoryTSecGrpMemb );
	}

	public ICFSecJavaFXTenantFactory getTenantFactory() {
		if( factoryTenant == null ) {
			factoryTenant = new CFSecJavaFXTenantFactory( this );
		}
		return( factoryTenant );
	}

	public ICFIntJavaFXTldFactory getTldFactory() {
		if( factoryTld == null ) {
			factoryTld = new CFIntJavaFXTldFactory( this );
		}
		return( factoryTld );
	}

	public ICFIntJavaFXTopDomainFactory getTopDomainFactory() {
		if( factoryTopDomain == null ) {
			factoryTopDomain = new CFIntJavaFXTopDomainFactory( this );
		}
		return( factoryTopDomain );
	}

	public ICFIntJavaFXTopProjectFactory getTopProjectFactory() {
		if( factoryTopProject == null ) {
			factoryTopProject = new CFIntJavaFXTopProjectFactory( this );
		}
		return( factoryTopProject );
	}

	public ICFIntJavaFXURLProtocolFactory getURLProtocolFactory() {
		if( factoryURLProtocol == null ) {
			factoryURLProtocol = new CFIntJavaFXURLProtocolFactory( this );
		}
		return( factoryURLProtocol );
	}
}
