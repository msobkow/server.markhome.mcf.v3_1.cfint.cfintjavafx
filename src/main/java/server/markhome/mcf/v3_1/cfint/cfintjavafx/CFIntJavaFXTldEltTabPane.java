// Description: Java 25 JavaFX Element TabPane implementation for Tld.

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
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;

/**
 *	CFIntJavaFXTldEltTabPane JavaFX Element TabPane implementation
 *	for Tld.
 */
public class CFIntJavaFXTldEltTabPane
extends CFTabPane
implements ICFIntJavaFXTldPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFIntJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	public final String LABEL_TabComponentsTopDomainList = "Optional Components Top Domain";
	protected CFTab tabComponentsTopDomain = null;
	protected CFBorderPane tabViewComponentsTopDomainListPane = null;

	public CFIntJavaFXTldEltTabPane( ICFFormManager formManager, ICFIntJavaFXSchema argSchema, ICFIntTldObj argFocus ) {
		super();
		final String S_ProcName = "construct-schema-focus";
		if( formManager == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"formManager" );
		}
		cfFormManager = formManager;
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				2,
				"argSchema" );
		}
		// argFocus is optional; focus may be set later during execution as
		// conditions of the runtime change.
		javafxSchema = argSchema;
		setJavaFXFocusAsTld( argFocus );
		// Wire the newly constructed Panes/Tabs to this TabPane
		tabComponentsTopDomain = new CFTab();
		tabComponentsTopDomain.setText( LABEL_TabComponentsTopDomainList );
		tabComponentsTopDomain.setContent( getTabViewComponentsTopDomainListPane() );
		getTabs().add( tabComponentsTopDomain );
		javafxIsInitializing = false;
	}

	public ICFFormManager getCFFormManager() {
		return( cfFormManager );
	}

	public void setCFFormManager( ICFFormManager value ) {
		final String S_ProcName = "setCFFormManager";
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"value" );
		}
		cfFormManager = value;
	}

	public ICFIntJavaFXSchema getJavaFXSchema() {
		return( javafxSchema );
	}

	public void setJavaFXFocus( ICFLibAnyObj value ) {
		final String S_ProcName = "setJavaFXFocus";
		if( ( value == null ) || ( value instanceof ICFIntTldObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFIntTldObj" );
		}
	}

	public void setJavaFXFocusAsTld( ICFIntTldObj value ) {
		setJavaFXFocus( value );
	}

	public ICFIntTldObj getJavaFXFocusAsTld() {
		return( (ICFIntTldObj)getJavaFXFocus() );
	}

	protected class RefreshComponentsTopDomainList
	implements ICFRefreshCallback
	{
		public RefreshComponentsTopDomainList() {
		}

		public void refreshMe() {
			Collection<ICFIntTopDomainObj> dataCollection;
			ICFIntTldObj focus = (ICFIntTldObj)getJavaFXFocusAsTld();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsTopDomain( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsTopDomainListPane();
			ICFIntJavaFXTopDomainPaneList jpList = (ICFIntJavaFXTopDomainPaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsTopDomainListPane() {
		if( tabViewComponentsTopDomainListPane == null ) {
			ICFIntTldObj focus = (ICFIntTldObj)getJavaFXFocusAsTld();
			Collection<ICFIntTopDomainObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsTopDomain( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFIntTldObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFIntTldObj ) ) {
				javafxContainer = (ICFIntTldObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsTopDomainListPane = javafxSchema.getTopDomainFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsTopDomainList(), false );
		}
		return( tabViewComponentsTopDomainListPane );
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		CFPane.PaneMode oldMode = getPaneMode();
		super.setPaneMode( value );
		if( tabViewComponentsTopDomainListPane != null ) {
			((ICFIntJavaFXTopDomainPaneCommon)tabViewComponentsTopDomainListPane).setPaneMode( value );
		}
	}
}
