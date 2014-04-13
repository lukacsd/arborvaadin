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

package org.vaadin.arborgraph;

import org.vaadin.arborgraph.client.ui.VArborGraph;
import org.vaadin.arborgraph.util.BranchToJson;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

@ClientWidget( VArborGraph.class )
public class ArborGraph extends AbstractComponent {
	private static final long serialVersionUID = -814880102222515544L;

	private String graphModel;
	private BranchToJson branchToJson = new BranchToJson( );

	@Override
	public void paintContent( PaintTarget target ) throws PaintException {
		super.paintContent( target );

		if ( graphModel != null ) {
			target.addVariable( this, VArborGraph.UDIL_GRAPH_MODEL, graphModel );

			graphModel = null;
		}
	}

	public void showGraph( String jsonModel ) {
		this.graphModel = jsonModel;

		requestRepaint( );
	}

	public void showGraph( Branch branch ) {
		this.graphModel = branchToJson.getJson( branch );

		requestRepaint( );
	}

}