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

package org.vaadin.arborgraph.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.arborgraph.Node;

public class DemoNode implements Node {
	private String name;
	private Map<String, String> attributes = new HashMap<String, String>( );

	public DemoNode( String name ) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public void addAttribute( String name, String value ) {
		attributes.put( name, value );
	}

	@Override
	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap( attributes );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( attributes == null ) ? 0 : attributes.hashCode( ) );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode( ) );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		DemoNode other = ( DemoNode ) obj;
		if ( attributes == null ) {
			if ( other.attributes != null )
				return false;
		} else if ( !attributes.equals( other.attributes ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		return true;
	}

}
