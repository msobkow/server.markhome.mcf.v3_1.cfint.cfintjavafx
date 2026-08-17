// Description: Java 25 JavaFX Picker of Obj Pane implementation for TopProject.

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
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
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
 *	CFIntJavaFXTopProjectPickerPane JavaFX Pick Obj Pane implementation
 *	for TopProject.
 */
public class CFIntJavaFXTopProjectPickerPane
extends CFBorderPane
implements ICFIntJavaFXTopProjectPaneList
{
	public static String S_FormName = "Choose TopProject";
	protected ICFIntJavaFXSchema javafxSchema = null;
	protected Collection<ICFIntTopProjectObj> javafxDataCollection = null;
	protected ObservableList<ICFIntTopProjectObj> observableListOfTopProject = null;
	protected TableColumn<ICFIntTopProjectObj, $implJavaOptAtomType$> tableColumnId = null;
	protected TableColumn<ICFIntTopProjectObj, $implJavaOptAtomType$> tableColumnName = null;
	protected TableColumn<ICFIntTopProjectObj, $implJavaOptAtomType$> tableColumnDescription = null;
	protected TableView<ICFIntTopProjectObj> dataTable = null;
	protected CFHBox hboxMenu = null;
	public final String S_ColumnNames[] = { "Name" };
	protected ICFFormManager cfFormManager = null;
	protected ICFIntJavaFXTopProjectChosen invokeWhenChosen = null;
	protected ICFIntTopDomainObj javafxContainer = null;
	protected CFButton buttonCancel = null;
	protected CFButton buttonChooseNone = null;
	protected CFButton buttonChooseSelected = null;
	protected ScrollPane scrollMenu = null;
	public CFIntJavaFXTopProjectPickerPane( ICFFormManager formManager,
		ICFIntJavaFXSchema argSchema,
		ICFIntTopProjectObj argFocus,
		ICFIntTopDomainObj argContainer,
		Collection<ICFIntTopProjectObj> argDataCollection,
		ICFIntJavaFXTopProjectChosen whenChosen )
	{
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
		if( whenChosen == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				6,
				"whenChosen" );
		}
		invokeWhenChosen = whenChosen;
		// argFocus is optional; focus may be set later during execution as
		// conditions of the runtime change.
		javafxSchema = argSchema;
		javaFXFocus = argFocus;
		javafxContainer = argContainer;
		setJavaFXDataCollection( argDataCollection );
		dataTable = new TableView<ICFIntTopProjectObj>();
		tableColumnId = new TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>( "Id" );
		tableColumnId.setCellValueFactory( new Callback<CellDataFeatures<ICFIntTopProjectObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFIntTopProjectObj, $implJavaOptAtomType$> p ) {
				ICFIntTopProjectObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredId();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnId.setCellFactory( new Callback<TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>,TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$> call(
				TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$> arg)
			{
				return new CFDbKeyHash256TableCell<ICFIntTopProjectObj>();
			}
		});
		dataTable.getColumns().add( tableColumnId );
		tableColumnName = new TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>( "Name" );
		tableColumnName.setCellValueFactory( new Callback<CellDataFeatures<ICFIntTopProjectObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFIntTopProjectObj, $implJavaOptAtomType$> p ) {
				ICFIntTopProjectObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredName();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnName.setCellFactory( new Callback<TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>,TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$> call(
				TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFIntTopProjectObj>();
			}
		});
		dataTable.getColumns().add( tableColumnName );
		tableColumnDescription = new TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>( "Description" );
		tableColumnDescription.setCellValueFactory( new Callback<CellDataFeatures<ICFIntTopProjectObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFIntTopProjectObj, $implJavaOptAtomType$> p ) {
				ICFIntTopProjectObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getOptionalDescription();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnDescription.setCellFactory( new Callback<TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$>,TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFIntTopProjectObj,$implJavaOptAtomType$> call(
				TableColumn<ICFIntTopProjectObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFIntTopProjectObj>();
			}
		});
		dataTable.getColumns().add( tableColumnDescription );
		dataTable.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<ICFIntTopProjectObj>() {
				@Override public void changed( ObservableValue<? extends ICFIntTopProjectObj> observable,
					ICFIntTopProjectObj oldValue,
					ICFIntTopProjectObj newValue )
				{
					setJavaFXFocus( newValue );
					if( buttonChooseSelected != null ) {
						if( newValue != null ) {
							buttonChooseSelected.setDisable( false );
						}
						else {
							buttonChooseSelected.setDisable( true );
						}
					}
				}
			});
		hboxMenu = new CFHBox( 10 );
		buttonCancel = new CFButton();
		buttonCancel.setMinWidth( 200 );
		buttonCancel.setText( "Cancel" );
		buttonCancel.setOnAction( new EventHandler<ActionEvent>() {
			@Override public void handle( ActionEvent e ) {
				final String S_ProcName = "handle";
				try {
					cfFormManager.closeCurrentForm();
				}
				catch( Throwable t ) {
					CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
				}
			}
		});
		hboxMenu.getChildren().add( buttonCancel );
		buttonChooseNone = new CFButton();
		buttonChooseNone.setMinWidth( 200 );
		buttonChooseNone.setText( "ChooseNone" );
		buttonChooseNone.setOnAction( new EventHandler<ActionEvent>() {
			@Override public void handle( ActionEvent e ) {
				final String S_ProcName = "handle";
				try {
					ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
					if( schemaObj == null ) {
						throw new CFLibNullArgumentException( getClass(),
							S_ProcName,
							0,
							"schemaObj" );
					}
					invokeWhenChosen.choseTopProject( null );
					cfFormManager.closeCurrentForm();
				}
				catch( Throwable t ) {
					CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
				}
			}
		});
		hboxMenu.getChildren().add( buttonChooseNone );
		buttonChooseSelected = new CFButton();
		buttonChooseSelected.setMinWidth( 200 );
		buttonChooseSelected.setText( "ChooseSelected" );
		buttonChooseSelected.setOnAction( new EventHandler<ActionEvent>() {
			@Override public void handle( ActionEvent e ) {
				final String S_ProcName = "handle";
				try {
					ICFIntSchemaObj schemaObj = (ICFIntSchemaObj)javafxSchema.getSchema();
					if( schemaObj == null ) {
						throw new CFLibNullArgumentException( getClass(),
							S_ProcName,
							0,
							"schemaObj" );
					}
					ICFIntTopProjectObj selectedInstance = getJavaFXFocusAsTopProject();
					invokeWhenChosen.choseTopProject( selectedInstance );
					cfFormManager.closeCurrentForm();
				}
				catch( Throwable t ) {
					CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
				}
			}
		});
		hboxMenu.getChildren().add( buttonChooseSelected );
		if( argFocus != null ) {
			dataTable.getSelectionModel().select( argFocus );
		}

		scrollMenu = new ScrollPane();
		scrollMenu.setVbarPolicy( ScrollBarPolicy.NEVER );
		scrollMenu.setHbarPolicy( ScrollBarPolicy.AS_NEEDED );
		scrollMenu.setFitToHeight( true );
		scrollMenu.setContent( hboxMenu );

		setTop( scrollMenu );
		setCenter( dataTable );
		adjustListButtons();
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
		if( ( value == null ) || ( value instanceof ICFIntTopProjectObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFIntTopProjectObj" );
		}
		if( dataTable == null ) {
			return;
		}
	}

	public ICFIntTopProjectObj getJavaFXFocusAsTopProject() {
		return( (ICFIntTopProjectObj)getJavaFXFocus() );
	}

	public void setJavaFXFocusAsTopProject( ICFIntTopProjectObj value ) {
		setJavaFXFocus( value );
	}

	public class TopProjectByQualNameComparator
	implements Comparator<ICFIntTopProjectObj>
	{
		public TopProjectByQualNameComparator() {
		}

		public int compare( ICFIntTopProjectObj lhs, ICFIntTopProjectObj rhs ) {
			if( lhs == null ) {
				if( rhs == null ) {
					return( 0 );
				}
				else {
					return( -1 );
				}
			}
			else if( rhs == null ) {
				return( 1 );
			}
			else {
				String lhsValue = lhs.getObjQualifiedName();
				String rhsValue = rhs.getObjQualifiedName();
				if( lhsValue == null ) {
					if( rhsValue == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhsValue == null ) {
					return( 1 );
				}
				else {
					return( lhsValue.compareTo( rhsValue ) );
				}
			}
		}
	}

	protected TopProjectByQualNameComparator compareTopProjectByQualName = new TopProjectByQualNameComparator();

	public Collection<ICFIntTopProjectObj> getJavaFXDataCollection() {
		return( javafxDataCollection );
	}

	public void setJavaFXDataCollection( Collection<ICFIntTopProjectObj> value ) {
		final String S_ProcName = "setJavaFXDataCollection";
		javafxDataCollection = value;
		observableListOfTopProject = FXCollections.observableArrayList();
		if( javafxDataCollection != null ) {
				Iterator<ICFIntTopProjectObj> iter = javafxDataCollection.iterator();
				while( iter.hasNext() ) {
					observableListOfTopProject.add( iter.next() );
				}
				observableListOfTopProject.sort( compareTopProjectByQualName );
		}
		if( dataTable != null ) {
			dataTable.setItems( observableListOfTopProject );
			// Hack from stackoverflow to fix JavaFX TableView refresh issue
			((TableColumn)dataTable.getColumns().get(0)).setVisible( false );
			((TableColumn)dataTable.getColumns().get(0)).setVisible( true );
		}
	}

	public ICFIntTopDomainObj getJavaFXContainer() {
		return( javafxContainer );
	}

	public void setJavaFXContainer( ICFIntTopDomainObj value ) {
		javafxContainer = value;
	}

	public void adjustListButtons() {
		boolean enableState;
		ICFIntTopProjectObj selectedObj = getJavaFXFocusAsTopProject();
		if( selectedObj == null ) {
			enableState = false;
		}
		else {
			enableState = true;
		}

		if( buttonChooseSelected != null ) {
			buttonChooseSelected.setDisable( ! enableState );
		}
		if( buttonChooseNone != null ) {
			buttonChooseNone.setDisable( false );
		}
		if( buttonCancel != null ) {
			buttonCancel.setDisable( false );
		}

	}
}

