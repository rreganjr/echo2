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

package nextapp.echo2.webrender.clientupdate;

import org.w3c.dom.Element;

/**
 * A utility class to add <code>EchoDomPropertyStore</code> message parts to 
 * the <code>ServerMessage</code>.  <code>EchoDomPropertyStore</code> message
 * parts are used to store non-rendered information in the client DOM.
 */
public class DomPropertyStore {

    private static final String[] PROPERTY_STORE_KEYS= new String[]{"name", "value"};

    private static final String MESSAGE_PART_NAME = "EchoDomPropertyStore";

    /**
     * Creates a <code>storeproperty</code> operation to store a non-rendered
     * named property in an HTMLElement of the client DOM.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param elementId the id of the element on which to set the non-rendered
     *        property
     * @param propertyName the name of the property
     * @param propertyValue the value of the property
     */
    public static void createDomPropertyStore(ServerMessage serverMessage, String elementId, String propertyName, 
            String propertyValue) {
        Element itemizedUpdateElement = serverMessage.getItemizedDirective(ServerMessage.GROUP_ID_UPDATE, 
                MESSAGE_PART_NAME, "storeproperty", PROPERTY_STORE_KEYS, new String[]{propertyName, propertyValue});
        Element itemElement = serverMessage.getDocument().createElement("item");
        itemElement.setAttribute("eid", elementId);
        itemizedUpdateElement.appendChild(itemElement);
    }
}
