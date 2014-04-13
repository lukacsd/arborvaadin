/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014, David Lukacs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.vaadin.arborgraph.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.Util;

public class VArborGraph extends Widget implements Paintable {
	public static final String CLASSNAME = "v-arbor-graph";
	public static final String CANVAS_ID = "viewport";
	public static final String PARTICLE_SYSTEM_PARAMETERS = "{stiffness:900, repulsion:2000, gravity:true, dt:0.015}";
	public static final String UDIL_GRAPH_MODEL = "graphModel";

	private CanvasElement canvas;

	public VArborGraph( ) {
		canvas = Document.get( ).createCanvasElement( );
		canvas.setId( CANVAS_ID );

		setElement( canvas );

		setStyleName( CLASSNAME );
	}

	@Override
	public void setHeight( String height ) {
		Util.setHeightExcludingPaddingAndBorder( this, height, 0 );

		canvas.setHeight( getOffsetHeight( ) );
	}

	@Override
	public void setWidth( String width ) {
		Util.setWidthExcludingPaddingAndBorder( this, width, 0 );

		canvas.setWidth( getOffsetWidth( ) );
	}

	@Override
	public void updateFromUIDL( UIDL uidl, ApplicationConnection client ) {
		if ( !client.updateComponent( this, uidl, true ) ) {
			if ( uidl.hasVariable( UDIL_GRAPH_MODEL ) ) {
				String model = uidl.getStringVariable( UDIL_GRAPH_MODEL );

				if ( model != null && !model.isEmpty( ) ) {
					showGraph( model );
				}
			}
		}
	}

	private void showGraph( String model ) {
		boolean initialized = initParticleSystem( CANVAS_ID, getJSon( PARTICLE_SYSTEM_PARAMETERS ) );

		if ( initialized ) {
			graft( getJSon( model ) );
		} else {
			merge( getJSon( model ) );
		}
	}

	private native JavaScriptObject getJSon( String json )
	/*-{
		return eval('(' + json + ')');
	}-*/;

	private native boolean initParticleSystem( String canvasId, JavaScriptObject parameters )
	/*-{
		if ($wnd.sys === undefined) {
			var sys = $wnd.arbor.ParticleSystem();
			sys.parameters(parameters);
			sys.renderer = $wnd.renderer(canvasId);
			$wnd.sys = sys;
			return true;
		}
		return false;
	}-*/;

	private native void graft( JavaScriptObject model )
	/*-{
		$wnd.sys.graft(model);
	}-*/;

	private native void merge( JavaScriptObject model )
	/*-{
		$wnd.sys.merge(model);
	}-*/;

}
