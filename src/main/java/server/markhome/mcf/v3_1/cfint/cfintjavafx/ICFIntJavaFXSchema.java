// Description: Java 25 JavaFX Schema Interface for CFInt.

/*
 *	server.markhome.mcf.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
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

/**
 *	The ICFIntJavaFXSchema defines the factory interface for the
 *	for the various panes and components used to construct a GUI interface
 *	using the JavaFX framework.
 */
public interface ICFIntJavaFXSchema
	extends ICFSecJavaFXSchema
{
	ICFSecSchemaObj getSchema();
	void setSchema( ICFSecSchemaObj argSchema );

	String getClusterName();
	void setClusterName( String argClusterName );
	ICFSecClusterObj getClusterObj();

	String getTenantName();
	void setTenantName( String argTenantName );
	ICFSecTenantObj getTenantObj();

	String getSecUserName();
	void setSecUserName( String argSecUserName );
	ICFSecSecUserObj getSecUserObj();

	ICFSecSecSessionObj getSecSessionObj();

	public ICFSecJavaFXClusterFactory getClusterFactory();

	public ICFSecJavaFXISOCcyFactory getISOCcyFactory();

	public ICFSecJavaFXISOCtryFactory getISOCtryFactory();

	public ICFSecJavaFXISOCtryCcyFactory getISOCtryCcyFactory();

	public ICFSecJavaFXISOCtryLangFactory getISOCtryLangFactory();

	public ICFSecJavaFXISOLangFactory getISOLangFactory();

	public ICFSecJavaFXISOTZoneFactory getISOTZoneFactory();

	public ICFIntJavaFXLicenseFactory getLicenseFactory();

	public ICFIntJavaFXMajorVersionFactory getMajorVersionFactory();

	public ICFIntJavaFXMimeTypeFactory getMimeTypeFactory();

	public ICFIntJavaFXMinorVersionFactory getMinorVersionFactory();

	public ICFSecJavaFXSecClusGrpFactory getSecClusGrpFactory();

	public ICFSecJavaFXSecClusGrpIncFactory getSecClusGrpIncFactory();

	public ICFSecJavaFXSecClusGrpMembFactory getSecClusGrpMembFactory();

	public ICFSecJavaFXSecSessionFactory getSecSessionFactory();

	public ICFSecJavaFXSecSysGrpFactory getSecSysGrpFactory();

	public ICFSecJavaFXSecSysGrpIncFactory getSecSysGrpIncFactory();

	public ICFSecJavaFXSecSysGrpMembFactory getSecSysGrpMembFactory();

	public ICFSecJavaFXSecTentGrpFactory getSecTentGrpFactory();

	public ICFSecJavaFXSecTentGrpIncFactory getSecTentGrpIncFactory();

	public ICFSecJavaFXSecTentGrpMembFactory getSecTentGrpMembFactory();

	public ICFSecJavaFXSecUserFactory getSecUserFactory();

	public ICFSecJavaFXSecUserPWHistoryFactory getSecUserPWHistoryFactory();

	public ICFSecJavaFXSecUserPasswordFactory getSecUserPasswordFactory();

	public ICFIntJavaFXSubProjectFactory getSubProjectFactory();

	public ICFSecJavaFXSysClusterFactory getSysClusterFactory();

	public ICFSecJavaFXTenantFactory getTenantFactory();

	public ICFIntJavaFXTldFactory getTldFactory();

	public ICFIntJavaFXTopDomainFactory getTopDomainFactory();

	public ICFIntJavaFXTopProjectFactory getTopProjectFactory();

	public ICFIntJavaFXURLProtocolFactory getURLProtocolFactory();
}
