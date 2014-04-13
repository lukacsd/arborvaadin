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

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.terminal.gwt.server.ApplicationServlet;

public class ArborApplicationServlet extends ApplicationServlet {
	private static final long serialVersionUID = -2073025750454242422L;

	private static final String JQUERY = "jQuery/jquery-1.11.0.min.js";
	private static final String ARBOR_CORE = "arbor/arbor.js";
	private static final String ARBOR_GRAPHICS = "arbor/graphics.js";
	private static final String ARBOR_RENDERER = "arbor/demo_renderer.js";

	@Override
	protected void writeAjaxPageHtmlHeader( BufferedWriter page, String title, String themeUri, HttpServletRequest request )
			throws IOException {
		super.writeAjaxPageHtmlHeader( page, title, themeUri, request );

		page.write( String.format( "<script type=\"text/javascript\" src=\"%s/VAADIN/%s\"></script>",
				getServletContext( ).getContextPath( ), JQUERY ) );
		page.write( String.format( "<script type=\"text/javascript\" src=\"%s/VAADIN/%s\"></script>",
				getServletContext( ).getContextPath( ), ARBOR_CORE ) );
		page.write( String.format( "<script type=\"text/javascript\" src=\"%s/VAADIN/%s\"></script>",
				getServletContext( ).getContextPath( ), ARBOR_GRAPHICS ) );
		page.write( String.format( "<script type=\"text/javascript\" src=\"%s/VAADIN/%s\"></script>",
				getServletContext( ).getContextPath( ), ARBOR_RENDERER ) );
	}

}
