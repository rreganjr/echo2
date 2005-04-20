/* 
 * This file is part of the Echo Web Application Framework (hereinafter "Echo").
 * Copyright (C) 2002-2005 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.app.util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class for retrieving property files matching a specific name
 * across the CLASSPATH.
 */
public class PropertiesDiscovery {

    /**
     * Returns a map containing the contents of all property files with  
     * the specified resource name.
     * 
     * @param resourceName the name of the properties file(s) to load
     * @return a map containing properties of all propertyies files 
     *         in the CLASSPATH matching the specified 
     *         <code>resourceName</code>
     */
    public static Map loadProperties(String resourceName, ClassLoader classLoader) 
    throws IOException {
        Map propertyMap = new HashMap();
        Enumeration resources = classLoader.getResources(resourceName);
        while (resources.hasMoreElements()) {
            URL resourceUrl = (URL) resources.nextElement();
            Properties peerProperties = new Properties();
            peerProperties.load(resourceUrl.openStream());
            propertyMap.putAll(peerProperties);
        }
        return propertyMap;
    }
    
    /** Non-instantiable class. */
    private PropertiesDiscovery() {  }
}